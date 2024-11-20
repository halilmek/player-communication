package entity;

import dto.MessageDTO;
import mapper.PlayerMapper;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Player {

    private String name;
    private final BlockingQueue<String> incomingMessage;
    private final BlockingQueue<String> outgoingMessage;
    private final boolean isInitiator;

    public Player(String name, BlockingQueue<String> incomingMessage,
                  BlockingQueue<String> outgoingMessage, boolean isInitiator) {

        this.name = name;
        this.incomingMessage = incomingMessage;
        this.outgoingMessage = outgoingMessage;
        this.isInitiator = isInitiator;
    }

    public String getName() {
        return name;
    }

    public BlockingQueue<String> getIncomingMessage() {
        return incomingMessage;
    }

    public BlockingQueue<String> getOutgoingMessage() {
        return outgoingMessage;
    }

    public boolean isInitiator() {
        return isInitiator;
    }

}
