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
}
