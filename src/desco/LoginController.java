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
            int dashboardType = 0; // default value for invalid user IDs
            int userid;
            userid = Integer.parseInt(user.getUserID());
            if (userid >= 1000 && userid < 2000) {
                if (userid >= 1000 && userid < 2000) {
                    dashboardType = 1; // set flag for users with userID between 1000 and 2000
                } else if (userid >= 2000 && userid < 3000) {
                    dashboardType = 2; // set flag for users with userID between 2000 and 3000
                } else if (userid >= 3000 && userid < 4000) {
                    dashboardType = 3; // set flag for users with userID between 3000 and 4000
                } else if (userid >= 4000 && userid < 5000) {
                    dashboardType = 4; // set flag for users with userID between 4000 and 5000
                } else if (userid >= 5000 && userid < 6000) {
                    dashboardType = 5; // set flag for users with userID between 5000 and 6000
                } else if (userid >= 6000 && userid < 7000) {
                    dashboardType = 6; // set flag for users with userID between 6000 and 7000
                } else if (userid >= 7000 && userid < 8000) {
                    dashboardType = 7; // set flag for users with userID between 7000 and 8000
                } else if (userid >= 8000 && userid < 9000) {
                    dashboardType = 8; // set flag for users with userID between 8000 and 9000
                }
            }
            switch (dashboardType) {
                case 1:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer.fxml"));
                        Parent root = loader.load();
                        customerController customerController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 2:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MeterReader.fxml"));
                        Parent root = loader.load();
                        MeterReaderController MeterReaderController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 3:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("BillingAdmin.fxml"));
                        Parent root = loader.load();
                        BillingAdminController BillingAdminController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 4:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerServiceRep.fxml"));
                        Parent root = loader.load();
                        customerServiceController customerServiceController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 5:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("technician.fxml"));
                        Parent root = loader.load();
                        technicianController technicianController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 6:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("sysAd.fxml"));
                        Parent root = loader.load();
                        sysAdController sysAdController = loader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                    break;
                case 7:
//                    try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager.fxml"));
//                        Parent root = loader.load();
//                        ManagerController ManagerController = loader.getController();
//                        Scene scene = new Scene(root);
//                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        stage.setScene(scene);
//                        stage.show();
//                    } catch (IOException ex) {
//                    }
                    break;
                case 8:
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("humanResource.fxml"));
                        Parent root = loader.load();
                        humanResourceController humanResourceController = loader.getController();
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
