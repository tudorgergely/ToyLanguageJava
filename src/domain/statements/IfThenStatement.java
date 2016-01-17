package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class IfThenStatement implements MyStatement {
    private static final long serialVersionUID = -8530726832927261023L;
    private final Expression expression;
    private final MyStatement statement;


    public IfThenStatement(final Expression expression,
        final MyStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException, VariableNotDefinedException {
        new IfStatement(expression, statement, new SkipStatement()).execute(programState);
        return programState;
    }

    @Override
    public String toString() {
        return "if (" + expression.toString() + ") then ("
            + statement.toString() + ')';
    }
}
