package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class IncrementStatement implements MyStatement, Serializable {
    private final Expression expr;

    public IncrementStatement(Expression ex){
        this.expr = ex;
    }


    @Override
    public String toString() {
       return "increment(" + expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getSymbolTable().addModifyElem(expr.toString(), expr.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        ) + 1);
        return programState;
    }

    public Expression getExpression() {
        return expr;
    }
}
