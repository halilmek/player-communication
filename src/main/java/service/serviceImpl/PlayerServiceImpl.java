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
        playerRepository.createAndSavePlayer("Initiator", queue1,queue2,true,10);
        playerRepository.createAndSavePlayer("Beta", queue2, queue1, false, 10);
    }

    @Override
    public void startGame() {

        //Bring players from DB
        Player player1 = playerRepository.findByName("Initiator");
        Player player2 = playerRepository.findByName("Beta");

        //Starting player objects with different Thread
        Thread player1Thread = new Thread(player1);
        Thread player2Thread = new Thread(player2);

        //Starting threads
        player1Thread.start();
        player2Thread.start();

        //Starting chat
        try {

            //Waiting for threads completion
            player1Thread.join();
            player2Thread.join();

        }
        catch (InterruptedException interruptedException) {

            System.err.println(
                    "Game interrupted: " + interruptedException.getMessage()
            );

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Messaging Quote Reached!!!"
        );
    }
}
