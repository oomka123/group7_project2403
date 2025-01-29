package ui;

import controllers.QuizController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Quiz;

public class QuizEditorScreen {
    private final VBox layout = new VBox(10);

    public QuizEditorScreen(Navigation navigation, QuizController quizController) {
        TextField quizNameField = new TextField();
        quizNameField.setPromptText("Quiz Name");
        Button createQuizButton = new Button("Create Quiz");
        ListView<Quiz> quizList = new ListView<>();
        Button deleteQuizButton = new Button("Delete Selected Quiz");
        Button backButton = new Button("Back");

        createQuizButton.setOnAction(e -> {
            String quizName = quizNameField.getText();
            if (!quizName.isEmpty()) {
                String response = quizController.createQuiz(quizName, 1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
                alert.show();
                quizList.getItems().setAll(quizController.showQuizzes(1));
            }
        });

        deleteQuizButton.setOnAction(e -> {
            Quiz selectedQuiz = quizList.getSelectionModel().getSelectedItem();
            if (selectedQuiz != null) {
                String response = quizController.deleteQuiz(selectedQuiz.getQuizId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
                alert.show();
                quizList.getItems().setAll(quizController.showQuizzes(1));
            }
        });

        backButton.setOnAction(e -> navigation.showQuizMenu());

        layout.getChildren().addAll(quizNameField, createQuizButton, quizList, deleteQuizButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    }

    public VBox getLayout() {
        return layout;
    }
}
