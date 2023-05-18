package implement;

import java.io.PrintWriter;
import java.util.Random;

public class JokeTeller implements Runnable {
    private final JokeType jokeType;
    private final Random random = new Random();
    private final PrintWriter pout;

    private final String[] javaJokes = {
            "Why did the Java developer refuse to hug his coworkers? He didn't want to start a NullPointerException.",
            "Why don't programmers like nature? It has too many bugs.",
            "Why do Java developers wear glasses? Because they can't C#!",
            "Why did the Java developer take a break? He needed to write his JavaDoc.",
            "Why don't Java developers trust atoms? They make up everything.",
            "Why was the JavaScript developer sad? He didn't know how to `null` his feelings.",
            "Why did the Java developer go broke? Because he used up all his cache.",
            "Why is it hard to tell a Java developer when they've got a sunburn? They don't like to be anywhere near the `burn` keyword."
    };

    private final String[] batmanJokes = {
            "What happens when Batman forgets to pay the electricity bills? A dark night.",
            "Why doesn’t Batman like Mr Freeze? Because he always gives him the cold shoulder.",
            "Why does Robin wear bright colors? Because Batman doesn’t want to get shot.",
            "What does Batman go when he’s urgent? The Bat-room.",
            "Why does Batman like Hawaiian pizza? Because it Got-ham.",
            "Where does Batman keep his fish? In the Bat-tub.",
            "What is Batman’s favorite beverage? Vigilan-tea.",
            "What do you call Batman when he falls off this motorbike? Bruised Wayne."
    };

    private final String[] mathsJokes = {
            "I saw my math teacher with a piece of graph paper yesterday. I think he must be plotting something.",
            "How does a mathematician plow fields? With a pro-tractor.",
            "Why was math class so long? The teacher kept going off on a tangent.",
            "Why did the student do multiplication problems on the floor? The teacher told him not to use tables.",
            "How do you solve any equation? Multiply both sides by zero.",
            "What tool is best suited for math? Multi-pliers.",
            "Why did the girl wear glasses during math class? It improved di-vision.",
            "Are monsters good at math? Not unless you Count Dracula."
    };

    public JokeTeller(JokeType jokeType, PrintWriter pout) {
        this.jokeType = jokeType;
        this.pout = pout;
    }

    @Override
    public void run() {
        String joke = "";
        switch (jokeType) {
            case JavaJoke:
                joke = "Java joke: " + getJoke(javaJokes);
                System.out.println("Sent: " + joke);
                break;
            case BatmanJoke:
                joke = "Batman joke: " + getJoke(batmanJokes);
                System.out.println("Sent: " + joke);
                break;

            case MathsJoke:
                joke = "Maths joke: " + getJoke(mathsJokes);
                System.out.println("Sent: " + joke);
                break;

        }

        pout.println(joke);
    }

    private String getJoke(String[] jokes) {
        int randomIndex = random.nextInt(jokes.length);
        return jokes[randomIndex];
    }

}
