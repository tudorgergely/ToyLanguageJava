package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class BoolExpression implements Expression {
    private static final long serialVersionUID = -523862729246301861L;
    private final Expression firstExpression;
    private final Expression secondExpression;
    private final String boolOperator;

    public BoolExpression(final Expression firstExpression,
        final Expression secondExpression, final String boolOperator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.boolOperator = boolOperator;
    }

    @Override
    public Integer eval(final SymbolTable symTable, final Heap heap)
        throws InvalidOptionException, DivByZeroException,
        VariableNotDefinedException {
        final int firstResult = firstExpression.eval(symTable, heap);
        final int secondResult = secondExpression.eval(symTable, heap);
        final Integer result;

        //noinspection SwitchStatement
        switch (boolOperator) {
            case "&":
                result = ((firstResult == 0) && (secondResult == 0)) ? 1 : 0;

                break;
            case "|":
                result = ((firstResult == 0) || (secondResult == 0)) ? 1 : 0;

                break;
            case "!":
                result = firstResult;

                break;
            default:
                throw new InvalidOptionException("Invalid Boolean operator \n");
        }
        return result;
    }

    @Override
    public String toString() {
        return firstExpression.toString() + ' ' + boolOperator + ' ' + firstExpression.toString();
    }
}
