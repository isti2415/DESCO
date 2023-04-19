/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelClass.Customer;
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
        for (int i = 2023; i >= 2000; i--) {
            yearList.add(Integer.toString(i));
        }
        usageYearCombo.setItems(yearList);
        yearCombo.setItems(yearList);
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
        User p = null;
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
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonth = currentDate.minusMonths(1);
        int month = previousMonth.getMonthValue();
        int year = previousMonth.getYear();
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("readings.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Reading r;
            try {
                while (true) {
                    r = (Reading) ois.readObject();
                    if(r.getMeterID().equals(meterID) && r.getMonth()==month && r.getYear()==year){
                       energyUsePrevReadingTextField.setText(Float.toString(r.getValue()));
                    }
                }
            } catch (IOException ex) {
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
        }
    }

    @FXML
    private void energyUseSaveChangesOnClick(ActionEvent event
    ) {
        String meterID = energyUseMeterIDTextField.getText();
        String month = usageMonthCombo.getValue();
        String year = usageYearCombo.getValue();
        float value = Float.parseFloat(energyUseCurrReadingTextField.getText());
        Reading r = new Reading(month,year,value,meterID);
        
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

}
