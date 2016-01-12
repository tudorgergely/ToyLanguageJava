package domain.theADTs;

import domain.statements.MyStatement;
import exceptions.EmptyContainerException;

import java.io.Serializable;
import java.util.Stack;

public final class LibStack implements MyStack<MyStatement>, Serializable {
    private static final long serialVersionUID = 3L;
    private final Stack<MyStatement> statements;

    public LibStack() {
        statements = new Stack<>();
    }

    @Override
    public boolean isEmpty() {
        return statements.empty();
    }

    //The function eliminate the las element of the stack, returning it
    @Override
    public MyStatement popSt() throws EmptyContainerException {
        if (statements.empty()) {
            throw new EmptyContainerException("The Stack is empty \n");
        }
        return statements.pop();
    }

    //Put an element on the stack, and increment the size of the stack by 1
    @Override
    public void pushSt(MyStatement statement) {
        statements.push(statement);
    }

    //Override the toString method, for a better print view for the user
    @Override
    public String toString() {
        return statements.stream()
            .map(MyStatement::toString)
            .reduce((s, s2) -> s + ", " + s2)
            .orElse("");
    }
}
