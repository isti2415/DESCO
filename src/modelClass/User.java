package modelClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String userID;
    private String password;
    private String userType;
    private static final String FILENAME = "users.bin"; // the name of the binary file

    public User(String userID, String password, String userType) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static void saveUsers(List<User> users) {
        try {
            java.io.FileOutputStream fileOut = new java.io.FileOutputStream(FILENAME);
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.println("Users data is saved in " + FILENAME);
        } catch (java.io.IOException e) {
            System.err.println("Error saving users data to " + FILENAME + ": " + e.getMessage());
            // handle the error, such as by throwing an exception or rolling back changes
        }
    }
    
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(FILENAME);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            users = (List<User>) in.readObject();
            System.out.println("Users data is loaded from " + FILENAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users data from " + FILENAME + ": " + e.getMessage());
            // handle the error, such as by throwing an exception or creating a new list
        }
        return users;
    }
}
