package service.serviceImpl;

import dto.MessageDTO;
import entity.Player;
import mapper.PlayerMapper;
import repository.PlayerRepository;
import service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;


    public PlayerServiceImpl() {

        this.playerRepository = new PlayerRepository();

        savingPlayerToDB();
    }

    private void savingPlayerToDB() {

        playerRepository.save(new Player("Player Initiator"));
        playerRepository.save(new Player("Player Beta"));
    }


    @Override
    public void initiatingConversation(MessageDTO initialMessageDTO) {

        Player player1 = playerRepository.findByName("Player Initiator");
        Player player2 = playerRepository.findByName("Player Beta");

        System.out.println(
                PlayerMapper.mapToMessage(player1, initialMessageDTO.getMessage())
        );

        String response = "";

        for (int i = 1; i < 11 ; i++) {

            if (i < 2) {

                response = i + " message received!";

            } else {

                response = " " + i + " messages received!";
            }

            System.out.println(
                    PlayerMapper.mapToMessage(player2, response)
            );

        }

        System.out.println(
                "Stop Condition Happened!"
        );
    }
}
