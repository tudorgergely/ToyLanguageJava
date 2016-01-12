package controller;

import domain.statements.*;
import domain.theADTs.*;
import exceptions.EmptyContainerException;
import repository.MyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public final class MyController implements Controller {
    private final MyRepository repo;
    private final ExecutorService pool;

    public MyController(MyRepository repository) {
        repo = repository;
        pool = Executors.newFixedThreadPool(100);
    }

    @Override
    public void loadProgramFromFile() {
        repo.deserializeProgramStateFromFile();
    }

    @Override
    public void allSteps() throws EmptyContainerException, InterruptedException {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            if (repo.getCurrentProgramStates().size() == 0) {
                break;
            }
            oneStepForAllPrg(removeCompletedPrograms(repo.getCurrentProgramStates()));
        }
    }

    @Override
    public void stop() {
        pool.shutdownNow();
    }

    //The function put the given program into the repository
    //and after the program is added to the repository
    //the stack will be created based on the added program
    @Override
    public void loadProgram(MyStatement statement) {
        repo.setCurrentProgramStates(new ArrayList<>());
        repo.addProgram(statement);
    }

    @Override
    public void oneStepForAllPrg(List<ProgramState> programStates) throws EmptyContainerException, InterruptedException {
        programStates.addAll(
            pool.invokeAll(programStates.stream()
                .map(p -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList())).stream()
            .map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            })
            .filter(p -> p != null)
            .collect(Collectors.toList())
        );
        repo.setCurrentProgramStates(programStates);

        programStates.forEach(programState -> System.out.println(programState.toString()));
    }

    @Override
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
            .filter(ProgramState::isNotCompleted)
            .collect(Collectors.toList());
    }
}
