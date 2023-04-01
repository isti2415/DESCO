/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FieldTechnician;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class Scene1Controller implements Initializable {

    @FXML
    private TableView<?> taskListViewTable;
    @FXML
    private TableColumn<?, ?> taskId;
    @FXML
    private TableColumn<?, ?> taskDescription;
    @FXML
    private TableColumn<?, ?> taskDate;
    @FXML
    private TableColumn<?, ?> taskStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewSubmitReportsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewCustomerComplaintsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewComplaintsOnClick(ActionEvent event) {
    }

    @FXML
    private void checkFaultyEquipmentOnClick(ActionEvent event) {
    }

    @FXML
    private void checkInventoryEquipmentOnClick(ActionEvent event) {
    }

    @FXML
    private void viewUploadDataOnClick(ActionEvent event) {
    }

    @FXML
    private void viewPolicyOnClick(ActionEvent event) {
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
    }

    @FXML
    private void selectTaskandMarkasDoneOnClick(ActionEvent event) {
    }
    
}
