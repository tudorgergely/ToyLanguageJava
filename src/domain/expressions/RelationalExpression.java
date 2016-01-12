package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;
import exceptions.InvalidOptionException;

import java.io.Serializable;

public final class RelationalExpression implements Serializable, Expression {
    private final Expression exp1;
    private final Expression exp2;
    private final String operator;


    public RelationalExpression(Expression exp1, Expression exp2,
        String operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    @Override
    public int eval(MyDictionary symbols, MyHeap heap) throws Exception {
        int result1 = exp1.eval(symbols, heap);
        int result2 = exp2.eval(symbols, heap);

        if (operator.equals(">")) {
            return result1 > result2 ? 1 : 0;
        }
        if (operator.equals("<")) {
            return result1 < result2 ? 1 : 0;
        }
        if (operator.equals("<=")) {
            return result1 <= result2 ? 1 : 0;
        }
        if (operator.equals("==")) {
            return result1 == result2 ? 1 : 0;
        }
        if (operator.equals(">=")) {
            return result1 >= result2 ? 1 : 0;
        }
        if (operator.equals("!=")) {
            return result1 != result2 ? 1 : 0;
        }
        throw new InvalidOptionException("Invalid Relational operator \n");
    }

    @Override
    public String toString() {
        return exp1.toString() + " " + operator + " " + exp2.toString();
    }
}
