package ui;

import controllers.QuizController;
import controllers.QuestionController;
import controllers.AnswerController;
import repositories.QuizRepository;
import repositories.QuestionRepository;
import repositories.AnswerRepository;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Question;

public class Navigation {
    private final Stage stage;
    private final QuizController quizController;
    private final QuestionController questionController;
    private final AnswerController answerController;

    public Navigation(Stage stage) {
        this.stage = stage;
        PostgresDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "password", "quiz_app");
        this.quizController = new QuizController(new QuizRepository(db));
        this.questionController = new QuestionController(new QuestionRepository(db));
        this.answerController = new AnswerController(new AnswerRepository(db));
    }

    public void showAnswerEditor(Question question) {
        AnswerEditorScreen answerEditor = new AnswerEditorScreen(
                this,
                questionController,
                answerController,
                question
        );
        stage.setScene(new Scene(answerEditor.getLayout(), 600, 500));
    }

    public void showQuestionEditor() {
        QuestionEditorScreen questionEditor = new QuestionEditorScreen(
                this,
                quizController,
                questionController
        );
        stage.setScene(new Scene(questionEditor.getLayout(), 600, 500));
    }

    // Other navigation methods (showQuizMenu, showLogin, etc.)
}