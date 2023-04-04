/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ManagerController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerUsernameTextField;
    @FXML
    private DatePicker customerDOBdatepicker;
    @FXML
    private TextField currPassTextField;
    @FXML
    private TextField customerEmailTextField;
    @FXML
    private TextField customerConNumTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private Pane pane2;
    @FXML
    private ComboBox<?> attendEmpDeptComboBox;
    @FXML
    private TableView<?> performanceTable;
    @FXML
    private TableColumn<?, ?> goalColumn;
    @FXML
    private TableColumn<?, ?> completionColumn;
    @FXML
    private TableView<?> attendanceColumn;
    @FXML
    private TableColumn<?, ?> daysColumn;
    @FXML
    private TableColumn<?, ?> percentageColumn;
    @FXML
    private TextField attendEmpIDTextField;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> complaintTable;
    @FXML
    private TableColumn<?, ?> complaintColumn;
    @FXML
    private TableColumn<?, ?> customerIDcolumn;
    @FXML
    private TableColumn<?, ?> feedbackColumn;
    @FXML
    private TableColumn<?, ?> employeeIDColumn;
    @FXML
    private Pane pane4;
    @FXML
    private TextArea contractTextArea;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> inventoryTable;
    @FXML
    private TableColumn<?, ?> serialColumn;
    @FXML
    private TableColumn<?, ?> itemColumn;
    @FXML
    private TableColumn<?, ?> invQtyColumn;
    @FXML
    private TableColumn<?, ?> invDepartmentColumn;
    @FXML
    private Pane pane6;
    @FXML
    private ComboBox<?> deptComboBox;
    @FXML
    private TableView<?> reportTable;
    @FXML
    private TableColumn<?, ?> taskColumn;
    @FXML
    private TableColumn<?, ?> reportColumn;
    @FXML
    private TableColumn<?, ?> employeeColumn;
    @FXML
    private Pane pane7;
    @FXML
    private TextArea policyTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewProfile(ActionEvent event) {
    }

    @FXML
    private void viewPerformAttendOnClick(ActionEvent event) {
    }

    @FXML
    private void viewCustomerComplaintOnClick(ActionEvent event) {
    }

    @FXML
    private void viewContractsOnClick(ActionEvent event) {
    }

    @FXML
    private void checkInvOnClick(ActionEvent event) {
    }

    @FXML
    private void viewTargetsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void openContractOnClick(ActionEvent event) {
    }

    @FXML
    private void saveContractOnClick(ActionEvent event) {
    }

    @FXML
    private void restockOnClick(ActionEvent event) {
    }

    @FXML
    private void updateOnClick(ActionEvent event) {
    }
    
}
