package controller;

import domain.state.State;
import domain.statements.MyStatement;
import exceptions.EmptyContainerException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public interface Controller {
    void loadProgramFromFile();

    void allSteps() throws InterruptedException;

    void loadProgram(MyStatement myStatement) throws FileNotFoundException;

    void oneStepForAllPrg(List<State> states) throws EmptyContainerException, InterruptedException;

    List<State> removeCompletedPrograms(List<State> states);

    void stop();
}
