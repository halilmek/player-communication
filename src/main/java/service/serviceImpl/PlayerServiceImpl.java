package service.serviceImpl;

import dto.MessageDTO;
import entity.Player;
import enums.PlayerStatus;
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
        playerRepository.createAndSavePlayer("Initiator", queue1,queue2, PlayerStatus.INITIATOR);
        playerRepository.createAndSavePlayer("Beta", queue2, queue1, PlayerStatus.RECEIVER);
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

    private void sendMessage(Player sender, String message) throws InterruptedException {

        System.out.println(
                sender.getName() + " sent: " + message
        );

        sender.getOutgoingMessage().put(message);
    }

    private String receiveMessage(Player receiver) throws InterruptedException {

        String message = receiver.getIncomingMessage().take();

        System.out.println(
                receiver.getName() + " received: " + message
        );

        return message;
    }

    public void initiateConversation(Player player1, Player player2) throws InterruptedException {

        int messageCounter = 1;

        sendMessage(player1, new MessageDTO("", "hi from initiator!!!").toString());

        while (messageCounter < 10) {

            String receivedMessage = receiveMessage(player2);

            MessageDTO response = PlayerMapper.createResponse("", messageCounter);

            sendMessage(player2, response.toString());

            String notification = receiveMessage(player1);

            MessageDTO nextMessage = PlayerMapper.createMessage("", ++messageCounter);

            sendMessage(player1, nextMessage.toString());
        }
    }

}
