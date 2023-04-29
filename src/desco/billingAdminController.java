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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modelClass.CurrUserID;
import modelClass.Employee;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class billingAdminController implements Initializable {

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
    private Pane pane1;
    @FXML
    private Pane pane2;
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private TableColumn<?, ?> ViewUsageColumn;
    @FXML
    private TableColumn<?, ?> ViewDateColumn;
    @FXML
    private TableColumn<?, ?> ViewPaidColumn;
    @FXML
    private Pane pane5;
    @FXML
    private TableView<?> ViewCustomerBillsTable;
    @FXML
    private TableColumn<?, ?> ViewBillNumberCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewMonthCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewYearCustomerColumn;
    @FXML
    private TableColumn<?, ?> ViewBillAmountCustomerColumn;
    @FXML
    private TextField CustomerIDTextField;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TableView<?> disputeTableView;
    @FXML
    private TableColumn<?, ?> disputeIDColumn;
    @FXML
    private TableColumn<?, ?> disputeMonthColumn;
    @FXML
    private TableColumn<?, ?> disputeYearColumn;
    @FXML
    private TableColumn<?, ?> disputeAmountColumn;
    @FXML
    private TextField newAmountColumn;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

        switch (paneNumber) {
            case 1:
                pane1.setVisible(true);
                break;
            case 2:
                pane2.setVisible(true);
                break;
            case 4:
                pane4.setVisible(true);
                break;
            case 5:
                pane5.setVisible(true);
                break;
        }
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

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void ViewCustomerBillPaneButtononClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void ViewbillingdisputesButtononclick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        switchPane(5);
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
    private void UpdateBillsButtononClick(ActionEvent event) {
    }

    @FXML
    private void DownloadBillButtononClick(ActionEvent event) {
        File f = new File("bill.pdf");
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

                Text title = new Text("Customer Bill");
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
                cusid.add(CustomerIDTextField.getText());
                doc.add(cusid);
                doc.add(lineSpace);

                String bil = "Bill Number: ";
                Text billId = new Text(bil);
                Paragraph bill = new Paragraph(billId);
                bill.setBold();
                doc.add(bill);
                doc.add(lineSpace);

                String month = "Month: ";
                Text mont = new Text(month);
                Paragraph mon = new Paragraph(mont);
                mon.setBold();
                doc.add(mon);
                doc.add(lineSpace);

                String year = "Year: ";
                Text yea = new Text(year);
                Paragraph yr = new Paragraph(yea);
                yr.setBold();
                doc.add(yr);
                doc.add(lineSpace);

                String usage = "Usage: ";
                Text usee = new Text(usage);
                Paragraph use = new Paragraph(usee);
                use.setBold();
                doc.add(use);
                doc.add(lineSpace);

                String billAmnt = "Bill Amount: ";
                Text billAmt = new Text(billAmnt);
                Paragraph billAt = new Paragraph(billAmt);
                billAt.setBold();
                doc.add(billAt);
                doc.add(lineSpace);

                String dueDate = "Due Date: ";
                Text dueDat = new Text(dueDate);
                Paragraph dueDt = new Paragraph(dueDat);
                dueDt.setBold();
                doc.add(dueDt);
                doc.add(lineSpace);

                String paid = "Paid: ";
                Text padd = new Text(paid);
                Paragraph paidd = new Paragraph(padd);
                paidd.setBold();
                doc.add(paidd);
                doc.add(lineSpace);

                doc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(billingAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DownloadNewBillButtononClick(ActionEvent event) {

    }

    @FXML
    private void SearchBillButtononClick(ActionEvent event) {
    }

}
