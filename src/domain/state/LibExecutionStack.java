package domain.state;

import domain.statements.MyStatement;
import exceptions.EmptyContainerException;
import java.util.Stack;

/**
 * @author tudor.gergely on 1/17/2016
 */
public final class LibExecutionStack implements ExecutionStack {
    private static final long serialVersionUID = -3231937230328898606L;
    private final Stack<MyStatement> stack;

    public LibExecutionStack() {
        stack = new Stack<>();
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void pushStatement(final MyStatement statement) {
        stack.add(statement);
    }

    @Override
    public MyStatement popStatement() throws EmptyContainerException {
        if (stack.empty()) {
            throw new EmptyContainerException("The stack is empty");
        }
        return stack.pop();
    }

    @Override
    public String toString() {
        return "Execution stack: \n" + stack.stream()
            .map(MyStatement::toString)
            .reduce((accumulator, s) -> accumulator + ", " + s)
            .orElse("");
    }
}
