package controllers;

import java.util.List;

import models.Quiz;
import services.QuizService;

public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    public List<Quiz> getQuizzesByUser(int userId) {
        try {
            return quizService.getQuizzesByUser(userId);
        } catch (Exception e) {
            System.out.println("Error fetching questions: " + e.getMessage());
            return List.of();
        }
    }

    public String createQuiz(String quizName, int userId) {
        return this.quizService.createQuiz(new Quiz(quizName, userId));
    }

    public List<Quiz> showQuizzes(int userId) {
        return quizService.getQuizzesByUser(userId);
    }

    public String deleteQuiz(int quizId) {
        return this.quizService.deleteQuiz(quizId);
    }

}