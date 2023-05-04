/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.Bill;
import modelClass.CurrUser;
import modelClass.Employee;

public class billingAdminController implements Initializable {

    @FXML
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
    private Pane pane1;
    @FXML
    private Pane pane2;
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
    private TableView<Bill> ViewCustomerBillsTable;
    @FXML
    private TableColumn<Bill, String> ViewBillNumberCustomerColumn;
    @FXML
    private TableColumn<Bill, String> ViewMonthCustomerColumn;
    @FXML
    private TableColumn<Bill, String> ViewYearCustomerColumn;
    @FXML
    private TableColumn<Bill, Float> ViewBillAmountCustomerColumn;
    @FXML
    private TextField CustomerIDTextField;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TableView<?> disputeTableView;
    @FXML
    private TableColumn<?, ?> disputeIDColumn;
    @FXML
    private TableColumn<?, ?> disputeMonthColumn;
    @FXML
    private TableColumn<?, ?> disputeYearColumn;
    @FXML
    private TableColumn<?, ?> disputeAmountColumn;
    @FXML
    private TextField newAmountColumn;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

        switch (paneNumber) {
            case 1:
                pane1.setVisible(true);
                break;
            case 2:
                pane2.setVisible(true);
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
        Employee curr;
        try {
            curr = CurrUser.getEmployee();
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
    private void ViewCustomerBillPaneButtononClick(ActionEvent event) {
        switchPane(2);
        ViewMonthCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("billMonth"));
        ViewYearCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("billYear"));
        ViewBillAmountCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        ViewUsageColumn.setCellValueFactory(new PropertyValueFactory<>("usuage"));
        ViewBillAmountCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        ViewCustomerBillsTable.setItems(FXCollections.observableList(Bill.loadBill()));
    }

    @FXML
    private void ViewbillingdisputesButtononclick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        switchPane(5);
        policyTextArea.clear();
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
    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        CurrUser.getEmployee().logout(event);
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = CurrUser.getEmployee();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
        if (!(currPassTextField.getText().equals("") && newPassTextField.getText().equals(""))) {
            if (currPassTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Your current password is incorrect. Please try again.");
                alert.showAndWait();
            } else if (newPassTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Enter new password and try again.");
                alert.showAndWait();
            } else {
                if (curr.getPassword().equals(currPassTextField.getText())) {
                    curr.setPassword(newPassTextField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("Your password has been changed successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Change Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Your current password is incorrect. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void UpdateBillsButtononClick(ActionEvent event) {
    }

    @FXML
    private void DownloadBillButtononClick(ActionEvent event) {
        
    }

    @FXML
    private void DownloadNewBillButtononClick(ActionEvent event) {

    }

    @FXML
    private void SearchBillButtononClick(ActionEvent event) {
    }

}
