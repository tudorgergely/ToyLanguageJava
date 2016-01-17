package com.company;

import controller.MyController;
import repository.MyRepository;
import ui.UserInterface;

public class Main {
    public static void main(final String[] args) {
        final MyRepository repository = new MyRepository();
        final MyController controller = new MyController(repository);

        final UserInterface userInterface = new UserInterface(controller);
        userInterface.run();
    }
}
