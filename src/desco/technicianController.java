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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class technicianController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<?> taskListViewTable;
    @FXML
    private TableColumn<?, ?> taskId;
    @FXML
    private TableColumn<?, ?> taskDescription;
    @FXML
    private TableColumn<?, ?> taskDate;
    @FXML
    private TableColumn<?, ?> taskStatus;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> customerComplainListViewTable;
    @FXML
    private TableColumn<?, ?> complaintID;
    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private TableColumn<?, ?> customerAddress;
    @FXML
    private TableColumn<?, ?> complainDate;
    @FXML
    private TableColumn<?, ?> contactInfo;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<?> ComplainListViewTable;
    @FXML
    private TableColumn<?, ?> complaintID1;
    @FXML
    private TableColumn<?, ?> complaintDescription;
    @FXML
    private TableColumn<?, ?> complainDate1;
    @FXML
    private TableColumn<?, ?> complainStatus;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> faultyEquipmentViewTable;
    @FXML
    private TableColumn<?, ?> equipmentID;
    @FXML
    private TableColumn<?, ?> complaintID2;
    @FXML
    private TableColumn<?, ?> customerAddress1;
    @FXML
    private TableColumn<?, ?> problemDescription;
    @FXML
    private Pane pane6;
    @FXML
    private TableView<?> inventoryEquipmentViewTable;
    @FXML
    private TableColumn<?, ?> equipmentID1;
    @FXML
    private TableColumn<?, ?> equipmentName;
    @FXML
    private TableColumn<?, ?> qtyOfEquipment;
    @FXML
    private Pane pane7;
    @FXML
    private DatePicker dateOfWork;
    @FXML
    private TextField complainIDtextField;
    @FXML
    private TextArea noteTextArea;
    @FXML
    private Pane pane8;
    @FXML
    private DatePicker dateOfWorkOfReport;
    @FXML
    private TextField getComplainID;
    @FXML
    private TextArea noteTextArea1;
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
    private TextArea policyViewTextArea;
    
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
    private void viewTaskListOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewCustomerInfoOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void viewComplaintsOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void checkFaultyEquipmentOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void checkInventoryEquipmentOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void viewUploadDataOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void viewSubmitReportsOnClick(ActionEvent event) {
        switchPane(8);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
        switchPane(9);
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"))) {
                policyViewTextArea.setWrapText(true);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    policyViewTextArea.appendText(line + "\n");
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
    private void selectTaskandMarkasDoneOnClick(ActionEvent event) {
    }

    @FXML
    private void selectTaskandUpdateOnClick(ActionEvent event) {
    }

    @FXML
    private void selectComplainandMarkasResolvedOnClick(ActionEvent event) {
    }

    @FXML
    private void selectEquipmentAndMarkAsRepaired(ActionEvent event) {
    }

    @FXML
    private void selectEquipmentAndRequestRestockOnClick(ActionEvent event) {
    }

    @FXML
    private void fileChooserOnClick(ActionEvent event) {
    }

    @FXML
    private void textChooserOnClick(ActionEvent event) {
    }

    @FXML
    private void ReportChooserOnClick(ActionEvent event) {
    }

    @FXML
    private void submitReportOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }
    
}
