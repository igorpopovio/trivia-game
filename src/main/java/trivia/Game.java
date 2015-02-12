package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static trivia.Logger.log;

public class Game {
    List<Player> players = new ArrayList<>();
    PenaltyBox penaltyBox = new PenaltyBox();

    LinkedList<Question> popQuestions = new LinkedList<>();
    LinkedList<Question> scienceQuestions = new LinkedList<>();
    LinkedList<Question> sportsQuestions = new LinkedList<>();
    LinkedList<Question> rockQuestions = new LinkedList<>();

    int currentPlayerIndex = -1;
    Player currentPlayer;
    private Random random;

    public Game(Random random) {
        this();
        this.random = random;
    }

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop", "Pop Question " + i, "Pop Answer " + i));
            scienceQuestions.addLast(new Question("Science", "Science Question " + i, "Science Answer " + i));
            sportsQuestions.addLast(new Question("Sports", "Sports Question " + i, "Sports Answer " + i));
            rockQuestions.addLast(new Question("Rock", "Rock Question " + i, "Rock Answer " + i));
        }
    }

    public Game add(String playerName) {
        players.add(new Player(playerName));
        currentPlayer = players.get(0);

        log("%s was added", playerName);
        log("They are player number %d", players.size());
        return this;
    }

    public void start() {
        do {
            advanceToNextPlayer();
            roll(random.nextInt(5) + 1);
            if (random.nextInt(9) == 7) wrongAnswer();
            else wasCorrectlyAnswered();
        } while (!isOver());
    }

    public void roll(int roll) {
        log("They have rolled a %d", roll);
        penaltyBox.removeBasedOn(currentPlayer, roll);
        if (!penaltyBox.contains(currentPlayer)) play(roll);
    }

    public void play(int roll) {
        currentPlayer.updatePlaceBasedOn(roll);
        askQuestion();
    }

    private void askQuestion() {
        String category = currentCategory();
        log("The category is %s", category);

        if ("Pop".equals(category))
            log(popQuestions.removeFirst());
        if ("Science".equals(category))
            log(scienceQuestions.removeFirst());
        if ("Sports".equals(category))
            log(sportsQuestions.removeFirst());
        if ("Rock".equals(category))
            log(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        int place = currentPlayer.getPlace();
        if (place % 4 == 0) return "Pop";
        if (place % 4 == 1) return "Science";
        if (place % 4 == 2) return "Sports";
        if (place % 4 == 3) return "Rock";
        return "";
    }

    public void wasCorrectlyAnswered() {
        if (penaltyBox.contains(currentPlayer)) return;

        log("Answer was correct!!!!");
        currentPlayer.giveCoins(1);
    }

    public void advanceToNextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex %= players.size();
        currentPlayer = players.get(currentPlayerIndex);
        log("%s is the current player", currentPlayer);
    }

    public void wrongAnswer() {
        log("Question was incorrectly answered");
        penaltyBox.add(currentPlayer);
    }

    public boolean isOver() {
        for (Player player : players)
            if (player.getCoins() == 6) return true;
        return false;
    }
}
