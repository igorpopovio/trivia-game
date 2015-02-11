package trivia;

import java.util.Random;


public class GameRunner {
    public static Random rand = new Random();

    public static void main(String[] args) {
        Game aGame = new Game(rand);
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");
        aGame.start();
    }
}
