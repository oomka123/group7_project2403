package repositories;

import database.PostgresDB;
import models.Quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    private final PostgresDB db;

    public QuizRepository(PostgresDB db) {
        this.db = db;
    }

    public List<Quiz> showQuizzes(int userId) {
        List<Quiz> quizzes = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT quiz_id, quiz_name FROM quizzes WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                quizzes.add(new Quiz(
                        rs.getInt("quiz_id"),
                        userId,
                        rs.getString("quiz_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching quizzes: " + e.getMessage());
        }
        return quizzes;
    }
}