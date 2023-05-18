package implement;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Welcome to the chatBot!, enter 'help' to see the functions of the chatbot");
        System.out.print("> ");
        boolean exit = false;
        while (!exit) {
            String input = scannerIn.nextLine();
            try (Socket sock = new Socket("127.0.0.1", 6013)) {
                InputStream in = sock.getInputStream();
                BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

                while (true) {
                    // Send the user's input to the src.implement.server
                    pout.println(input);

                    // Wait for a response from the src.implement.server
                    String serverResponse = bin.readLine();
                    System.out.println(serverResponse);
                    System.out.print("> ");
                    input = scannerIn.nextLine();
                    if (input.equals("exit")) {
                        exit = true;
                    }
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }

        }
    }

}
