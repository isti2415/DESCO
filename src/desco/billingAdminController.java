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

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class BillingAdminController implements Initializable {

    @FXML
    private Pane scene1;
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
    private Pane scene2;
    @FXML
    private TableView<?> ViewCustomerAccountTable;
    @FXML
    private TableColumn<?, ?> CustomerIDColumn;
    @FXML
    private TableColumn<?, ?> ViewInvoiceNumberColumn;
    @FXML
    private TableColumn<?, ?> ViewBillAmountColumn;
    @FXML
    private TableColumn<?, ?> ViewDueDateColumn;
    @FXML
    private Pane scene3;
    @FXML
    private TextField biLLissuecustomerIDTextField;
    @FXML
    private Label currentBillLabel;
    @FXML
    private TextField updatedBillTextField;
    @FXML
    private Pane scene4;
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
    private void viewProfileOnClick(ActionEvent event) {
        
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
    private void ViewCustomerAccountButtononClick(ActionEvent event) {
    }

    @FXML
    private void ViewbillingdisputesButtononclick(ActionEvent event) {
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void GenerateBillButtononClick(ActionEvent event) {
    }

    @FXML
    private void UpdateBillsButtononClick(ActionEvent event) {
    }

    @FXML
    private void GenerateNewBillButtononClick(ActionEvent event) {
    }
    
}
