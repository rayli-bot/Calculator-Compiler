package extension.compiler;
import core.compilor.*;
import core.expression.Expression;
import exception.CompileException;
import extension.decorator.DecimalDecorator;

import java.util.List;

/**
 * A Fixed Decimal Place Compiler
 */
public class FixedPlaceCompiler extends CalculatorCompiler {
    protected int place;

    public FixedPlaceCompiler(String input, int place) {
        super(input);
        this.place = place;
    }

    @Override
    public Expression compile(List<CompilerElement> list) throws CompileException {
        Expression output = super.compile(list);
        return new DecimalDecorator(output, place);
    }
}
