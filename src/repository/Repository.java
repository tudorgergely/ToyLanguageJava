package repository;

import domain.state.State;
import domain.statements.MyStatement;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public interface Repository {
    void addProgram(MyStatement statement) throws FileNotFoundException;

    List<State> getCurrentProgramStates();

    void setCurrentProgramStates(List<State> currentProgramStates);

    void writeProgramToFile();

    void serializeProgramToFile() throws FileNotFoundException;

    void deserializeProgramStateFromFile();

    void clear();
}
