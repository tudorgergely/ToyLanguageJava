package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

/**
 * @author Gergely on 12/10/2015.
 */
public final class NewStatement implements MyStatement {
    private static final long serialVersionUID = -4345389845261888651L;
    private final String varName;
    private final Expression expression;

    public NewStatement(final String varName, final Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public State execute(final State programState)
        throws VariableNotDefinedException, DivByZeroException, InvalidOptionException {
        programState.newSymbol(varName, programState.getEvaluation(expression));
        return programState;
    }

    @Override
    public String toString() {
        return "new (" + varName + ", " + expression.toString() + ')';
    }
}
