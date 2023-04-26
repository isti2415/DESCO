/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modelClass.CurrUserID;
import modelClass.Customer;
import modelClass.Employee;
import modelClass.Meter;
import modelClass.Reading;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class meterReaderController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TextField profileUserIDTextField;
    @FXML
    private DatePicker profileDOBdatepicker;
    @FXML
    private TextField profileEmailTextField;
    @FXML
    private TextField profileConNumTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private Pane pane2;
    @FXML
    private TextField energyUseMeterIDTextField;
    @FXML
    private TextField energyUsePrevReadingTextField;
    @FXML
    private TextField energyUseCurrReadingTextField;
    @FXML
    private TextArea energyUseNotesTextArea;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<?> inventoryTableView;
    @FXML
    private TableColumn<?, ?> equipmentCol;
    @FXML
    private TableColumn<?, ?> modelCol;
    @FXML
    private TableColumn<?, ?> qntCol;
    @FXML
    private Pane pane4;
    @FXML
    private TextField reportMeterIDTextField;
    @FXML
    private DatePicker reportDatePicker;
    @FXML
    private ComboBox<?> reportTypeComboBox;
    @FXML
    private TextArea reportNoteTextArea;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> performanceTableView;
    @FXML
    private TableColumn<?, ?> perDateCol;
    @FXML
    private TableColumn<?, ?> perTitleCol;
    @FXML
    private TableColumn<?, ?> perDescriptionCol;
    @FXML
    private Pane pane6;
    @FXML
    private ListView<?> safetyProceduresTextArea;
    @FXML
    private Pane pane7;
    @FXML
    private TextField meterIDTextField2;
    @FXML
    private TextField cusIDTextField;
    @FXML
    private TextField cusNameTextField;
    @FXML
    private TextField cusAddressTextField;
    @FXML
    private Pane pane8;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private ComboBox<String> usageMonthCombo;
    @FXML
    private ComboBox<String> usageYearCombo;
    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private ComboBox<String> yearCombo;
    @FXML
    private TextField currPassTextField;

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

    private Employee getCurrUser() throws IOException, ClassNotFoundException {
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
            e.printStackTrace();
        }

        // Look for a matching customer in the customers file
        Employee currUser = null;
        List<Employee> employees = new ArrayList<>();
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employees.bin"))) {
                employees = (List<Employee>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        for (Employee employee : employees) {
            if (employee.getId().equals(userID)) {
                currUser = employee;
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
        // Initialize month combo box
        ObservableList<String> monthList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        usageMonthCombo.setItems(monthList);
        monthCombo.setItems(monthList);

        // Initialize year combo box
        ObservableList<String> yearList = FXCollections.observableArrayList();
        for (int i = LocalDate.now().getYear(); i >= 2000; i--) {
            yearList.add(Integer.toString(i));
        }
        usageYearCombo.setItems(yearList);
        yearCombo.setItems(yearList);
        customerMeterIDGen();

        Employee curr;
        try {
            curr = getCurrUser();
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
    private void viewEnergyUsageOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewInventoryOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void viewPerformaneTargetOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void viewSafetyProceduresOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void viewMeterReordsOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
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
        Meter meter = new Meter(meterIDTextField2.getText(), monthCombo.getValue(), yearCombo.getValue());
        Customer customer = new Customer(cusIDTextField.getText(), passwordField.getText(), meter, cusNameTextField.getText(), cusAddressTextField.getText());
    }

    @FXML
    private void energyUseLoadInfoOnClick(ActionEvent event) throws ClassNotFoundException {
        String meterID = energyUseMeterIDTextField.getText();
        int latestYear = 0;
        int latestMonth = 0;
        float latestValue = 0.0f;

        List<Reading> readings = new ArrayList<>();
        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("readings.bin"))) {
                readings = (List<Reading>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        boolean found = true;
        for (Reading r : readings) {
            if (r.getMeterID().equals(meterID)) {
                if (r.getYear() > latestYear || (r.getYear() == latestYear && r.getMonth() > latestMonth)) {
                    latestYear = r.getYear();
                    latestMonth = r.getMonth();
                    latestValue = r.getValue();
                    found = true;
                }
            } else {
                found = false;
            }
        }
        if (found == true) {
            energyUsePrevReadingTextField.setText(Float.toString(latestValue));
        } else {
            Alert alert = new Alert(AlertType.ERROR, "No valid readings found for the given meter ID.");
            alert.showAndWait();
        }

    }

    private void customerMeterIDGen() {
        List<Customer> customers = new ArrayList<>();
        String startID = "1001";
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("customers.bin"))) {
                customers = (List<Customer>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        customers.sort(Comparator.comparing(Customer::getId, String.CASE_INSENSITIVE_ORDER));
        for (Customer c : customers) {
            if (startID.equals(c.getId())) {
                int id = Integer.parseInt(startID);
                id++;
                startID = Integer.toString(id);
            }
        }
        meterIDTextField2.setText(startID);
        cusIDTextField.setText(startID);

    }

    @FXML
    private void energyUseSaveChangesOnClick(ActionEvent event
    ) {
        String meterID = energyUseMeterIDTextField.getText();
        String month = usageMonthCombo.getValue();
        String year = usageYearCombo.getValue();
        float value = Float.parseFloat(energyUseCurrReadingTextField.getText());
        Reading r = new Reading(month, year, value, meterID);
    }

    @FXML
    private void requestRestockOnClick(ActionEvent event
    ) {
    }

    @FXML
    private void reportOnClick(ActionEvent event
    ) {
    }

    @FXML
    private void updateTargetOnClick(ActionEvent event
    ) {
    }

    @FXML
    private void markAsDoneOnClick(ActionEvent event
    ) {
    }

    @FXML
    private void saveProfileOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = getCurrUser();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }

}
