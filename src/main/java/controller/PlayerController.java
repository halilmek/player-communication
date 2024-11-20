package controller;

import dto.MessageDTO;
import service.PlayerService;
import service.serviceImpl.PlayerServiceImpl;

import java.util.logging.Logger;

public class PlayerController {

    private final PlayerService playerService;
    private static final Logger logger = Logger.getLogger(PlayerController.class.getName());

    public PlayerController(PlayerService playerService) {

        this.playerService = playerService;
    }


    //Starting the game by invoking the service layer
    public void startGameController() {

        logger.info("Game is starting!!!");
        //System.out.println("Game is starting!!!");

        playerService.startGame();
    }
}
