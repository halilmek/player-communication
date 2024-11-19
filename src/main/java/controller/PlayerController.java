package controller;

import dto.MessageDTO;
import service.serviceImpl.PlayerServiceImpl;

public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;

    public PlayerController() {

        this.playerServiceImpl = new PlayerServiceImpl();
    }


    public void startGameController() {

        System.out.println("Game is starting!!!");

        playerServiceImpl.startGame();
        //playerServiceImpl.initiatingConversation(new MessageDTO(" says hello to Player Beta!"));
    }
}
