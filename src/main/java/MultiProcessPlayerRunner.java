import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MultiProcessPlayerRunner {

    public static void run() {
        System.out.println("Running Multi-Process Player...");

        try {
            ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "target/classes", "PlayerProcess", "Player1", "Initiator");
            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "target/classes", "PlayerProcess", "Player2", "Receiver");

            Process player1 = pb1.start();
            System.out.println("Player1 PID: " + player1.pid()); // Player1 PID yazdırılıyor

            Process player2 = pb2.start();
            System.out.println("Player2 PID: " + player2.pid()); // Player2 PID yazdırılıyor

            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            PrintWriter player1Out = new PrintWriter(new OutputStreamWriter(player1.getOutputStream()), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            PrintWriter player2Out = new PrintWriter(new OutputStreamWriter(player2.getOutputStream()), true);

            int messageCounter = 0;

            while (messageCounter < 10) {

                System.out.println("[DEBUG] Iteration: " + (messageCounter + 1));

                // Taking message from player1
                String messageFromPlayer1 = player1In.readLine();

                if (messageFromPlayer1 == null) {
                    System.err.println("[ERROR] Player1 disconnected. Exiting loop.");
                    break;
                }

                System.out.println("Initiator sent: " + messageFromPlayer1);

                // Sending message player2
                player2Out.println(messageFromPlayer1);
                System.out.println("[DEBUG] Sent to Player2: " + messageFromPlayer1);

                // Taking response from player2
                String responseFromPlayer2 = player2In.readLine();

                if (responseFromPlayer2 == null) {

                    System.err.println("[ERROR] Player2 disconnected. Exiting loop.");
                    break;
                }

                System.out.println("Beta received: " + responseFromPlayer2);

                // Giving feedback to player1
                player1Out.println(responseFromPlayer2);

                System.out.println("[DEBUG] Sent to Player1: " + responseFromPlayer2);

                messageCounter++;
            }

            System.out.println("Messaging Quote Reached!!!");

            player1.destroy();
            player2.destroy();

            System.out.println("[DEBUG] Processes terminated. Exiting program.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}