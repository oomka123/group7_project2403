package repositories;

import database.PostgresDB1;
import models.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    private final PostgresDB1 db;

    public QuestionRepository(PostgresDB1 db) {
        this.db = db;
    }

    public List<Question> getQuestionsByQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT question_id, question_text FROM questions WHERE quiz_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, quizId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("question_id"),
                        quizId,
                        rs.getString("question_text")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching questions: " + e.getMessage());
        }
        return questions;
    }
}