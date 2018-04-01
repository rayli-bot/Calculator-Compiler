package expression;
import type.OrderType;
import exception.OperateException;

public interface Expression {
    public OrderType exprOrder();
    public float operate() throws OperateException;
    public String toString();
}
