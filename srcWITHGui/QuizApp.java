package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class QuizApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Navigation navigation = new Navigation(primaryStage);
        navigation.showLoginScreen(); // Start with the login screen
    }

    public static void main(String[] args) {
        launch(args);
    }
}
