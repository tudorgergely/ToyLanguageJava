package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class RelationalExpression implements Expression {
    private static final long serialVersionUID = 7813366915212279773L;
    private final Expression firstExpression;
    private final Expression secondExpression;
    private final String operator;


    public RelationalExpression(final Expression firstExpression,
        final Expression secondExpression,
        final String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @SuppressWarnings("OverlyComplexMethod")
    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap)
        throws InvalidOptionException, DivByZeroException,
        VariableNotDefinedException {

        final int firstResult = firstExpression.eval(symbolTable, heap);
        final int secondResult = secondExpression.eval(symbolTable, heap);
        final Integer result;

        //noinspection SwitchStatement
        switch (operator) {
            case ">":
                result = (firstResult > secondResult) ? 1 : 0;

                break;
            case "<":
                result =  (firstResult < secondResult) ? 1 : 0;

                break;
            case "<=":
                result = (firstResult <= secondResult) ? 1 : 0;

                break;
            case "==":
                result = (firstResult == secondResult) ? 1 : 0;

                break;
            case ">=":
                result = (firstResult >= secondResult) ? 1 : 0;

                break;
            case "!=":
                result = (firstResult == secondResult) ? 0 : 1;

                break;
            default:
                throw new InvalidOptionException("Invalid Relational operator \n");
        }
        return result;
    }

    @Override
    public String toString() {
        return firstExpression.toString() + " " + operator + " " + secondExpression.toString();
    }
}
