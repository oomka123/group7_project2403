import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private Connection conn;

    public Quiz(Connection conn) {
        this.conn = conn;
    }

    public void startQuiz() {
        String query = "SELECT * FROM quizes";
        int score = 0;
        int totalQuestions = 0;

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            List<Question> questions = new ArrayList<>();
            while (rs.next()) {
                questions.add(new Question(
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                ));
            }

            Collections.shuffle(questions);

            Scanner scanner = new Scanner(System.in);

            for (Question question : questions) {
                totalQuestions++;
                System.out.println("Question " + totalQuestions + ": " + question.getText());


                List<String> options = question.getShuffledOptions();
                System.out.println("A: " + options.get(0));
                System.out.println("B: " + options.get(1));
                System.out.println("C: " + options.get(2));
                System.out.println("D: " + options.get(3));
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine().toUpperCase();

                if (userAnswer.equals(question.getCorrectOption(options))) {
                    score++;
                }
            }

            System.out.println("Quiz Completed!");
            System.out.println("Your score: " + score + " out of " + totalQuestions);

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
