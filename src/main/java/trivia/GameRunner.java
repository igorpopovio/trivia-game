package trivia;

import java.util.Random;


public class GameRunner {
    public static Random rand = new Random();

    public static void main(String[] args) {
        Game aGame = new Game();
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {
            aGame.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7)
                aGame.wrongAnswer();
            else
                aGame.wasCorrectlyAnswered();

            aGame.advanceToNextPlayer();
        } while (!aGame.isOver());
    }
}
