package ui;

import controller.MyController;
import domain.expressions.*;
import domain.statements.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class UserInterface {
    private final boolean fromFile;
    private MyController ctrl;
    private Scanner keyboard;

    //constructor based on the controler
    public UserInterface(MyController ctrl) {
        this.ctrl = ctrl;
        keyboard = new Scanner(System.in);
        fromFile = false;
    }

    //constructor based on the controller and a file
    public UserInterface(MyController ctrl, String fileName) throws FileNotFoundException {
        this.ctrl = ctrl;
        File inputFile = new File(fileName);
        keyboard = new Scanner(inputFile);
        fromFile = true;
    }

    //Runs the main menu and let the user to choose the option
    public void run() {
        try {
            while (true) {
                printMainMenu();
                System.out.print("Menu entry: ");
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                if (fromFile)
                    System.out.println(Integer.toString(choice));
                switch (choice) {
                    case 0:
                        ctrl.stop();
                        return;
                    case 1:
                        loadProgram();
                        break;
                    case 2:
                        try {
                            executeProgram();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        loadFromFile();
                        break;
                    default:
                        System.out.print("Choice " + Integer.toString(choice) + " is invalid");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        ctrl.loadProgramFromFile();
    }

    //Execute the program in one step
    private void executeProgram() throws Exception {
        ctrl.allSteps();
    }

    //Creat the main program , and will put it into the Repository
    private void loadProgram() {
        ctrl.loadProgram(new CompoundStatement(
                new CompoundStatement(
                    new AssignStatement("v", new ConstantExpression(10)),
                    new NewStatement("a", new ConstantExpression(22))
                ),
                new CompoundStatement(
                        new ForkStatement(new CompoundStatement(
                                new CompoundStatement(new wHStatement("a", new ConstantExpression(30)), new AssignStatement("v", new ConstantExpression(32))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new rHExpression("a"))))
                        ),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new rHExpression("a")))
                )));
    }

    //Creates a new statement, it will be caled recursivly evry time
    //the user want's to create a new statament for a program
    private MyStatement newStatement() {
        while (true) {
            printStatementMenu();
            System.out.print("Enter choice: ");
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            if (fromFile)
                System.out.println(Integer.toString(choice));
            if (choice == 1) {
                System.out.println("First Statement:");
                MyStatement first = newStatement();
                System.out.println("Second Statement:");
                MyStatement second = newStatement();
                return new CompoundStatement(first, second);
            }
            if (choice == 2) {
                System.out.print("Variable name: ");
                String name = keyboard.nextLine();
                if (fromFile)
                    System.out.println(name);
                System.out.println("Assigned value: ");
                Expression value = newExpression();
                return new AssignStatement(name, value);
            }
            if (choice == 3) {
                System.out.println("Expression: ");
                Expression expression = newExpression();
                return new PrintStatement(expression);
            }
            if (choice == 4) {
                System.out.println("Condition: ");
                Expression condition = newExpression();
                System.out.println("Then branch: ");
                MyStatement thenStatement = newStatement();
                System.out.println("Else branch: ");
                MyStatement elseStatement = newStatement();
                return new IfStatement(condition, thenStatement, elseStatement);
            }
            if (choice == 5) {
                System.out.println("Expression: ");
                Expression exp = newExpression();
                return new IncrementStatement(exp);
            }
            if (choice == 6) {
                System.out.println("Expression: ");
                Expression exp = newExpression();
                return new DecrementStatement(exp);
            }

            if (choice == 7) {
                System.out.println("Expression: ");
                Expression exp = newExpression();
                System.out.println("Statement: ");
                MyStatement thenStatement = newStatement();
                return new WhileStatement(exp, thenStatement);

            }
            if (choice == 8) {
                System.out.println("Expression: ");
                Expression exp = newExpression();
                System.out.println("Expression1: ");
                Expression e1 = newExpression();
                System.out.println("Statement1: ");
                MyStatement s1 = newStatement();
                System.out.println("Expression2: ");
                Expression e2 = newExpression();
                System.out.println("Statement2: ");
                MyStatement s2 = newStatement();
                return new SwitchStatement(exp, e1, s1, e2, s2);
            }
            if (choice == 9) {
                System.out.println("Expression: ");
                Expression e = newExpression();
                System.out.println("Statement: ");
                MyStatement s = newStatement();
                return new IfThenStatement(e, s);

            }
            if (choice == 10) {
                System.out.println("Skip ");
                return new SkipStatement();
            }
            if (choice == 11) {
                System.out.println("Var name: ");
                String l = keyboard.nextLine();
                System.out.println("Expression: ");
                Expression e = newExpression();
                return new NewStatement(l, e);
            }
            if (choice == 12) {
                System.out.println("Var name: ");
                String l = keyboard.nextLine();
                System.out.println("Expression: ");
                Expression e = newExpression();
                return new wHStatement(l, e);
            }
            if (choice == 13) {
                System.out.println("Statement: ");
                MyStatement statement = newStatement();
                return new ForkStatement(statement);
            }

            System.out.print("Choice " + Integer.toString(choice) + " is invalid!");
        }
    }

    //Creates Expressions wich are used by the created statments
    //The function is called recursevly every time is neded
    private Expression newExpression() {
        while (true) {
            printExpressionMenu();
            System.out.print("Enter choice: ");
            try {
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                if (fromFile)
                    System.out.println(Integer.toString(choice));
                if (choice == 1) {
                    System.out.print("Enter operator (+, -, *): ");
                    String operator = keyboard.nextLine();
                    if (fromFile)
                        System.out.println(operator);
                    System.out.println("Left Expression: ");
                    Expression left = newExpression();
                    System.out.println("Right Expression: ");
                    Expression right = newExpression();
                    return new ArithmeticExpression(left, right, operator);
                }
                if (choice == 2) {
                    System.out.print("Enter constant value: ");
                    int value = keyboard.nextInt();
                    keyboard.nextLine();
                    if (fromFile)
                        System.out.println(Integer.toString(value));
                    return new ConstantExpression(value);
                }
                if (choice == 3) {
                    System.out.print("Enter variable name: ");
                    String name = keyboard.nextLine();
                    if (fromFile)
                        System.out.println(name);
                    return new VariableExpression(name);
                }
                if (choice == 4) {
                    System.out.print("ReadExpression will be executed \n");
                    return new ReadExpression();
                }
                if (choice == 5) {
                    System.out.print("Enter operator (&,|,!): ");
                    String op = keyboard.nextLine();
                    System.out.println("Expression1: ");
                    Expression left1 = newExpression();
                    System.out.println("Expression2: ");
                    Expression right2 = newExpression();
                    return new BoolExpression(left1, right2, op);
                }
                if (choice == 6) {
                    System.out.print("Enter operator (<,>,>=,<=,!=): ");
                    String l = keyboard.nextLine();
                    System.out.println("Expression1: ");
                    Expression left1 = newExpression();
                    System.out.println("Expression2: ");
                    Expression right2 = newExpression();
                    return new RelationalExpression(left1, right2, l);
                }
                if (choice == 7) {
                    System.out.println("Enter var name");
                    String l = keyboard.nextLine();
                    return new rHExpression(l);
                }
                System.out.print("Choice " + Integer.toString(choice) + " is invalid!");
            } catch (Exception e) {
                System.out.println("InvalidInputException: " + e.getMessage());
            }
        }
    }

    //Print the ExpressionMenu
    private void printExpressionMenu() {
        System.out.println("1. Arithmetic Expression\n" +
                "2. Constant Expression\n" +
                "3. Variable Expression\n" +
                "4. Read Expression\n" +
                "5. Bool Expression\n" +
                "6. Relational Expression \n" +
                "7. Read Heap Expression\n");
    }

    //Print the Statement menu
    private void printStatementMenu() {
        System.out.println("1. Compound Statement\n" +
                "2. Assign Statement\n" +
                "3. Print Statement\n" +
                "4. If Statement\n" +
                "5. Increment Statement\n" +
                "6. Decrement Statement\n" +
                "7. While Statement \n" +
                "8. Switch Statement \n" +
                "9. IfThen Statement \n" +
                "10. Skip Statement\n" +
                "11. New Statement\n" +
                "12. Write Heap Statement\n" +
                "13. Fork Statement");
    }


    //Print the MainMenu
    private void printMainMenu() {
        System.out.println("1. Load Program\n" +
                "2. Execute Program\n" +
                "3. Read from file\n" +
                "0. Exit\n");
    }
}
