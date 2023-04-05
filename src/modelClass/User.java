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
    private String userType;
    private String userName;
    private LocalDate userDoB;
    private String userEmail;
    private String userContact;

    public User(String userID, String password, String userType) {
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        saveUser();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
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
        return "User{" + "userID=" + userID + ", password=" + password + ", userType=" + userType + ", userName=" + userName + ", userDoB=" + userDoB + ", userEmail=" + userEmail + ", userContact=" + userContact + '}';
    }

    private void saveUser() {
        // Load the list of users from the file
        List<User> userList = User.loadUsers();

        // Check if the user ID of the current user already exists in the list
        boolean exists = false;
        for (User user : userList) {
            if (user.getUserID().equals(this.getUserID())) {
                exists = true;
                break;
            }
        }

        // If the user ID already exists, show an error message and do not save the user
        if (exists) {
            System.out.println("User with this ID already exists");
        } else {
            // Otherwise, add the user to the list and save the list to the file
            userList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("users.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(userList);
                System.out.println("User saved to users.bin file");
            } catch (IOException e) {
                System.out.println("Error saving user to file");
            }
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
