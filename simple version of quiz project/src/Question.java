import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String text;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final String correctAnswer;

    public Question(String text, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public List<String> getShuffledOptions() {
        List<String> options = new ArrayList<>(Arrays.asList(optionA, optionB, optionC, optionD));
        Collections.shuffle(options);
        return options;
    }

    public String getCorrectOption(List<String> shuffledOptions) {
        String correctText = switch (correctAnswer.toLowerCase()) {
            case "a" -> optionA;
            case "b" -> optionB;
            case "c" -> optionC;
            case "d" -> optionD;
            default -> null;
        };

        for (int i = 0; i < shuffledOptions.size(); i++) {
            if (shuffledOptions.get(i).equalsIgnoreCase(correctText)) {
                return String.valueOf((char) ('A' + i)); // Return "A", "B", "C", or "D"
            }
        }
        return null;
    }
}