package controllers.Icontollers;

import models.Answer;
import java.util.List;

public interface IAnswerController {

    List<Answer> getAnswersByQuestion(int questionId);

    boolean addAnswer(Answer answer);

    boolean deleteAnswer(int answerId);

    boolean updateAnswer(Answer answer);
}