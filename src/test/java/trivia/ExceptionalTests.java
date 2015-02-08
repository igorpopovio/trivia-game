package trivia;

import org.junit.Before;
import org.junit.Test;


public class ExceptionalTests {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.add("Igor");
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

    @Test
    public void canPlayWhenThereAreAtMostFivePlayers() throws Exception {
        addPlayersTo(new Game(), 5);
    }

    public void addPlayersTo(Game game, int howMany) {
        for (int i = 1; i <= howMany; i++)
            game.add("Player " + i);
    }
}
