import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class MultiProcessPlayerRunner {
    public static void run() {
        try {
            // Oyuncu süreçlerini başlat
            Process player1 = new ProcessBuilder("java", "-cp", "target/classes", "PlayerProcess", "Player1", "5000").start();
            Process player2 = new ProcessBuilder("java", "-cp", "target/classes", "PlayerProcess", "Player2", "5002").start();

            System.out.println("Both players started in separate processes.");

            // Oyuncuların başlatılması için bekleme süresi
            Thread.sleep(5000);

            // Soket bağlantıları oluştur
            System.out.println("Connecting to Player 1 on port 5000...");
            Socket player1Socket = new Socket("localhost", 5000);
            System.out.println("Connected to Player 1.");

            System.out.println("Connecting to Player 2 on port 5002...");
            Socket player2Socket = new Socket("localhost", 5002);
            System.out.println("Connected to Player 2.");

            PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));

            PrintWriter out2 = new PrintWriter(player2Socket.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));

            // Mesajlaşma döngüsü
            int messageCounter = 1;
            out1.println("Hi from Initiator!");
            System.out.println("Message sent to Player 1: Hi from Initiator!");



            while (messageCounter <= 10) {
                try {
                    // Player 1'e mesaj gönder
                    String messageToPlayer1 = "Message # " + messageCounter;
                    System.out.println("[LOG] Sending to Player 1: " + messageToPlayer1);
                    out1.println(messageToPlayer1);
                    out1.flush();

                    // Player 1'den yanıt al
                    System.out.println("[LOG] Waiting for Player 1 response...");
                    String response1 = in1.readLine(); // Burada takılıyor olabilir
                    if (response1 == null) {
                        System.err.println("[ERROR] Player 1 did not respond. Breaking loop.");
                        break;
                    }
                    System.out.println("[LOG] Player 1 responded with: " + response1);

                    // Player 2'ye mesaj gönder
                    String messageToPlayer2 = "Response from Player 1: " + response1;
                    System.out.println("[LOG] Sending to Player 2: " + messageToPlayer2);
                    out2.println(messageToPlayer2);
                    out2.flush();

                    // Player 2'den yanıt al
                    System.out.println("[LOG] Waiting for Player 2 response...");
                    String response2 = in2.readLine();
                    if (response2 == null) {
                        System.err.println("[ERROR] Player 2 did not respond. Breaking loop.");
                        break;
                    }
                    System.out.println("[LOG] Player 2 responded with: " + response2);

                    messageCounter++;
                } catch (IOException e) {
                    System.err.println("[ERROR] Connection issue: " + e.getMessage());
                    break;
                }
            }




            // Soketleri kapat
            player1Socket.close();
            System.out.println("Connection to Player 1 closed.");
            player2Socket.close();
            System.out.println("Connection to Player 2 closed.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
