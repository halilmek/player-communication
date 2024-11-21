import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PlayerProcess {
    public static void main(String[] args) {

        if (args.length < 2) {

            System.err.println("Usage: java PlayerProcess <PlayerName> <Role>");
            System.exit(1);
        }

        String playerName = args[0];
        String role = args[1];
        int messageCounter = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true)) {

            if (role.equalsIgnoreCase("Initiator")) {
                String initialMessage = "Message #1 from " + playerName;
                out.println(initialMessage);
                System.out.println("[DEBUG] " + playerName + " sent: " + initialMessage);
            }

            while (messageCounter < 10) {

                String receivedMessage = in.readLine();

                if (receivedMessage == null) {
                    System.err.println("[DEBUG] " + playerName + " received null. Exiting loop.");
                    break;
                }

                System.out.println("[DEBUG] " + playerName + " received: " + receivedMessage);

                messageCounter++;

                String responseMessage = role.equalsIgnoreCase("Initiator")
                        ? "Message #" + messageCounter + " from " + playerName
                        : "Response #" + messageCounter + " from " + playerName;

                out.println(responseMessage);

                System.out.println("[DEBUG] " + playerName + " sent: " + responseMessage);
            }

            System.out.println("[DEBUG] " + playerName + " finished messaging. Exiting.");

        } catch (Exception e) {

            System.err.println("[ERROR] " + playerName + " encountered an error: " + e.getMessage());
        }
    }
}
