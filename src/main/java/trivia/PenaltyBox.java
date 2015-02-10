package trivia;

import java.util.HashSet;
import java.util.Set;

import static trivia.Logger.log;

public class PenaltyBox {
    private Set<Player> players;

    public PenaltyBox() {
        this.players = new HashSet<>();
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }

    public void add(Player player) {
        players.add(player);
        log("%s was sent to the penalty box", player);
    }

    public void removeBasedOn(Player player, int roll) {
        if (!contains(player)) return;

        if (!shouldGetOut(roll)) {
            log("%s is not getting out of the penalty box", player);
            return;
        }

        log("%s is getting out of the penalty box", player);
        players.remove(player);
    }

    public boolean shouldGetOut(int roll) {
        return roll % 2 != 0;
    }
}
