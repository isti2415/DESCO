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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Customer extends User {

    private static final String FILENAME = "customers.bin";

    private Meter meter;
    private String name;
    private String address;

    // Add any additional relevant information as needed
    public Customer(String id, String password, Meter meter, String name, String address) {
        super(id, password);
        this.meter = meter;
        this.name = name;
        this.address = address;
        saveCustomer();
    }

    public Meter getMeter() {
        return meter;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void saveCustomer() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Customer> readCustomers() {
    List<Customer> customers = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
        Object obj;
        while ((obj = ois.readObject()) != null) {
            if (obj instanceof Customer) {
                Customer customer = (Customer) obj;
                customers.add(customer);
            }
        }
        System.out.println("Customers loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error reading customers: " + e.getMessage());
    }
    return customers;
}


    static void deleteCustomer(Customer customer) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            List<User> userList = (List<User>) ois.readObject();
            ois.close();

            int index = -1;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i) instanceof Customer && userList.get(i).equals(customer)) {
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
}
