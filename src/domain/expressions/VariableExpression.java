package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.VariableNotDefinedException;


/**
 * @author tudor.gergely on 10/19/2015.
 */
public final class VariableExpression implements Expression {
    private static final long serialVersionUID = -4071560190717718598L;
    private final String name;

    //Construct a new VariableExpression with the name given as parameter
    public VariableExpression(final String name) {
        this.name = name;
    }

    //The function evaluate the expression, search it in
    //the SymbolTable and return it's value
    //The function will throw an error if the name does
    //not have an associated value
    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap)
        throws VariableNotDefinedException {
        return symbolTable.get(name);
    }

    //Return the name of the variable as a string
    @Override
    public String toString() {
        return name;
    }
}
