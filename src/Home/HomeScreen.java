package Home;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Account.Administration;
import Account.User;
import Calculator.QuickCalculator;
import Games.MasterMind;
import Games.Minesweeper;
import Notes.BulletList;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class HomeScreen {

    public static JFrame frame;
    public static JPanel panel;
    public static GridBagLayout gbl;
    public static GridBagConstraints gbc;
    public static JButton quickCalcB, quickNotesB, bullitB, temp3, temp4, temp5, minesweeperB, masterMindB, settingsB;
    private static User currentUser;

    public HomeScreen(User pCurrentUser) throws FontFormatException, IOException {
        // Initialize objects
        frame = new JFrame("Home Screen");  
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        currentUser = pCurrentUser;

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
    }

    public static User getCurrentUser(){
        return currentUser;
    } 

    private static void initialize() throws FontFormatException, IOException {
        quickCalcB = new JButton("\uf1ec");
        quickNotesB = new JButton("\uf15b");
        bullitB = new JButton("\uf03a");
        temp3 = new JButton("\uf15b");
        temp4 = new JButton("\uf15b");
        temp5 = new JButton("\uf15b");
        minesweeperB = new JButton("\uf1e2");
        masterMindB = new JButton("\uf1fc");
        settingsB = new JButton("\uf013");

        // create input stream for FontAwesome icons
        InputStream is = HomeScreen.class.getResourceAsStream("/FontAwesome/fontawesome-webfont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        font = font.deriveFont(Font.PLAIN, 24f);

        quickCalcB.setFont(font);
        quickNotesB.setFont(font);
        bullitB.setFont(font);
        temp3.setFont(font);
        temp4.setFont(font);
        temp5.setFont(font);
        minesweeperB.setFont(font);
        masterMindB.setFont(font);
        settingsB.setFont(font);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(quickCalcB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(quickNotesB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(bullitB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(temp3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(temp4, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(temp5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(minesweeperB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(masterMindB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(settingsB, gbc);

        quickCalcB.addActionListener(e -> quickCalculator());
        quickNotesB.addActionListener(e -> quickCalculator());
        bullitB.addActionListener(e -> bulletList());
        temp3.addActionListener(e -> quickCalculator());
        temp4.addActionListener(e -> quickCalculator());
        temp5.addActionListener(e -> quickCalculator());
        minesweeperB.addActionListener(e -> minesweeper());
        masterMindB.addActionListener(e -> masterMind());
        settingsB.addActionListener(e -> settingsGUI());

        frame.setVisible(true);
    }

    private static void quickCalculator() {
        new QuickCalculator();
    }

    private static void bulletList(){
        new BulletList();
    }

    private static void settingsGUI(){
        Administration.settings.setVisible(true);
        Administration.sUserNameF.setText(currentUser.getUsername());
        Administration.sEmailF.setText(currentUser.getEmail());
        Administration.sNewPasswordPF.setText(currentUser.getPassword());
    }

    private static void masterMind(){
        new MasterMind();
    }

    private static void minesweeper(){
        new Minesweeper();
    }

    public static void main(String[] args) throws Exception{
        new Administration(false);
        new HomeScreen(Administration.users.get(0));
    }

}
