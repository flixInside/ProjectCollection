package Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Account.Administration;
import Account.User;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class ChatClient {

    private static Socket s;
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel chatL;
    private static JTextField messageF;
    private static JButton sendB, refreshB;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static User user;

    public ChatClient(/*User user*/) throws UnknownHostException, IOException{
        // Initialize objects
        frame = new JFrame("Home Screen");  
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        //ChatClient.user = user;

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);
        
        initialize();

        try{
            s = new Socket("localhost", 4999);

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msin = "";
            String msout = "";

            while(!msin.equals("end")){
                msout = br.readLine();
                out.writeUTF(msout);
                msin = in.readUTF();
                System.out.println(msin);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void initialize() throws FileNotFoundException {
        chatL = new JLabel();
        messageF = new JTextField();
        sendB = new JButton("send");
        refreshB = new JButton("refresh");

        chatL.setBorder(new LineBorder(Color.black, 2));

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(chatL, gbc);
        gbc.weightx = 0.8;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(messageF, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 1;
        panel.add(refreshB, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 2;
        panel.add(sendB, gbc);

        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        //new Administration(false);
        new ChatClient(/*Administration.users.get(0)*/);
    }

}
