package trivia;

public class Question {
    private String category;
    private String question;
    private String correctAnswer;

    public Question(String category, String question, String correctAnswer) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return question;
    }

    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }
}
