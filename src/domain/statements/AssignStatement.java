package domain.statements;


import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class AssignStatement implements MyStatement, Serializable {
    public final String name;
    public final Expression expr;

    //Construct an AssignStatement with 2 parameters
    //The first string parameter will have the value of the evaluation
    //of the second Expression parameter
    public AssignStatement(String name, Expression expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return name + " = " + expr.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getSymbolTable().addModifyElem(name, expr.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        ));
        return programState;
    }

    //Returns the Expression of the Statement
    public Expression getExpression() {
        return expr;
    }

    public String getName() {
        return name;
    }
}
