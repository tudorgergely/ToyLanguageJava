package domain.statements;

import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class CompoundStatement implements MyStatement, Serializable {
    private final MyStatement left;
    private final MyStatement right;

    //Constructor with 2 parameters, both of them of type MyStatement
    public CompoundStatement(MyStatement left, MyStatement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "; " + right.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getExeStack().pushSt(right);
        programState.getExeStack().pushSt(left);
        return programState;
    }

    //Returns the leftStatement of the CompundStatement
    public MyStatement getLeft() {
        return left;
    }

    //Returns the rightStatement of the CompundStatement
    public MyStatement getRight() {
        return right;
    }
}
