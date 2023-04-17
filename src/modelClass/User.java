package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final String FILENAME = "users.bin";

    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        saveUser();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void saveUser() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<User>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Users loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
        }
        return users;
    }

    public static void deleteUser(User user) throws FileNotFoundException, IOException {
        // read the list of User objects from the binary file
        List<User> userList = readUsers();

        // find the index of the User object to delete
        int index = userList.indexOf(user);

        // if the User object was not found, print an error message and return
        if (index == -1) {
            System.out.println("User not found.");
            return;
        }

        // delete the User object at the specified index
        User deletedUser = userList.remove(index);

        // if the deleted User object was an instance of the Customer class,
        // remove its associated Customer object as well
        if (deletedUser instanceof Customer) {
            Customer deletedCustomer = (Customer) deletedUser;
            Customer.deleteCustomer(deletedCustomer);
        } else {
            Employee deletedEmployee = (Employee) deletedUser;
            Employee.deleteEmployee(deletedEmployee);
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"));
        oos.writeObject(userList);
        oos.close();
    }
}
