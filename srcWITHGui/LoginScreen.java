package ui;

import controllers.AuthController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginScreen {
    private final VBox layout = new VBox(10);
    private final Navigation navigation;

    public LoginScreen(Navigation navigation, AuthController authController) {
        this.navigation = navigation;

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authController.loginUser(username, password) != null) {
                navigation.showQuizMenu(); // Navigate to quiz menu
            } else {
                messageLabel.setText("Invalid login credentials.");
            }
        });

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String result = authController.registerUser(username, password);
            messageLabel.setText(result);
        });

        layout.getChildren().addAll(usernameField, passwordField, loginButton, registerButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    }

    public VBox getLayout() {
        return layout;
    }
}
