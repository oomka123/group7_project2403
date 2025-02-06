package repositories;

import database.PostgresDB1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizAttemptRepository {
    private final PostgresDB1 db;

    public QuizAttemptRepository(PostgresDB1 db) {
        this.db = db;
    }

    public void recordAttempt(int userId, int quizId, int score) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO quiz_attempts (user_id, quiz_id, score) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error recording quiz attempt: " + e.getMessage());
        }
    }
}
