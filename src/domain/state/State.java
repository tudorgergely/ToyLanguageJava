package domain.state;

import domain.expressions.Expression;
import domain.statements.MyStatement;
import exceptions.DivByZeroException;
import exceptions.EmptyContainerException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;
import java.io.Serializable;

/**
 * @author tudor.gergely on 1/17/2016
 */
public interface State extends Serializable {
    State oneStep() throws EmptyContainerException, VariableNotDefinedException, DivByZeroException, InvalidOptionException;

    Boolean isNotCompleted();

    void putSymbol(String name, Integer value) throws VariableNotDefinedException, DivByZeroException, InvalidOptionException;

    void addToExeStack(MyStatement statement);

    void newSymbol(String name, Integer value);

    void putHeap(Integer address, Integer value);

    Integer getSymbol(String name) throws VariableNotDefinedException;

    void addToOutput(String value);

    Integer getEvaluation(Expression expression) throws VariableNotDefinedException, DivByZeroException, InvalidOptionException;

    State fork();
}
