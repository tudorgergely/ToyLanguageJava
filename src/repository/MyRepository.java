package repository;

import domain.state.ProgramState;
import domain.state.State;
import domain.statements.MyStatement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MyRepository implements Repository {
    private final List<State> currentProgramStates;
    private final File file;

    //Constructor of the Repository, will allocate an array of ProgramState's
    //an int for the currentProgram as the position in the array,
    //and an int as the size of the array
    public MyRepository() {
        currentProgramStates = new ArrayList<>(10);
        file = new File("programState.txt");
        resetFile();
    }

    @Override
    public List<State> getCurrentProgramStates() {
        return Collections.unmodifiableList(currentProgramStates);
    }

    @Override
    public void setCurrentProgramStates(final List<State> currentProgramStates) {
        this.currentProgramStates.clear();
        this.currentProgramStates.addAll(currentProgramStates);
    }

    //Add a program to the Repository, a program which is a complex Statement
    //The parameter is of type MyStatement and represent the program
    @Override
    public void addProgram(final MyStatement statement) {
        final State state = new ProgramState();
        state.addToExeStack(statement);
        currentProgramStates.add(state);
        serializeProgramToFile();
    }

    @Override
    public void serializeProgramToFile() {
        try (FileOutputStream fout = new FileOutputStream("programStates.f");
             ObjectOutput oos = new ObjectOutputStream(fout)) {
            currentProgramStates.forEach(programState -> {
                try {
                    oos.writeObject(programState);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserializeProgramStateFromFile() {
        try (FileInputStream streamIn = new FileInputStream("programStates.f");
             ObjectInput ois = new ObjectInputStream(streamIn)) {

            currentProgramStates.clear();
            currentProgramStates.addAll((Collection<State>) ois.readObject());
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        currentProgramStates.clear();
    }

    @Override
    public void writeProgramToFile() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            currentProgramStates.forEach(programState -> {
                try {
                    bw.write(programState.toString() + '\n');
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void resetFile() {
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("File could not be deleted");
            }
        }
    }
}
