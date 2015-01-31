package trivia;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTests {

    @Test
    public void canCreateGame() throws Exception {
        new Game();
    }

    @Test
    public void shouldWinCoinsWhenAnsweringCorrectly() throws Exception {
        Game game = new Game();
        game.add("Igor");

        game.roll(0);
        game.wasCorrectlyAnswered();

        assertThat(game.getCoinsForCurrentPlayer()).isEqualTo(1);
    }
}
