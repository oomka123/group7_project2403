import Controllers.*;
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

            QuizController quizController = new QuizController(connection);
            quizController.listQuizzes();

            System.out.print("Enter the Quiz ID you want to take: ");
            Scanner scanner = new Scanner(System.in);
            int quizId = scanner.nextInt();

            Quiz quiz = new Quiz(connection);
            quiz.startQuiz(quizId);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
