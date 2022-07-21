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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement", "felix",
                    "PASSWORD");
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
            readData();
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

    private static ArrayList<ArrayList<String>> readData() {
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

    public static String readData(String what, int userID) {
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

    public static void writeData(int userID, String what, String intoWhat) {
        intoWhat = "'" + intoWhat + "'";
        try {
            Connection con = getConnection();
            PreparedStatement isertion = con
                    .prepareStatement("UPDATE usertable set " + what + " = " + intoWhat + " WHERE ID = " + userID);
            isertion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeData(int userID, String username, String email, String password) {
        try {
            Connection con = getConnection();
            PreparedStatement isertion = con
                    .prepareStatement("UPDATE usertable set username = '" + username + "' WHERE ID = " + userID);
            isertion.executeUpdate();

            isertion = con.prepareStatement("UPDATE usertable set username = '" + email + "' WHERE ID = " + userID);
            isertion.executeUpdate();

            isertion = con.prepareStatement("UPDATE usertable set username = '" + password + "' WHERE ID = " + userID);
            isertion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateList() {
        ArrayList<ArrayList<String>> list = readData();

        if (list.size() > Administration.users.size()) {
            for (int i = Administration.users.size(); i < list.size(); i++) {
                System.out.println(list.get(i).get(1));
                System.out.println(i);
                Administration.users.add(new User(list.get(i).get(1), list.get(i).get(2), list.get(i).get(3)));
                System.out.println(Administration.users.get(0).getUsername());
                System.out.println(Administration.userListToString());
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("for");
            User user = Administration.users.get(i);
            if (!list.get(i).get(0).equals(String.valueOf(Administration.users.get(i).getID()))) {

            }
            if (!list.get(i).get(1).equals(Administration.users.get(i).getUsername())) {
                System.out.println("username");
                Administration.getUserWithID(user.getID()).setUsername(list.get(i).get(1));
            }
            if (!list.get(i).get(2).equals(Administration.users.get(i).getEmail())) {
                System.out.println("email");
                Administration.getUserWithID(user.getID()).setEmail(list.get(i).get(2));
            }
            if (!list.get(i).get(3).equals(Administration.users.get(i).getPassword())) {
                System.out.println("password");
                Administration.getUserWithID(user.getID()).setPassword(list.get(i).get(3));
            }

        }

        System.out.println("done");

    }

    public static void main(String[] args) throws Exception {
        new Administration(false);
        // System.out.println(Administration.users.size());
        //System.out.println(Administration.userListToString());
    }

}
