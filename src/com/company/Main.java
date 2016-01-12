package com.company;

import controller.MyController;
import repository.MyRepository;
import ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        MyRepository repository = new MyRepository();
        MyController controller = new MyController(repository);

        UserInterface userInterface = new UserInterface(controller);
        userInterface.run();
    }
}
