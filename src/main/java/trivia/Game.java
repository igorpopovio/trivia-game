package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static trivia.Logger.log;

public class Game {
    List<Player> players = new ArrayList<>();

    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayerIndex;
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        players.add(new Player(playerName));

        log("%s was added", playerName);
        log("They are player number %d", players.size());
    }

    public void roll(int roll) {
        currentPlayer = players.get(currentPlayerIndex);
        log("%s is the current player", currentPlayer);
        log("They have rolled a %d", roll);

        if (inPenaltyBox[currentPlayerIndex]) {
            isGettingOutOfPenaltyBox = roll % 2 != 0;
            inPenaltyBox[currentPlayerIndex] = !isGettingOutOfPenaltyBox;
            if (isGettingOutOfPenaltyBox)
                log("%s is getting out of the penalty box", currentPlayer);
            else
                log("%s is not getting out of the penalty box", currentPlayer);
        }

        if (!inPenaltyBox[currentPlayerIndex]) play(roll);
    }

    public void play(int roll) {
        currentPlayer.updatePlaceBasedOn(roll);

        log("%s's new location is %d", currentPlayer, currentPlayer.getPlace());
        log("The category is %s", currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if ("Pop".equals(currentCategory()))
            log(popQuestions.removeFirst());
        if ("Science".equals(currentCategory()))
            log(scienceQuestions.removeFirst());
        if ("Sports".equals(currentCategory()))
            log(sportsQuestions.removeFirst());
        if ("Rock".equals(currentCategory()))
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
        if (inPenaltyBox[currentPlayerIndex]) return;

        currentPlayer.giveCoins(1);
        log("Answer was correct!!!!");
        log("%s now has %d Gold Coins.", currentPlayer, currentPlayer.getCoins());
    }

    public void advanceToNextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex %= players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    public void wrongAnswer() {
        log("Question was incorrectly answered");
        log("%s was sent to the penalty box", currentPlayer);
        inPenaltyBox[currentPlayerIndex] = true;
    }

    public boolean isOver() {
        for (Player player : players)
            if (player.getCoins() == 6) return true;
        return false;
    }
}
