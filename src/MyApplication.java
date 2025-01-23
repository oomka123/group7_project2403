import controllers.AuthController;


import models.*;

import java.util.*;

public class MyApplication {
    private final AuthController authController;
    private final Scanner scanner;
    private User currentUser;

    public MyApplication(AuthController authController) {
        this.scanner = new Scanner(System.in);
        this.authController = authController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Quiz Application");
        System.out.println("Select option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (0-2): ");
    }

    private void quizMenu() {
        System.out.println();
        System.out.println("Quiz Menu");
        System.out.println("Select option:");
        System.out.println("1. Start Quiz");
        System.out.println("2. Create Quiz");
        System.out.println("3. Show Quizzes");
        System.out.println("4. Delete Quizzes");
        System.out.println("5. Manage Questions");
        System.out.println("0. Logout");
        System.out.println();
        System.out.print("Enter option (0-5): ");
    }



    public void start() {
        boolean running = true;

        while (running) {
            mainMenu();

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> getRegistration();
                    case 2 -> {
                        getLogin();
                        if (currentUser != null) {
                            quizMenu();
                        }
                    }
                    case 0 -> {
                        System.out.println("\n*************************\n");
                        System.out.println("Goodbye!");
                        System.out.println("\n*************************\n");
                        running = false;
                    } // Завершение работы приложения
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer. " + e.getMessage());
                scanner.next(); // Очистка неверного ввода
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println("*************************");
        }
    }


    public void getRegistration() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String response = authController.registerUser(username, password);
            System.out.println(response);

            if (response.equals("Registration successful!")) {
                currentUser = authController.loginUser(username, password);
                if (currentUser != null) {
                    System.out.println("Registration and login successful! Welcome, " + currentUser.getUser_name());
                }
            }
        } catch (Exception e) {
            System.out.println("Error in registration: " + e.getMessage());
        }
    }

    public void getLogin() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = authController.loginUser(username, password);
            if (user != null) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + user.getUser_name());
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }

}
