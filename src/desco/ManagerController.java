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
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import modelClass.Attendance;
import modelClass.Complaint;
import modelClass.CurrUser;
import modelClass.Employee;
import modelClass.Inventory;
import modelClass.Report;
import modelClass.Task;

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
    private TableView<Report> reportTable;
    @FXML
    private TableColumn<Report, String> employeeColumn;
    @FXML
    private Pane pane7;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private TableView<Task> performanceTableView;
    @FXML
    private TableColumn<Task, LocalDate> perDateCol;
    @FXML
    private TableColumn<Task, String> perTitleCol;
    @FXML
    private TableColumn<Task, String> perDescriptionCol;
    @FXML
    private TextField perfEmployeeIDTextField;
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
    @FXML
    private TextField targetTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TableColumn<Task, String> perEmpID;
    @FXML
    private TableView<Attendance> attendanceTable;
    @FXML
    private TableColumn<Attendance, String> IDAttdColumn;
    @FXML
    private TableColumn<Attendance, Boolean> presentAttdColumn;
    @FXML
    private TableColumn<Attendance, String> reasonAttdColumn;
    @FXML
    private TableColumn<Attendance, Integer> absentTableColumn;
    @FXML
    private DatePicker attendanceDatePicker;
    @FXML
    private DatePicker targetDatePicker;
    @FXML
    private TableColumn<Report, String> fileColumn;
    @FXML
    private TableColumn<Report, LocalDate> dateColumn;
    @FXML
    private TableColumn<Report, String> subjectColumn;
    @FXML
    private TableColumn<Report, String> detailsColumn;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        itemDeptCombo.setItems(Employee.getDepartments());
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
    private void viewPerformAttendOnClick(ActionEvent event) {
        switchPane(2);
        attendanceDatePicker.setValue(LocalDate.now());
        IDAttdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        presentAttdColumn.setCellFactory(column -> {
            return new CheckBoxTableCell<Attendance, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        CheckBox checkBox = new CheckBox();
                        checkBox.setSelected(item);
                        checkBox.setDisable(true);
                        setGraphic(checkBox);
                    }
                }
            };
        });
        presentAttdColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
        reasonAttdColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        absentTableColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));

        // Load the attendance data for today
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        List<Attendance> todayAttendanceList = Attendance.loadAttendance()
                .stream()
                .filter(a -> a.getDate().equals(today))
                .collect(Collectors.toList());
        attendanceList.addAll(todayAttendanceList);

        // Set the attendance data to the table
        attendanceTable.setItems(attendanceList);
    }

    @FXML
    private void viewCustomerComplaintOnClick(ActionEvent event) {
        switchPane(3);
        complaintColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        customerIDcolumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        complaintTable.setItems(FXCollections.observableList(Complaint.loadComplaint()));
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
        inventoryTable.setItems(FXCollections.observableList(Inventory.loadInventory()));
    }

    @FXML
    private void viewTargetsOnClick(ActionEvent event) {
        switchPane(6);
        perDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        perTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        perDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        perEmpID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        performanceTableView.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if (item.getDate().isBefore(LocalDate.now())) {
                    if (!item.getStatus()) {
                        setTextFill(Color.RED);
                    }
                } else if (item.getStatus()) {
                    setTextFill(Color.GREEN);
                }
            }
        });
        performanceTableView.setItems(FXCollections.observableList(Task.loadTask()));
    }

    @FXML
    private void viewReportsOnClick(ActionEvent event) {
        switchPane(7);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        fileColumn.setCellFactory(col -> new TableCell<Report, String>() {
            private final Button button = new Button("Open");

            {
                button.setOnAction(event -> {
                    String filePath = getItem();
                    File file = new File(filePath);
                    if (file.exists()) {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException e) {
                            // handle the exception appropriately, e.g. display an error message
                        }
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });

        reportTable.setItems(FXCollections.observableList(Report.loadReport()));
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
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
    private void saveChangesOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Employee curr = CurrUser.getEmployee();
        if (curr != null) {
            curr.setName(profileNameTextField.getText());
            curr.setDoB(profileDOBdatepicker.getValue());
            curr.setEmail(profileEmailTextField.getText());
            curr.setContact(profileConNumTextField.getText());
        }
        if (!(currPassTextField.getText().equals("") && newPassTextField.getText().equals(""))) {
            if (currPassTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Your current password is incorrect. Please try again.");
                alert.showAndWait();
            } else if (newPassTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Change Error");
                alert.setHeaderText(null);
                alert.setContentText("Enter new password and try again.");
                alert.showAndWait();
            } else {
                if (curr.getPassword().equals(currPassTextField.getText())) {
                    curr.setPassword(newPassTextField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("Your password has been changed successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Change Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Your current password is incorrect. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void openContractOnClick(ActionEvent event) {
        contractTextArea.clear();
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
        List<Inventory> inventoryList = Inventory.loadInventory();
        Boolean exist = false;
        for(Inventory i : inventoryList){
            if(i.getName().equals(name)&&i.getDepartment().equals(department)){
                i.setQuantity(String.valueOf(Integer.parseInt(i.getQuantity())+Integer.parseInt(quantity)));
                i.setRestock(false);
                exist = true;
                break;
            }
        }
        if(exist.equals(false)){
            Inventory i = new Inventory(name, quantity, department);
        }
        inventoryTable.setItems(FXCollections.observableList(Inventory.loadInventory()));
    }

    @FXML
    private void updateCompanyPolicyOnClick(ActionEvent event) {
        try (FileWriter fileWriter = new FileWriter("companypolicy.txt")) {
            fileWriter.write(policyTextArea.getText());
            System.out.println("File saved to " + "companypolicy.txt");
        } catch (IOException ex) {
            System.out.println("Error saving file: " + ex.getMessage());
        }
    }

    @FXML
    private void setTargetOnClick(ActionEvent event) {
        String id = perfEmployeeIDTextField.getText();
        String title = targetTextField.getText();
        String description = descriptionTextField.getText();
        LocalDate date = targetDatePicker.getValue();
        Task task = new Task(id, title, description, date);
        performanceTableView.setItems(FXCollections.observableArrayList(Task.loadTask()));
    }

    @FXML
    private void loadEmplTargetOnClick(ActionEvent event) {
        List<Task> tasks = Task.loadTask(); // Load all tasks
        String id = perfEmployeeIDTextField.getText();
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getEmployeeID().equals(id))
                .collect(Collectors.toList());
        performanceTableView.setItems(FXCollections.observableList(filteredTasks));
    }

    @FXML
    private void loadAttendanceOnClick(ActionEvent event) {
        // Load the attendance data for today
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        LocalDate today = attendanceDatePicker.getValue();
        List<Attendance> todayAttendanceList = Attendance.loadAttendance()
                .stream()
                .filter(a -> a.getDate().equals(today))
                .collect(Collectors.toList());
        attendanceList.addAll(todayAttendanceList);

        presentAttdColumn.setCellFactory(column -> {
            return new CheckBoxTableCell<Attendance, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        CheckBox checkBox = new CheckBox();
                        checkBox.setSelected(item);
                        checkBox.setDisable(true);
                        setGraphic(checkBox);
                    }
                }
            };
        });

        // Set the attendance data to the table
        attendanceTable.setItems(attendanceList);
    }

    @FXML
    private void downloadAttendanceOnClick(ActionEvent event) throws FileNotFoundException, MalformedURLException {
        // Get the selected date and format it for report title
        LocalDate selectedDate = attendanceDatePicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = selectedDate.format(formatter);
        // Create a PDF document with set margins and add an image
        PdfWriter writer = new PdfWriter("attendance_report " + dateString + ".pdf");
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4);
        document.setMargins(10f, 10f, 10f, 10f);
        String imagePath = "src/images/desco.png";
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image image = new Image(imageData);
        image.setAutoScale(true);
        document.add(image);

        // Add title with selected date
        String newline = "\n";
        Paragraph lineSpace = new Paragraph(newline);
        lineSpace.setHeight(10);
        Text titleText = new Text("Attendance Report of " + dateString);
        titleText.setFontSize(18f);
        Paragraph titleParagraph = new Paragraph(titleText);
        titleParagraph.setBold();
        titleParagraph.setTextAlignment(TextAlignment.CENTER);
        document.add(lineSpace);
        document.add(titleParagraph);
        document.add(lineSpace);

        // Create a table with headers and set alignment
        Table attendanceView = new Table(4);
        attendanceView.setWidth(UnitValue.createPercentValue(100));
        attendanceView.setHorizontalAlignment(HorizontalAlignment.CENTER);
        attendanceView.setVerticalAlignment(VerticalAlignment.MIDDLE);

        // Add headers to the table
        attendanceView.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Employee ID"));
        attendanceView.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Present"));
        attendanceView.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Reason"));
        attendanceView.addHeaderCell(new com.itextpdf.layout.element.Cell().add("Absent Days"));

        // Add attendance data to the table
        for (Attendance attendance : attendanceTable.getItems()) {
            attendanceView.addCell(new com.itextpdf.layout.element.Cell().add(attendance.getEmployeeID()));
            attendanceView.addCell(new com.itextpdf.layout.element.Cell().add(attendance.getPresent() ? "Yes" : "No"));
            attendanceView.addCell(new com.itextpdf.layout.element.Cell().add(attendance.getReason()));
            attendanceView.addCell(new com.itextpdf.layout.element.Cell().add(String.valueOf(attendance.getAbsence())));
        }

        // Add the attendance table to the document
        document.add(attendanceView);

        // Close the document
        document.close();
    }
}
