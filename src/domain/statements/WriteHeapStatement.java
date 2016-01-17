package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

/**
 * @author Gergely on 12/10/2015.
 */
public final class WriteHeapStatement implements MyStatement {
    private static final long serialVersionUID = -1383898196074963881L;
    private final String varName;
    private final Expression expression;

    public WriteHeapStatement(final String varName, final Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException, VariableNotDefinedException {
        programState.putHeap(programState.getSymbol(varName), programState.getEvaluation(expression));
        return programState;
    }

    @Override
    public String toString() {
        return "writeHeap(" + varName + ", " + expression.toString() + ')';
    }
}
