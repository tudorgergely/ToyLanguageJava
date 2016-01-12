package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

public final class PrintStatement implements MyStatement, Serializable {
    private final Expression expr;

    //Construct the printStatement depending on an Expression
    public PrintStatement(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "print(" + expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getOutput().addList(Integer.toString(expr.eval(
                programState.getSymbolTable(),
                programState.getHeap()
        )));
        return programState;
    }

    //Returns the Expression of the PrintStatements
    public Expression getExpression() {
        return expr;
    }
}
