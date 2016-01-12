package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class IfThenStatement implements MyStatement, Serializable {
    private final Expression exp;
    private final MyStatement stm;


    public IfThenStatement(Expression e, MyStatement s) {
        this.exp = e;
        this.stm = s;
    }

    @Override
    public String toString() {
        return "if" + exp.toString() + "then" + stm.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        new IfStatement(exp, stm, new SkipStatement()).execute(programState);
        return programState;
    }

    public Expression getExp() {
        return exp;
    }

    public MyStatement getStm() {
        return stm;
    }
}
