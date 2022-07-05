package Account;

import Home.HomeScreen;
import Database.UserManegment;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxExpr.Identifier;

import GUIs.ErrorNote;

public class Administration {

    public static JFrame login, createAccount, settings;
    public static JPanel loginP, createAccountP, settingsP;
    private static JButton createAccountB, goToLoginB, loginB, goToCreateAccountB, backB, saveChangesB, showHidePasswordB;
    private static JLabel userNameL, emailL, passwordL, repeatPasswordL, sNewPasswordL;
    public static JTextField userNameF, emailF, lUserNameF, sUserNameF, sEmailF;
    public static JPasswordField passwordPF, repeatPasswordPF, lPasswordFP, sNewPasswordPF;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    public static ArrayList<User> users;
    public static User currentUser;

    public Administration(boolean showLoginGUI) throws Exception {
        loginP = new JPanel();
        createAccountP = new JPanel();
        settingsP = new JPanel();
        users = new ArrayList<>();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        loginP.setLayout(gbl);
        createAccountP.setLayout(gbl);
        settingsP.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        UserManegment.updateList();

        initialize(showLoginGUI);
    }

    private static void initialize(boolean showLoginGUI) throws FontFormatException, IOException {
        // create input stream for FontAwesome icons
        InputStream is = HomeScreen.class.getResourceAsStream("/FontAwesome/fontawesome-webfont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        font = font.deriveFont(Font.PLAIN, 24f);

        loginGUI(showLoginGUI);
        createAccountGUI();
        settingsGUI();
    }

    private static void loginGUI(boolean showLoginGUI) {
        login = new JFrame("Login");
        login.add(loginP);
        login.setMinimumSize(new Dimension(400, 150));
        login.pack();
        login.setFocusable(true);

        userNameL = new JLabel("Username:");
        passwordL = new JLabel("Password:");

        lUserNameF = new JTextField();
        lPasswordFP = new JPasswordField();

        goToCreateAccountB = new JButton("Don't have an account? Create one");
        loginB = new JButton("Login");

        userNameL.setHorizontalAlignment(JLabel.RIGHT);
        passwordL.setHorizontalAlignment(JLabel.RIGHT);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginP.add(userNameL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginP.add(lUserNameF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginP.add(passwordL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginP.add(lPasswordFP, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginP.add(goToCreateAccountB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginP.add(loginB, gbc);

        goToCreateAccountB.addActionListener(e -> goToCreateAccount());
        loginB.addActionListener(e -> login());

        if(showLoginGUI){ 
            login.setVisible(true);
        }
    }

    private static void createAccountGUI() {
        createAccount = new JFrame("Create Account");
        createAccount.add(createAccountP);
        createAccount.setMinimumSize(new Dimension(400, 200));
        createAccount.pack();
        createAccount.setFocusable(true);

        userNameL = new JLabel("Username:");
        emailL = new JLabel("E-mail:");
        passwordL = new JLabel("Password:");
        repeatPasswordL = new JLabel("Repeat Password:");

        userNameF = new JTextField();
        emailF = new JTextField();
        passwordPF = new JPasswordField();
        repeatPasswordPF = new JPasswordField();

        goToLoginB = new JButton("Already have an Account? Login");
        createAccountB = new JButton("Create Account");

        userNameL.setHorizontalAlignment(JLabel.RIGHT);
        emailL.setHorizontalAlignment(JLabel.RIGHT);
        passwordL.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordL.setHorizontalAlignment(JLabel.RIGHT);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        createAccountP.add(userNameL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        createAccountP.add(userNameF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        createAccountP.add(emailL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        createAccountP.add(emailF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        createAccountP.add(passwordL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        createAccountP.add(passwordPF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        createAccountP.add(repeatPasswordL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        createAccountP.add(repeatPasswordPF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        createAccountP.add(goToLoginB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        createAccountP.add(createAccountB, gbc);

        goToLoginB.addActionListener(e -> goToLogin());
        createAccountB.addActionListener(e -> newUser());
    }

    public static void settingsGUI() {
        settings = new JFrame("Settings");
        settings.add(settingsP);
        settings.setMinimumSize(new Dimension(400, 200));
        settings.pack();
        settings.setFocusable(true);

        backB = new JButton("Back");
        saveChangesB = new JButton("Save changes");
        showHidePasswordB = new JButton("\uf06e");
        userNameL = new JLabel("Username:");
        emailL = new JLabel("E-Mail:");
        passwordL = new JLabel("Password:");
        sNewPasswordL = new JLabel();
        sUserNameF = new JTextField(); 
        sEmailF = new JTextField();
        sNewPasswordPF = new JPasswordField();

        userNameL.setHorizontalAlignment(JLabel.RIGHT);
        emailL.setHorizontalAlignment(JLabel.RIGHT);
        passwordL.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordL.setHorizontalAlignment(JLabel.RIGHT);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        settingsP.add(userNameL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        settingsP.add(sUserNameF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        settingsP.add(emailL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        settingsP.add(sEmailF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        settingsP.add(passwordL, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        settingsP.add(showHidePasswordB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        settingsP.add(sNewPasswordL, gbc);
        settingsP.add(sNewPasswordPF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        settingsP.add(backB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        settingsP.add(saveChangesB, gbc);

        backB.addActionListener(e -> goToHomeScreen());
        saveChangesB.addActionListener(e -> editProfile());
        showHidePasswordB.addActionListener(e -> showHidePassword());
    }

    private static void newUser() {
        if (passwordPF.getPassword() != null && userNameF.getText() != null && emailF.getText() != null) {
            if (passwordToString(passwordPF.getPassword()).equals(passwordToString(repeatPasswordPF.getPassword()))) {
                if(User.validUsername(userNameF.getText().toLowerCase())){
                    try {
                        UserManegment.newUser(new User(userNameF.getText().toLowerCase(), emailF.getText().toLowerCase(),
                                passwordToString(passwordPF.getPassword())));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    login.setVisible(true);
                    createAccount.setVisible(false);
                }                
            } else {
                new ErrorNote("Passwords aren't the same. Please repeat your password.");
            }
        } else {
            new ErrorNote("Please fill in all fields.");
        }
    }

    private static void goToLogin() {
        createAccount.setVisible(false);
        login.setVisible(true);
    }

    private static void goToCreateAccount() {
        login.setVisible(false);
        createAccount.setVisible(true);
    }

    private static void login() {
        boolean userfound = false;
        for (User u : users) {
            if (u.getUsername().equals(lUserNameF.getText().toLowerCase())) {
                if (checkPassword(lPasswordFP.getPassword(), u.getPasswordAsChars())) {
                    userfound = true;
                    currentUser = u;
                    break;
                }
            }
        }
        if (userfound) {
            login.setVisible(false);
            try {
                new HomeScreen(currentUser);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        } else {
            new ErrorNote("Username or Password incorect. Try again.");
        }
    }

    private static boolean checkPassword(char[] pPassword1, char[] pPassword2){
        String password1 = "", password2 = "";
        for(char c : pPassword1){
            password1 += c;
        }
        for(char c : pPassword2){
            password2 += c;
        }
        return password1.equals(password2);
    }

    private static void goToHomeScreen(){
        settings.setVisible(false);
    }

    private static void editProfile(){
        UserManegment.writeData(currentUser.getID(), sUserNameF.getText(), sEmailF.getText(), passwordToString(sNewPasswordPF.getPassword()));
        settings.setVisible(false);
    }

    private static void showHidePassword(){
        //when password visible
        if(showHidePasswordB.getText().equals("\uf070")){
            showHidePasswordB.setText("\uf06e"); //hidden eye
            sNewPasswordL.setVisible(false);
            sNewPasswordPF.setVisible(true);
        }else{ //when password hidden
            showHidePasswordB.setText("\uf070"); //visible eye
            sNewPasswordL.setVisible(true);
            sNewPasswordPF.setVisible(false);
            sNewPasswordL.setText(passwordToString(sNewPasswordPF.getPassword()));
        }
        
    }

    public static User getUserWithID(int ID) {
        for(User u : users){
            if(u.getID() == ID){
                return u;
            }
        }
        return null;
    }

    public static String passwordToString(char[] password){
        String output = "";
        for(char c : password){
            output += c;
        }
        return output;
    }

    public static String userListToString() {
        String out = "";
        for(User u : users){
            out += "[" + u.getID() + ", " + u.getUsername() + ", " + u.getEmail() + ", " + u.getPassword() + "]" + "\n";
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        new Administration(true);
    }

}
