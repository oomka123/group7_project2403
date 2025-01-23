package controllers;

import models.Answer;
import repositories.AnswerRepository;

import java.util.List;

public class AnswerController {

    private final AnswerRepository answerRepo;

    public AnswerController(AnswerRepository answerRepo) {
        this.answerRepo = answerRepo;
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        return answerRepo.getAnswersByQuestion(questionId);
    }

    public boolean addAnswer(Answer answer) {
        if (answer.getAnswerText() == null || answer.getAnswerText().isEmpty()) {
            System.out.println("Answer text cannot be null or empty.");
            return false;
        }

        return answerRepo.addAnswer(answer);
    }

    public boolean deleteAnswer(int answerId) {
        if (answerId <= 0) {
            System.out.println("Invalid answer ID.");
            return false;
        }

        return answerRepo.deleteAnswer(answerId);
    }

    public boolean updateAnswer(Answer answer) {
        if (answer.getAnswerId() <= 0) {
            System.out.println("Invalid answer ID.");
            return false;
        }
        if (answer.getAnswerText() == null || answer.getAnswerText().isEmpty()) {
            System.out.println("Answer text cannot be null or empty.");
            return false;
        }

        return answerRepo.updateAnswer(answer);
    }
}
