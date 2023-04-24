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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class billingAdminController implements Initializable {

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
    private TableColumn<?, ?> CustomerIDColumn;
    @FXML
    private TextField biLLissuecustomerIDTextField;
    @FXML
    private Label currentBillLabel;
    @FXML
    private TextField updatedBillTextField;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private TableColumn<?, ?> ViewUsageColumn;
    @FXML
    private TableColumn<?, ?> ViewDateColumn;
    @FXML
    private TableColumn<?, ?> ViewPaidColumn;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> ViewCustomerBillsTable;
    @FXML
    private TableView<?> ViewDowloadBillsTable;
    @FXML
    private TableColumn<?, ?> ViewBillNumberCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewMonthCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewYearCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewBillAmountCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewBillNumberDownloadColumn;
    @FXML
    private TableColumn<?, ?> ViewMonthDownloadColumn;
    @FXML
    private TableColumn<?, ?> ViewYearDownloadColumn;
    @FXML
    private TableColumn<?, ?> ViewBillAmountDownloadColumn;
    @FXML
    private TextField CustomerIDTestField;
    
    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

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
    private void ViewCustomerBillPaneButtononClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void ViewGenerateBillPaneButtononClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void ViewbillingdisputesButtononclick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        switchPane(5);
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
    private void UpdateBillsButtononClick(ActionEvent event) {
    }

    @FXML
    private void GenerateNewBillButtononClick(ActionEvent event) {
    }

    @FXML
    private void DownloadNewBillButtononClick(ActionEvent event) {
    }

    
    
}
