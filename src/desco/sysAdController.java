/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.CurrUserID;
import modelClass.Employee;
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
    private TextField profileUserIDTextField;
    @FXML
    private DatePicker profileDOBdatepicker;
    @FXML
    private TextField currPassTextField;
    @FXML
    private TextField profileEmailTextField;
    @FXML
    private TextField profileConNumTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private TextField profileUsernameTextField;

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

    private Employee getCurrUser() throws IOException, ClassNotFoundException {
        // Read the current user ID from the session file
        String userID = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("session.bin"));
            CurrUserID savedUser = (CurrUserID) in.readObject();
            if (savedUser != null) {
                userID = savedUser.getCurrUserID();
            }
            System.out.println(userID);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Look for a matching customer in the customers file
        Employee currUser = null;
        List<Employee> employees = new ArrayList<>();
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employees.bin"))) {
                employees = (List<Employee>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        for (Employee employee : employees) {
            if (employee.getId().equals(userID)) {
                currUser = employee;
                break;
            }
        }

        return currUser;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    // initialize the user list
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);

        // load the user list from the file and add it to the table view
        Employee curr;
        try {
            curr = getCurrUser();
            if (curr != null) {
                profileNameTextField.setText(curr.getName());
                profileUserIDTextField.setText(curr.getId());
                profileDOBdatepicker.setValue(curr.getDoB());
                profileEmailTextField.setText(curr.getEmail());
                profileConNumTextField.setText(curr.getContact());
            }
        } catch (IOException ex) {
            Logger.getLogger(customerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(customerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void manageAccountOnClick(ActionEvent event) {
        switchPane(2);
        userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        ObservableList<User> users = FXCollections.observableList(new ArrayList<>());

        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("users.bin"))) {
                users.addAll((List<User>) inputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions as needed
        }

        userListTableView.setItems((ObservableList<User>) users);

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
    private void logOutOnClick(ActionEvent event) throws IOException {
        User p = new User();
        p.logout(event);
    }

    @FXML
    private void deleteUserOnClick(ActionEvent event) throws IOException {
        // get the selected user from the table
        User selectedUser = userListTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("No user selected.");
        }
        userListTableView.getItems().clear();
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
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = getCurrUser();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }

    @FXML
    private void resetPasswordOnClick(ActionEvent event) {
    }
}
