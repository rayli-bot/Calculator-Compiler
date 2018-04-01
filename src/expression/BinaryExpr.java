package expression;
import type.OrderType;
import exception.OperateException;

public class BinaryExpr implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    String operator;

    public BinaryExpr(Expression left, Expression right, String operator) {
        this.leftOperand = left;
        this.rightOperand = right;
        this.operator = operator;
    }

    public OrderType exprOrder() {
        if(this.operator.matches("^\\+|\\-"))
            return OrderType.TERTIARY;
        else return OrderType.SECONDARY;
    }

    public float operate() throws OperateException{
        float a = leftOperand.operate();
        float b = rightOperand.operate();
        switch(operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                try {
                    float answer = a/b;
                    if (Float.isNaN(answer)) {
                        throw new OperateException(a, b, operator);
                    }
                    return a / b;
                } catch (Exception e) {
                    throw new OperateException(a, b, operator);
                }
            default:
                throw new OperateException(a, b, operator);
        }
    }

    public String toString() {
        return leftOperand.toString() + this.operator + rightOperand.toString();
    }
}
