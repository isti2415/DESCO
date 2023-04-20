/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class customerServiceController implements Initializable {

    private Label policyViewTextLabel;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private TextField profileNameTextField;
    @FXML
    private TextField profileUsernameTextField;
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
    private TextField CustomerIDTextField;
    @FXML
    private TableView<?> ViewCustomerAccountTable;
    @FXML
    private TableColumn<?, ?> ViewCustomerIDColumn;
    @FXML
    private TableColumn<?, ?> ViewComplainIDColumn;
    @FXML
    private TableColumn<?, ?> ViewCustomerComplaintColumn;
    @FXML
    private TableColumn<?, ?> ViewDateColumn;
    @FXML
    private TableColumn<?, ?> viewFeedback;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea emailTextArea;
    @FXML
    private TextField feedbackSubjectTextField;
    @FXML
    private TextArea feedbackEmailTextArea;
    @FXML
    private TextArea policyTextArea;

    private void switchPane(int paneNumber) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);

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
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        switchPane(1);
    }

    @FXML
    private void viewCustomerComplaintsOnClick(ActionEvent event) {
        switchPane(2);
    }

    @FXML
    private void ViewPromotionsOnClick(ActionEvent event) {
        switchPane(3);
    }

    @FXML
    private void ViewFeedBackManagerOnClick(ActionEvent event) {
        switchPane(4);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        switchPane(5);
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

    private void logOutOnClick(ActionEvent event) throws IOException {
        User p = new User();
        p.logout(event);
    }

    @FXML
    private void saveChangesOnClick(ActionEvent event) {
    }

    @FXML
    private void markAsResolvedOnclick(ActionEvent event) {
    }

    @FXML
    private void sendtoAllCustomersOnClick(ActionEvent event) {
    }

    @FXML
    private void attachFilesOnClick(ActionEvent event) {
    }

    @FXML
    private void sendtoManagerfOnClick(ActionEvent event) {
    }

}
