package extension.decorator;
import core.expression.*;
import exception.OperateException;
import java.math.BigDecimal;

public class DecimalDecorator extends ExpressionDecorator {
    private int place;
    public DecimalDecorator (Expression expr, int place) {
        super (expr);
        this.place = place; // Default 3 decimal place
    }

    @Override
    public float operate() throws OperateException {
        float answer = expr.operate();
        return BigDecimal.valueOf(answer).setScale(place, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
