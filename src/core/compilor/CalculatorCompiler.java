package core.compilor;
import java.util.List;
import java.util.ArrayList;

import core.expression.*;
import exception.CompileException;


/**
 * Factory Pattern
 */
public class CalculatorCompiler {
    public Expression getFinalExpr() {
        return finalExpr;
    }

    protected String input;
    protected List<String> tokens;
    protected Expression finalExpr;

    public CalculatorCompiler(String input) {
        this.input = input;
        System.out.println("Input: " + input);
    }

    public String execute() throws CompileException {
        try {
            lexing();
            List<CompilerElement> ast = parse(); // AST = Abstract Syntax Tree
            finalExpr = compile(ast);
            return Float.toString(finalExpr.operate());
        } catch (Exception e) {
            throw new CompileException(e.toString());
        }
    }

    /**
     * Tokenizer
     * Seperate Numbers and Operators
     * @return
     * @throws CompileException
     */
    public void lexing() throws CompileException {
        List<String> output = new ArrayList<String>();
        String number = "";
        String operator = "";
        // Split Expression as Numbers and Operators
        for (char element: input.toCharArray()) {
            if (Character.toString(element).matches("^[0-9]|\\.")) {
                number += element;
                if (operator.length() > 0) {
                    output.add(operator);
                    operator = "";
                }
            }
            else {
                if (number.length() >0) {
                    // Check Valid Number
                    int count = number.length() - number.replaceAll("\\.", "").length();
                    if (count > 1) {
                        throw new CompileException(input);
                    }
                    output.add(number);
                    number = "";
                }
                // Special Characters need to seperates
                if (Character.toString(element).matches("^[\\+\\-\\*\\/\\)]")) {
                    output.add(Character.toString(element));
                    operator = "";
                }
                else {
                    if (Character.toString(element).matches("\\(")) {
                        operator += element;
                        output.add(operator);
                        operator = "";
                    }
                    else {
                        // Used for sin( / cos( / etc
                        operator += element;
                    }
                }
            }
        }

        if (operator.length() > 0) {
            output.add(operator);
        }
        else if (number.length() > 0) {
            // Check Valid Number
            int count = number.length() - number.replaceAll("\\.", "").length();
            if (count > 1) {
                throw new CompileException(input);
            }
            output.add(number);
        }
        tokens = output;
        System.out.println(tokens);
    }

    /**
     * Parse Number and Operators into Expressions
     * You may override this method to Parse different Expression
     * @throws CompileException
     */
    public List<CompilerElement> parse() throws CompileException {
        List<CompilerElement> list = new ArrayList<CompilerElement>();
        Expression output;

        while(tokens.size() > 0) {
            String token = tokens.get(0);
            // Is Number
            if(token.matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
                try{
                    float num = Float.valueOf(token);
                    Constant constant = new Constant(num);
                    list.add(new CompilerElement(constant, true));
                }
                catch (Exception e) {
                    throw new CompileException(token);
                }
            }
            // Is Operator
            // Left Parenthesis
            else if (token.matches("\\(")) {
                tokens.remove(0);
                Expression innerExpr = compile(parse());
                ParenthesesExpr parent = new ParenthesesExpr(innerExpr);
                list.add(new CompilerElement(parent, true));
            }
            // Right Parenthesis
            else if (token.matches("\\)")) {
                // Finish All Expressions and Operators in Parenthesis
                return list;
            }
            // Current Supported Operator
            else if (token.matches("^[\\+\\-\\*\\/]")) {
                list.add(new CompilerElement(token, false));
            }
            // Exception
            else {
                throw new CompileException(token);
            }
            // Digest Token
            tokens.remove(0);
        }

        return list;
    }

    /**
     * Compile Expressions
     * You may override this method to Compile different Expression
     * @throws CompileException
     */
    public Expression compile(List<CompilerElement> list) throws CompileException {
        List<Expression> expressions = new ArrayList<Expression>();
        // Expression last = null;
        // Finish All Expressions and operators
        boolean primaryFinished = false;
        while(list.size() > 1) {
            boolean isExpression = false;

            for(int i = 0 ; i < list.size() ; i++) {
                Object element = list.get(i).getObject();

                // Operator
                if (!list.get(i).isExpression) {
                    if (!(((i - 1) >= 0) && ((i + 1) < list.size()))) { throw new CompileException("Headless Expression"); }
                    else if (!list.get(i-1).isExpression || !list.get(i+1).isExpression) throw new CompileException("Successive Operators");
                    if (element.toString().matches("^[\\*\\/]")) {
                        BinaryExpr expression = new BinaryExpr((Expression) list.get(i-1).getObject(), (Expression) list.get(i+1).getObject(), (String) element);
                        list.subList(i-1, i+2).clear();
                        list.add(i-1, new CompilerElement(expression, true));
                    }
                    // Not Primary Operation
                    // Do this until no more Primary Operation
                    else {
                        if(!hasPrimary(list)) {
                            BinaryExpr expression = new BinaryExpr((Expression) list.get(i-1).getObject(), (Expression) list.get(i+1).getObject(), (String) element);
                            list.subList(i-1, i+2).clear();
                            list.add(i-1, new CompilerElement(expression, true));
                        }
                    }
                }
            }
        }
        return (Expression) list.get(0).getObject();
    }

    public boolean hasPrimary (List<CompilerElement> objs) {
        for(CompilerElement obj: objs) {
            if (!obj.isExpression) {
                if (obj.getObject().toString().matches("^[\\*\\/]")) {
                    return true;
                }
            }
        }
        return false;
    }

    protected class CompilerElement {
        private Object obj;
        public boolean isExpression;

        public CompilerElement(Object obj, boolean isExpression) {
            this.obj = obj;
            this.isExpression = isExpression;
        }

        public Object getObject() { return obj; }
    }
}
