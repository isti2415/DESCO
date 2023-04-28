/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import modelClass.Complaint;
import modelClass.CurrUserID;
import modelClass.Employee;
import modelClass.Inventory;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ManagerController implements Initializable {

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
    private Pane pane2;
    @FXML
    private ComboBox<?> attendEmpDeptComboBox;
    @FXML
    private TableView<?> performanceTable;
    @FXML
    private TableColumn<?, ?> goalColumn;
    @FXML
    private TableColumn<?, ?> completionColumn;
    @FXML
    private TableView<?> attendanceColumn;
    @FXML
    private TableColumn<?, ?> daysColumn;
    @FXML
    private TableColumn<?, ?> percentageColumn;
    @FXML
    private TextField attendEmpIDTextField;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<Complaint> complaintTable;
    @FXML
    private TableColumn<Complaint, String> complaintColumn;
    @FXML
    private TableColumn<Complaint, String> customerIDcolumn;
    @FXML
    private TableColumn<Complaint, String> feedbackColumn;
    @FXML
    private TableColumn<Complaint, String> employeeIDColumn;
    @FXML
    private Pane pane4;
    @FXML
    private TextArea contractTextArea;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, String> invQtyColumn;
    @FXML
    private TableColumn<Inventory, String> invDepartmentColumn;
    @FXML
    private Pane pane6;
    @FXML
    private ComboBox<?> deptComboBox;
    @FXML
    private TableView<?> reportTable;
    @FXML
    private TableColumn<?, ?> taskColumn;
    @FXML
    private TableColumn<?, ?> reportColumn;
    @FXML
    private TableColumn<?, ?> employeeColumn;
    @FXML
    private Pane pane7;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private TableView<?> performanceTableView;
    @FXML
    private TableColumn<?, ?> perDateCol;
    @FXML
    private TableColumn<?, ?> perTitleCol;
    @FXML
    private TableColumn<?, ?> perDescriptionCol;
    @FXML
    private TextField perfEmployeeIDTextField;
    @FXML
    private ComboBox<?> perfEmployeeTypeComboBox;
    @FXML
    private Pane pane8;
    @FXML
    private TextField profileUserIDTextField;
    @FXML
    private TableColumn<Inventory, String> inventoryId;
    @FXML
    private TableColumn<Inventory, String> invName;
    @FXML
    private TextField itemNameTextField;
    @FXML
    private TextField itemQuantityTextField;
    @FXML
    private ComboBox<String> itemDeptCombo;

    ObservableList<String> departments = FXCollections.observableArrayList(
            "Meter Reader", "Billing Administrator", "Customer Service Represantative",
            "Human Resources", "Manager", "Technician", "System Administrator"
    );
    private String filePath;

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

    private ObservableList<Inventory> loadInventory() {
        ObservableList<Inventory> inventoryList = FXCollections.observableList(new ArrayList<>());
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("inventory.bin"))) {
            inventoryList.addAll((List<Inventory>) inputStream.readObject());
        } catch (FileNotFoundException e) {
            // Ignore if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
        }
        return inventoryList;
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
        itemDeptCombo.setItems(departments);
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

    private void viewProfile(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void viewPerformAttendOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void viewCustomerComplaintOnClick(ActionEvent event) {
        switchPane(3);
        complaintColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        customerIDcolumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        ObservableList<Complaint> complaints = FXCollections.observableArrayList();
        try {
            try ( // Read the list of complaints from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("complaints.bin"))) {
                complaints.addAll((List<Complaint>) inputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        complaintTable.setItems((ObservableList<Complaint>) complaints);
    }

    @FXML
    private void viewContractsOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void checkInvOnClick(ActionEvent event) {
        switchPane(5);
        inventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        invName.setCellValueFactory(new PropertyValueFactory<>("name"));
        invQtyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        invDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        invQtyColumn.setCellFactory(column -> {
            return new TableCell<Inventory, String>() {
                @Override
                protected void updateItem(String quantity, boolean empty) {
                    super.updateItem(quantity, empty);
                    if (empty || quantity == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(quantity);
                        Inventory inventory = getTableView().getItems().get(getIndex());
                        if (inventory.getRestock()) {
                            setStyle("-fx-text-fill: red;");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });

        inventoryTable.setItems(loadInventory());
    }

    @FXML
    private void viewTargetsOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
        switchPane(7);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
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
    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        getCurrUser().logout(event);
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = getCurrUser();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
    }

    @FXML
    private void openContractOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }
        System.out.println("File uploaded from " + filePath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
            contractTextArea.setWrapText(true);
            contractTextArea.setEditable(true);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contractTextArea.appendText(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    @FXML
    private void saveContractOnClick(ActionEvent event) {
        if (filePath != null) {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(contractTextArea.getText());
                System.out.println("File saved to " + filePath);
            } catch (IOException ex) {
                System.out.println("Error saving file: " + ex.getMessage());
            }
        } else {
            System.out.println("No file has been selected");
        }
    }

    @FXML
    private void restockOnClick(ActionEvent event) {
        String name = itemNameTextField.getText();
        String quantity = itemQuantityTextField.getText();
        String department = itemDeptCombo.getValue();
        Inventory i = new Inventory(name, quantity, department);
        inventoryTable.setItems(loadInventory());
    }

    @FXML
    private void updateOnClick(ActionEvent event) {
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
    }

    @FXML
    private void updateTargetOnClick(ActionEvent event) {
    }

}
