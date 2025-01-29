package ui;

import controllers.AnswerController;
import controllers.QuestionController;
import controllers.QuizController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Answer;
import models.Question;
import models.Quiz;
import java.util.List;

public class QuizAttemptScreen {
    private final VBox layout = new VBox(10);
    private int currentQuestionIndex = 0;
    private List<Question> questions;
    private int correctAnswers = 0;

    public QuizAttemptScreen(Navigation navigation, QuizController quizController, QuestionController questionController, AnswerController answerController) {
        Label selectQuizLabel = new Label("Select a Quiz:");
        ComboBox<Quiz> quizComboBox = new ComboBox<>();
        Button startQuizButton = new Button("Start Quiz");
        Label questionLabel = new Label();
        ToggleGroup answerGroup = new ToggleGroup();
        VBox answerBox = new VBox(5);
        Button submitAnswerButton = new Button("Submit Answer");
        Button backButton = new Button("Back");

        List<Quiz> quizzes = quizController.showQuizzes(1);
        quizComboBox.getItems().addAll(quizzes);

        startQuizButton.setOnAction(e -> {
            Quiz selectedQuiz = quizComboBox.getSelectionModel().getSelectedItem();
            if (selectedQuiz != null) {
                questions = questionController.getQuestionsByQuiz(selectedQuiz.getQuizId());
                currentQuestionIndex = 0;
                correctAnswers = 0;
                showNextQuestion(questionLabel, answerBox, answerGroup, answerController);
            }
        });

        submitAnswerButton.setOnAction(e -> {
            if (questions != null && currentQuestionIndex < questions.size()) {
                Question currentQuestion = questions.get(currentQuestionIndex);
                RadioButton selectedRadio = (RadioButton) answerGroup.getSelectedToggle();

                if (selectedRadio != null) {
                    String selectedAnswerText = selectedRadio.getText();
                    List<Answer> answers = answerController.getAnswersByQuestion(currentQuestion.getQuestionId());
                    for (Answer answer : answers) {
                        if (answer.getAnswerText().equals(selectedAnswerText) && answer.isCorrectAnswer()) {
                            correctAnswers++;
                            break;
                        }
                    }
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showNextQuestion(questionLabel, answerBox, answerGroup, answerController);
                } else {
                    showQuizResult(navigation);
                }
            }
        });

        backButton.setOnAction(e -> navigation.showQuizMenu());

        layout.getChildren().addAll(selectQuizLabel, quizComboBox, startQuizButton, questionLabel, answerBox, submitAnswerButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    }

    private void showNextQuestion(Label questionLabel, VBox answerBox, ToggleGroup answerGroup, AnswerController answerController) {
        if (questions != null && currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText("Question: " + currentQuestion.getQuestionText());
            answerBox.getChildren().clear();
            answerGroup.getToggles().clear();

            List<Answer> answers = answerController.getAnswersByQuestion(currentQuestion.getQuestionId());
            for (Answer answer : answers) {
                RadioButton radioButton = new RadioButton(answer.getAnswerText());
                radioButton.setToggleGroup(answerGroup);
                answerBox.getChildren().add(radioButton);
            }
        }
    }

    private void showQuizResult(Navigation navigation) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You scored " + correctAnswers + " correct answers!");
        alert.showAndWait();
        navigation.showQuizMenu();
    }

    public VBox getLayout() {
        return layout;
    }
}
