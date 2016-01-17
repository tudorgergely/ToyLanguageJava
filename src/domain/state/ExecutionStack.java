package domain.state;

import domain.statements.MyStatement;
import exceptions.EmptyContainerException;
import java.io.Serializable;

/**
 * @author tudor.gergely on 1/17/2016
 */
public interface ExecutionStack extends Serializable {
    Boolean isEmpty();

    void pushStatement(MyStatement statement);

    MyStatement popStatement() throws EmptyContainerException;
}
