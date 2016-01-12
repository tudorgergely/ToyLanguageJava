package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class WhileStatement implements MyStatement, Serializable {
    private final Expression expr;
    private final MyStatement stm;

    public WhileStatement(Expression e, MyStatement s) {
        this.expr = e;
        this.stm = s;
    }

    public String toString() {
        return "while(" + expr.toString() + " != 0" + ") do (" + stm.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        if (expr.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        ) != 0) {
            programState.getExeStack().pushSt(this);
            programState.getExeStack().pushSt(stm);
        }
        return programState;
    }

    public Expression getExpression() {
        return expr;
    }

    public MyStatement getStatement() {
        return stm;
    }
}
