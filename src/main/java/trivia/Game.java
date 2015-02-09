package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static trivia.Logger.log;

public class Game {
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer;
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
        players.add(playerName);

        log("%s was added", playerName);
        log("They are player number %d", players.size());
    }

    public void roll(int roll) {
        log("%s is the current player", players.get(currentPlayer));
        log("They have rolled a %d", roll);

        if (inPenaltyBox[currentPlayer]) {
            isGettingOutOfPenaltyBox = roll % 2 != 0;
            inPenaltyBox[currentPlayer] = !isGettingOutOfPenaltyBox;
            if (isGettingOutOfPenaltyBox)
                log("%s is getting out of the penalty box", players.get(currentPlayer));
            else
                log("%s is not getting out of the penalty box", players.get(currentPlayer));
        }

        if (!inPenaltyBox[currentPlayer]) play(roll);
    }

    public void play(int roll) {
        places[currentPlayer] += roll;
        places[currentPlayer] %= 12;

        log("%s's new location is %d", players.get(currentPlayer), places[currentPlayer]);
        log("The category is %s", currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            log(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            log(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            log(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            log(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        if (places[currentPlayer] % 4 == 0) return "Pop";
        if (places[currentPlayer] % 4 == 1) return "Science";
        if (places[currentPlayer] % 4 == 2) return "Sports";
        if (places[currentPlayer] % 4 == 3) return "Rock";
        return "";
    }

    public void wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) return;

        purses[currentPlayer]++;
        log("Answer was correct!!!!");
        log("%s now has %d Gold Coins.", players.get(currentPlayer), purses[currentPlayer]);
    }

    public void advanceToNextPlayer() {
        currentPlayer++;
        currentPlayer %= players.size();
    }

    public void wrongAnswer() {
        log("Question was incorrectly answered");
        log("%s was sent to the penalty box", players.get(currentPlayer));
        inPenaltyBox[currentPlayer] = true;
    }

    public boolean isOver() {
        for (int coins : purses)
            if (coins == 6) return true;
        return false;
    }

    public int getCoinsForCurrentPlayer() {
        return purses[currentPlayer];
    }
}
