package Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {

    String message;

    public ChatServer() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try (ServerSocket ss = new ServerSocket(4999)) {
            while (true) {
                Socket s = ss.accept();

                System.out.println("Connected");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

                message = br.readLine();
                sendToAll(message, s);

                //s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void sendToAll(String message, Socket s) throws IOException {
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println(message);
        pw.flush();
    }

    public static void main(String[] args) {
        new ChatServer();
    }

}
