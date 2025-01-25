import java.util.List;

public class Question {
    private final int id;
    private final String text;
    private final String correctOption;
    private final List<String> options;

    public Question(int id, String text, String correctOption, List<String> options) {
        this.id = id;
        this.text = text;
        this.correctOption = correctOption;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctOption);
    }
}