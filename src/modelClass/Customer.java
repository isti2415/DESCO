/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
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
    private String email;
    private String contact;
    private LocalDate DoB;

    // Add any additional relevant information as needed
    public Customer(String id, String password, Meter meter, String name, String address) {
        super(id, password);
        this.meter = meter;
        this.name = name;
        this.address = address;
        saveCustomer();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate DoB) {
        this.DoB = DoB;
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

    private void saveCustomer() {
        List<Customer> customerList = Customer.loadCustomer();
        // Check if the customer ID of the current customer already exists in the list
        boolean exists = false;
        for (Customer customer : customerList) {
            if (customer.getId().equals(this.getId())) {
                exists = true;
                break;
            }
        }
        // If the customer ID already exists, show an error message and do not save the customer
        if (exists) {
            System.out.println("Customer already exists");
        } else {
            // Otherwise, add the customer to the list and save the list to the file
            customerList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("customers.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(customerList);
                System.out.println("Customer saved to customers.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private static List<Customer> loadCustomer() {
        List<Customer> customers = new ArrayList<>();
        try {
            try ( // Read the list of customers from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("customers.bin"))) {
                customers = (List<Customer>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return customers;
    }
}
