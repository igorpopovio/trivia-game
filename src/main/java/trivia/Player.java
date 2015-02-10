package trivia;

public class Player {
    private String name;
    private int totalCoins;
    private int currentPlace;

    public Player(String name) {
        this.name = name;
    }

    public void giveCoins(int coins) {
        totalCoins += coins;
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
    }

    @Override
    public String toString() {
        return name;
    }
}
