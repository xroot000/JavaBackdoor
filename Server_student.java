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
      // TODO set up the data members
    } catch (IOException ioe) {

    }
  }

  /*
  * Send a command, and return the result
  * @param cmd is the command to execute on the client
  * @return the output of the command as a String
  */
  public String sendCommand(String cmd) throws IOException {
    
    //  send command to server
    //  TODO

    // read response from client
    StringBuilder output = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    String line;


    //TODO read message using BufferedReader

    return output.toString();

  }

  public Scanner getUserInput() {
    return this.userInput;
  }

  public static void main(String[] args) {
    // TODO create server
    // TODO read user input, send to server, print output, repeat
  }
}
