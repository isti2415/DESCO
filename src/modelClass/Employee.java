/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Employee extends User {
    
    private static final String FILENAME = "employee.bin";
    
    private String type;
    private String name;
    // Add any additional relevant information as needed

    public Employee(String id, String password, String type, String name) {
        super(id, password);
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
    private void saveEmployee() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deleteEmployee(Employee employee) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            List<User> userList = (List<User>) ois.readObject();
            ois.close();

            int index = -1;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i) instanceof Employee && userList.get(i).equals(employee)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                userList.remove(index);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                oos.writeObject(userList);
                oos.close();
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    // Add any additional relevant methods as needed
}