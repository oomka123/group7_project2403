package controllers;

import models.Answer;
import repositories.AnswerRepository;
import java.util.List;

public class AnswerController {
    private final AnswerRepository answerRepo;

    public AnswerController(AnswerRepository answerRepo) {
        this.answerRepo = answerRepo;
    }

    public boolean addAnswer(Answer answer) {
        return answerRepo.addAnswer(answer);
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        return answerRepo.getAnswersByQuestion(questionId);
    }

    public boolean deleteAnswer(int answerId) {
        return answerRepo.deleteAnswer(answerId);
    }
}