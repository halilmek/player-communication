package entity;

public class Player {

    private String name;

    private int messageCounter;

    public Player(String name) {

        this.name = name;
        this.messageCounter = 1;
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
}
