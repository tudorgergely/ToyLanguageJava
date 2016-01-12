package controller;

import domain.statements.MyStatement;
import domain.theADTs.ProgramState;
import exceptions.EmptyContainerException;

import java.util.List;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public interface Controller {
    void loadProgramFromFile();

    void allSteps() throws Exception;

    void loadProgram(MyStatement myStatement);

    void oneStepForAllPrg(List<ProgramState> programStates) throws EmptyContainerException, InterruptedException;

    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates);

    void stop();
}
