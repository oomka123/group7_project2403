import controllers.AuthController;
import controllers.QuizController;
import controllers.QuestionController;

import database.PostgresDB;

import repositories.UserRepository;
import repositories.QuizRepository;
import repositories.QuestionRepository;

public class Main {
    public static void main(String[] args) {
        String host = "jdbc:postgresql://localhost:5432";
        String username = "postgres";
        String password = "0000";
        String dbName = "quiz_app";

        PostgresDB db = new PostgresDB(host, username, password, dbName);

        UserRepository userRepo = new UserRepository(db);
        QuizRepository quizRepo = new QuizRepository(db);
        QuestionRepository questionRepo = new QuestionRepository(db);

        AuthController authController = new AuthController(userRepo);
        QuizController quizController = new QuizController(quizRepo);
        QuestionController questionController = new QuestionController(questionRepo);

        MyApplication app = new MyApplication(authController, quizController, questionController);
        app.start();

    }
}
