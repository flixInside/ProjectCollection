package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Account.Administration;
import Account.User;

public class UserManegment {

    public static Connection getConnection() throws Exception {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement", "felix", "PASSWORD");
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void newUser(User user) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement isertion = con
                    .prepareStatement("INSERT INTO usertable (id, username, email, password) VALUES('" +
                            user.getID() + "', '" + user.getUsername() + "', '" + user.getEmail() + "', '"
                            + user.getPassword() + "')");
            isertion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        updateList();
    }

    public static ArrayList<String> requestData(String what) {
        if (what.equals("*")) {
            requestData();
            return null;
        }
        try {
            Connection con = getConnection();
            PreparedStatement selection = con.prepareStatement("SELECT " + what + " FROM usertable");
            ResultSet result = selection.executeQuery();
            ArrayList<String> output = new ArrayList<>();
            while (result.next()) {
                output.add(result.getString(what));
            }
            return output;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static ArrayList<ArrayList<String>> requestData() {
        try {
            Connection con = getConnection();
            PreparedStatement selection = con.prepareStatement("SELECT * FROM usertable");
            ResultSet result = selection.executeQuery();
            ArrayList<ArrayList<String>> output = new ArrayList<>();
            while (result.next()) {
                ArrayList<String> list = new ArrayList<>();
                list.add(result.getString("ID"));
                list.add(result.getString("username"));
                list.add(result.getString("email"));
                list.add(result.getString("password"));
                output.add(list);
            }
            return output;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String requestData(String what, int userID) {
        try {
            Connection con = getConnection();
            PreparedStatement selection = con
                    .prepareStatement("SELECT " + what + " FROM usertable WHERE ID = " + String.valueOf(userID));
            ResultSet result = selection.executeQuery();
            if (result.next()) {
                return result.getString(what);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void writeData(int userID, String what, String intoWhat){
        intoWhat = "'" + intoWhat + "'";
        try {
            Connection con = getConnection();
            PreparedStatement isertion = con.prepareStatement("UPDATE usertable set " + what + " = " + intoWhat + " WHERE ID = " + userID);
            isertion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateList() {
        ArrayList<ArrayList<String>> list = requestData();

        if (Administration.users.size() >= list.size()) {
            ArrayList<User> removeList = new ArrayList<>();
            for (int i = list.size(); i < Administration.users.size(); i++) {
                removeList.add(Administration.users.get(i));
            }
            Administration.users.removeAll(removeList);
        }

        for (int i = 0; i < Administration.users.size(); i++) {
            if (!String.valueOf(Administration.users.get(i).getID()).equals(list.get(i).get(0))) {
            }
            if (!Administration.users.get(i).getUsername().equals(list.get(i).get(1))) {
                writeData(Administration.users.get(i).getID(), "username", Administration.users.get(i).getUsername());
            }
            if (!Administration.users.get(i).getEmail().equals(list.get(i).get(2))) {
                writeData(Administration.users.get(i).getID(), "email", Administration.users.get(i).getEmail());
            }
            if (!Administration.users.get(i).getPassword().equals(list.get(i).get(3))) {
                writeData(Administration.users.get(i).getID(), "password", Administration.users.get(i).getPassword());
            }

        }

        if (list.size() > Administration.users.size()) {
            for(ArrayList<String> s : list){
                Administration.users.add(new User(s.get(1), s.get(2), s.get(3)));
            }
        }

    }

    public static void main(String[] args){
        
    }

}
