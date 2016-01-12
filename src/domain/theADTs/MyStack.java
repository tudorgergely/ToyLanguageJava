package domain.theADTs;

import domain.statements.MyStatement;
import exceptions.EmptyContainerException;

//the interface for the stack
public interface MyStack<T> {
    //Verifies if the stack is empty
    //Return true if the stack is empty, false otherwise
    boolean isEmpty();

    //Returns and remove the last element from the stack
    MyStatement popSt() throws EmptyContainerException;

    //Add or put an element to the top of the stack
    //The parameter is the type MyStatement
    void pushSt(T statement);
}
