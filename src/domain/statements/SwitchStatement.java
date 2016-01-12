package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class SwitchStatement implements MyStatement, Serializable {
    private final Expression switchExpression;
    private final Expression case1;
    private final MyStatement case1Statement;
    private final Expression case2;
    private final MyStatement case2Statement;

    public SwitchStatement(
        Expression switchExpression,
        Expression case1,
        MyStatement case1Statement,
        Expression case2,
        MyStatement case2Statement
    ) {
        this.switchExpression = switchExpression;
        this.case1 = case1;
        this.case2 = case2;
        this.case1Statement = case1Statement;
        this.case2Statement = case2Statement;
    }

    @Override
    public String toString() {
        return "switch (" + switchExpression.toString() + ") case ("
            + case1.toString() + "): " + case1Statement.toString()
            + " case(" + case2.toString() + "): " + case2Statement.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        Integer switchResult = getSwitchExpression()
            .eval(programState.getSymbolTable(), programState.getHeap());

        if (switchResult - getCase1().eval(programState.getSymbolTable(), programState.getHeap()) == 0) {
            programState.getExeStack().pushSt(getCase1Statement());
        } else if (switchResult - getCase2().eval(programState.getSymbolTable(), programState.getHeap()) == 0) {
            programState.getExeStack().pushSt(getCase2Statement());
        }
        return programState;
    }

    public Expression getSwitchExpression() {
        return switchExpression;
    }

    public Expression getCase1() {
        return case1;
    }

    public MyStatement getCase1Statement() {
        return case1Statement;
    }

    public Expression getCase2() {
        return case2;
    }

    public MyStatement getCase2Statement() {
        return case2Statement;
    }
}
