package Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer{
    
    

    public ChatServer() throws IOException {
        try {
            ServerSocket ss = new ServerSocket(4999);
            Socket s = ss.accept();
            System.out.println("Connected");

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msin = "";
            String msout = "";

            while(!msin.equals("end")){
                msin = in.readUTF();
                System.out.println(msin);
                msout = br.readLine();
                out.writeUTF(msout);
                out.flush();
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer();
    }

}
