package domain.statements;

import domain.state.State;

public final class SkipStatement implements MyStatement {
    private static final long serialVersionUID = -5788825496056497287L;

    @Override
    public State execute(final State programState) {
        return programState;
    }

    @Override
    public String toString() {
        return "skip";
    }

}
