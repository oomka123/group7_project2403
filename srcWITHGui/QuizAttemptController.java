package controllers;

import repositories.QuizAttemptRepository;

public class QuizAttemptController {
    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptController(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public void saveQuizAttempt(int userId, int quizId, int score) {
        quizAttemptRepository.recordAttempt(userId, quizId, score);
    }
}
