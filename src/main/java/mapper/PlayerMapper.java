package mapper;

import dto.MessageDTO;
import entity.Player;

public class PlayerMapper {

    public static String mapToMessage (Player player, String message) {

        return player.getName() + message;
    }

    public static MessageDTO mapToDTO (String message) {

        return new MessageDTO(message);
    }
}
