package domain.statements;

import domain.theADTs.LibDictionary;
import domain.theADTs.LibStack;
import domain.theADTs.ProgramState;

import java.io.Serializable;

/**
 * @author tudor.gergely on 1/7/2016.
 */
public final class ForkStatement implements MyStatement, Serializable {
    private final MyStatement statement;

    public ForkStatement(MyStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        ProgramState newProgramState = new ProgramState();
        LibStack exeStack = new LibStack();
        exeStack.pushSt(statement);
        newProgramState.setExeStack(exeStack);
        newProgramState.setSymbolTable(new LibDictionary((LibDictionary)programState.getSymbolTable()));
        newProgramState.setHeap(programState.getHeap());
        newProgramState.setOutput(programState.getOutput());
        newProgramState.setId(programState.getId() * 10);
        return newProgramState;
    }
}
