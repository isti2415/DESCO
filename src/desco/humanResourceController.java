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
public class humanResourceController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private ComboBox<?> deptComboBox1;
    @FXML
    private TableView<?> employeeInfoTable;
    @FXML
    private TableColumn<?, ?> infoTypeColumn1;
    @FXML
    private TableColumn<?, ?> detailsColumn1;
    @FXML
    private TextField idTextField1;
    @FXML
    private TextField nameTextField1;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> attendanceTable;
    @FXML
    private TableColumn<?, ?> idColumn2;
    @FXML
    private TableColumn<?, ?> nameColumn2;
    @FXML
    private TableColumn<?, ?> presenceColumn2;
    @FXML
    private TableColumn<?, ?> reasonColumn2;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<?> payrollTable;
    @FXML
    private TableColumn<?, ?> paymentTypeColumn3;
    @FXML
    private TableColumn<?, ?> amountColumn3;
    @FXML
    private ComboBox<?> deptComboBox3;
    @FXML
    private TextField idTextField3;
    @FXML
    private TextField nameTextField3;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> performanceTable;
    @FXML
    private TableColumn<?, ?> taskColumn4;
    @FXML
    private TableColumn<?, ?> completionColumn4;
    @FXML
    private ComboBox<?> deptComboBox4;
    @FXML
    private TextField idTextField4;
    @FXML
    private TextField nameTextField4;
    @FXML
    private Pane pane6;
    @FXML
    private TableView<?> jobOpeningTable;
    @FXML
    private TableColumn<?, ?> infoColumn5;
    @FXML
    private TableColumn<?, ?> detailsColumn5;
    @FXML
    private Pane pane7;
    @FXML
    private ComboBox<?> deptComboBox6;
    @FXML
    private TableView<?> employeeOnboardTable;
    @FXML
    private TableColumn<?, ?> infoColumn6;
    @FXML
    private TableColumn<?, ?> detailsColumn6;
    @FXML
    private TextField idTextField6;
    @FXML
    private TextField nameTextField6;
    @FXML
    private Pane pane8;
    @FXML
    private TableView<?> employeeOffboardTable;
    @FXML
    private TableColumn<?, ?> idColumn7;
    @FXML
    private TableColumn<?, ?> nameColumn7;
    @FXML
    private TableColumn<?, ?> departmentColumn7;
    @FXML
    private TableColumn<?, ?> positionColumn7;
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
    private Pane pane9;
    @FXML
    private Label policyViewTextLabel;

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
     *
     * @param url
     * @param rb
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
    private void employeeInfoOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void employeeAttendanceOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void payrollOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void employeePerformanceOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void jobOpeningOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void employeeOnboardOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void employeeOffOnClick(ActionEvent event) {
        switchPane(8);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
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
    private void save1OnClick(ActionEvent event) {
    }

    @FXML
    private void saveS2OnClick(ActionEvent event) {
    }

    @FXML
    private void saveS3OnClick(ActionEvent event) {
    }

    @FXML
    private void saveS4OnClick(ActionEvent event) {
    }

    @FXML
    private void viewPrevOpeningsS5OnClick(ActionEvent event) {
    }

    @FXML
    private void saveS5OnClick(ActionEvent event) {
    }

    @FXML
    private void saveS6OnClick(ActionEvent event) {
    }

    @FXML
    private void offboardS7OnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

}