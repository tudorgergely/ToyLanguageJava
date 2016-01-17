package domain.statements;

import domain.expressions.Expression;
import domain.state.State;
import exceptions.DivByZeroException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;

public final class AssignStatement implements MyStatement {
    private static final long serialVersionUID = 5670541181126143423L;
    private final String name;
    private final Expression expr;

    //Construct an AssignStatement with 2 parameters
    //The first string parameter will have the value of the evaluation
    //of the second Expression parameter
    public AssignStatement(final String name, final Expression expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public State execute(final State programState)
        throws DivByZeroException, InvalidOptionException,
        VariableNotDefinedException {
        programState.putSymbol(name, programState.getEvaluation(expr));
        return programState;
    }

    @Override
    public String toString() {
        return name + " = " + expr;
    }
}
