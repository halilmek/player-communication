import service.PlayerService;
import service.serviceImpl.PlayerServiceImpl;

public class SingleProcessPlayerRunner {

    public static void run() {

        // Print the PID of the single process
        System.out.println("Single Process PID: " + ProcessHandle.current().pid());

        // Running single PID
        // Polymorphism and Functional Interface
        PlayerService playerService = new PlayerServiceImpl();

        playerService.startGame();
    }
}