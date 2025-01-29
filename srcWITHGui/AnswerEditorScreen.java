package ui;

import controllers.AnswerController;
import controllers.QuestionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Answer;
import models.Question;
import java.util.ArrayList;
import java.util.List;

public class AnswerEditorScreen {
    private final VBox layout = new VBox(10);
    private final List<HBox> answerRows = new ArrayList<>();
    private final QuestionController questionController;
    private final AnswerController answerController;
    private final ComboBox<Question> questionComboBox = new ComboBox<>();

    public AnswerEditorScreen(Navigation navigation,
                              QuestionController questionController,
                              AnswerController answerController,
                              Question selectedQuestion) {
        this.questionController = questionController;
        this.answerController = answerController;

        // UI Components
        Label titleLabel = new Label("Manage Answer Options");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        questionComboBox.setPromptText("Select Question");
        questionComboBox.setPrefWidth(300);

        VBox answerInputContainer = new VBox(5);
        Button addAnswerButton = new Button("Add New Answer Field");
        Button saveAllButton = new Button("Save All Answers");
        saveAllButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        ListView<Answer> existingAnswersList = new ListView<>();
        existingAnswersList.setPrefHeight(200);

        Button backButton = new Button("Back to Questions");
        backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        // Initialize with one empty field
        addAnswerRow(answerInputContainer);

        // Load questions and preselect
        refreshQuestions();
        if (selectedQuestion != null) {
            questionComboBox.getSelectionModel().select(selectedQuestion);
        }
        refreshAnswers(existingAnswersList);

        // Event Handlers
        questionComboBox.setOnAction(e -> refreshAnswers(existingAnswersList));
        addAnswerButton.setOnAction(e -> addAnswerRow(answerInputContainer));
        saveAllButton.setOnAction(e -> saveAnswers(existingAnswersList, answerInputContainer));
        backButton.setOnAction(e -> navigation.showQuestionEditor());

        // Layout
        layout.getChildren().addAll(
                titleLabel,
                new Label("Select Question:"),
                questionComboBox,
                new Separator(),
                new Label("Add New Answers:"),
                answerInputContainer,
                addAnswerButton,
                saveAllButton,
                new Separator(),
                new Label("Existing Answers:"),
                existingAnswersList,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    }

    // Helper Methods
    private void addAnswerRow(VBox container) {
        HBox row = new HBox(10);
        TextField answerField = new TextField();
        answerField.setPromptText("Answer text");
        answerField.setPrefWidth(200);

        CheckBox correctCheckbox = new CheckBox("Correct Answer");

        Button removeBtn = new Button("×");
        removeBtn.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        removeBtn.setOnAction(e -> {
            container.getChildren().remove(row);
            answerRows.remove(row);
        });

        row.getChildren().addAll(answerField, correctCheckbox, removeBtn);
        container.getChildren().add(row);
        answerRows.add(row);
    }

    private void refreshQuestions() {
        questionComboBox.getItems().clear();
        List<Question> questions = questionController.getQuestionsByQuiz(1); // Replace with actual quiz ID
        questionComboBox.getItems().addAll(questions);
    }

    private void refreshAnswers(ListView<Answer> listView) {
        Question selected = questionComboBox.getValue();
        if (selected != null) {
            listView.getItems().clear();
            listView.getItems().addAll(
                    answerController.getAnswersByQuestion(selected.getQuestionId())
            );
        }
    }

    private void saveAnswers(ListView<Answer> listView, VBox container) {
        Question selectedQuestion = questionComboBox.getValue();
        if (selectedQuestion == null) {
            showAlert("Error", "No question selected!");
            return;
        }

        List<Answer> answersToSave = new ArrayList<>();
        for (HBox row : answerRows) {
            TextField textField = (TextField) row.getChildren().get(0);
            CheckBox checkBox = (CheckBox) row.getChildren().get(1);

            if (!textField.getText().isEmpty()) {
                answersToSave.add(new Answer(
                        0, // Temporary ID
                        textField.getText(),
                        checkBox.isSelected(),
                        selectedQuestion.getQuestionId()
                ));
            }
        }

        if (answersToSave.isEmpty()) {
            showAlert("Error", "No answers entered!");
            return;
        }

        try {
            for (Answer answer : answersToSave) {
                if (!answerController.addAnswer(answer)) {
                    showAlert("Error", "Failed to save answer: " + answer.getAnswerText());
                    return;
                }
            }
            showAlert("Success", "Saved " + answersToSave.size() + " answers!");
            refreshAnswers(listView);
            container.getChildren().clear();
            answerRows.clear();
            addAnswerRow(container); // Reset to one empty field
        } catch (Exception e) {
            showAlert("Database Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public VBox getLayout() {
        return layout;
    }
}