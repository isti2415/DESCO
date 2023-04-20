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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modelClass.User;

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
    @FXML
    private TableView<?> performanceTableView;
    @FXML
    private TableColumn<?, ?> perDateCol;
    @FXML
    private TableColumn<?, ?> perTitleCol;
    @FXML
    private TableColumn<?, ?> perDescriptionCol;
    @FXML
    private TextField perfEmployeeIDTextField;
    @FXML
    private ComboBox<?> perfEmployeeTypeComboBox;
    @FXML
    private Pane pane8;
    
    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);

        

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
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
    }    

    private void viewProfile(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void viewPerformAttendOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewCustomerComplaintOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void viewContractsOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void checkInvOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void viewTargetsOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
        switchPane(8);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"));
            policyTextArea.setWrapText(true);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                policyTextArea.appendText(line + "\n");
            }
            bufferedReader.close();
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

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
    }

    @FXML
    private void updateTargetOnClick(ActionEvent event) {
    }
    
}
