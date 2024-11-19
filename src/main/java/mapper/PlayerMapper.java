package mapper;

import dto.MessageDTO;
import entity.Player;

public class PlayerMapper {

    public static MessageDTO createResponse (String senderName, int messageCounter) {

        return new MessageDTO(senderName, "response # " + messageCounter);
    }

    public static MessageDTO createMessage (String senderName, int messageCounter) {

        return new MessageDTO("message # " + messageCounter + " from ", senderName);
    }
}
