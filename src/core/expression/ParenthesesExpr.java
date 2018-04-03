package core.expression;
import type.OrderType;
import exception.OperateException;

public class ParenthesesExpr implements Expression {
    Expression expr;

    public ParenthesesExpr(Expression expr) {
        this.expr = expr;
    }

    public OrderType exprOrder() { return OrderType.PRIMARY; }

    public float operate() throws OperateException{
        return expr.operate();
    }

    public Expression getExpr() { return expr; }

    public String getName() { return "(Parentheses)"; }

    public String toString() {
        return expr.toString();
    }
}
