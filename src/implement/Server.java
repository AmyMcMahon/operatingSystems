package implement;

import java.net.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket sock = new ServerSocket(6013);
            BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
            Server serverInstance = new Server();
            Thread messageProcessorT = new Thread(() -> {
                try {
                    serverInstance.processMessages(messageQueue);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            messageProcessorT.start();
            while (true) {
                Socket client = sock.accept();
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                String input;
                while ((input = bin.readLine()) != null) {
                    if (input.equalsIgnoreCase("threads")) {
                        messageQueue.put(input);
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

    public void processMessages(BlockingQueue<String> messageQueue) throws IOException {
        ServerSocket sock = new ServerSocket(6013);
        Socket client = sock.accept();
        PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
        while (true) {
            try {
                String message = messageQueue.take();
                if (message.equals("threads")) {
                    Thread javaT = new Thread(new JokeTeller(JokeType.JavaJoke, pout));
                    Thread batmanT = new Thread(new JokeTeller(JokeType.BatmanJoke, pout));
                    Thread mathsT = new Thread(new JokeTeller(JokeType.MathsJoke, pout));
                    javaT.start();
                    batmanT.start();
                    mathsT.start();
                }
                client.close();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
