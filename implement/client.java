package implement;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class client {
    public static void main(String[] args) {

        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Welcome to the chatBot!, enter 'help' to see the functions of the chatbot");
        System.out.print("> ");
        boolean exit = false;

        // create the message queue
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
        // create the server instance
        server serverInstance = new server();
        // create the thread to process the messages coming from the server
        Thread messageProcessorThread = new Thread(() -> serverInstance.processMessages(messageQueue));
        messageProcessorThread.start();

        while (!exit) {
            // read the user input
            String input = scannerIn.nextLine();
            if (input.equals("tell me a joke")) {
                try {
                    // create a socket to connect to the server
                    Socket sock = new Socket("127.0.0.1", 6013);
                    PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

                    // Send the user's input to the server
                    messageQueue.put(input);

                    while (true) {
                        // Send the user's input to the server
                        pout.println(input);

                        // Wait for a response from the server
                        String serverResponse = messageQueue.take();
                        System.out.println(serverResponse);
                        System.out.print("> ");

                        // read the next user input
                        input = scannerIn.nextLine();
                        if (input.equals("exit")) {
                            exit = true;
                        }

                        // put the user's input in the queue
                        messageQueue.put(input);

                    }
                } catch (IOException | InterruptedException ioe) {
                    System.err.println(ioe);
                }
            }
        }
    }

}
