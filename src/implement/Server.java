package implement;

import java.net.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            while (true) {
                Socket client = sock.accept();
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                String input;
                while ((input = bin.readLine()) != null) {
                    if (input.equalsIgnoreCase("threads")) {
                        Thread javaT = new Thread(new JokeTeller(JokeType.JavaJoke, pout));
                        Thread batmanT = new Thread(new JokeTeller(JokeType.BatmanJoke, pout));
                        Thread mathsT = new Thread(new JokeTeller(JokeType.MathsJoke, pout));
                        javaT.start();
                        batmanT.start();
                        mathsT.start();
                    }
                }
                client.close();
                sock.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public void startServer() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket);
                System.out.println("User: '" + clientThread.getUsername() + "' has entered the chat!");

                Thread t = new Thread(clientThread);
                t.start();
            } catch (IOException ignored) {
            }
        }
    }
}