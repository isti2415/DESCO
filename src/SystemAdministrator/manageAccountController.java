/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemAdministrator;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelClass.User;

/**
 * FXML Controller class
 *
 * @author Istiaqs-PC
 */
public class manageAccountController implements Initializable {

    @FXML
    private TextField addUserID;

    @FXML
    private TableView<User> manageAccountList;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TextField passwordField;

    @FXML
    private Pane setUser;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private TableColumn<User, String> userid;

    @FXML
    private TableColumn<User, String> usertype;

    @FXML
    private Pane viewUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userTypeComboBox.getItems().addAll("Customer", "System Administrator", "Manager", "Human Resources", "Field Technician", "Meter Reader", "Customer Service Representative", "Billing Administrator");
        try {
            userViewScene(null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(manageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addAccountOnClick(ActionEvent event) {
        String userType = userTypeComboBox.getSelectionModel().getSelectedItem();
        String userID = addUserID.getText();
        String Password = passwordField.getText();
        // Check which user type was selected and create a new User instance accordingly
        User newUser = new User(userID, Password, userType);

        System.out.println("addAccountOnClick called");
        System.out.println("User type: " + userType);
        System.out.println("User ID: " + userID);
        System.out.println("Password: " + Password);

        // Clear the input fields
        userTypeComboBox.getSelectionModel().clearSelection();
        addUserID.clear();
        passwordField.clear();
    }

    @FXML
    void addScenePane(ActionEvent event) {
        viewUser.setVisible(false);
        setUser.setVisible(true);
    }

    @FXML
    void comboBoxSelect(ActionEvent event) {
        String selectedUserType = userTypeComboBox.getValue();
        int lastUserId = 0;

        // Determine the last user ID for the selected user type
        switch (selectedUserType) {
            case "Customer":
                lastUserId = 1000;
                break;
            case "Meter Reader":
                lastUserId = 2000;
                break;
            case "Billing Administrator":
                lastUserId = 3000;
                break;
            case "Customer Service Representative":
                lastUserId = 4000;
                break;
            case "Field Technician":
                lastUserId = 5000;
                break;
            case "System Administrator":
                lastUserId = 6000;
                break;
            case "Manager":
                lastUserId = 7000;
                break;
            case "Human Resources":
                lastUserId = 8000;
                break;
            default:
                // Handle invalid selection
                break;
        }

        // Generate new user ID
        int newUserId = lastUserId + 1;

        // Update the user ID text field
        addUserID.setText(String.valueOf(newUserId));
        passwordField.setText("123");
    }

    @FXML
    void deleteAccountOnClick(ActionEvent event) {

    }

    @FXML
    void logOutOnClick(ActionEvent event) {

    }

    @FXML
    void manageAccountOnClick(ActionEvent event) {

    }

    @FXML
    void userViewScene(ActionEvent event) throws ClassNotFoundException, FileNotFoundException {
        viewUser.setVisible(true);
        setUser.setVisible(false);
        userid.setCellValueFactory(new PropertyValueFactory<User, String>("userid"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        usertype.setCellValueFactory(new PropertyValueFactory<User, String>("usertype"));
        try {
            manageAccountList.setItems(getUsersList());
        } catch (IOException ex) {
            Logger.getLogger(manageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void viewActivtyLogOnClick(ActionEvent event) {

    }

    @FXML
    void viewAppUpdateOnScene(ActionEvent event) {

    }

    @FXML
    void viewBackupOnSceneOnClick(ActionEvent event) {

    }

    @FXML
    void viewCompanyPolicyOnClick(ActionEvent event) {

    }

    @FXML
    void viewEnergyTrendsOnClick(ActionEvent event) {

    }

    @FXML
    void viewNotificationSceneOnClick(ActionEvent event) {

    }

    @FXML
    void viewSecuritySceneOnClick(ActionEvent event) {

    }

    private ObservableList<User> getUsersList() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObservableList<User> usersList = FXCollections.observableArrayList();
        File users = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            users = new File("users.bin");
            fis = new FileInputStream(users);
            ois = new ObjectInputStream(fis);
            
            User u;
            try{
                while(true){
                    u = (User) ois.readObject();
                    usersList.add(u);
                }
            }
            catch(Exception e){
                
            }
        }
        catch(Exception e){
            
        }
        finally{
            try{
                if(ois!=null){
                    ois.close();
                }
            }
            catch(Exception e){
                
            }
        }
        return usersList;
    }

}
