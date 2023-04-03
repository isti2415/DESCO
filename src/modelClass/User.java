package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final String USER_FILE_NAME = "users.bin";
    private String userID;
    private String password;
    private String userName;
    private LocalDate userDoB;
    private String userEmail;
    private String userContact;

    public User(String userID, String password, String userName, LocalDate userDoB, String userEmail, String userContact) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.userDoB = userDoB;
        this.userEmail = userEmail;
        this.userContact = userContact;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getUserDoB() {
        return userDoB;
    }

    public void setUserDoB(LocalDate userDoB) {
        this.userDoB = userDoB;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", password=" + password + ", userName=" + userName + ", userDoB=" + userDoB + ", userEmail=" + userEmail + ", userContact=" + userContact + '}';
    }
    
    public void saveUser() {
        try {
            // Load existing users from file
            List<User> existingUsers = loadUsers();
            // Add this user to the list of existing users
            existingUsers.add(this);
            try ( // Write the updated list of users back to the file
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USER_FILE_NAME))) {
                outputStream.writeObject(existingUsers);
            }
        } catch (IOException e) {
        }
    }
    
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USER_FILE_NAME))) {
                users = (List<User>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return users;
    }
}
