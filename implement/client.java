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

        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
        server serverInstance = new server();
        Thread messageProcessorThread = new Thread(() -> serverInstance.processMessages(messageQueue));
        messageProcessorThread.start();

        while (!exit) {
            String input = scannerIn.nextLine();
            if (input.equals("tell me a joke")) {
                try {
                    Socket sock = new Socket("127.0.0.1", 6013);
                    PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

                    messageQueue.put(input);

                    while (true) {
                        // Send the user's input to the implement.server
                        pout.println(input);

                        // Wait for a response from the implement.server
                        String serverResponse = messageQueue.take();
                        System.out.println(serverResponse);
                        System.out.print("> ");
                        input = scannerIn.nextLine();
                        if (input.equals("exit")) {
                            exit = true;
                        }

                        messageQueue.put(input);

                    }
                } catch (IOException | InterruptedException ioe) {
                    System.err.println(ioe);
                }
            }
        }
    }

}
