package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class SwitchStatement implements MyStatement {
    private static final long serialVersionUID = -6530425230926535646L;
    private final Expression switchExpression;
    private final Expression firstCase;
    private final MyStatement firstCaseStatement;
    private final Expression secondCase;
    private final MyStatement secondCaseStatement;

    public SwitchStatement(
        final Expression switchExpression,
        final Expression firstCase,
        final MyStatement firstCaseStatement,
        final Expression secondCase,
        final MyStatement secondCaseStatement
    ) {
        this.switchExpression = switchExpression;
        this.firstCase = firstCase;
        this.secondCase = secondCase;
        this.firstCaseStatement = firstCaseStatement;
        this.secondCaseStatement = secondCaseStatement;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException, VariableNotDefinedException {
        final Integer switchResult = programState.getEvaluation(switchExpression);

        if ((switchResult - programState.getEvaluation(firstCase)) == 0) {
            programState.addToExeStack(firstCaseStatement);
        } else if ((switchResult - programState.getEvaluation(secondCase))== 0) {
            programState.addToExeStack(secondCaseStatement);
        }
        return programState;
    }

    @Override
    public String toString() {
        return "switch (" + switchExpression.toString() + ") case ("
            + firstCase.toString() + "): " + firstCaseStatement.toString()
            + " case(" + secondCase.toString() + "): " + secondCaseStatement.toString();
    }
}
