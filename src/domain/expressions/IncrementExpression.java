package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

/**
 * @author tudor.gergely on 1/17/2016
 */
public final class IncrementExpression implements Expression {
    private static final long serialVersionUID = -8352166354289408423L;
    private final Expression expression;

    public IncrementExpression(final Expression expression) {
        this.expression = expression;
    }

    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap)
        throws DivByZeroException, InvalidOptionException,
        VariableNotDefinedException {
        return expression.eval(symbolTable, heap) + 1;
    }

    @Override
    public String toString() {
        return "increment(" + expression + ')';
    }
}
