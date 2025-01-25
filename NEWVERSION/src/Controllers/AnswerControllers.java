package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerController {
    private final Connection connection;

    public AnswerController(Connection connection) {
        this.connection = connection;
    }

    public void saveAnswer(int quizId, int questionId, char userAnswer, boolean isCorrect) throws SQLException {
        String query = "INSERT INTO answers (quiz_id, question_id, user_answer, is_correct) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quizId);
            ps.setInt(2, questionId);
            ps.setString(3, String.valueOf(userAnswer));
            ps.setBoolean(4, isCorrect);
            ps.executeUpdate();
        }
    }
    public void getResults(int quizId) {
        String query = "SELECT * FROM answers WHERE quiz_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("No results found for quiz ID: " + quizId);
            } else {
                do {
                    System.out.println("Question ID: " + rs.getInt("question_id") +
                            ", Your Answer: " + rs.getString("user_answer") +
                            ", Correct: " + rs.getBoolean("is_correct"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
