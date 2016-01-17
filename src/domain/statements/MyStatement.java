package domain.statements;

import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;
import java.io.Serializable;

public interface MyStatement extends Serializable {
    String toString();

    State execute(State programState) throws DivByZeroException, InvalidOptionException, VariableNotDefinedException;
}
