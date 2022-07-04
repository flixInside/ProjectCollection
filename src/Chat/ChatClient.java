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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//import Account.Administration;
//import Account.User;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class ChatClient implements Runnable {

    private static Socket s;
    private static JFrame frame;
    private static JPanel panel, chatP;
    private static JTextField messageF;
    private static JTextArea chatA;
    private static JButton sendB;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    // private static User user;

    public ChatClient(/* User user */) throws UnknownHostException, IOException {
        // Initialize objects
        frame = new JFrame("Home Screen");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // ChatClient.user = user;

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

        Thread thread = new Thread(this);
        thread.start();

        s = new Socket("localhost", 4999);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }

    private static void initialize() throws FileNotFoundException {
        chatA = new JTextArea();
        messageF = new JTextField();
        sendB = new JButton("send");
        chatP = new JPanel();

        chatP.setBorder(new LineBorder(Color.black, 2));

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(chatP, gbc);
        gbc.weightx = 0.9;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(messageF, gbc);
        gbc.weightx = 0.1;
        gbc.gridx = 2;
        panel.add(sendB, gbc);
        chatP.add(chatA);

        sendB.addActionListener(e -> {
            try {
                send();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    private static void send() throws IOException {
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println(messageF.getText());
        pw.flush();
        messageF.setText("");
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String message = "";
            try {
                message = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            chatA.setText(chatA.getText() + message);
        } catch (IOException e) {
            
        }
    }

    public static void main(String[] args) throws Exception {
        // new Administration(false);
        new ChatClient(/* Administration.users.get(0) */);
    }

}
