package ui;

import controllers.QuestionController;
import controllers.QuizController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Question;
import models.Quiz;
import java.util.List;

public class QuestionEditorScreen {
    private final VBox layout = new VBox(10);

    public QuestionEditorScreen(Navigation navigation,
                                QuizController quizController,
                                QuestionController questionController) {
        // UI Components
        Label titleLabel = new Label("Manage Questions");
        ComboBox<Quiz> quizComboBox = new ComboBox<>();
        ListView<Question> questionListView = new ListView<>();
        TextField questionTextField = new TextField();
        Button addQuestionButton = new Button("Add Question");
        Button deleteQuestionButton = new Button("Delete Question");
        Button manageAnswersButton = new Button("Manage Answers");
        Button backButton = new Button("Back");

        // Load quizzes
        List<Quiz> quizzes = quizController.showQuizzes(1); // Replace with actual user ID
        quizComboBox.getItems().addAll(quizzes);

        // Event Handlers
        quizComboBox.setOnAction(e -> {
            Quiz selectedQuiz = quizComboBox.getValue();
            if (selectedQuiz != null) {
                questionListView.getItems().setAll(
                        questionController.getQuestionsByQuiz(selectedQuiz.getQuizId())
                );
            }
        });

        manageAnswersButton.setOnAction(e -> {
            Question selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
            if (selectedQuestion != null) {
                navigation.showAnswerEditor(selectedQuestion);
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a question first!").show();
            }
        });

        // Layout
        layout.getChildren().addAll(
                titleLabel,
                new Label("Select Quiz:"),
                quizComboBox,
                new Label("Questions:"),
                questionListView,
                new Label("Question Text:"),
                questionTextField,
                addQuestionButton,
                deleteQuestionButton,
                manageAnswersButton,
                backButton
        );
        layout.setPadding(new Insets(20));
    }

    public VBox getLayout() {
        return layout;
    }
}