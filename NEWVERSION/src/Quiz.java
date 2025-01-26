import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Controllers.*;

public class Quiz {
    private final Connection connection;

    public Quiz(Connection connection) {
        this.connection = connection;
    }

    public void startQuiz(int quizId) throws SQLException {
        AnswerController answerController = new AnswerController(connection);

        List<Question> questions = loadQuestions(quizId);
        Collections.shuffle(questions);

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getText());

            List<String> shuffledOptions = new ArrayList<>(question.getOptions());
            Collections.shuffle(shuffledOptions);
            char correctLabel = ' ';
            char optionLabel = 'A';

            for (String option : shuffledOptions) {
                System.out.println(optionLabel + ": " + option);
                if (option.equals(question.getCorrectOption())) {
                    correctLabel = optionLabel;
                }
                optionLabel++;
            }

            System.out.print("Your answer: ");
            char userAnswer = scanner.nextLine().toUpperCase().charAt(0);
            boolean isCorrect = userAnswer == correctLabel;

            if (isCorrect) {
                score++;
            }

            answerController.saveAnswer(quizId, question.getId(), userAnswer, isCorrect);
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }

    private List<Question> loadQuestions(int quizId) throws SQLException {
        String questionQuery = "SELECT * FROM questions WHERE quiz_id = ?";
        String optionQuery = "SELECT option_label, option_text FROM options WHERE question_id = ?";

        List<Question> questions = new ArrayList<>();
        try (PreparedStatement questionStmt = connection.prepareStatement(questionQuery)) {
            questionStmt.setInt(1, quizId);

            try (ResultSet questionRs = questionStmt.executeQuery()) {
                while (questionRs.next()) {
                    int questionId = questionRs.getInt("id");
                    String text = questionRs.getString("question_text");
                    String correctOption = questionRs.getString("correct_option");

                    List<String> options = new ArrayList<>();
                    try (PreparedStatement optionStmt = connection.prepareStatement(optionQuery)) {
                        optionStmt.setInt(1, questionId);
                        try (ResultSet optionRs = optionStmt.executeQuery()) {
                            while (optionRs.next()) {
                                options.add(optionRs.getString("option_text"));
                            }
                        }
                    }

                    questions.add(new Question(questionId, text, correctOption, options));
                }
            }
        }
        return questions;
    }
}