package domain.expressions;

import domain.theADTs.MyDictionary;
import domain.theADTs.MyHeap;
import exceptions.InvalidOptionException;

import java.io.Serializable;

public final class BoolExpression implements Serializable, Expression {
    private final Expression exp1;
    private final Expression exp2;
    private final String boolOperator;

    public BoolExpression(Expression e1, Expression e2, String lo) {
        this.exp1 = e1;
        this.exp2 = e2;
        this.boolOperator = lo;
    }

    @Override
    public int eval(MyDictionary symTable, MyHeap heap) throws Exception {
        int result1, result2;

        result1 = exp1.eval(symTable, heap);
        result2 = exp2.eval(symTable, heap);

        if (boolOperator.equals("&")) {
            if ((result1 == 0) && (result2 == 0)) {
                return 1;
            } else return 0;
        }

        if (boolOperator.equals("|")) {
            if (result1 == 0 || result2 == 0) {
                return 1;
            } else return 0;
        }

        if (boolOperator.equals("!")) {
            if (result1 != 0) {
                return 1;
            } else return 0;
        }
        throw new InvalidOptionException("Invalid Boolean operator \n");
    }

    @Override
    public String toString() {
        return exp1.toString() + " " + boolOperator + " " + exp1.toString();
    }
}
