package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;

import java.io.Serializable;

public final class ArithmeticExpression implements Serializable, Expression {
    private final Expression exp1;
    private final Expression exp2;
    private final String operator;

    //Constructor for ArithmeticExpression with 3 parameters, the first and the second one
    //expressions and the last one an operator which show the operation between the expressions
    public ArithmeticExpression(Expression exp1, Expression exp2, String operator){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    //Evaluate the operator given in the constructor and make the attached
    //operation between the 2 expressions

    @Override
    public int eval(MyDictionary symbolTable, MyHeap heap) throws Exception {
        int res1 = exp1.eval(symbolTable, heap);
        int res2 = exp2.eval(symbolTable, heap);
        switch (operator){
            case "+":
                return res1 + res2;
            case "-":
                return res1 - res2;
            case "*":
                return res1 * res2;
            case "/":
                if (res2 == 0){
                    throw new DivByZeroException("Division by zero \n");
                }
                return res1/res2;
        }
        throw new InvalidOptionException("Invalid Arithmetic operator \n");
    }

    @Override
    public String toString(){
        return exp1.toString() + " " + operator + " " + exp2.toString();
    }
}
