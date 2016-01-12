package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;
import exceptions.VariableNotDefined;
import java.io.Serializable;


/**
 * @author tudor.gergely on 10/19/2015.
 */
public final class VariableExpression implements Serializable, Expression {
    private final String name;

    //Construct a new VariableExpression with the name given as parameter
    public VariableExpression(String name) {
        this.name = name;
    }

    //The function evaluate the expression, search it in
    //the SymbolTable and return it's value
    //The function will throw an error if the name does
    //not have an associated value
    @Override
    public int eval(MyDictionary symbolTable, MyHeap heap) throws VariableNotDefined {
        return (int) symbolTable.getElem(name);
    }

    //Return the name of the variable as a string
    @Override
    public String toString() {
        return "VariableExpression " + name + "\n";
    }
}
