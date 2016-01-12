package repository;

import domain.statements.MyStatement;
import domain.theADTs.ProgramState;

import java.util.List;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public interface Repository {
    void addProgram(MyStatement statement);

    List<ProgramState> getCurrentProgramStates();

    void setCurrentProgramStates(List<ProgramState> currentProgramStates);

    void writeProgramToFile();
}
