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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class CurrUser implements Serializable {

    public String userID;

    public CurrUser(String userID) throws IOException {
        this.userID = userID;
        saveSession();
        saveLog();
    }

    public void setCurrUserID(String userID) throws FileNotFoundException, IOException {
        this.userID = userID;
    }

    public String getCurrUserID() {
        return userID;
    }

    private void saveSession() throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream("session.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    private void saveLog() {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        String date = timestamp.format(dateFormatter);
        String time = timestamp.format(timeFormatter);
        String[] session = {getCurrUserID(), date, time};

        List<String[]> sessions;
        try {
            // Read existing sessions from log.bin file if it exists
            FileInputStream fileIn = new FileInputStream("log.bin");
            try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
                sessions = (List<String[]>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            // If log.bin file does not exist, create new sessions list
            sessions = new ArrayList<>();
        }

        // Add current session to sessions list
        sessions.add(session);

        // Save updated sessions list to log.bin file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("log.bin"))) {
            out.writeObject(sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadSession() throws IOException, ClassNotFoundException {
        CurrUser session = null;
        try (FileInputStream fileIn = new FileInputStream("session.bin");
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            session = (CurrUser) in.readObject();
        } catch (FileNotFoundException e) {
            // If session.bin file does not exist, return null
        }
        return session.getCurrUserID();
    }

    public static List<String[]> loadLog() {
        List<String[]> sessions;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("log.bin"))) {
            sessions = (List<String[]>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If log.bin file does not exist, return empty list
            sessions = new ArrayList<>();
        }
        return sessions;
    }

    public static Customer getCustomer() throws IOException, ClassNotFoundException {
        // Search for the userID in customers.bin
        List<Customer> customers = Customer.loadCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(loadSession())) {
                return customer;
            }
        }
        return null;
    }
    
    public static Employee getEmployee() throws IOException, ClassNotFoundException {
        // If not found in customers.bin, search for the userID in employees.bin
        List<Employee> employees = Employee.loadEmployee();
        for (Employee employee : employees) {
            if (employee.getId().equals(loadSession())) {
                return employee;
            }
        }
        return null;
    }
    
}
