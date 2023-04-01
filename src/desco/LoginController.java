package desco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField useridfield;
    @FXML
    private PasswordField passwordfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginOnClick(ActionEvent event) {
        String userId = useridfield.getText();
        String password = passwordfield.getText();

        // try-with-resources to automatically close the stream
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"))) {

            // read the entire list of users from the file
            List<User> Users = (List<User>) ois.readObject();

            // loop through the users to find a matching user
            for (User user : Users) {
                if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                    // switch scene based on usertype
                    switch (user.getUserType()) {
                        case "Customer":
                            // switch to customer scene
                            break;
                        case "Meter Reader":
                            // switch to meter reader scene
                            break;
                        case "Billing Administrator":
                            // switch to billing administrator scene
                            break;
                        case "Customer Service Representative":
                            // switch to customer service representative scene
                            break;
                        case "Field Technician":
                            // switch to field technician scene
                            break;
                        case "System Administrator":
                            // switch to system administrator scene
                            break;
                        case "Manager":
                            // switch to manager scene
                            break;
                        case "Human Resources":
                            // switch to human resources scene
                            break;
                        default:
                            // unrecognized user type
                            break;
                    }
                    return;
                }
            }

            // display error message if no matching user is found
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid user ID or Password");
            alert.setContentText("Please enter a valid user ID and Password.");
            alert.showAndWait();

        } catch (FileNotFoundException ex) {
            // display error message if the file is not found
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("File Not Found");
            alert.setContentText("The user database file is missing.");
            alert.showAndWait();
        } catch (IOException | ClassNotFoundException ex) {
            // display error message if there is an error reading the file
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Error Reading user Database");
            alert.setContentText("There was an error reading the user database file.");
            alert.showAndWait();
        }
    }
}
