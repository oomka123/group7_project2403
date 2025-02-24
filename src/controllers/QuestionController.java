package controllers;

import controllers.Icontollers.IQuestionController;
import models.Question;
import services.Iservices.IQuestionService;

import java.util.List;

public class QuestionController implements IQuestionController {

    private final IQuestionService questionService;

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestionsByQuiz(int quizId) {
        try {
            return questionService.getQuestionsByQuiz(quizId);
        } catch (Exception e) {
            System.out.println("Error fetching questions: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public String addQuestion(String questionText, int quizId) {
        return questionService.addQuestion(questionText, quizId);
    }

    @Override
    public String deleteQuestion(int questionId) {
        return questionService.deleteQuestion(questionId);
    }
}