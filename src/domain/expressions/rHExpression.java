package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;

import java.io.Serializable;

/**
 * @author Gergely on 12/10/2015.
 */
public final class rHExpression implements Serializable, Expression {
    private final String varName;

    public rHExpression(String varName) {
        this.varName = varName;
    }

    @Override
    public int eval(MyDictionary symbolTable, MyHeap heap) throws Exception {
        return (int) heap.getVal((Integer) symbolTable.getElem(varName));
    }

    @Override
    public String toString() {
        return "ReadHeap " + varName + "\n";
    }
}
