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
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.Backup;
import modelClass.CurrUser;
import modelClass.Employee;
import modelClass.Reading;
import modelClass.User;
import modelClass.Version;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class sysAdController implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private TableView<User> userListTableView;
    @FXML
    private TableColumn<User, String> userIDTableColumn;
    @FXML
    private TableColumn<User, String> passwordTableColumn;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private TableColumn<String[], String> logDate;
    @FXML
    private Pane pane5;
    @FXML
    private CheckBox pushTickBox;
    @FXML
    private CheckBox emailTickBox;
    @FXML
    private Pane pane6;
    @FXML
    private Label currentVersionLabel;
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
    private TextArea policyTextArea;
    @FXML
    private Label releaseDateLabel;
    @FXML
    private TableColumn<Backup, String> backupFileCount;
    @FXML
    private TableColumn<Backup, String> backupSize;
    @FXML
    private TableColumn<Backup, LocalDateTime> backupTimestamp;
    @FXML
    private TableView<Backup> backupTableView;
    @FXML
    private TableView<String[]> logTableView;
    @FXML
    private TableColumn<String[], String> logUserID;
    @FXML
    private TableColumn<String[], String> logTime;
    @FXML
    private LineChart<String, Float> usageChart;

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
     *
     * @param url
     * @param rb
     */
    // initialize the user list
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchPane(1);

        // load the user list from the file and add it to the table view
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
    private void manageAccountOnClick(ActionEvent event) {
        switchPane(2);
        userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        userListTableView.setItems(FXCollections.observableArrayList(User.loadUser()));
    }

    @FXML
    private void viewEnergyTrendsOnClick(ActionEvent event) {
        switchPane(3);
        usageChart.setVisible(false);
    }

    @FXML
    private void viewActivtyLogOnClick(ActionEvent event) {
        switchPane(4);
        logUserID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        logDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        logTime.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        logTableView.setItems(FXCollections.observableArrayList(CurrUser.loadLog()));
    }

    @FXML
    private void viewNotificationSceneOnClick(ActionEvent event) {
        switchPane(5);
    }

    @FXML
    private void viewAppUpdateOnScene(ActionEvent event) {
        switchPane(6);
        List<Version> versionList = Version.loadVersion();
        currentVersionLabel.setText(versionList.get(versionList.size() - 1).getVersion());
        releaseDateLabel.setText(versionList.get(versionList.size() - 1).getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
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
    private void deleteUserOnClick(ActionEvent event) throws IOException {
        // get the selected user from the table
        User selectedUser = userListTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("No user selected.");
        }
        userListTableView.getItems().clear();
    }

    @FXML
    private void billDueToggle(ActionEvent event) {
    }

    @FXML
    private void promoToggle(ActionEvent event) {
    }

    @FXML
    private void outageToggle(ActionEvent event) {
    }

    @FXML
    private void accountToggle(ActionEvent event) {
    }

    @FXML
    private void energySaveToggle(ActionEvent event) {
    }

    @FXML
    private void emergencyToggle(ActionEvent event) {
    }

    @FXML
    private void pushOnCLick(ActionEvent event) {
        Version version = new Version();
        currentVersionLabel.setText(version.getVersion());
        releaseDateLabel.setText(version.getDate().toString());
    }

    @FXML
    private void rollbackOnClick(ActionEvent event) {
        List<Version> versionList = Version.loadVersion();
        versionList.remove(versionList.size() - 1);
        try (FileOutputStream fileOut = new FileOutputStream("versions.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(versionList);
            System.out.println("Version saved to versions.bin file");
        } catch (IOException e) {
            System.out.println("Error saving reading to file");
        }
        currentVersionLabel.setText(versionList.get(versionList.size() - 1).getVersion());
        releaseDateLabel.setText(versionList.get(versionList.size() - 1).getDate().toString());
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
    private void resetPasswordOnClick(ActionEvent event) {
    }

    @FXML
    private void viewBackupSceneOnClick(ActionEvent event) {
        switchPane(8);
        backupFileCount.setCellValueFactory(new PropertyValueFactory<>("fileCount"));
        backupTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        backupSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        backupTableView.setItems(FXCollections.observableArrayList(Backup.loadBackup()));
    }

    @FXML
    private void backupOnClick(ActionEvent event) throws IOException {
        Backup backup = new Backup();
        backupTableView.setItems(FXCollections.observableArrayList(Backup.loadBackup()));
    }

    @FXML
    private void deleteBackupOnClick(ActionEvent event) {
        // Get the selected backup from the table view
        Backup selectedBackup = backupTableView.getSelectionModel().getSelectedItem();
        if (selectedBackup == null) {
            System.out.println("No backup selected");
            return;
        }

        // Remove the selected backup from the backups list
        ObservableList<Backup> backups = backupTableView.getItems();
        backups.remove(selectedBackup);

        // Update the table view
        backupTableView.setItems(backups);

        // Delete the corresponding folder
        try {
            File backupFolder = new File(selectedBackup.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")));
            deleteDirectory(backupFolder);
            if (!backups.isEmpty()) {
                try (FileOutputStream fileOut = new FileOutputStream("backups.bin", false);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(backups);
                    System.out.println("Backups saved to backups.bin file");
                } catch (IOException e) {
                    System.out.println("Error saving backups to file");
                }
            } else {
                File backupsFile = new File("backups.bin");
                if (backupsFile.exists()) {
                    if (backupsFile.delete()) {
                        System.out.println("Backups file deleted");
                    } else {
                        System.out.println("Error deleting backups file");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting backup folder: " + e.getMessage());
        }

    }

    private void deleteDirectory(File directory) throws IOException {
        if (directory.isDirectory()) {
            // Delete all files and subdirectories inside the directory
            for (File file : directory.listFiles()) {
                deleteDirectory(file);
            }
        }
        // Delete the directory itself
        Files.delete(directory.toPath());
    }

    @FXML
    private void restoreOnClick(ActionEvent event) {
        // Get the selected backup instance from the table view
        Backup selectedBackup = backupTableView.getSelectionModel().getSelectedItem();
        if (selectedBackup == null) {
            System.out.println("No backup selected");
            return;
        }

        // Get the backup folder for the selected backup instance
        File backupFolder = new File(selectedBackup.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")));
        if (!backupFolder.exists() || !backupFolder.isDirectory()) {
            System.out.println("Backup folder not found");
            return;
        }

        // Copy the files from the backup folder to the root folder
        File rootFolder = new File(".");
        for (File file : backupFolder.listFiles()) {
            try {
                Path source = file.toPath();
                Path destination = new File(rootFolder, file.getName()).toPath();
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error copying file: " + file.getName());
            }
        }

        System.out.println("Backup restored");
    }

    @FXML
    private void viewThisYearUsage(ActionEvent event) {
        int selectedYear = LocalDate.now().getYear();
        List<Reading> readingsForYear = new ArrayList<>();
        for (Reading reading : Reading.loadReadings()) {
            if (reading.getYear() == selectedYear) {
                readingsForYear.add(reading);
            }
        }

        // Sort the readings by month
        Collections.sort(readingsForYear, Comparator.comparingInt(Reading::getMonth));

        // Create a new series for the chart
        XYChart.Series<String, Float> series = new XYChart.Series<>();
        series.setName(selectedYear + " Usage");

        // Add the readings to the series
        for (int i = 1; i <= 12; i++) {
            String monthName = "january";
            float usage = 0;
            for (Reading reading : readingsForYear) {
                if (reading.getMonth() == i) {
                    usage += reading.getValue();
                    monthName = reading.convertMonth(i);
                    System.out.println(monthName);
                    System.out.println(usage);
                }
            }
            series.getData().add(new XYChart.Data<>(monthName, usage));
        }

        // Set the chart data
        usageChart.getData().clear();
        usageChart.getData().add(series);

        // Show the chart
        usageChart.setVisible(true);
    }

    @FXML
    private void viewAllYearUsage(ActionEvent event) {
        // Load all the readings for the selected year
        List<Reading> readings = Reading.loadReadings();
        // Sort the readings by month
        Collections.sort(readings, Comparator.comparingInt(Reading::getYear).reversed());

        // Create a new series for the chart
        XYChart.Series<String, Float> series = new XYChart.Series<>();
        series.setName("Lifetime Usage");

        // Get the current year
        int currentYear = LocalDate.now().getYear();

        // Iterate over the readings list
        for (int year = currentYear; year >= 2000; year--) {
            float usage = 0;
            // Iterate over the readings for the current year
            for (Reading reading : readings) {
                if (reading.getYear() == year) {
                    usage += reading.getValue();
                }
            }
            series.getData().add(new XYChart.Data<>(String.valueOf(year), usage));
        }

        // Set the chart data
        usageChart.getData().clear();
        usageChart.getData().add(series);

        // Show the chart
        usageChart.setVisible(true);
    }

    @FXML
    private void downloadLogOnClick(ActionEvent event) throws FileNotFoundException, MalformedURLException {
        // Get the selected date and format it for report title
        LocalDate selectedDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = selectedDate.format(formatter);
        // Create a PDF document with set margins and add an image
        PdfWriter writer = new PdfWriter("Activity Log" + dateString + ".pdf");
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
        Text titleText = new Text("Activity Log till " + dateString);
        titleText.setFontSize(18f);
        Paragraph titleParagraph = new Paragraph(titleText);
        titleParagraph.setBold();
        titleParagraph.setTextAlignment(TextAlignment.CENTER);
        document.add(lineSpace);
        document.add(titleParagraph);
        document.add(lineSpace);

        // Create a PDF table with headers and data
        Table logPdfTable = new Table(3);
        logPdfTable.setWidth(UnitValue.createPercentValue(100));

        logPdfTable.addCell("User ID");
        logPdfTable.addCell("Date");
        logPdfTable.addCell("Time");

        for (String[] logData : logTableView.getItems()) {
            logPdfTable.addCell(logData[0]);
            logPdfTable.addCell(logData[1]);
            logPdfTable.addCell(logData[2]);
        }

        // Add the PDF table to the document
        document.add(logPdfTable);

        // Close the document
        document.close();
    }
}
