/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import modelClass.Complaint;
import modelClass.CurrUser;
import modelClass.Employee;
import modelClass.Notification;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class customerServiceController implements Initializable {

    private Label policyViewTextLabel;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private TextField profileNameTextField;
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
    private TextField CustomerIDTextField;
    @FXML
     private TableView<Complaint> ViewCustomerAccountTable;
    @FXML
    private TableColumn<Complaint, String> ViewCustomerIDColumn;
    @FXML
    private TableColumn<Complaint, String> ViewComplainIDColumn;
    @FXML
    private TableColumn<Complaint, String> ViewCustomerComplaintColumn;
    @FXML
    private TableColumn<Complaint, LocalDate> ViewDateColumn;
    @FXML
    private TableColumn<Complaint, String> viewFeedback;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea emailTextArea;
    @FXML
    private TextField feedbackSubjectTextField;
    @FXML
    private TextArea feedbackEmailTextArea;
    @FXML
    private TextArea policyTextArea;
    
    private String filePath;

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
     *
     * @param url
     * @param rb
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
    private void viewCustomerComplaintsOnClick(ActionEvent event) {
        switchPane(2);
        ViewCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ViewComplainIDColumn.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        ViewDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        viewFeedback.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        ViewCustomerComplaintColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        ViewCustomerAccountTable.setItems(FXCollections.observableArrayList(Complaint.loadComplaint()));
    }

    @FXML
    private void ViewPromotionsOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void ViewFeedBackManagerOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        switchPane(5);
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
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = CurrUser.getEmployee();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }

    @FXML
    private void markAsResolvedOnclick(ActionEvent event) {
    }

    @FXML
    private void sendtoAllCustomersOnClick(ActionEvent event) {
        String subject = subjectTextField.getText();
        String details = emailTextArea.getText();
        String type = "Promotions";
        LocalDate date = LocalDate.now();
        Notification notification = new Notification(date, subject, details, type);
    }

    @FXML
    private void attachFilesOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }
        System.out.println("File uploaded from "+filePath);
    }

    @FXML
    private void sendtoManagerfOnClick(ActionEvent event) {
        String subject = subjectTextField.getText();
        String details = emailTextArea.getText();
        String type = "Reports";
        LocalDate date = LocalDate.now();
        Notification notification = new Notification(date, subject, details, type);
        notification.setFilepath(filePath);
    }

    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        CurrUser.getEmployee().logout(event);
    }

    @FXML
    private void searchCustomerOnclick(ActionEvent event) {
    }

    @FXML
    private void logOutOnclick(ActionEvent event) {
    }

}
