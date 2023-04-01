/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginOnClick(ActionEvent event) {
        String userID = useridfield.getText(); // get the entered user ID
        String password = passwordfield.getText(); // get the entered password
        User user = null;
        List<User> userList = User.loadUsers();
        for (User u : userList) {
            if (u.getUserID().equals(userID) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if (user != null) {
            // redirect to the appropriate dashboard based on user type
            switch (user.getUserType()) {
                case "Customer":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Customer/Scene1.fxml"));
                        Parent root = loader.load();
                        Customer.Scene1Controller customerScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Meter Reader":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MeterReader/Scene1.fxml"));
                        Parent root = loader.load();
                        MeterReader.Scene1Controller meterReaderScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Billing Administrator":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BillingAdministrator/Scene1.fxml"));
                        Parent root = loader.load();
                        BillingAdministrator.Scene1Controller billingAdministratorScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Customer Service Representative":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerServiceRepresentative/Scene1.fxml"));
                        Parent root = loader.load();
                        CustomerServiceRepresentative.Scene1Controller customerServiceRepresentativeScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Field Technician":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FieldTechnician/Scene1.fxml"));
                        Parent root = loader.load();
                        FieldTechnician.Scene1Controller fieldTechnicianScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "System Administrator":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SystemAdministrator/Scene1.fxml"));
                        Parent root = loader.load();
                        SystemAdministrator.Scene1Controller systemAdministratorScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Manager":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Manager/Scene1.fxml"));
                        Parent root = loader.load();
                        Manager.Scene1Controller managerScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case "Human Resources":
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HumanResources/Scene1.fxml"));
                        Parent root = loader.load();
                        HumanResources.Scene1Controller humanResourcesScene1Controller = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                default:
                    System.out.println("Invalid username or password.");
            }
        }
    }
}
