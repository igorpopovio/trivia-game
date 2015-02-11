package trivia;

import static trivia.Logger.log;

public class Player {
    private String name;
    private int totalCoins;
    private int currentPlace;

    public Player(String name) {
        this.name = name;
    }

    public void giveCoins(int coins) {
        totalCoins += coins;
        log("%s now has %d Gold Coins.", this, totalCoins);
    }

    public int getCoins() {
        return totalCoins;
    }

    public int getPlace() {
        return currentPlace;
    }

    public void updatePlaceBasedOn(int roll) {
        this.currentPlace += roll;
        this.currentPlace %= 12;
        log("%s's new location is %d", this, currentPlace);
    }

    @Override
    public String toString() {
        return name;
    }
}
