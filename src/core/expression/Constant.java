package core.expression;
import type.OrderType;
import exception.OperateException;

public class Constant implements Expression {
    float val;

    public Constant(float val) {
        this.val = val;
    }

    public OrderType exprOrder() { return OrderType.LAST; }

    public float operate() throws OperateException {
        if (Float.isNaN(val)) {
            throw new OperateException(val, 0, "Const");
        }
        return val;
    }

    public String getName() { return "Constant : " + Float.toString(val); }

    public String toString() { return Float.toString(val); }
}
