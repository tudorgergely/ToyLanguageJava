package domain.statements;

import domain.theADTs.ProgramState;

import java.io.Serializable;

public class SkipStatement implements MyStatement, Serializable {
    public SkipStatement() {

    }

    @Override
    public String toString() {
        return "Skip";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        return programState;
    }

}
