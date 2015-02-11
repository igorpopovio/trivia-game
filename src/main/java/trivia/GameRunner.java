package trivia;

import java.util.Random;


public class GameRunner {
    public static Random rand = new Random();

    public static void main(String[] args) {
        new Game(rand).add("Chet").add("Pat").add("Sue").start();
    }
}
