package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizController {
    private final Connection connection;

    public QuizController(Connection connection) {
        this.connection = connection;
    }

    public void createQuiz(String name) throws SQLException {
        String query = "INSERT INTO quizzes (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public void listQuizzes() throws SQLException {
        String query = "SELECT * FROM quizzes";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("Quiz ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }
}