import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class chatBot {
    public static void main(String args[]) {
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Welcome to the chatBot!, enter 'help' to see the functions of the chatbot");
        System.out.print("> ");
        String input = scannerIn.nextLine();
        if (input.equals("tell me a joke")) {
            try {
                Socket sock = new Socket("127.0.0.1", 6013);
                InputStream in = sock.getInputStream();
                BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

                // Wait for input from the user

                while (true) {
                    // Send the user's input to the server
                    pout.println(input);

                    // Wait for a response from the server
                    String serverResponse = bin.readLine();
                    System.out.println(serverResponse);
                    System.out.print("> ");
                    input = scannerIn.nextLine();
                    if (input.equals("exit")) {
                        break;
                    }
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        } else {
            System.out.println("Invalid input, please try again");
        }

        scannerIn.close();
    }
}
