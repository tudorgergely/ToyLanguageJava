package domain.statements;

import domain.state.State;

public final class CompoundStatement implements MyStatement {
    private static final long serialVersionUID = 8744380961429868971L;
    private final MyStatement left;
    private final MyStatement right;

    //Constructor with 2 parameters, both of them of type MyStatement
    public CompoundStatement(final MyStatement left, final MyStatement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public State execute(final State programState) {
        programState.addToExeStack(right);
        programState.addToExeStack(left);
        return programState;
    }

    @Override
    public String toString() {
        return '(' + left.toString() + "; " + right.toString() + ')';
    }
}
