package trivia;

import com.google.common.collect.Iterators;

import java.util.*;

import static trivia.Logger.log;

public class Game {
    List<Player> players;
    Iterator<Player> playersIterator;
    Player currentPlayer;

    LinkedList<Question> popQuestions = new LinkedList<>();
    LinkedList<Question> scienceQuestions = new LinkedList<>();
    LinkedList<Question> sportsQuestions = new LinkedList<>();
    LinkedList<Question> rockQuestions = new LinkedList<>();

    PenaltyBox penaltyBox;
    Random random;

    public Game(Random random) {
        this.random = random;
        this.penaltyBox = new PenaltyBox();
        this.players = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop", "Pop Question " + i, "Pop Answer " + i));
            scienceQuestions.addLast(new Question("Science", "Science Question " + i, "Science Answer " + i));
            sportsQuestions.addLast(new Question("Sports", "Sports Question " + i, "Sports Answer " + i));
            rockQuestions.addLast(new Question("Rock", "Rock Question " + i, "Rock Answer " + i));
        }
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
        Question question = askQuestion();
        log(question);

        String answer = currentPlayer.answer(question);
        if (question.isCorrect(answer)) {
            log("Answer was correct!!!!");
            currentPlayer.giveCoins(1);
        } else {
            log("Question was incorrectly answered");
            penaltyBox.add(currentPlayer);
        }
    }

    private Question askQuestion() {
        String category = currentCategory();
        log("The category is %s", category);
        if ("Pop".equals(category))
            return popQuestions.removeFirst();
        if ("Science".equals(category))
            return scienceQuestions.removeFirst();
        if ("Sports".equals(category))
            return sportsQuestions.removeFirst();
        return rockQuestions.removeFirst();
    }

    private String currentCategory() {
        int place = currentPlayer.getPlace();
        if (place % 4 == 0) return "Pop";
        if (place % 4 == 1) return "Science";
        if (place % 4 == 2) return "Sports";
        return "Rock";
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
