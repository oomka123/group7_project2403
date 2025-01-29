package repositories;

import database.PostgresDB;
import models.Answer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {
    private final PostgresDB db;

    public AnswerRepository(PostgresDB db) {
        this.db = db;
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        List<Answer> answers = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT answer_id, answer_text, is_correct FROM answers WHERE question_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, questionId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                answers.add(new Answer(
                        rs.getInt("answer_id"),
                        questionId,
                        rs.getString("answer_text"),
                        rs.getBoolean("is_correct")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching answers: " + e.getMessage());
        }
        return answers;
    }
}