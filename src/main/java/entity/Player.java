package entity;

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

    public String getName() {

        return name;
    }

    public int getMessageCounter() {

        return messageCounter;
    }

    public void increasingMessageCounter () {

        this.messageCounter++;
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

                    String response = "response # " + (messageCounter);

                    System.out.println(
                            name + " sent: " + response
                    );

                    outgoingMessage.put(response);
                }
                else {

                    //Initiator sending next message
                    //messageCounter++;

                    String nextMessage = "message # " + messageCounter + " from Initiator!!!";

                    System.out.println(
                            name + " sent: " + nextMessage
                    );

                    outgoingMessage.put(nextMessage);
                }

                //Seperating them each other
                //System.out.println("=========================//======================");
                //System.out.println("=========================//======================");
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

        String initialMessage = "hi from Initiator";

        System.out.println(
                name + " sent " + initialMessage
        );

        outgoingMessage.put(initialMessage);
    }
}
