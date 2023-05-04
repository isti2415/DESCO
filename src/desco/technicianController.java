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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import modelClass.Complaint;
import modelClass.CurrUser;
import modelClass.Customer;
import modelClass.CustomerComplaint;
import modelClass.Employee;
import modelClass.Inventory;
import modelClass.Payroll;
import modelClass.Report;
import modelClass.Service;
import modelClass.Task;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class technicianController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<Task> taskListViewTable;
    @FXML
    private TableColumn<Task, String> taskId;
    @FXML
    private TableColumn<Task, String> taskDescription;
    @FXML
    private TableColumn<Task, LocalDate> taskDate;
    @FXML
    private TableColumn<Task, Boolean> taskStatus;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<CustomerComplaint> customerComplainListViewTable;
    @FXML
    private TableColumn<CustomerComplaint, String> complaintID;
    @FXML
    private TableColumn<CustomerComplaint, String> customerID;
    @FXML
    private TableColumn<CustomerComplaint, String> customerAddress;
    @FXML
    private TableColumn<CustomerComplaint, LocalDate> complainDate;
    @FXML
    private TableColumn<CustomerComplaint, String> contactInfo;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<Complaint> ComplainListViewTable;
    @FXML
    private TableColumn<Complaint, String> complaintID1;
    @FXML
    private TableColumn<Complaint, String> complaintDescription;
    @FXML
    private TableColumn<Complaint, LocalDate> complainDate1;
    @FXML
    private TableColumn<Complaint, Boolean> complainStatus;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<Service> faultyEquipmentViewTable;
    @FXML
    private Pane pane6;
    @FXML
    private TableView<Inventory> inventoryEquipmentViewTable;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane1;
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
    private TextArea policyViewTextArea;
    @FXML
    private TableColumn<Inventory, String> inventoryID;
    @FXML
    private TableColumn<Inventory, String> invName;
    @FXML
    private TableColumn<Inventory, String> qtyInv;
    @FXML
    private TableColumn<Inventory, String> invDept;
    @FXML
    private TextField feedbackSubjectTextField;
    @FXML
    private TextArea feedbackEmailTextArea;
    @FXML
    private TableColumn<Service, String> faultyComplaintId;
    @FXML
    private TableColumn<Service, String> faultyMeterId;
    @FXML
    private TableColumn<Service, LocalDate> faultyDate;
    @FXML
    private TableColumn<Service, String> faultyProblem;

    private String feedbackFilePath;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);

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
            case 8:
                pane8.setVisible(true);
                break;
            case 9:
                pane9.setVisible(true);
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
    private void viewTaskListOnClick(ActionEvent event) {
        switchPane(2);

        taskId.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        taskDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        taskDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        taskStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        taskListViewTable.setItems(FXCollections.observableArrayList(Task.loadTask()));
    }

    @FXML
    private void viewCustomerInfoOnClick(ActionEvent event) {
        switchPane(3);

        complaintID.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        complainDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        contactInfo.setCellValueFactory(new PropertyValueFactory<>("contact"));

        List<Complaint> complaintList = Complaint.loadComplaint();
        for (Complaint c : complaintList) {
            if (c.getResolved() == true) {
                complaintList.remove(c);
            }
        }
        ObservableList<CustomerComplaint> combinedList = FXCollections.observableArrayList(new ArrayList<>());
        for (Customer customer : Customer.loadCustomer()) {
            for (Complaint complaint : complaintList) {
                if (customer.getId().equals(complaint.getCustomerID())) {
                    combinedList.add(new CustomerComplaint(customer.getId(), complaint.getComplaintID(), customer.getAddress(), complaint.getDate(), customer.getContact()));
                }
            }
        }
        customerComplainListViewTable.setItems(combinedList);
    }

    @FXML
    private void viewComplaintsOnClick(ActionEvent event) {
        switchPane(4);
        complaintID1.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        complaintDescription.setCellValueFactory(new PropertyValueFactory<>("details"));
        complainDate1.setCellValueFactory(new PropertyValueFactory<>("date"));
        complainStatus.setCellValueFactory(new PropertyValueFactory<>("resolved"));

        ComplainListViewTable.setItems(FXCollections.observableArrayList(Complaint.loadComplaint()));

    }

    @FXML
    private void checkFaultyEquipmentOnClick(ActionEvent event) {
        switchPane(5);

        faultyComplaintId.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        faultyMeterId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        faultyDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        faultyProblem.setCellValueFactory(new PropertyValueFactory<>("details"));
        faultyEquipmentViewTable.setRowFactory(tv -> new TableRow<Service>() {
            @Override
            protected void updateItem(Service item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (item.getStatus()) {
                        setStyle("-fx-background-color: green;");
                    }
                }
            }
        });

        faultyEquipmentViewTable.setItems(FXCollections.observableArrayList(Service.loadService()));
    }

    @FXML

    private void checkInventoryEquipmentOnClick(ActionEvent event) {
        switchPane(6);

        inventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        invName.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyInv.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        invDept.setCellValueFactory(new PropertyValueFactory<>("department"));

        inventoryEquipmentViewTable.setItems(FXCollections.observableArrayList(Inventory.loadInventory()));
    }

    @FXML
    private void viewSubmitReportsOnClick(ActionEvent event) {
        switchPane(8);
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
        switchPane(9);
        policyViewTextArea.clear();
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"))) {
                policyViewTextArea.setWrapText(true);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    policyViewTextArea.appendText(line + "\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

    }

    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        CurrUser.getEmployee().logout(event);
    }

    @FXML
    private void selectTaskandMarkasDoneOnClick(ActionEvent event) {
        TableViewSelectionModel<Task> selectionModel = taskListViewTable.getSelectionModel();
        Task selectedItem = selectionModel.getSelectedItem();
        selectedItem.setStatus(true);
        taskListViewTable.setItems(FXCollections.observableArrayList(Task.loadTask()));
    }

    @FXML
    private void selectComplainandMarkasResolvedOnClick(ActionEvent event) {
        TableViewSelectionModel<Complaint> selectionModel = ComplainListViewTable.getSelectionModel();
        Complaint selectedItem = selectionModel.getSelectedItem();
        selectedItem.setResolved(true);
        ComplainListViewTable.setItems(FXCollections.observableArrayList(Complaint.loadComplaint()));
    }

    @FXML
    private void selectEquipmentAndMarkAsRepaired(ActionEvent event) {
        TableViewSelectionModel<Service> selectionModel = faultyEquipmentViewTable.getSelectionModel();
        Service selectedItem = selectionModel.getSelectedItem();
        selectedItem.setStatus(true);
        faultyEquipmentViewTable.setItems(FXCollections.observableArrayList(Service.loadService()));
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
    private void selectInventoryAndRequestRestockOnClick(ActionEvent event) {
        TableViewSelectionModel<Inventory> selectionModel = inventoryEquipmentViewTable.getSelectionModel();
        Inventory selectedItem = selectionModel.getSelectedItem();
        selectedItem.setRestock(true);
    }

    @FXML
    private void attachFilesOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            feedbackFilePath = selectedFile.getAbsolutePath();
        }
        System.out.println("File uploaded from " + feedbackFilePath);
    }

    @FXML
    private void sendtoManagerOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        String subject = feedbackSubjectTextField.getText();
        String details = feedbackEmailTextArea.getText();
        LocalDate date = LocalDate.now();
        Report report = new Report(CurrUser.getEmployee().getId(), date, subject, details, feedbackFilePath);
        System.out.println(report.getFilePath());
    }
}
