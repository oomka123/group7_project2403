package controllers;

import models.Answer;
import services.AnswerService;

import java.util.List;

public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        return answerService.getAnswersByQuestion(questionId);
    }

    public boolean addAnswer(Answer answer) {
        return answerService.addAnswer(answer);
    }

    public boolean deleteAnswer(int answerId) {
        return answerService.deleteAnswer(answerId);
    }

    public boolean updateAnswer(Answer answer) {
        return answerService.updateAnswer(answer);
    }
}