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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Service implements Serializable{

    private String serviceType;
    private String details;
    private String customerID;
    private LocalDate date;
    private Boolean status;
    private String complaintID;

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Service(String serviceType, String details, String customerID, LocalDate date) {
        this.serviceType = serviceType;
        this.details = details;
        this.customerID = customerID;
        this.date = date;
        this.status = false;
        saveService();
    }
    
    private String generateComplaintID() {
        List<Complaint> complaints = new ArrayList<>();
        String startID = "1";
        try {
            try ( // Read the list of complaints from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("services.bin"))) {
                complaints = (List<Complaint>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        complaints.sort(Comparator.comparing(Complaint::getComplaintID, String.CASE_INSENSITIVE_ORDER));
        for (Complaint c : complaints) {
            if (startID.equals(c.getComplaintID())) {
                int id = Integer.parseInt(startID.substring(1));
                id++;
                startID = String.valueOf(id);
            }
        }
        return startID;
    }

    private void saveService() {
        List<Service> serviceList = Service.loadService();
    // Check if the service ID of the current service already exists in the list
        boolean exists = false;
        for (Service service : serviceList) {
            if (service.getDetails().equals(this.getDetails())&&service.getCustomerID().equals(this.getCustomerID())) {
                exists = true;
                break;
            }
        }
    // If the service ID already exists, show an error message and do not save the service
        if (exists) {
            System.out.println("Service already exists");
        } else {
    // Otherwise, add the service to the list and save the list to the file
            serviceList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("services.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(serviceList);
                System.out.println("Service saved to services.bin file");
            } catch (IOException e) {
                System.out.println("Error saving service to file");
            }
        }
    }

    private static List<Service> loadService() {
        List<Service> services = new ArrayList<>();
        try {
            try ( // Read the list of services from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("services.bin"))) {
                services = (List<Service>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return services;
    }

}
