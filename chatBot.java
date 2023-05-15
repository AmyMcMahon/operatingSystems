import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class chatBot {
    public static void main(String args[]) {
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Welcome to the chatBot!, enter 'help' to see the functions of the chatbot");

        try {
            Socket sock = new Socket("127.0.0.1", 6013);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

            // Wait for input from the user
            String input;
            while (true) {
                System.out.print("> ");
                input = scannerIn.nextLine();

                // Send the user's input to the server
                pout.println(input);

                // Wait for a response from the server
                String serverResponse = bin.readLine();
                System.out.println(serverResponse);
            }
            sock.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        scannerIn.close();
    }
}
