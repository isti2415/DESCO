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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import modelClass.CurrUser;
import modelClass.Customer;
import modelClass.Employee;
import modelClass.Inventory;
import modelClass.Meter;
import modelClass.Notification;
import modelClass.Reading;

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
    private Pane pane3;
    @FXML
    private TableView<Inventory> inventoryTableView;
    @FXML
    private TableColumn<?, ?> qntCol;
    @FXML
    private Pane pane4;
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
    private ListView<String> safetyProceduresTextArea;
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
    @FXML
    private TextField subjectTextField;

    private String filePath;
    @FXML
    private TableColumn<?, ?> deptuseCol;
    @FXML
    private TableColumn<?, ?> invIdCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    
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
            curr = CurrUser.getEmployee();
            if (curr != null) {
                profileNameTextField.setText(curr.getName());
                profileUserIDTextField.setText(curr.getId());
                profileDOBdatepicker.setValue(curr.getDoB());
                profileEmailTextField.setText(curr.getEmail());
                profileConNumTextField.setText(curr.getContact());
            }
        } catch (IOException | ClassNotFoundException ex) {
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
        invIdCol.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qntCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        deptuseCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        
        inventoryTableView.setItems(FXCollections.observableArrayList(Inventory.loadInventory()));        
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
        File file = new File("safety_procedures.txt");
        ObservableList<String> safetyProcedures = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                safetyProcedures.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        safetyProceduresTextArea.setItems(safetyProcedures);
    }

    @FXML
    private void viewMeterReordsOnClick(ActionEvent event) {
        switchPane(7);
       
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
        switchPane(8);
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
    private void saveChangesOnClick(ActionEvent event) {
        Meter meter = new Meter(meterIDTextField2.getText(), monthCombo.getValue(), yearCombo.getValue());
        Customer customer = new Customer(cusIDTextField.getText(), passwordField.getText(), meter, cusNameTextField.getText(), cusAddressTextField.getText());
        for (Inventory i : Inventory.loadInventory()) {
            if (i.getName().equals("Meter")) {
                int quantity = Integer.parseInt(i.getQuantity());
                quantity--;
                i.setQuantity(String.valueOf(quantity));
            }
        }
    }

    @FXML
    private void energyUseLoadInfoOnClick(ActionEvent event) throws ClassNotFoundException {
        String meterID = energyUseMeterIDTextField.getText();
        int latestYear = 0;
        int latestMonth = 0;
        float latestValue = 0.0f;
        boolean found = true;
        for (Reading r : Reading.loadReadings()) {
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
        List<Customer> customers = Customer.loadCustomer();
        String startID = "1001";
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
        TableView.TableViewSelectionModel<Inventory> selectionModel = inventoryTableView.getSelectionModel();
        Inventory selectedItem = selectionModel.getSelectedItem();
        selectedItem.setRestock(true);
        inventoryTableView.refresh();            
    }

    @FXML
    private void reportOnClick(ActionEvent event
    ) {
        String subject = subjectTextField.getText();
        String details = reportNoteTextArea.getText();
        String type = "Reports";
        LocalDate date = LocalDate.now();
        Notification notification = new Notification(date, subject, details, type);
        notification.setFilepath(filePath);        
    }

    @FXML
    private void markAsDoneOnClick(ActionEvent event
    ) {
    }

    @FXML
    private void saveProfileOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = CurrUser.getEmployee();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }

    @FXML
    private void attachOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }
        System.out.println("File uploaded from "+filePath);
    }        
    }


