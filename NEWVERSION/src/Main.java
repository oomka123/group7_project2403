import Controllers.AuthController;
import Controllers.QuizController;
import Database.JDBCConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (connection == null) {
                System.out.println("Failed to connect to the database.");
                return;
            }

            AuthController authController = new AuthController();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Welcome! Please choose an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. List Quizzes");
                System.out.println("4. Take a Quiz");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter your name: ");
                        String regName = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String regPassword = scanner.nextLine();

                        if (authController.register(regName, regPassword)) {
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Registration failed. Try again.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter your name: ");
                        String loginName = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();

                        if (authController.login(loginName, loginPassword)) {
                            System.out.println("Login successful! Welcome, " + loginName);
                        } else {
                            System.out.println("Login failed. Check your credentials.");
                        }
                        break;

                    case 3:
                        QuizController quizController = new QuizController(connection);
                        quizController.listQuizzes();
                        break;

                    case 4:
                        System.out.print("Enter the Quiz ID you want to take: ");
                        int quizId = scanner.nextInt();
                        scanner.nextLine();

                        Quiz quiz = new Quiz(connection);
                        quiz.startQuiz(quizId);
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
