import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz {
    private Connection conn;

    public Quiz(Connection conn) {
        this.conn = conn;
    }

    public void startQuiz() throws SQLException {
        String query = "SELECT * FROM quizes";
        int score = 0;
        int totalQuestions = 0;

        try(PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery()){

            Scanner scanner = new Scanner(System.in);

            while (rs.next()) {
                totalQuestions++;
                System.out.println("Question: " + totalQuestions + ": " + rs.getString("question_text"));
                System.out.println("A: " + rs.getString("option_a"));
                System.out.println("B: " + rs.getString("option_b"));
                System.out.println("C: " + rs.getString("option_c"));
                System.out.println("D: " + rs.getString("option_d"));
                System.out.print("Your answer");
                String userAnswer = scanner.nextLine().toUpperCase();

                if (userAnswer.equals(rs.getString("correct_answer"))) {
                    score++;
                }
            }

            System.out.println("Quiz Completed!");
            System.out.println("Your score: " + score + "out of " + totalQuestions);

        }catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }

    }


}
