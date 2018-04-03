import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import core.compilor.CalculatorCompiler;
import visualization.*;
import core.expression.*;
import extension.compiler.FixedPlaceCompiler;
import extension.decorator.ExpressionDecorator;

public class Main {

    public static void main(String[] args) {
        System.out.println("========== Welcome to the Calculator! ==========");
        System.out.println("Type: '/?' for help ; 'exit' for exit");

        boolean running = true;
        while(running) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            // Trim
            input = input.replaceAll(" ", "");
            // Help Command
            if (input.length() == 0) {
                input = "/?";
            }
            if (input.matches("\\/\\?")) {
                System.out.println("Type: '/?' for help ; 'exit' for exit");
            }
            // Exit Command
            else if (input.matches("exit")) {
                running = false;
            }
            // Calculate
            else {
                try {
                    CalculatorCompiler compiler = null;
                    String fixed = "";
                    do {
                        System.out.println("Will you use A Fixed Decimal Place Constant Compiler ? (Y: Yes, N: No)");
                        fixed = scanner.nextLine();
                        System.out.println(fixed);
                    } while (! (fixed.matches("Y") || fixed.matches("N")));
                    if (fixed.matches("Y")) {
                        System.out.println("What is the Constant Fixed Decimal Place ?");
                        int placement = scanner.nextInt();
                        compiler = new FixedPlaceCompiler(input, placement);
                    }
                    else {
                        compiler = new CalculatorCompiler(input);
                    }
                    // Compiling and Execute
                    String result = compiler.execute();
                    System.out.println("= " + result);

                    // Visualise Expressions
                    TreePrinter.print(visualize(compiler.getFinalExpr()));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        System.out.println("Bye!");
    }


    public static Node visualize(Expression finalExpr) {
        boolean finished = false;
        List<Node> currentNodes = new ArrayList<Node> ();
        Node finalNode = new Node(finalExpr);
        currentNodes.add(finalNode);
        System.out.println();
        System.out.println("========== Syntax Tree Visualisation ==========");
        do {
            List<Node> newNodes = new ArrayList<Node> ();
            // Build Current Level's Tree Diagram
            for (Node node: currentNodes) {
                Expression expr = node.getExpr();
                if (expr.getName().matches("(.*)Extended(.*)")) {
                    expr = (ExpressionDecorator) expr;
                }
                if (expr.getName().matches("(.*)BinaryExpr(.*)")) {
                    BinaryExpr b = null;
                    if (expr.getName().matches("(.*)Extended(.*)")) {
                        ExpressionDecorator exprDecorator = (ExpressionDecorator) node.getExpr();
                        b = (BinaryExpr) exprDecorator.getExpression();
                    }
                    else {
                        b = (BinaryExpr) node.getExpr();
                    }
                    Node newLeftNode = new Node(b.getLeft());
                    newNodes.add(newLeftNode);
                    node.left = newLeftNode;
                    Node newRightNode = new Node(b.getRight());
                    newNodes.add(newRightNode);
                    node.right = newRightNode;
                }
                else if (expr.getName().matches("(.*)Parentheses(.*)")) {
                    ParenthesesExpr p = (ParenthesesExpr) node.getExpr();
                    Node newNode = new Node(p.getExpr());
                    newNodes.add(newNode);
                    node.left = newNode;
                }
            }

            // Check for Finish
            finished = true;
            for(Node node: newNodes) {
                if (!node.getExpr().getName().matches("(.*)Constant(.*)")) {
                    finished = false;
                }
            }

            // Retrieve new Level of Tree
            currentNodes.clear();
            currentNodes = newNodes;

        } while (!finished);
        return finalNode;
    }
}
