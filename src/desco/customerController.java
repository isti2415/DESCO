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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.Bill;
import modelClass.Complaint;
import modelClass.CurrUser;
import modelClass.Customer;
import modelClass.Inventory;
import modelClass.Notification;
import modelClass.Service;
import modelClass.Task;

public class customerController implements Initializable {

    @FXML
    private Pane pane2;
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
    private ComboBox energyUseMonthCombobox;
    @FXML
    private ComboBox energyUseYearCombobox;
    @FXML
    private TextArea viewTextArea;
    @FXML
    private Pane pane4;
    @FXML
    private TextField detailsTextField;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<Notification> notificationsTableView;
    @FXML
    private TableColumn<Notification, LocalDate> dateCol;
    @FXML
    private TableColumn<Notification, String> subjectCol;
    @FXML
    private TableColumn<Notification, String> detailsCol;
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
    @FXML
    private CheckBox autoBIllCheckbox;
    @FXML
    private CheckBox paperlessBillCheckBox;
    @FXML
    private TableView<Bill> billTableView;
    @FXML
    private TableColumn<Bill, String> billIDColumn;
    @FXML
    private TableColumn<Bill, YearMonth> monthColumn;
    @FXML
    private TableColumn<Bill, String> amountColumn;
    @FXML
    private TableColumn<Bill, Boolean> statusColumn;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        Customer curr;
        try {
            curr = CurrUser.getCustomer();
            if (curr != null) {
                profileNameTextField.setText(curr.getName());
                profileUseridTextField.setText(curr.getId());
                profileDOBdatepicker.setValue(curr.getDoB());
                profileEmailTextField.setText(curr.getEmail());
                profileConNumTextField.setText(curr.getContact());
                profileAddressTextField.setText(curr.getAddress());
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
    private void viewMyBillsOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        switchPane(2);
        billIDColumn.setCellValueFactory(new PropertyValueFactory<>("billID"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("yearMonth"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        List<Bill> bills = Bill.loadBill(); // Load all tasks
        String id = CurrUser.getCustomer().getId();
        List<Bill> filteredBills = bills.stream()
                .filter(bill -> bill.getUserID().equals(id))
                .collect(Collectors.toList()); // Filter tasks by employeeID
        billTableView.setItems(FXCollections.observableList(filteredBills));
    }

    @FXML
    private void viewEnergyUsageOnClick(ActionEvent event) {
        switchPane(3);
        energyUseMonthCombobox.setItems(monthList);
        energyUseYearCombobox.setItems(yearList);
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
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        
        notificationsTableView.setItems(FXCollections.observableList(Notification.loadNotifications()));
    }

    @FXML
    private void fileComplaintOnClick(ActionEvent event) {
        switchPane(6);
    }

    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        CurrUser.getCustomer().logout(event);
    }

    @FXML
    private void viewBillOnClick(ActionEvent event) throws ClassNotFoundException {
        TableView.TableViewSelectionModel<Bill> selectionModel = billTableView.getSelectionModel();
        Bill selectedItem = selectionModel.getSelectedItem();
        File f = new File("bill "+selectedItem.getYearMonth().toString()+".pdf");
        try {
            if (f != null) {
                PdfWriter pw = new PdfWriter(new FileOutputStream(f));
                PdfDocument pdf = new PdfDocument(pw);
                pdf.setDefaultPageSize(PageSize.A5);
                pdf.addNewPage();
                Document doc = new Document(pdf);

                doc.setMargins(10f, 10f, 10f, 10f);

                String imagepath = "src/images/desco.png";
                ImageData data = ImageDataFactory.create(imagepath);
                Image image = new Image(data);
                image.setAutoScale(true);
                doc.add(image);

                String newline = "\n";
                Paragraph lineSpace = new Paragraph(newline);
                lineSpace.setHeight(10);

                Text title = new Text("Customer Bill "+selectedItem.getYearMonth().toString());
                title.setFontSize(18f);
                Paragraph pageTitle = new Paragraph(title);
                pageTitle.setBold();
                pageTitle.setTextAlignment(TextAlignment.CENTER);
                doc.add(lineSpace);
                doc.add(pageTitle);
                doc.add(lineSpace);

                String id = "Customer ID: ";
                Text custId = new Text(id);
                Paragraph cusid = new Paragraph(custId);
                cusid.setBold();
                cusid.add(selectedItem.getUserID());
                doc.add(cusid);
                doc.add(lineSpace);

                String bill = "Bill Number: ";
                Text billId = new Text(bill);
                Paragraph bills = new Paragraph(billId);
                bills.setBold();
                bills.add(selectedItem.getBillID());
                doc.add(bills);
                doc.add(lineSpace);

                String month = "Month: ";
                Text mont = new Text(month);
                Paragraph mon = new Paragraph(mont);
                mon.setBold();
                mon.add(selectedItem.getYearMonth().getMonth().toString());
                doc.add(mon);
                doc.add(lineSpace);

                String year = "Year: ";
                Text yea = new Text(year);
                Paragraph yr = new Paragraph(yea);
                yr.setBold();
                yr.add(String.valueOf(selectedItem.getYearMonth().getYear()));
                doc.add(yr);
                doc.add(lineSpace);

                String usage = "Usage: ";
                Text usee = new Text(usage);
                Paragraph use = new Paragraph(usee);
                use.setBold();
                use.add(String.valueOf(selectedItem.getUsage()));
                doc.add(use);
                doc.add(lineSpace);

                String billAmnt = "Bill Amount: ";
                Text billAmt = new Text(billAmnt);
                Paragraph billAt = new Paragraph(billAmt);
                billAt.setBold();
                billAt.add(String.valueOf(selectedItem.getTotal()));
                doc.add(billAt);
                doc.add(lineSpace);

                String dueDate = "Due Date: ";
                Text dueDat = new Text(dueDate);
                Paragraph dueDt = new Paragraph(dueDat);
                dueDt.setBold();
                dueDt.add(selectedItem.getDueDate().toString());
                doc.add(dueDt);
                doc.add(lineSpace);

                String paid = "Paid: ";
                Text padd = new Text(paid);
                Paragraph paidd = new Paragraph(padd);
                paidd.setBold();
                paidd.add(selectedItem.getStatus()? "Paid":"Unpaid");
                doc.add(paidd);

                doc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(billingAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void makePaymentOnClick(ActionEvent event) {
        TableView.TableViewSelectionModel<Bill> selectionModel = billTableView.getSelectionModel();
        Bill selectedItem = selectionModel.getSelectedItem();
        selectedItem.setStatus(true);
        billTableView.refresh();
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Customer curr = CurrUser.getCustomer();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
            curr.setAddress(profileAddressTextField.getText());
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
    private void viewMontlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void ViewYearlyUsageOnClick(ActionEvent event) {
    }

    @FXML
    private void submitOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        String details = detailsTextField.getText();
        String cusID = CurrUser.getCustomer().getId();
        LocalDate date = LocalDate.now();
        final String[] type = new String[1];
        serviceToggle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                final RadioButton selectedRadioButton = (RadioButton) newValue;
                type[0] = selectedRadioButton.getText();

            }
        });
        Service service = new Service(type[0], details, cusID, date);
    }

    @FXML
    private void submitComplaintButton(ActionEvent event) throws IOException, ClassNotFoundException {
        String details = complaintTextArea.getText();
        String cusID = CurrUser.getCustomer().getId();
        LocalDate date = LocalDate.now();
        Complaint complaint = new Complaint(cusID, details, date);
    }

    @FXML
    private void disputeBillOnClick(ActionEvent event) {
        TableView.TableViewSelectionModel<Bill> selectionModel = billTableView.getSelectionModel();
        Bill selectedItem = selectionModel.getSelectedItem();
        selectedItem.setDispute(true);
    }

}
