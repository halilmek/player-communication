package service.serviceImpl;

import dto.MessageDTO;
import entity.Player;
import mapper.PlayerMapper;
import repository.PlayerRepository;
import service.PlayerService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;


    public PlayerServiceImpl() {

        this.playerRepository = new PlayerRepository();

        savingPlayerToDB();
    }

    private void savingPlayerToDB() {

        //Creating BlockingQueue
        BlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();

        //Defining the players
        playerRepository.createAndSavePlayer("Initiator", queue1,queue2,true);
        playerRepository.createAndSavePlayer("Beta", queue2, queue1, false);
    }

    @Override
    public void startGame() {

        //Bringing players from DB
        Player player1 = playerRepository.findByName("Initiator");
        Player player2 = playerRepository.findByName("Beta");

        try {

            initiateConversation(player1, player2);
        } catch (InterruptedException interruptedException) {

            Thread.currentThread().interrupt();

            System.err.println(
                    "Game interrupted " + interruptedException.getMessage()
            );
        }

        System.out.println(
                "Messaging Quote Reached!!!"
        );
    }

    public void  initiateConversation (Player player1, Player player2) throws InterruptedException {

        int messageCounter = 1;
        String initialMessage = new MessageDTO("", "hi from initiator!!!").toString();

        System.out.println(
                player1.getName() + " sent: " + initialMessage
        );

        player1.getOutgoingMessage().put(initialMessage);

        while (messageCounter < 10) {

            String receivedMessage = player2.getIncomingMessage().take();

            System.out.println(
                   player2.getName() + " received: " + receivedMessage
            );

            MessageDTO response = PlayerMapper.createResponse("", messageCounter);

            System.out.println(
                   player2.getName() + " sent: " + response
            );

            player2.getOutgoingMessage().put(response.toString());

            String notification = player1.getIncomingMessage().take();

            System.out.println(
                   player1.getName() + " received: " + notification
            );

            MessageDTO initiatorNextMessage = PlayerMapper.createMessage("", ++messageCounter);

            System.out.println(
                    player1.getName() + " sent: " + initiatorNextMessage
            );

            player1.getOutgoingMessage().put(initiatorNextMessage.toString());
        }


    }
}
