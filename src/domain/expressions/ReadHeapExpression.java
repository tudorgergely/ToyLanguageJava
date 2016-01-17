package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.VariableNotDefinedException;

/**
 * @author Gergely on 12/10/2015.
 */
public final class ReadHeapExpression implements Expression {
    private static final long serialVersionUID = -8713951914893961922L;
    private final String varName;

    public ReadHeapExpression(final String varName) {
        this.varName = varName;
    }

    @Override
    public Integer eval(final SymbolTable symbolTable, final Heap heap)
        throws VariableNotDefinedException {
        return heap.get(symbolTable.get(varName));
    }

    @Override
    public String toString() {
        return "readHeap(" + varName + ')';
    }
}
