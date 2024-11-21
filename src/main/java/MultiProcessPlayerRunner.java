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
            Process player2 = pb2.start();

            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            PrintWriter player1Out = new PrintWriter(new OutputStreamWriter(player1.getOutputStream()), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            PrintWriter player2Out = new PrintWriter(new OutputStreamWriter(player2.getOutputStream()), true);

            int messageCounter = 0;

            while (messageCounter < 10) {
                System.out.println("[DEBUG] Iteration: " + (messageCounter + 1));

                // Player1'den mesaj al
                String messageFromPlayer1 = player1In.readLine();
                if (messageFromPlayer1 == null) {
                    System.err.println("[ERROR] Player1 disconnected. Exiting loop.");
                    break;
                }
                System.out.println("Initiator sent: " + messageFromPlayer1);

                // Player2'ye mesaj gönder
                player2Out.println(messageFromPlayer1);
                System.out.println("[DEBUG] Sent to Player2: " + messageFromPlayer1);

                // Player2'den yanıt al
                String responseFromPlayer2 = player2In.readLine();
                if (responseFromPlayer2 == null) {
                    System.err.println("[ERROR] Player2 disconnected. Exiting loop.");
                    break;
                }
                System.out.println("Beta received: " + responseFromPlayer2);

                // Player2'nin yanıtını Player1'e gönder
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
