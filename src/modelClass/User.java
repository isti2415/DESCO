package modelClass;

import desco.LoginController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void saveUser() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public boolean verificataion(String userid, String pass) {
        if (id.equals(userid) && password.equals(pass)) {
            return true;
        }
        return false;
    }

    public void logout(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
