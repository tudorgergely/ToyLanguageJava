package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;

public interface Expression {
    //the parameter is a dictionary of symbols
    //the return is an integer, which is the result of the evaluation of the expression
    //it will throw an exception if the expression can't be executed
    int eval(MyDictionary symbolTable, MyHeap heap) throws Exception;
}
