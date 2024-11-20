package controller;

import dto.MessageDTO;
import service.PlayerService;
import service.serviceImpl.PlayerServiceImpl;

public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {

        this.playerService = playerService;
    }


    public void startGameController() {

        System.out.println("Game is starting!!!");

        playerService.startGame();
    }
}
