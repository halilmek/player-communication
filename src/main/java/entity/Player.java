package entity;

import dto.MessageDTO;
import mapper.PlayerMapper;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Player implements Runnable {

    private String name;
    private final BlockingQueue<String> incomingMessage;
    private final BlockingQueue<String> outgoingMessage;
    private final int maxMessages;
    private final boolean isInitiator;
    private int messageCounter = 0;

    public Player(String name, BlockingQueue<String> incomingMessage, BlockingQueue<String> outgoingMessage, boolean isInitiator, int maxMessages) {

        this.name = name;
        this.incomingMessage = incomingMessage;
        this.outgoingMessage = outgoingMessage;
        this.isInitiator = isInitiator;
        this.maxMessages = maxMessages;
    }

    @Override
    public void run() {

        try {

            if (isInitiator) {

                startConversation();
            }

            while (messageCounter < maxMessages) {

                messageCounter++;

                //Taking a message
                String receivedMessage = incomingMessage.take();

                System.out.println(
                        name + " received: " + receivedMessage
                );

                //Beta sending response
                if (!isInitiator) {

                    MessageDTO response = PlayerMapper.createResponse("",  messageCounter);

                    System.out.println(
                            name + " sent: " + response
                    );

                    outgoingMessage.put(response.toString());
                }
                else {

                    //Initiator sending next message
                    MessageDTO nextMessage = PlayerMapper.createMessage("", messageCounter);

                    System.out.println(
                            name + " sent: " + nextMessage
                    );

                    outgoingMessage.put(nextMessage.toString());
                }
            }
        }
        catch (InterruptedException interruptedException) {

            Thread.currentThread().interrupt();

            System.err.println(
                    name + " was interrupted!!!"
            );
        }
    }

    public void startConversation () throws InterruptedException{

        messageCounter = 1;

        MessageDTO initialMessage = new MessageDTO("", "hi from Initiator!!!");

        System.out.println(
                name + " sent: " + initialMessage
        );

        outgoingMessage.put(initialMessage.toString());
    }
}
