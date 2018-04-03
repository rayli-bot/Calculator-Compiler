package extension.decorator;
import core.expression.*;
import exception.OperateException;
import type.OrderType;

/**
 * Strategy Context
 */
public class ExpressionDecorator implements Expression{
    Expression expr;

    public ExpressionDecorator(Expression expr) {
        this.expr = expr;
    }

    public OrderType exprOrder() {
        return this.expr.exprOrder();
    }
    public float operate() throws OperateException {
        return this.expr.operate();
    };
    public String toString() {
        return this.expr.toString();
    }
    public String getName() {
        return "Extended-" + this.expr.getName();
    }
    public Expression getExpression() { return this.expr; }
}
