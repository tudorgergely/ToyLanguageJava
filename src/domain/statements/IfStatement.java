package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class IfStatement implements MyStatement, Serializable {
    private final Expression condition;
    private final MyStatement thenStatement;
    private final MyStatement elseStatement;

    public IfStatement(Expression expression, MyStatement thenStatement, MyStatement elseStatement) {
        this.condition = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public Expression getCondition() {
        return condition;
    }

    public MyStatement getThenStatement() {
        return thenStatement;
    }

    public MyStatement getElseStatement() {
        return elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        if (condition.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        ) != 0) {
            thenStatement.execute(programState);
        } else {
            elseStatement.execute(programState);
        }
        return programState;
    }
}
