package modelClass;

import desco.LoginController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class User implements Serializable {

    private static final String FILENAME = "users.bin";

    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        saveUser();
    }

    public User() {

    }

    public void setId(String id) {
        this.id = id;
        updateUser();
    }

    public void setPassword(String password) {
        this.password = password;
        updateUser();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    private void saveUser() {
        List<User> userList = loadUser();
        boolean exists = false;
        for (User user : userList) {
            if (user.getId().equals(this.getId())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("User already exists");
        } else {
            userList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(userList);
                System.out.println("User saved to users.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private void updateUser() {
        List<User> userList = loadUser();
        Boolean found = false;
        for (User user : userList) {
            if (user.getId().equals(this.getId())) {
                userList.remove(user);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("User not found");
        } else {
            userList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(userList);
                System.out.println("User saved to users.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    public static List<User> loadUser() {
        List<User> users = new ArrayList<>();
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                users = (List<User>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return users;
    }

    public boolean verification(String userid, String pass) {
        if (id.equals(userid) && password.equals(pass)) {
            return true;
        }
        return false;
    }

    public void logout(ActionEvent event) throws IOException {
        try {
            File file = new File("session.bin");
            file.delete();

            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/desco/login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
        }
    }

}
