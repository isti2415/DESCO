/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class sysAdController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<User> userListTableView;
    @FXML
    private TableColumn<User, String> userIDTableColumn;
    @FXML
    private TableColumn<User, String> passwordTableColumn;
    @FXML
    private TableColumn<User, String> userTypeTableColumn;
    @FXML
    private ComboBox<String> usertypeCombo;
    private final String[] userTypes = {"Customer", "Meter Reader", "Billing Administrator",
        "Customer Service Representative", "Field Technician",
        "System Administrator", "Manager", "Human Resources"};
    @FXML
    private TextField useridfield;
    @FXML
    private TextField passwordfield;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<?> activityLogTask;
    @FXML
    private TableColumn<?, ?> userId;
    @FXML
    private TableColumn<?, ?> userAction;
    @FXML
    private TableColumn<?, ?> logDate;
    @FXML
    private Pane pane5;
    @FXML
    private CheckBox pushTickBox;
    @FXML
    private CheckBox emailTickBox;
    @FXML
    private Pane pane6;
    @FXML
    private Label currentVersionLabel;
    @FXML
    private Pane pane7;
    @FXML
    private TableView<?> adminRequestTable;
    @FXML
    private TableColumn<?, ?> adminTableUserID;
    @FXML
    private TableColumn<?, ?> adminTableDate;
    @FXML
    private TableView<?> twoFARequestTable;
    @FXML
    private TableColumn<?, ?> twoFATableUserID;
    @FXML
    private TableColumn<?, ?> twoFATableDate;
    @FXML
    private Pane pane8;
    @FXML
    private TableView<?> resetPasswordTaskList;
    @FXML
    private TableColumn<?, ?> fileType;
    @FXML
    private TableColumn<?, ?> fileSize;
    @FXML
    private TableColumn<?, ?> backupDate;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane1;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TextField profileUsernameTextField;
    @FXML
    private DatePicker profileDOBdatepicker;
    @FXML
    private TextField currPassTextField;
    @FXML
    private TextField profileEmailTextField;
    @FXML
    private TextField customerConNumTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private TableColumn<User, String> nameTableColumn;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);

        switch (paneNumber) {
            case 1:
                pane1.setVisible(true);
                break;
            case 2:
                pane2.setVisible(true);
                break;
            case 3:
                pane3.setVisible(true);
                break;
            case 4:
                pane4.setVisible(true);
                break;
            case 5:
                pane5.setVisible(true);
                break;
            case 6:
                pane6.setVisible(true);
                break;
            case 7:
                pane7.setVisible(true);
                break;
            case 8:
                pane8.setVisible(true);
                break;
            case 9:
                pane9.setVisible(true);
                break;
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    // initialize the user list
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        usertypeCombo.getItems().addAll(userTypes);
        userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        
        // load the user list from the file and add it to the table view
        userListTableView.getItems().addAll(User.loadUsers());
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void manageAccountOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewEnergyTrendsOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void viewActivtyLogOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void viewNotificationSceneOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void viewAppUpdateOnScene(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void viewSecuritySceneOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void viewBackupOnSceneOnClick(ActionEvent event) {
        switchPane(8);
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
        switchPane(9);
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"))) {
                policyTextArea.setWrapText(true);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    policyTextArea.appendText(line + "\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desco/login.fxml"));
            Parent root = loader.load();
            desco.LoginController loginController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void addUserOnClick(ActionEvent event) {
        User user = new User(useridfield.getText(), passwordfield.getText(), usertypeCombo.getValue());
        userListTableView.getItems().clear();
        userListTableView.getItems().addAll(User.loadUsers());
    }

    @FXML
    private void deleteUserOnClick(ActionEvent event) {
        // get the selected user from the table
        User selectedUser = userListTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("No user selected.");
        }
        userListTableView.getItems().clear();
        userListTableView.getItems().addAll(User.loadUsers());
    }

    @FXML
    private void generateIDOnAction(ActionEvent event) {
        String userType = usertypeCombo.getValue();
        int startingID = 0;

        switch (userType) {
            case "Customer":
                startingID = 1000;
                break;
            case "Meter Reader":
                startingID = 2000;
                break;
            case "Billing Administrator":
                startingID = 3000;
                break;
            case "Customer Service Representative":
                startingID = 4000;
                break;
            case "Field Technician":
                startingID = 5000;
                break;
            case "System Administrator":
                startingID = 6000;
                break;
            case "Manager":
                startingID = 7000;
                break;
            case "Human Resources":
                startingID = 8000;
                break;
            default:
                break;
        }

        int maxID = startingID;
        for (User user : User.loadUsers()) {
            if (user.getUserType().equals(userType)) {
                int userID = Integer.parseInt(user.getUserID());
                if (userID > maxID) {
                    maxID = userID;
                }
            }
        }

        int nextID = maxID + 1;
        useridfield.setText(Integer.toString(nextID));
        passwordfield.setText("123");
    }

    @FXML
    private void billDueToggle(ActionEvent event) {
    }

    @FXML
    private void promoToggle(ActionEvent event) {
    }

    @FXML
    private void outageToggle(ActionEvent event) {
    }

    @FXML
    private void accountToggle(ActionEvent event) {
    }

    @FXML
    private void energySaveToggle(ActionEvent event) {
    }

    @FXML
    private void emergencyToggle(ActionEvent event) {
    }

    @FXML
    private void uploadOnClick(ActionEvent event) {
    }

    @FXML
    private void scheduleOnClick(ActionEvent event) {
    }

    @FXML
    private void pushOnCLick(ActionEvent event) {
    }

    @FXML
    private void rollbackOnClick(ActionEvent event) {
    }

    @FXML
    private void acceptOnClick(ActionEvent event) {
    }

    @FXML
    private void rejectOnClick(ActionEvent event) {
    }

    @FXML
    private void editPassOnClick(ActionEvent event) {
    }

    @FXML
    private void editNetworkOnClick(ActionEvent event) {
    }

    @FXML
    private void editDataOnClick(ActionEvent event) {
    }

    @FXML
    private void viewPassDetailsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewNetworkDetailsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewDataEncryptionDetailsOnClick(ActionEvent event) {
    }

    @FXML
    private void verifyOnClick(ActionEvent event) {
    }

    @FXML
    private void flagOnClick(ActionEvent event) {
    }

    @FXML
    private void initiateBackupOnClick(ActionEvent event) {
    }

    @FXML
    private void scheduleAutoBackupOnClick(ActionEvent event) {
    }

    @FXML
    private void restorePreviousVersionOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

}
