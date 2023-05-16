package implement;

import java.net.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class server {
    public void processMessages(BlockingQueue<String> messageQueue) {
        while (true) {
            try {
                String message = messageQueue.take();
                if (message.equals("tell me a joke")) {
                    String[] jokeList = { "Why did the tomato turn red? Because it saw the salad dressing!",
                            "Why was the math book sad? Because it had too many problems!",
                            "Why did the mushroom go to the party? Because hes a Fungi",
                            "What do you call a fish wearing a bow-tie? So-fish-ticated",
                            "What do you call a fake noodle? An impasta!",
                            "What do you call a cow with no legs? Ground beef!",
                            "Why don't seagulls fly by the bay? Because then they would be bagels." };
                    String joke = jokeList[(int) (Math.random() * jokeList.length)];
                    messageQueue.put(joke);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

            server serverInstance = new server();
            Thread messageProcessorT = new Thread(() -> serverInstance.processMessages(messageQueue));
            messageProcessorT.start();

            while (true) {
                Socket client = sock.accept();
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String input;
                while ((input = bin.readLine()) != null) {
                    if (input.toLowerCase().equals("tell me a joke")) {
                        messageQueue.put(input);
                    }
                }
                client.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
