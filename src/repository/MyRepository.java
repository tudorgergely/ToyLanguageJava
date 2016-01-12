package repository;

import domain.statements.MyStatement;
import domain.theADTs.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class MyRepository implements Repository {
    private final List<ProgramState> currentProgramStates;
    private final File file;

    //Constructor of the Repository, will allocate an array of ProgramState's
    //an int for the currentProgram as the position in the array,
    //and an int as the size of the array
    public MyRepository() {
        currentProgramStates = new ArrayList<>();
        file = new File("programState.txt");
        resetFile();
    }

    @Override
    public List<ProgramState> getCurrentProgramStates() {
        return currentProgramStates;
    }

    @Override
    public void setCurrentProgramStates(List<ProgramState> currentProgramStates) {
        this.currentProgramStates.clear();
        this.currentProgramStates.addAll(currentProgramStates);
    }

    //Add a program to the Repository, a program which is a complex Statement
    //The parameter is of type MyStatement and represent the program
    @Override
    public void addProgram(MyStatement stm) {
        currentProgramStates.add(new ProgramState(stm));
        serializeProgramToFile();
    }

    private void serializeProgramToFile() {
        try {
            FileOutputStream fout = new FileOutputStream("programStates.f");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            currentProgramStates.forEach(programState -> {
                try {
                    oos.writeObject(programState);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            oos.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deserializeProgramStateFromFile() {
        try {
            FileInputStream streamIn = new FileInputStream("programStates.f");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            currentProgramStates.clear();
            currentProgramStates.addAll((List<ProgramState>) objectinputstream.readObject());
            objectinputstream.close();
            streamIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeProgramToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            currentProgramStates.forEach(programState -> {
                try {
                    bw.write(programState.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetFile() {
        if (file.exists()) {
            file.delete();
        }
    }
}
