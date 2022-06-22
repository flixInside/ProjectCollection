package Account;

import Database.UserManegment;
import GUIs.ErrorNote;

public class User {

    private static String username, email, password;
    private static int id;

    public User(String pUsername, String pEmail, String pPassword){
        username = pUsername;
        email = pEmail;
        password = pPassword;
        id = Administration.users.size() + 1;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getID(){
        return id;
    }

    public char[] getPasswordAsChars(){
        char chars[] = new char[password.length()];
        for(int i = 0; i < password.length(); i++){
            chars[i] = password.charAt(i);
        }
        return chars;
    }

    public void setUsername(String pUsername){
        if(validUsername(pUsername)){
            username = pUsername;
            UserManegment.updateList();
        }
    }

    public void setEmail(String pEmail){
        email = pEmail;
        UserManegment.updateList();
    }

    public void setPassword(String pPassword){
        password = pPassword;
        UserManegment.updateList();
    }

    public static boolean validUsername(String pUsername){
        for(User u : Administration.users){
            if(u.getUsername() == pUsername){
                new ErrorNote("The username is already taken. Please choose another username.");
                return false;
            }
        }
        return true;
    }
    
}
