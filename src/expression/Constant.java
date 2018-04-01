package expression;
import type.OrderType;

public class Constant implements Expression {
    float val;

    public Constant(float val) {
        this.val = val;
    }

    public OrderType exprOrder() { return OrderType.LAST; }

    public float operate() {
        return val;
    }

    public String toString() { return Float.toString(val); }
}
