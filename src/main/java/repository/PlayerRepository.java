package repository;

import entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class PlayerRepository {

    private final Map<String, Player> playerMap = new HashMap<>();

    public Player createAndSavePlayer (String name, BlockingQueue<String> incomingQueue, BlockingQueue<String> outgoingQueue, boolean isInitiator) {

        Player player = new Player(name, incomingQueue, outgoingQueue,isInitiator);

        playerMap.put(name, player);

        return player;
    }

    public Player findByName (String playerName) {

        //give the key (name), take the value (player)
        return playerMap.get(playerName);
    }
}
