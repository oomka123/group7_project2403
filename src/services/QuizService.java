package services;

import models.Quiz;
import repositories.QuizRepository;

import java.util.List;

public class QuizService {

    private final QuizRepository quizRepo;

    public QuizService(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }

    public String createQuiz(Quiz quiz) {
        if (quiz.getQuizName() == null || quiz.getQuizName().trim().isEmpty()) {
            return "Quiz name cannot be empty.";
        }

        boolean success = quizRepo.quizCreation(quiz, quiz.getUserId());
        return success ? "Quiz created successfully!" : "Failed to create quiz.";
    }

    public List<Quiz> getQuizzesByUser(int userId) {
        return quizRepo.quizShowing(userId);
    }

    public String deleteQuiz(int quizId) {
        if (quizId <= 0) {
            return "Invalid quiz ID.";
        }

        boolean success = quizRepo.quizDelete(quizId);
        return success ? "Quiz deleted successfully!" : "Failed to delete quiz.";
    }
}
