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

public class Customer extends User {

    private static final String FILENAME = "customers.bin";

    private Meter meter;
    private String name;
    private String address;
    private String email;
    private String contact;
    private LocalDate DoB;

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
        updateCustomer();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
        updateCustomer();
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate DoB) {
        this.DoB = DoB;
        updateCustomer();
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
        updateCustomer();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateCustomer();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateCustomer();
    }

    private void updateCustomer() {
        List<Customer> customerList = loadCustomer();
        Boolean found = false;
        for (Customer customer : customerList) {
            if (customer.getId().equals(this.getId())) {
                customerList.remove(customer);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Customer not found");
        } else {
            customerList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(customerList);
                System.out.println("Customer saved to customers.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }

    }

    private void saveCustomer() {
        List<Customer> customerList = loadCustomer();
        boolean exists = false;
        for (Customer customer : customerList) {
            if (customer.getId().equals(this.getId())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Customer already exists");
        } else {
            customerList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
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
