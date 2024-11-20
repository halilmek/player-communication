package mapper;

import dto.MessageDTO;
import entity.Player;

public class PlayerMapper {

    /**
     * Creates a response message for a player.
     * @param senderName The name of the sender.
     * @param messageCounter The message counter.
     * @return A MessageDTO containing the response.
     */
    public static MessageDTO createResponse (String senderName, int messageCounter) {

        return new MessageDTO(senderName, "response # " + messageCounter);
    }

    /**
     * Creates a new message for a player.
     * @param senderName The name of the sender.
     * @param messageCounter The message counter.
     * @return A MessageDTO containing the new message.
     */
    public static MessageDTO createMessage (String senderName, int messageCounter) {

        return new MessageDTO("message # " + messageCounter, senderName);
    }
}
