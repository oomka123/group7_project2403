package services;

import models.Answer;
import repositories.AnswerRepository;

import java.util.List;

public class AnswerService {

    private final AnswerRepository answerRepo;

    public AnswerService(AnswerRepository answerRepo) {
        this.answerRepo = answerRepo;
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        if (questionId <= 0) {
            throw new IllegalArgumentException("Question ID must be greater than 0.");
        }
        return answerRepo.getAnswersByQuestion(questionId);
    }

    public boolean addAnswer(Answer answer) {
        if (answer == null || answer.getAnswerText() == null || answer.getAnswerText().trim().isEmpty()) {
            System.out.println("Answer text cannot be null or empty.");
            return false;
        }
        if (answer.getQuestionId() <= 0) {
            System.out.println("Invalid question ID.");
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
        if (answer == null) {
            System.out.println("Answer cannot be null.");
            return false;
        }
        if (answer.getAnswerId() <= 0) {
            System.out.println("Invalid answer ID.");
            return false;
        }
        if (answer.getAnswerText() == null || answer.getAnswerText().trim().isEmpty()) {
            System.out.println("Answer text cannot be null or empty.");
            return false;
        }

        return answerRepo.updateAnswer(answer);
    }
}
