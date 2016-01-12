package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class DecrementStatement implements MyStatement, Serializable {
    private final Expression expr;

    public DecrementStatement(Expression e) {
        this.expr = e;
    }

    @Override
    public String toString() {
        return "decrement(" + expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getSymbolTable().addModifyElem(toString(), expr.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        ) - 1);
        return programState;
    }

    public Expression getExpression() {
        return expr;
    }
}
