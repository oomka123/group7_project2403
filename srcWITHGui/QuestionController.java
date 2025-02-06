package controllers;

import models.Question;
import repositories.QuestionRepository;

public class QuestionController {
    private final QuestionRepository questionRepo;

    public QuestionController(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    public List<Question> getQuestionsByQuiz(int quizId) {
        return questionRepo.getQuestionsByQuiz(quizId);
    }

    public String addQuestion(Question question) {
        boolean success = questionRepo.addQuestion(question);
        return success ? "Question added!" : "Failed to add question.";
    }

    public String deleteQuestion(int questionId) {
        boolean success = questionRepo.deleteQuestion(questionId);
        return success ? "Question deleted!" : "Failed to delete question.";
    }
}