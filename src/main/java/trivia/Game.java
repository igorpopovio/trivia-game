package trivia;

import com.google.common.collect.Iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static trivia.Logger.log;

public class Game {
    private List<Player> players;
    private Iterator<Player> playersIterator;
    private Player currentPlayer;

    private Board board;
    private PenaltyBox penaltyBox;
    private Random random;

    public Game(Random random) {
        this.random = random;
        this.penaltyBox = new PenaltyBox();
        this.players = new ArrayList<>();
        this.board = new Board();
    }

    public Game add(String playerName) {
        players.add(new AiPlayer(playerName, random));

        log("%s was added", playerName);
        log("They are player number %d", players.size());
        return this;
    }

    public void start() {
        do {
            switchPlayerTurns();
            roll(generateRandomDiceFace());
        } while (!isOver());
    }

    private int generateRandomDiceFace() {
        return random.nextInt(5) + 1;
    }

    public void roll(int roll) {
        log("They have rolled a %d", roll);
        penaltyBox.removeBasedOn(currentPlayer, roll);
        if (!penaltyBox.contains(currentPlayer)) play(roll);
    }

    public void play(int roll) {
        currentPlayer.updatePlaceBasedOn(roll);
        Question question = board.retrieveQuestionFor(currentPlayer.getPlace());
        log(question);

        String answer = currentPlayer.answer(question);
        if (question.isCorrect(answer))
            rewardPlayer();
        else
            punishPlayer();
    }

    private void punishPlayer() {
        log("Question was incorrectly answered");
        penaltyBox.add(currentPlayer);
    }

    private void rewardPlayer() {
        log("Answer was correct!!!!");
        currentPlayer.giveCoins(1);
    }

    public void switchPlayerTurns() {
        if (playersIterator == null)
            playersIterator = Iterators.cycle(players);

        currentPlayer = playersIterator.next();
        log("%s is the current player", currentPlayer);
    }

    public boolean isOver() {
        for (Player player : players)
            if (player.getCoins() == 6) return true;
        return false;
    }
}
