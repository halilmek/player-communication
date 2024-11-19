package repository;

import entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {

    private final Map<String, Player> playerMap = new HashMap<>();

    public void save (Player player) {

        playerMap.put(player.getName(), player);
    }

    public Player findByName (String playerName) {

        //give the key (name), take the value (player)
        return playerMap.get(playerName);
    }
}
