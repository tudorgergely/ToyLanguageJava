package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class ArithmeticExpression implements Expression {
    private static final long serialVersionUID = -3266927576087732224L;
    private final Expression firstExpression;
    private final Expression secondExpression;
    private final String operator;

    //Constructor for ArithmeticExpression with 3 parameters, the first and the second one
    //expressions and the last one an operator which show the operation between the expressions
    public ArithmeticExpression(final Expression firstExpression,
        final Expression secondExpression, final String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    //Evaluate the operator given in the constructor and make the attached
    //operation between the 2 expressions

    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap)
        throws InvalidOptionException, DivByZeroException,
        VariableNotDefinedException {
        final Integer result;
        final int firstResult = firstExpression.eval(symbolTable, heap);
        final int secondResult = secondExpression.eval(symbolTable, heap);
        //noinspection SwitchStatement
        switch (operator) {
            case "+":
                result = firstResult + secondResult;

                break;
            case "-":
                result = firstResult - secondResult;

                break;
            case "*":
                result = firstResult * secondResult;

                break;
            case "/":
                if (secondResult == 0) {
                    throw new DivByZeroException("Division by zero \n");
                }
                result = firstResult / secondResult;

                break;
            default:
                throw new InvalidOptionException("Invalid Arithmetic operator \n");
        }
        return result;
    }

    @Override
    public String toString() {
        return firstExpression.toString() + ' ' + operator + ' ' + secondExpression.toString();
    }
}
