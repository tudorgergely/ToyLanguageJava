package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;

public final class ConstantExpression implements Expression {
    private static final long serialVersionUID = -8768614264755824512L;
    private final int constant;

    //Creates a ConstantExpression. entity which hold an integer
    //The parameter is an int which will be stored
    public ConstantExpression(final int constant) {
        this.constant = constant;
    }

    //Evaluate the ConstExpression. by returning its value
    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap) {
        return constant;
    }

    @Override
    public String toString() {
        return Integer.toString(constant);
    }
}
