/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.File;
import java.io.FileNotFoundException;
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

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class customerServiceController implements Initializable {

    private Label policyViewTextLabel;
    private Pane pane1;
    private Pane pane2;
    private Pane pane3;
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private TextArea policViewTextArea;

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
            // Open the companypolicy.txt file
            File file = new File("companypolicy.txt");
            // Read the contents of the file into a string
            try (Scanner scanner = new Scanner(file)) {
                // Read the contents of the file into a string
                StringBuilder policyText = new StringBuilder();
                while (scanner.hasNextLine()) {
                    policyText.append(scanner.nextLine());
                    policyText.append("\n");
                }   // Set the text of the label to the policy text
                policyViewTextLabel.setText(policyText.toString());
                // Close the scanner
            }
        } catch (FileNotFoundException e) {
            // Handle the file not found exception

        }
    }

    @FXML
    private void logOutOnclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desco/login.fxml"));
            Parent root = loader.load();
            desco.LoginController loginController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }


}
