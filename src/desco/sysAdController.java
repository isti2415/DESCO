/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class sysAdController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<?> userListTableView;
    @FXML
    private TableColumn<?, ?> userIDTableColumn;
    @FXML
    private TableColumn<?, ?> passwordTableColumn;
    @FXML
    private TableColumn<?, ?> userTypeTableColumn;
    @FXML
    private ComboBox<?> usertypeCombo;
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
    private Label policyViewTextLabel;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
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
    }

    @FXML
    private void deleteUserOnClick(ActionEvent event) {
    }

    @FXML
    private void generateIDOnAction(ActionEvent event) {
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
