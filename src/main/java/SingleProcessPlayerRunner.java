import controller.PlayerController;
import service.PlayerService;
import service.serviceImpl.PlayerServiceImpl;

public class SingleProcessPlayerRunner {


    public static void run () {

        //Running single PID
        //Polymorphism and Functional Interface
        PlayerService playerService = new PlayerServiceImpl();

        playerService.startGame();
    }
}
