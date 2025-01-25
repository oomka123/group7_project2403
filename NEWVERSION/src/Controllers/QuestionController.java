package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionController {
    private final Connection connection;

    public QuestionController(Connection connection) {
        this.connection = connection;
    }

    public void addQuestion(int quizId, String text, String correctOption) throws SQLException {
        String query = "INSERT INTO questions (quiz_id, question_text, correct_option) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quizId);
            ps.setString(2, text);
            ps.setString(3, correctOption);
            ps.executeUpdate();
        }
    }

    public void listQuestions(int quizId) throws SQLException {
        String query = "SELECT * FROM questions WHERE quiz_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Question ID: " + rs.getInt("id") + ", Text: " + rs.getString("question_text"));
            }
        }
    }
}