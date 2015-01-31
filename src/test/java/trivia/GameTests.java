package trivia;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTests {
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
    public void canRollInvalidNumbers() throws Exception {
        game.roll(-1);
        game.roll(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void cannotPlayWhenThereAreNoPlayers() throws Exception {
        new Game().roll(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void cannotPlayWhenThereAreMoreThanFivePlayers() throws Exception {
        addPlayersTo(new Game(), 6);
    }

    @Test
    public void canPlayWhenThereAreAtMostFivePlayers() throws Exception {
        addPlayersTo(new Game(), 5);
    }

    public void addPlayersTo(Game game, int howMany) {
        for (int i = 1; i <= howMany; i++)
            game.add("Player " + i);
    }
}
