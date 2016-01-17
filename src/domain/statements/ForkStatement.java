package domain.statements;

import domain.state.State;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public final class ForkStatement implements MyStatement {
    private static final long serialVersionUID = -8000001459541418266L;
    private final MyStatement statement;

    public ForkStatement(final MyStatement statement) {
        this.statement = statement;
    }

    @Override
    public State execute(final State programState) {
        final State newState = programState.fork();
        newState.addToExeStack(statement);
        return newState;
    }

    @Override
    public String toString() {
        return "fork(" + statement + ')';
    }
}
