package ui;

import controllers.QuizController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class QuizMenuScreen {
    private final VBox layout = new VBox(10);

    public QuizMenuScreen(Navigation navigation, QuizController quizController) {
        Button createQuizButton = new Button("Create Quiz");
        Button attemptQuizButton = new Button("Take a Quiz");
        Button manageQuestionsButton = new Button("Manage Questions");
        Button logoutButton = new Button("Logout");

        createQuizButton.setOnAction(e -> navigation.showQuizEditor());
        attemptQuizButton.setOnAction(e -> navigation.showQuizAttempt());
        manageQuestionsButton.setOnAction(e -> navigation.showQuestionEditor());
        logoutButton.setOnAction(e -> navigation.showLoginScreen());

        layout.getChildren().addAll(createQuizButton, attemptQuizButton, manageQuestionsButton, logoutButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    }

    public VBox getLayout() {
        return layout;
    }
}
