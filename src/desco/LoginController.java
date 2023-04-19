/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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
import modelClass.Customer;
import modelClass.Employee;
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
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("users.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User p;
            try {
                while (true) {
                    p = (User) ois.readObject();
                    if (p.verificataion(userID, password)) {
                        if (p instanceof Customer) {
                            // redirect to customer dashboard
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/desco/customer.fxml"));
                                Parent root = loader.load();
                                desco.customerController customerController = loader.getController();
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                            }
                        } else if (p instanceof Employee) {
                            Employee employee = (Employee) p;
                            String userType = employee.getType();
                            // redirect to appropriate dashboard based on employee type
                            switch (userType) {
                                case "Meter Reader":
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MeterReader.fxml"));
                                        Parent root = loader.load();
                                        meterReaderController meterReaderController = loader.getController();
                                        Scene scene = new Scene(root);
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException ex) {
                                    }
                                    break;
                                case "Billing Administrator":
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("BillingAdmin.fxml"));
                                        Parent root = loader.load();
                                        billingAdminController billingAdminController = loader.getController();
                                        Scene scene = new Scene(root);
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException ex) {
                                    }
                                    break;
                                case "Customer Service Representative":
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
                                case "Field Technician":
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
                                case "System Administrator":
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
                                case "Manager":
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager.fxml"));
                                        Parent root = loader.load();
                                        ManagerController managerController = loader.getController();
                                        Scene scene = new Scene(root);
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException ex) {
                                    }
                                    break;
                                case "Human Resources":
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("humanResource.fxml"));
                                        Parent root = loader.load();
                                        humanResourceController humanResourcesController = loader.getController();
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
            }//end of nested try
            catch (IOException | ClassNotFoundException e) {
                //
            }//nested catch             
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }
    }
}
