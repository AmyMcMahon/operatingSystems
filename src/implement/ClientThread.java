package implement;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.username = bufferedReader.readLine();
    }

    public void sendMessage() {

    }

    public void receiveMessages() {

    }

    public String getUsername() {
        return username;
    }

    @Override
    public void run() {

    }
}
