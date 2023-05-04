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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelClass.CurrUser;
import modelClass.Customer;
import modelClass.Employee;
import modelClass.User;
import modelClass.Version;

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
    @FXML
    private Label versionLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Version> versionList = Version.loadVersion();
        if (!versionList.isEmpty()) {
            versionLabel.setText("Current version: v" + versionList.get(versionList.size() - 1).getVersion());
        }
    }

    @FXML
    private void loginOnClick(ActionEvent event) throws IOException {
        String userID = useridfield.getText(); // get the entered user ID
        String password = passwordfield.getText(); // get the entered password
        List<User> users = User.loadUser();
        Boolean match = false;
        for (User p : users) {
            if (p.verification(userID, password)) {
                match=true;
                CurrUser c = new CurrUser(userID);
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
                    String userType = "";
                    for (Employee employee : Employee.loadEmployee()) {
                        if (p.getId().equals(employee.getId())) {
                            userType = employee.getType();
                            break;
                        }
                    }
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
        if(match.equals(false)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Either id or password is wrong");
                alert.showAndWait();
        }
    }
}
