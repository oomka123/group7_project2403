import controllers.AuthController;
import controllers.QuizController;

import models.*;

import java.util.*;

public class MyApplication {
    private final AuthController authController;
    private final QuizController quizController;
    private final Scanner scanner;
    private User currentUser;

    public MyApplication(AuthController authController, QuizController quizController) {
        this.scanner = new Scanner(System.in);
        this.authController = authController;
        this.quizController = quizController;
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
        System.out.println("1. Start Quiz(not working)");
        System.out.println("2. Create Quiz");
        System.out.println("3. Show Quizzes");
        System.out.println("4. Delete Quizzes");
        System.out.println("5. Manage Questions(not working)");
        System.out.println("0. Logout");
        System.out.println();
        System.out.print("Enter option (0-5): ");
    }

    private void questionMenu() {
        System.out.println("\n=== Question Menu ===");
        System.out.println("1. View Questions");
        System.out.println("2. Add Question");
        System.out.println("3. Delete Question");
        System.out.println("4. Manage Answers");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
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
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer. " + e.getMessage());
                scanner.next();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    private void quizMenuHandler() {
        boolean inQuizMenu = true;

        while (inQuizMenu) {
            quizMenu();

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> System.out.println("Not working");
                    case 2 -> createQuiz();
                    case 3 -> showQuizzes();
                    case 4 -> deleteQuiz();
                    case 5 -> System.out.println("Not working");
                    case 0 -> {
                        System.out.println("\n*************************\n");
                        System.out.println("Logging out...");
                        System.out.println("\n*************************\n");
                        currentUser = null;
                        inQuizMenu = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer: " + e.getMessage());
                scanner.next();
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
                    quizMenuHandler();
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
                quizMenuHandler();
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }



    private void createQuiz() {
        if (currentUser == null) {
            System.out.println("You must be logged in to create a quiz.");
            return;
        }

        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();

        String response = quizController.createQuiz(quizName, currentUser.getUser_id());
        System.out.println(response);
    }

    private void showQuizzes() {
        if (currentUser == null) {
            System.out.println("You must be logged in to view quizzes.");
            return;
        }

        List<Quiz> quizzes = quizController.showQuizzes(currentUser.getUser_id());
        System.out.println("Your Quizzes:");
        quizzes.forEach(quiz -> System.out.println(quiz.getQuizName()));
    }

    public void deleteQuiz() {
        System.out.println("Select quiz to delete:");

        List<Quiz> quizzes = quizController.showQuizzes(currentUser.getUser_id());
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available to delete.");
            return;
        }


        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getQuizName());
        }

        try {
            System.out.print("Enter the number of the quiz to delete: ");
            int quizNumber = scanner.nextInt();
            scanner.nextLine();

            if (quizNumber > 0 && quizNumber <= quizzes.size()) {
                int quizId = quizzes.get(quizNumber - 1).getQuizId();
                String response = quizController.deleteQuiz(quizId);
                System.out.println(response);
            } else {
                System.out.println("Invalid quiz number. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be an integer: " + e.getMessage());
            scanner.next();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
