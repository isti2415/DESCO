/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import modelClass.Complaint;
import modelClass.CurrUserID;
import modelClass.Customer;
import modelClass.Service;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class customerController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private ComboBox<?> billMonthComboBox;
    @FXML
    private ComboBox<?> billYearComboBox;
    @FXML
    private CheckBox autoPaymentCheckbox;
    @FXML
    private CheckBox paperlessbillCheckBox;
    @FXML
    private TextArea billTextField;
    @FXML
    private Pane pane1;
    @FXML
    private TextField profileNameTextField;
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
    private Pane pane3;
    @FXML
    private ComboBox<?> energyUseMonthCombobox;
    @FXML
    private ComboBox<?> energyUseYearCombobox;
    @FXML
    private TextArea viewTextArea;
    @FXML
    private Pane pane4;
    @FXML
    private TextField detailsTextField;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> notificationsTableViewOnClick;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> subjectCol;
    @FXML
    private TableColumn<?, ?> detailsCol;
    @FXML
    private Pane pane6;
    @FXML
    private TextArea complaintTextArea;
    @FXML
    private TextField profileUseridTextField;
    @FXML
    private TextField profileAddressTextField;
    @FXML
    private RadioButton suspicousRadioButton;
    @FXML
    private RadioButton malfunctionRadioButton;
    @FXML
    private RadioButton otherRadioButton;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);

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
        }
    }

    private Customer getCurrUser() throws IOException, ClassNotFoundException {
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
        }

        // Look for a matching customer in the customers file
        Customer currUser = null;
        List<Customer> customers = new ArrayList<>();
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("customers.bin"))) {
                customers = (List<Customer>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        for (Customer customer : customers) {
            if (customer.getId().equals(userID)) {
                currUser = customer;
                break;
            }
        }

        return currUser;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        Customer curr;
        try {
            curr = getCurrUser();
            if (curr != null) {
                profileNameTextField.setText(curr.getName());
                profileUseridTextField.setText(curr.getId());
                profileDOBdatepicker.setValue(curr.getDoB());
                profileEmailTextField.setText(curr.getEmail());
                profileConNumTextField.setText(curr.getContact());
                profileAddressTextField.setText(curr.getAddress());
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
    private void viewMyBillsOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewEnergyUsageOnClick(ActionEvent event) {
        switchPane(3);
    }

    private ToggleGroup serviceToggle;

    @FXML
    private void viewServiceRequestOnClick(ActionEvent event) {
        switchPane(4);
        serviceToggle = new ToggleGroup();
        suspicousRadioButton.setToggleGroup(serviceToggle);
        malfunctionRadioButton.setToggleGroup(serviceToggle);
        otherRadioButton.setToggleGroup(serviceToggle);
    }

    @FXML
    private void viewNotificationsOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void fileComplaintOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException {
        User p = new User();
        p.logout(event);
    }

    @FXML
    private void viewBillOnClick(ActionEvent event) {
    }

    @FXML
    private void makePaymentOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Customer curr = getCurrUser();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
            curr.setAddress(profileAddressTextField.getText());
        }
    }

    @FXML
    private void viewMontlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewYearlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void submitOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        String details = detailsTextField.getText();
        String cusID = getCurrUser().getId();
        LocalDate date = LocalDate.now();
        final String[] type = new String[1];
        serviceToggle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                final RadioButton selectedRadioButton = (RadioButton) newValue;
                type[0] = selectedRadioButton.getText();

            }
        });
        Service service = new Service(type[0],details,cusID,date);
    }

    @FXML
    private void submitComplaintButton(ActionEvent event) throws IOException, ClassNotFoundException {
        String details = complaintTextArea.getText();
        String cusID = getCurrUser().getId();
        LocalDate date = LocalDate.now();
        Complaint complaint = new Complaint(cusID,details,date);
    }

}
