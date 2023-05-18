package implement;

import java.io.PrintWriter;
import java.util.Random;

public class JokeTeller implements Runnable {
    private JokeType jokeType;
    private Random random = new Random();
    private PrintWriter pout;

    private String[] punJokes = {"Why did the Java developer refuse to hug his coworkers? He didn't want to start a NullPointerException.",
            "Why don't programmers like nature? It has too many bugs.",
            "Why do Java developers wear glasses? Because they can't C#!",
            "Why did the Java developer take a break? He needed to write his JavaDoc.",
            "Why don't Java developers trust atoms? They make up everything.",
            "Why was the JavaScript developer sad? He didn't know how to `null` his feelings.",
            "Why did the Java developer go broke? Because he used up all his cache.",
            "Why don't Java developers go camping? They don't like to `throw` exceptions.",
            "Why is it hard to tell a Java developer when they've got a sunburn? They don't like to be anywhere near the `burn` keyword."};

    public JokeTeller(JokeType jokeType, PrintWriter pout) {
        this.jokeType = jokeType;
        this.pout = pout;
    }

    @Override
    public void run() {
        if (jokeType == JokeType.PunJoke) {
            String joke = getJoke(punJokes);
            System.out.println("Sent pun joke: " + joke);
            pout.println(joke);
        }
        return;
    }

    private String getJoke(String[] jokes) {
        int randomIndex = random.nextInt(jokes.length);
        return jokes[randomIndex];
    }


}
