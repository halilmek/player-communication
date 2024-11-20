import java.io.*;
import java.net.*;

public class PlayerProcess {
    public static void main(String[] args) throws IOException {
        String playerName = args[0];
        int port = Integer.parseInt(args[1]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(playerName + " started on port " + port);

            while (true) {
                Socket client = null;
                try {
                    System.out.println("[LOG] " + playerName + " waiting for a connection...");
                    client = serverSocket.accept();
                    System.out.println("[LOG] " + playerName + " connection accepted.");

                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    // Mesaj al
                    String message = in.readLine();
                    if (message == null) {
                        System.err.println("[ERROR] " + playerName + " received null message. Closing connection.");
                        client.close();
                        continue;
                    }
                    System.out.println("[LOG] " + playerName + " received: " + message);

                    // Yanıt oluştur ve gönder
                    String response = message + "(Response from " + playerName + ")";
                    System.out.println("[LOG] " + playerName + " sending response: " + response);
                    out.println(response);
                    out.flush();

                    Thread.sleep(200); // Yanıt gönderildikten sonra bekleme
                    System.out.println("[LOG] " + playerName + " response sent successfully.");

                } catch (SocketTimeoutException e) {
                    System.err.println("[ERROR] " + playerName + " connection timeout: " + e.getMessage());
                } catch (IOException | InterruptedException e) {
                    System.err.println("[ERROR] " + playerName + " encountered an issue: " + e.getMessage());
                } finally {
                    if (client != null && !client.isClosed()) {
                        try {
                            client.close();
                            System.out.println("[LOG] " + playerName + " connection closed.");
                        } catch (IOException e) {
                            System.err.println("[ERROR] " + playerName + " failed to close connection: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] " + playerName + " could not start on port " + port + ": " + e.getMessage());
        }
    }
}
