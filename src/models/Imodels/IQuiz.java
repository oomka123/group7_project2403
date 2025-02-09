package models.Imodels;

public interface IQuiz {
    int getQuizId();
    void setQuizId(int quizId);

    String getQuizName();
    void setQuizName(String quizName);

    String getCategory();
    void setCategory(String category);
}
