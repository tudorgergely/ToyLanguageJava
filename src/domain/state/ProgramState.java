package domain.state;

import domain.expressions.Expression;
import domain.statements.MyStatement;
import exceptions.DivByZeroException;
import exceptions.EmptyContainerException;
import exceptions.InvalidOptionException;
import exceptions.VariableNotDefinedException;
import java.security.SecureRandom;

//Represent the state of a program
//This works with a stack, a dictionary for symbols(variables), and a list that store the outputs
public final class ProgramState implements State {
    private static final long serialVersionUID = 1498402181324269297L;
    private final Integer id;
    private final SymbolTable symbolTable;
    private final Output output;
    private final Heap heap;
    private final ExecutionStack executionStack;

    //The constructor, creates a ProgramState for a program.
    //The parameter is an MyStatement, which hold the entire program,
    //and this will be pushed on the stack
    public ProgramState() {
        this.id = new SecureRandom().nextInt();
        this.executionStack = new LibExecutionStack();
        this.symbolTable = new LibSymbolTable();
        this.output = new LibOutput();
        this.heap = new LibHeap();
    }

    private ProgramState(final Integer id, final ExecutionStack executionStack,
        final SymbolTable symbolTable, final Output output, final Heap heap) {
        this.id = id;
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.heap = heap;
    }

    @Override
    public Boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    @Override
    public void putSymbol(final String name, final Integer value) throws VariableNotDefinedException, DivByZeroException, InvalidOptionException {
        symbolTable.set(name, value);
    }

    @Override
    public void addToExeStack(final MyStatement statement) {
        executionStack.pushStatement(statement);
    }

    @Override
    public void newSymbol(final String name, final Integer value) {
        symbolTable.set(name, heap.push(value));
    }

    @Override
    public void putHeap(final Integer address, final Integer value) {
        heap.put(address, value);
    }

    @Override
    public Integer getSymbol(final String name) throws VariableNotDefinedException {
        return symbolTable.get(name);
    }

    @Override
    public void addToOutput(final String value) {
        output.add(value);
    }

    @Override
    public Integer getEvaluation(final Expression expression) throws VariableNotDefinedException, DivByZeroException, InvalidOptionException {
        return expression.eval(symbolTable, heap);
    }

    @Override
    public State fork() {
        return new ProgramState(
            new SecureRandom().nextInt(),
            new LibExecutionStack(),
            new LibSymbolTable(symbolTable),
            output,
            heap
        );
    }

    @SuppressWarnings("MethodWithTooExceptionsDeclared")
    @Override
    public State oneStep()
        throws EmptyContainerException, VariableNotDefinedException,
        DivByZeroException, InvalidOptionException {
        return executionStack.popStatement().execute(this);
    }

    //Override the ToString() method for a better print view
    @SuppressWarnings("StringConcatenationMissingWhitespace")
    @Override
    public String toString() {
        return "\nProgramState: \n" +
            "\nid: " + id + '\n' + executionStack + '\n' +
            symbolTable + '\n' + output + '\n' + heap + "\n\n";
    }
}
