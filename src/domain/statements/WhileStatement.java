package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class WhileStatement implements MyStatement {
    private static final long serialVersionUID = 3958115633880462949L;
    private final Expression expression;
    private final MyStatement statement;

    public WhileStatement(final Expression expression, final MyStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException, VariableNotDefinedException {
        if (programState.getEvaluation(expression) != 0) {
            programState.addToExeStack(this);
            programState.addToExeStack(statement);
        }
        return programState;
    }

    public String toString() {
        return "while (" + expression.toString() + " != 0" + ") do (" + statement.toString() + ')';
    }
}
