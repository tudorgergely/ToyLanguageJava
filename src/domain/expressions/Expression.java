package domain.expressions;

import domain.state.Heap;
import domain.state.SymbolTable;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;
import java.io.Serializable;

public interface Expression extends Serializable {
    //the parameter is a dictionary of symbols
    //the return is an integer, which is the result of the evaluation of the expression
    //it will throw an exception if the expression can't be executed
    Integer eval(SymbolTable symbolTable, Heap heap) throws DivByZeroException,
        InvalidOptionException, VariableNotDefinedException;
}
