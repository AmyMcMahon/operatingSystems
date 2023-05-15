import java.net.*;
import java.io.*;

public class client {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("127.0.0.1", 6013);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

            pout.println("tell me a joke");
            String response = bin.readLine();
            System.out.println(response);

            sock.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
