package domain.statements;

import domain.theADTs.ProgramState;

public interface MyStatement {
    String toString();

    ProgramState execute(ProgramState programState) throws Exception;
}
