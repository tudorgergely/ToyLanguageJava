package domain.theADTs;

import domain.statements.MyStatement;
import exceptions.EmptyContainerException;

import java.io.Serializable;

//Represent the state of a program
//This works with a stack, a dictionary for symbols(variables), and a list that store the outputs
public final class ProgramState implements Serializable {
    private static final long serialVersionUID = 2L;
    private Integer id;
    private MyDictionary<String, Integer> symbolTable;
    private MyList<String> output;
    private MyHeap<Integer> heap;
    private MyStack<MyStatement> exeStack;

    //The constructor, creates a ProgramState for a prog.
    //The parameter is an MyStatement, which hold the entire program,
    //and this will be pushed on the stack
    public ProgramState() {
        this.id = 1002;
        this.exeStack = new LibStack();
        this.symbolTable = new LibDictionary();
        this.output = new LibList();
        this.heap = new LibHeap();
    }

    public ProgramState(MyStatement stm) {
        this.id = 1002;
        this.exeStack = new LibStack();
        this.symbolTable = new LibDictionary();
        this.output = new LibList();
        this.heap = new LibHeap();
        this.exeStack.pushSt(stm);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MyHeap<Integer> getHeap() {
        return heap;
    }

    public void setHeap(MyHeap<Integer> heap) {
        this.heap = heap;
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep() throws EmptyContainerException {
        MyStatement statement = exeStack.popSt();
        try {
            return statement.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    //returns the exeStack
    public MyStack<MyStatement> getExeStack() {
        return exeStack;
    }

    //returns the dictionary with the symbols(variables)
    public MyDictionary getSymbolTable() {
        return symbolTable;
    }

    //return the list of the outputs
    public MyList<String> getOutput() {
        return output;
    }

    //Override the ToString() method for a better print view
    @Override
    public String toString() {
        return "\nProgramState \n" +
                "\nid: " + id + "\n" +
                "\nexecutionStack: \n" + exeStack +
                "\nsymbols: \n" + symbolTable +
                "\noutput: \n" + output +
                "\nheap: \n" + heap +
                "\n\n";
    }

    public void setExeStack(MyStack<MyStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymbolTable(MyDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setOutput(MyList<String> output) {
        this.output = output;
    }
}
