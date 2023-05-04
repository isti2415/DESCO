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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import modelClass.Reading;
import modelClass.Report;
import modelClass.Task;

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
    private TableColumn<Inventory, String> qntCol;
    @FXML
    private Pane pane4;
    @FXML
    private TextArea reportNoteTextArea;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<Task> performanceTableView;
    @FXML
    private TableColumn<Task, LocalDate> perDateCol;
    @FXML
    private TableColumn<Task, String> perTitleCol;
    @FXML
    private TableColumn<Task, String> perDescriptionCol;
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
    @FXML
    private TableColumn<Inventory, String> deptuseCol;
    @FXML
    private TableColumn<Inventory, String> invIdCol;
    @FXML
    private TableColumn<Inventory, String> nameCol;
    @FXML
    private TableColumn<Task, Boolean> perStatusCol;
    
    private String filePath;

    // Initialize month combo box
    ObservableList<String> monthList = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    // Initialize year combo box
    ObservableList<String> yearList = IntStream.rangeClosed(2000, LocalDate.now().getYear())
            .mapToObj(Integer::toString)
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    

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
        usageYearCombo.setItems(yearList);
        usageMonthCombo.setItems(monthList);
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
    private void viewPerformaneTargetOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        switchPane(5);
        perTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        perDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        perDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        perStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<Task> tasks = Task.loadTask(); // Load all tasks
        String id = CurrUser.getEmployee().getId();
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getEmployeeID().equals(id))
                .collect(Collectors.toList()); // Filter tasks by employeeID
        performanceTableView.setItems(FXCollections.observableArrayList(filteredTasks)); // Set filtered tasks in table view
    }

    @FXML
    private void viewSafetyProceduresOnClick(ActionEvent event) {
        switchPane(6);
        safetyProceduresTextArea.getItems().clear();
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
        monthCombo.setItems(monthList);
        yearCombo.setItems(yearList);
        customerMeterIDGen();

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
        Meter meter = new Meter(cusIDTextField.getText(),meterIDTextField2.getText(), monthCombo.getValue(), yearCombo.getValue());
        Customer customer = new Customer(cusIDTextField.getText(), passwordField.getText(), meter, cusNameTextField.getText(), cusAddressTextField.getText());
        for (Inventory i : Inventory.loadInventory()) {
            if (i.getName().equals("Meter")) {
                int quantity = Integer.parseInt(i.getQuantity());
                quantity--;
                i.setQuantity(String.valueOf(quantity));
            }
        }
        meterIDTextField2.clear();
        monthCombo.setValue(null);
        yearCombo.setValue(null);
        cusIDTextField.clear();
        passwordField.clear();
        cusNameTextField.clear();
        cusAddressTextField.clear();
        customerMeterIDGen();
    }

    @FXML
    private void energyUseLoadInfoOnClick(ActionEvent event) throws ClassNotFoundException {
        String meterID = energyUseMeterIDTextField.getText();
        int latestYear = 0;
        int latestMonth = 0;
        float latestValue = 0.0f;
        boolean found = false;
        for (Reading r : Reading.loadReadings()) {
            if (r.getMeterID().equals(meterID)) {
                if (r.getYear() > latestYear || (r.getYear() == latestYear && r.getMonth() > latestMonth)) {
                    latestYear = r.getYear();
                    latestMonth = r.getMonth();
                    latestValue = r.getValue();
                    found = true;
                    energyUsePrevReadingTextField.setText(Float.toString(latestValue));
                }
            }
        }
        if (found == false) {
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
        float prevValue = Float.parseFloat(energyUsePrevReadingTextField.getText());
        Reading r = new Reading(month, year, value, meterID,prevValue);
    }

    @FXML
    private void requestRestockOnClick(ActionEvent event
    ) {
        TableView.TableViewSelectionModel<Inventory> selectionModel = inventoryTableView.getSelectionModel();
        Inventory selectedItem = selectionModel.getSelectedItem();
        selectedItem.setRestock(true);
    }

    @FXML
    private void reportOnClick(ActionEvent event
    ) throws IOException, ClassNotFoundException {
        String subject = subjectTextField.getText();
        String details = reportNoteTextArea.getText();
        LocalDate date = LocalDate.now();
        Report report = new Report(CurrUser.getEmployee().getId(), date, subject, details, filePath);
    }

    @FXML
    private void markAsDoneOnClick(ActionEvent event
    ) {
        TableView.TableViewSelectionModel<Task> selectionModel = performanceTableView.getSelectionModel();
        Task selectedItem = selectionModel.getSelectedItem();
        selectedItem.setStatus(true);
        performanceTableView.refresh();
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
        if (!(currPassTextField.getText().equals("") && newPassTextField.getText().equals(""))) {
            if (currPassTextField.getText().equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Your current password is incorrect. Please try again.");
                alert.showAndWait();
            } else if (newPassTextField.getText().equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Enter new password and try again.");
                alert.showAndWait();
            } else {
                if (curr.getPassword().equals(currPassTextField.getText())) {
                    curr.setPassword(newPassTextField.getText());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Password Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("Your password has been changed successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Password Change Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Your current password is incorrect. Please try again.");
                    alert.showAndWait();
                }
            }
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
        System.out.println("File uploaded from " + filePath);
    }
}
