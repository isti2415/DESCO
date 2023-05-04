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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import modelClass.Attendance;
import modelClass.CurrUser;
import modelClass.Employee;
import modelClass.Payroll;
import modelClass.Task;

public class humanResourceController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<Employee> employeeInfoTable;
    @FXML
    private Pane pane3;
    @FXML
    private TableView<Attendance> attendanceTable;
    @FXML
    private Pane pane4;
    @FXML
    private TableView<Payroll> payrollTable;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<Task> performanceTable;
    @FXML
    private Pane pane7;
    @FXML
    private ComboBox<String> deptComboBox6;
    @FXML
    private TextField idTextField6;
    @FXML
    private TextField nameTextField6;
    @FXML
    private Pane pane8;
    @FXML
    private TableView<Employee> employeeOffboardTable;
    @FXML
    private TableColumn<Employee, String> idColumn7;
    @FXML
    private TableColumn<Employee, String> nameColumn7;
    @FXML
    private TableColumn<Employee, String> departmentColumn7;
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
    private Pane pane9;
    @FXML
    private TableColumn<Employee, String> IDInfoColumn;
    @FXML
    private TableColumn<Employee, String> NameInfoColumn;
    @FXML
    private TableColumn<Employee, String> DeptInfoColumn;
    @FXML
    private DatePicker dobPicker6;
    @FXML
    private TextField passwordTextField6;
    @FXML
    private TextField numberField6;
    @FXML
    private TextField emailTextField6;
    @FXML
    private TextArea policyTextArea;
    @FXML
    private TableColumn<Employee, String> ContactInfoColumn;
    @FXML
    private TableColumn<Attendance, Boolean> presentAttdColumn;
    @FXML
    private TableColumn<Attendance, String> reasonAttdColumn;
    @FXML
    private TableColumn<Task, String> performanceIDColumn;
    @FXML
    private TableColumn<Task, String> performanceTaskColumn;
    @FXML
    private TableColumn<Employee, String> SalaryInfoColumn;
    @FXML
    private TextField salaryTxtField;
    @FXML
    private TableColumn<Employee, String> servicePeriodColumn;
    @FXML
    private TextField perfIDField;
    @FXML
    private TableColumn<Attendance, String> IDAttdColumn;
    @FXML
    private TableColumn<Attendance, Integer> absentTableColumn;
    @FXML
    private DatePicker attendanceDatePicker;
    @FXML
    private TableColumn<Payroll, String> payrollIDColumn;
    @FXML
    private TableColumn<Payroll, String> payrollDeptColumn;
    @FXML
    private TableColumn<Payroll, String> payrollAmountColumn;
    @FXML
    private TextField payrollIDField;
    @FXML
    private TableColumn<Task, LocalDate> performanceDateColumn;
    @FXML
    private TableColumn<Task, String> performaneDescriptionColumn;
    @FXML
    private TableColumn<Payroll, YearMonth> payrollYearMonthColumn;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane7.setVisible(false);
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
            case 7:
                pane7.setVisible(true);
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);
        deptComboBox6.setItems(Employee.getDepartments());
        Employee curr = null;
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
        }

        Boolean exist = false;
        for (Payroll p : Payroll.loadPayroll()) {
            if (YearMonth.now().equals(p.getYearMonth())) {
                exist = true;
                break;
            }
        }
        if (exist == false) {
            for (Employee e : Employee.loadEmployee()) {
                Payroll payroll = new Payroll(e.getId(), e.getType(), e.getSalary(), false);
            }
        }
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) throws IOException, ClassNotFoundException {
        switchPane(1);
    }

    @FXML
    private void employeeInfoOnClick(ActionEvent event) {
        switchPane(2);

        IDInfoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameInfoColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DeptInfoColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        SalaryInfoColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ContactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        servicePeriodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));
        ObservableList<Employee> employees = FXCollections.observableArrayList(Employee.loadEmployee());
        for (Employee e : employees) {
            e.setPeriod();
        }
        employeeInfoTable.setItems(employees);
    }

    @FXML
    private void employeeAttendanceOnClick(ActionEvent event) {
        switchPane(3);
        attendanceDatePicker.setValue(LocalDate.now());
        IDAttdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        // Set the cell factory for the presentAttdColumn
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
                        setGraphic(checkBox);

                        // Add a listener to the checkbox
                        checkBox.setOnAction(event -> {
                            Attendance attendance = getTableView().getItems().get(getIndex());
                            attendance.setPresent(checkBox.isSelected());
                        });
                    }
                    attendanceTable.refresh();
                }
            };
        });

        presentAttdColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
        reasonAttdColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));

        // Set the cell factory for the reasonAttdColumn to allow editing
        reasonAttdColumn.setCellFactory(column -> {
            return new TableCell<Attendance, String>() {
                private TextField textField;

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if (textField == null) {
                            textField = new TextField();
                            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                                if (!isNowFocused) {
                                    commitEdit(textField.getText());
                                }
                            });
                        }
                        textField.setText(item);
                        setGraphic(textField);
                    }
                }

                @Override
                public void commitEdit(String newValue) {
                    Attendance attendance = getTableView().getItems().get(getIndex());
                    attendance.setReason(newValue);
                    super.commitEdit(newValue);
                }
            };
        });

        absentTableColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));

        // Load the attendance data for today
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        List<Attendance> todayAttendanceList = Attendance.loadAttendance()
                .stream()
                .filter(a -> a.getDate().equals(today))
                .collect(Collectors.toList());
        if (!todayAttendanceList.isEmpty()) {
            attendanceList.addAll(todayAttendanceList);
        } else {
            for (Employee employee : Employee.loadEmployee()) {
                attendanceList.add(new Attendance(employee.getId(), today, false));
            }
        }

        // Set the attendance data to the table
        attendanceTable.setItems(attendanceList);
    }

    @FXML
    private void payrollOnClick(ActionEvent event
    ) {
        switchPane(4);
        payrollIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        payrollDeptColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        payrollAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        payrollYearMonthColumn.setCellValueFactory(new PropertyValueFactory<>("yearMonth"));
        payrollTable.setRowFactory(tv -> new TableRow<Payroll>() {
            @Override
            protected void updateItem(Payroll item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if (item.getYearMonth().atEndOfMonth().isBefore(LocalDate.now())) {
                    if (!item.getStatus()) {
                        setStyle("-fx-background-color: red;");
                    }
                } else if (item.getStatus()) {
                    setStyle("-fx-background-color: green;");
                }
            }
        });
        payrollTable.setItems(FXCollections.observableArrayList(Payroll.loadPayroll()));
    }

    @FXML
    private void employeePerformanceOnClick(ActionEvent event
    ) {
        switchPane(5);
        performanceDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        performanceTaskColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        performaneDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        performanceIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        performanceTable.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if (item.getDate().isBefore(LocalDate.now())) {
                    if (!item.getStatus()) {
                        setStyle("-fx-background-color: red;");
                    }
                } else if (item.getStatus()) {
                    setStyle("-fx-background-color: green;");
                }
            }
        });
        performanceTable.setItems(FXCollections.observableArrayList(Task.loadTask()));
    }

    @FXML
    private void employeeOnboardOnClick(ActionEvent event
    ) {
        switchPane(7);
    }

    @FXML
    private void employeeOffOnClick(ActionEvent event
    ) {
        switchPane(8);
        idColumn7.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn7.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn7.setCellValueFactory(new PropertyValueFactory<>("type"));
        employeeOffboardTable.setItems(FXCollections.observableArrayList(Employee.loadEmployee()));
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event
    ) {
        switchPane(9);
        policyTextArea.clear();
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("companypolicy.txt"))) {
                policyTextArea.setWrapText(true);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    policyTextArea.appendText(line + "\n");
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
    private void saveS6OnClick(ActionEvent event
    ) {
        String id = idTextField6.getText();
        String pass = passwordTextField6.getText();
        String type = deptComboBox6.getValue();
        String name = nameTextField6.getText();
        String salary = salaryTxtField.getText();
        String email = emailTextField6.getText();
        String contact = numberField6.getText();
        LocalDate DoB = dobPicker6.getValue();
        Employee e = new Employee(id, pass, type, name, email, contact, DoB, salary);
    }

    @FXML
    private void offboardS7OnClick(ActionEvent event
    ) throws IOException {
        TableView.TableViewSelectionModel<Employee> selectionModel = employeeOffboardTable.getSelectionModel();
        Employee selectedItem = selectionModel.getSelectedItem();
        selectedItem.deleteEmployee();
        employeeOffboardTable.refresh();
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
    private void paySalaryOnClick(ActionEvent event
    ) {
        TableView.TableViewSelectionModel<Payroll> selectionModel = payrollTable.getSelectionModel();
        Payroll selectedItem = selectionModel.getSelectedItem();
        selectedItem.setStatus(true);
        payrollTable.refresh();
    }

    @FXML
    private void loadAttdOnClick(ActionEvent event
    ) {// Set the cell factory for the presentAttdColumn
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
                        setGraphic(checkBox);

                        // Add a listener to the checkbox
                        checkBox.setOnAction(event -> {
                            Attendance attendance = getTableView().getItems().get(getIndex());
                            attendance.setPresent(checkBox.isSelected());
                        });
                    }
                }
            };
        });

        // Set the cell factory for the reasonAttdColumn to allow editing
        reasonAttdColumn.setCellFactory(column -> {
            return new TableCell<Attendance, String>() {
                private TextField textField;

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if (textField == null) {
                            textField = new TextField();
                            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                                if (!isNowFocused) {
                                    commitEdit(textField.getText());
                                }
                            });
                        }
                        textField.setText(item);
                        setGraphic(textField);
                    }
                }

                @Override
                public void commitEdit(String newValue) {
                    Attendance attendance = getTableView().getItems().get(getIndex());
                    attendance.setReason(newValue);
                    super.commitEdit(newValue);
                }
            };
        });

        // Load the attendance data for today
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        LocalDate today = attendanceDatePicker.getValue();
        List<Attendance> todayAttendanceList = Attendance.loadAttendance()
                .stream()
                .filter(a -> a.getDate().equals(today))
                .collect(Collectors.toList());
        if (!todayAttendanceList.isEmpty()) {
            attendanceList.addAll(todayAttendanceList);
        } else {
            for (Employee employee : Employee.loadEmployee()) {
                attendanceList.add(new Attendance(employee.getId(), today, false));
            }
        }

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
        PdfWriter writer = new PdfWriter("attendance_report_" + dateString + ".pdf");
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

    @FXML
    private void payrollSearchOnClick(ActionEvent event) {
        List<Payroll> payrollList = Payroll.loadPayroll().stream()
                .filter(payroll -> payroll.getEmployeeID().equals(payrollIDField.getText()))
                .collect(Collectors.toList());
        payrollTable.setItems(FXCollections.observableArrayList(payrollList));
    }

    @FXML
    private void searchTaskOnClick(ActionEvent event) {
        List<Task> Tasks = Task.loadTask().stream()
                .filter(task -> task.getEmployeeID().equals(perfIDField.getText()))
                .collect(Collectors.toList());
        performanceTable.setItems(FXCollections.observableArrayList(Tasks));
    }
}
