package SystemAdministrator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelClass.User;

public class Scene1Controller implements Initializable {

    @FXML
    private TableView<User> userListTableView;
    @FXML
    private TableColumn<User, String> userIDTableColumn;
    @FXML
    private TableColumn<User, String> passwordTableColumn;
    @FXML
    private TableColumn<User, String> userTypeTableColumn;
    @FXML
    private ComboBox<String> usertypeCombo;
    @FXML
    private TextField useridfield;
    @FXML
    private TextField passwordfield;
    private final String[] userTypes = {"Customer", "Meter Reader", "Billing Administrator",
        "Customer Service Representative", "Field Technician",
        "System Administrator", "Manager", "Human Resources"};

    // initialize the user list
    private List<User> userList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usertypeCombo.getItems().addAll(userTypes);
        // set up the user list table
        userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        // load the user list from the file and add it to the table view
        userList = User.loadUsers();
        userListTableView.getItems().addAll(userList);

    }

    @FXML
    private void manageAccountOnClick(ActionEvent event) {
    }

    @FXML
    private void viewEnergyTrendsOnClick(ActionEvent event) {
    }

    @FXML
    private void viewActivtyLogOnClick(ActionEvent event) {
    }

    @FXML
    private void viewNotificationSceneOnClick(ActionEvent event) {
    }

    @FXML
    private void viewAppUpdateOnScene(ActionEvent event) {
    }

    @FXML
    private void viewSecuritySceneOnClick(ActionEvent event) {
    }

    @FXML
    private void viewBackupOnSceneOnClick(ActionEvent event) {
    }

    @FXML
    private void viewCompanyPolicyOnClick(ActionEvent event) {
    }

    @FXML
    private void logOutOnClick(ActionEvent event) {
    }

    private void viewUserListOnClick(ActionEvent event) {
        userListTableView.getItems().clear();
        userListTableView.getItems().addAll(userList);
    }

    @FXML
    private void addUserOnClick(ActionEvent event) {
        String userType = usertypeCombo.getValue(); // get the selected user type
        String userID = useridfield.getText(); // get the entered user ID
        String password = passwordfield.getText(); // get the entered password
        User user = new User(userID, password, userType); // create a new User object
        userList.add(user); // add the new user to the user list
        User.saveUsers(userList); // save the updated user list to the file
        userListTableView.getItems().clear();
        userListTableView.getItems().addAll(userList);
        System.out.println("User added successfully.");
    }

    @FXML
    private void deleteUserOnClick(ActionEvent event) {
        // get the selected user from the table
        User selectedUser = userListTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userList.remove(selectedUser); // remove the user from the list
            User.saveUsers(userList); // save the updated user list to the file
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("No user selected.");
        }
        userListTableView.getItems().clear();
        userListTableView.getItems().addAll(userList);
    }

    @FXML
    private void generateIDOnAction(ActionEvent event) {
        String userType = usertypeCombo.getValue();
        int startingID = 0;

        switch (userType) {
            case "Customer":
                startingID = 1000;
                break;
            case "Meter Reader":
                startingID = 2000;
                break;
            case "Billing Administrator":
                startingID = 3000;
                break;
            case "Customer Service Representative":
                startingID = 4000;
                break;
            case "Field Technician":
                startingID = 5000;
                break;
            case "System Administrator":
                startingID = 6000;
                break;
            case "Manager":
                startingID = 7000;
                break;
            case "Human Resources":
                startingID = 8000;
                break;
            default:
                break;
        }

        int maxID = startingID;
        for (User user : userList) {
            if (user.getUserType().equals(userType)) {
                int userID = Integer.parseInt(user.getUserID());
                if (userID > maxID) {
                    maxID = userID;
                }
            }
        }

        int nextID = maxID + 1;
        useridfield.setText(Integer.toString(nextID));
        passwordfield.setText("123");
    }

}
