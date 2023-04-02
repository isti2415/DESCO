/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    @FXML
    private Pane scene1pane;
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
    private Pane scene2pane;
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
    private Pane scene3pane;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea emailTextArea;
    @FXML
    private Pane scene4pane;
    @FXML
    private TextField feedbackSubjectTextField;
    @FXML
    private TextArea feedbackEmailTextArea;
    @FXML
    private Pane scene5pane;
    @FXML
    private Label policyViewTextLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scene1pane.setVisible(true);
        scene2pane.setVisible(false);
        scene3pane.setVisible(false);
        scene4pane.setVisible(false);
        scene5pane.setVisible(false);
    }

    @FXML
    private void viewProfileOnClick(ActionEvent event) {
        scene1pane.setVisible(true);
        scene2pane.setVisible(false);
        scene3pane.setVisible(false);
        scene4pane.setVisible(false);
        scene5pane.setVisible(false);
    }

    @FXML
    private void viewCustomerComplaintsOnClick(ActionEvent event) {
        scene1pane.setVisible(false);
        scene2pane.setVisible(true);
        scene3pane.setVisible(false);
        scene4pane.setVisible(false);
        scene5pane.setVisible(false);
    }

    @FXML
    private void ViewPromotionsOnClick(ActionEvent event) {
        scene1pane.setVisible(false);
        scene2pane.setVisible(false);
        scene3pane.setVisible(true);
        scene4pane.setVisible(false);
        scene5pane.setVisible(false);
    }

    @FXML
    private void ViewFeedBackManagerOnClick(ActionEvent event) {
        scene1pane.setVisible(false);
        scene2pane.setVisible(false);
        scene3pane.setVisible(false);
        scene4pane.setVisible(true);
        scene5pane.setVisible(false);
    }

    @FXML
    private void ViewCompanyPolicyButtonsOnclick(ActionEvent event) {
        scene1pane.setVisible(false);
        scene2pane.setVisible(false);
        scene3pane.setVisible(false);
        scene4pane.setVisible(false);
        scene5pane.setVisible(true);
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

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
    }

    @FXML
    private void agreeButtonOnClick(ActionEvent event) {
    }

}
