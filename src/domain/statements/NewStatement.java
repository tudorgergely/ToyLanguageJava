package domain.statements;

import domain.expressions.Expression;
import domain.theADTs.ProgramState;

import java.io.Serializable;

/**
 * @author Gergely on 12/10/2015.
 */
public final class NewStatement implements MyStatement, Serializable {
    private final String varName;
    private final Expression expression;

    public NewStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "new (" + varName + ", " + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        programState.getSymbolTable().addModifyElem(varName,
                programState.getHeap().pushVal(expression.eval(
                        programState.getSymbolTable(),
                        programState.getHeap()
                )));
        return programState;
    }
}
