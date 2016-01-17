package controller;

import domain.state.State;
import domain.statements.MyStatement;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import repository.Repository;

public final class MyController implements Controller {
    private final Repository repo;
    private final ExecutorService pool;

    public MyController(final Repository repository) {
        repo = repository;
        pool = Executors.newFixedThreadPool(100);
    }

    @Override
    public void loadProgramFromFile() {
        repo.deserializeProgramStateFromFile();
    }

    @Override
    public void allSteps() throws InterruptedException {
        while (!(Thread.currentThread().isInterrupted()
            || repo.getCurrentProgramStates().isEmpty())) {
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
    public void loadProgram(final MyStatement statement) throws FileNotFoundException {
        repo.clear();
        repo.addProgram(statement);
    }

    @SuppressWarnings("ReturnOfNull")
    @Override
    public void oneStepForAllPrg(final List<State> programStates) throws InterruptedException {
        programStates.addAll(
            pool.invokeAll(
                programStates.stream()
                    .map(p -> (Callable<State>) p::oneStep)
                    .collect(Collectors.toList())).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
//                        e.printStackTrace();
                        return null;
                    }
                    return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList())
        );
        repo.setCurrentProgramStates(programStates);

        programStates.forEach(programState -> System.out.println(programState.toString() + "\n-----------------------------\n"));
    }

    @Override
    public List<State> removeCompletedPrograms(final List<State> programStates) {
        return programStates.stream()
            .filter(State::isNotCompleted)
            .distinct()
            .collect(Collectors.toList());
    }
}
