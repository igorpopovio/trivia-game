package trivia;

import java.util.Random;

public class AiPlayer extends Player {
    private static final String INCORRECT_ANSWER = "incorrect answer";
    private final Random random;

    public AiPlayer(String name, Random random) {
        super(name);
        this.random = random;
    }

    @Override
    public String answerQuestion(Question question) {
        return random.nextInt(9) == 7
                ? INCORRECT_ANSWER
                : question.getCorrectAnswer();
    }
}
