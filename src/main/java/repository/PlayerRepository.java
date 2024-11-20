package repository;

import entity.Player;
import enums.PlayerStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class PlayerRepository {

    private final Map<String, Player> playerMap = new HashMap<>();

    /**
     * Creates a new player and saves it in the repository.
     * @param name The name of the player.
     * @param incomingQueue The queue for incoming messages.
     * @param outgoingQueue The queue for outgoing messages.
     * @param playerStatus Indicates if the player is the initiator.
     * @return The created Player object.
     */
    public Player createAndSavePlayer (String name, BlockingQueue<String> incomingQueue, BlockingQueue<String> outgoingQueue, PlayerStatus playerStatus) {

        Player player = new Player(name, incomingQueue, outgoingQueue, playerStatus);

        playerMap.put(name, player);

        return player;
    }

    /**
     * Finds a player by their name.
     * @param playerName The name of the player to find.
     * @return The Player object if found, otherwise null.
     */
    public Player findByName (String playerName) {

        Player player = playerMap.get(playerName);

        if (player == null) {

            throw new IllegalArgumentException("Player with name " + playerName + " not found!!!");
        }
        //give the key (name), take the value (player)
        return player;
    }
}
