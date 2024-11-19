package controller;

import dto.MessageDTO;
import service.serviceImpl.PlayerServiceImpl;

public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;

    public PlayerController() {

        this.playerServiceImpl = new PlayerServiceImpl();
    }


    public void startGame() {

        System.out.println("Game is starting!!!");

        playerServiceImpl.initiatingConversation(new MessageDTO("Hello to Player Beta!"));
    }
}
