package entity;

import dto.MessageDTO;
import enums.PlayerStatus;
import mapper.PlayerMapper;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Player {

    private final String name;
    private final BlockingQueue<String> incomingMessage;
    private final BlockingQueue<String> outgoingMessage;
    private final PlayerStatus playerStatus;

    public Player(String name, BlockingQueue<String> incomingMessage,
                  BlockingQueue<String> outgoingMessage, PlayerStatus playerStatus) {

        this.name = name;
        this.incomingMessage = incomingMessage;
        this.outgoingMessage = outgoingMessage;
        this.playerStatus = playerStatus;
    }

    public String getName() {
        return name;
    }

    //Queue for incoming message
    public BlockingQueue<String> getIncomingMessage() {
        return incomingMessage;
    }

    //Queue for outgoing message
    public BlockingQueue<String> getOutgoingMessage() {
        return outgoingMessage;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
}
