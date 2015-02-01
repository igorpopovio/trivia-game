package trivia;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HappyPathTests {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.add("Igor");
    }

    @Test
    public void shouldWinCoinsWhenProvidingCorrectAnswer() throws Exception {
        game.roll(1);
        game.wasCorrectlyAnswered();

        assertThat(game.getCoinsForCurrentPlayer()).isEqualTo(1);
    }

    @Test
    public void shouldNotWinCoinsWhenProvidingWrongAnswer() throws Exception {
        game.roll(1);
        game.wrongAnswer();

        assertThat(game.getCoinsForCurrentPlayer()).isEqualTo(0);
    }

    @Test
    public void shouldEndGameWhenPlayerProvidesSixCorrectAnswers() throws Exception {
        giveCorrectAnswers(6);

        assertThat(game.isOver()).isTrue();
    }

    @Test
    public void shouldContinueGameWhenPlayerProvidedOnlyFiveCorrectAnswers() throws Exception {
        giveCorrectAnswers(5);

        assertThat(game.isOver()).isFalse();
    }

    @Test
    public void shouldWinCoinsWhenGettingOutOfPenaltyBox() throws Exception {
        // move in penalty box by giving a wrong answer
        game.roll(1);
        game.wrongAnswer();

        // make him get out of penalty box (odd roll + correct answer)
        game.roll(1);
        game.wasCorrectlyAnswered();

        assertThat(game.getCoinsForCurrentPlayer()).isEqualTo(1);
    }

    @Test
    public void shouldNotWinAnyCoinsWhenRemainingInPenaltyBox() throws Exception {
        // move in penalty box by giving a wrong answer
        game.roll(1);
        game.wrongAnswer();

        // make him stay in the penalty box (even roll + wrong answer)
        game.roll(2);
        game.wrongAnswer();

        assertThat(game.getCoinsForCurrentPlayer()).isEqualTo(0);
    }

    private void giveCorrectAnswers(int howMany) {
        for (int i = 1; i <= howMany; i++)
            game.wasCorrectlyAnswered();
    }
}
