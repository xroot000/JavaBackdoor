import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {
  private ServerSocket serversock;
  private Socket client;
  private Scanner userInput;
  private PrintWriter pw;

  public Server(int port) {
    try {
      serversock = new ServerSocket(port);
      client = serversock.accept();
      pw = new PrintWriter(client.getOutputStream());
      userInput = new Scanner(System.in);
    } catch (IOException ioe) {

    }

  }

  /*
  * Send a command, and return the result
  * @param cmd is the command to execute on the client
  * @return the output of the command as a String
  */
  public String sendCommand(String cmd) throws IOException {
    this.pw.println(cmd);
    this.pw.flush();

    StringBuilder output = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    String line;


    int linesInt = Integer.parseInt(reader.readLine());

    for (int i = 0; i < linesInt; i++) {
        line = reader.readLine();
        output.append(line + "\n");
    }

    return output.toString();

  }

  public Scanner getUserInput() {
    return this.userInput;
  }

    public static void main(String[] args) {
        Server s = new Server(4444);

        Scanner userInput = s.getUserInput();

        System.out.print("cmd> ");
        while(userInput.hasNextLine()) {
            String cmd = userInput.nextLine();
            String output;

            try {
                output = s.sendCommand(cmd);
            } catch (IOException ioe) {
                output = "Buffered Reader Error";
            }

            System.out.println("Output for command: " + cmd);
            System.out.println(output);
            System.out.print("cmd> ");
        }

    }
}
