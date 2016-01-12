package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;

import java.io.Serializable;

public final class ConstantExpression implements Serializable, Expression {
    private final int nr;

    //Creates a ConstantExpression. entity which hold an integer
    //The parameter is an int which will be stored
    public ConstantExpression(int nr) {
        this.nr = nr;
    }

    //Evaluate the ConstExpression. by returning its value
    @Override
    public int eval(MyDictionary symbolTable, MyHeap heap) {
        return nr;
    }

    @Override
    public String toString() {
        return Integer.toString(nr);
    }
}
