package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class IfStatement implements MyStatement {
    private static final long serialVersionUID = 7349845643587142149L;
    private final Expression condition;
    private final MyStatement thenStatement;
    private final MyStatement elseStatement;

    public IfStatement(final Expression expression,
        final MyStatement thenStatement, final MyStatement elseStatement) {
        this.condition = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException, VariableNotDefinedException {
        if (programState.getEvaluation(condition) == 0) {
            elseStatement.execute(programState);
        } else {
            thenStatement.execute(programState);
        }
        return programState;
    }

    @Override
    public String toString() {
        return "if (" + condition + ") then (" + thenStatement.toString()
            + ") else (" + elseStatement.toString() + ')';
    }
}
