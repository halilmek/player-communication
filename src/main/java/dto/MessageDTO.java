package dto;

//A Data Transfer Object representing a message sent between players.
public class MessageDTO {

    private final String senderName;
    private final String content;

    public MessageDTO(String senderName, String content) {

        this.senderName = senderName;
        this.content = content;

    }

    public String getSenderName() {

        return senderName;
    }


    public String getContent() {

        return content;
    }

    @Override
    public String toString() {

        return senderName + content;
    }
}
