package expression;
import type.OrderType;
import exception.OperateException;

public class ParenthesisExpr implements Expression {
    Expression expr;

    public ParenthesisExpr(Expression expr) {
        this.expr = expr;
    }

    public OrderType exprOrder() { return OrderType.PRIMARY; }

    public float operate() throws OperateException{
        return expr.operate();
    }

    public String toString() {
        return expr.toString();
    }
}
