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
import javafx.scene.control.ListView;
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
public class MeterReaderController implements Initializable {

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
    private DatePicker energyUsageDatePicker;
    @FXML
    private TextField energyUseCusIDTextField;
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
    private DatePicker recordsDatePicker;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
    }

    @FXML
    private void viewEnergyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void viewInventoryOnClick(ActionEvent event) {
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewPerformaneTargetOnClick(ActionEvent event) {
    }

    @FXML
    private void viewSafetyProceduresOnClick(ActionEvent event) {
    }

    @FXML
    private void viewMeterReordsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
        
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
    private void logOutOnClick(ActionEvent event) {
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void energyUseLoadInfoOnClick(ActionEvent event) {
    }

    @FXML
    private void energyUseSaveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void requestRestockOnClick(ActionEvent event) {
    }

    @FXML
    private void reportOnClick(ActionEvent event) {
    }

    @FXML
    private void updateTargetOnClick(ActionEvent event) {
    }

    @FXML
    private void markAsDoneOnClick(ActionEvent event) {
    }
    
}
