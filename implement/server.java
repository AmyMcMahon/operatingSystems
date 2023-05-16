package implement;

import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            while (true) {
                Socket client = sock.accept();
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                String input;
                while ((input = bin.readLine()) != null) {
                    if (input.toLowerCase().equals("tell me a joke")) {
                        String[] jokeList = { "Why did the tomato turn red? Because it saw the salad dressing!",
                                "Why was the math book sad? Because it had too many problems!",
                                "Why did the mushroom go to the party? Because hes a Fungi",
                                "What do you call a fish wearing a bow-tie? So-fish-ticated",
                                "What do you call a fake noodle? An impasta!",
                                "What do you call a cow with no legs? Ground beef!",
                                "Why don't seagulls fly by the bay? Because then they would be bagels." };
                        String joke = jokeList[(int) (Math.random() * jokeList.length)];
                        pout.println(joke);
                        System.out.println(joke);
                    }
                }
                client.close();
                sock.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
