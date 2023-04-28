/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import static com.itextpdf.kernel.pdf.collection.PdfCollectionField.FILENAME;
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
import javafx.collections.ObservableList;

/**
 *
 * @author Istiaqs-PC
 */
public class Complaint implements Serializable {

    private String customerID;
    private String complaintID;
    private String details;
    private LocalDate date;
    private String feedback;
    private Boolean resolved;
    private String employeeID;

    public Complaint(String customerID, String details, LocalDate date) {
        this.customerID = customerID;
        this.details = details;
        this.date = date;
        this.resolved = false;
        this.complaintID = generateComplaintID();
        saveComplaint();
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        updateComplaint();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
        updateComplaint();
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
        updateComplaint();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        updateComplaint();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        updateComplaint();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
        updateComplaint();
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
        updateComplaint();
    }

    private String generateComplaintID() {
        List<Complaint> complaints = new ArrayList<>();
        String startID = "1";
        try {
            try ( // Read the list of complaints from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("complaints.bin"))) {
                complaints = (List<Complaint>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        complaints.sort(Comparator.comparing(Complaint::getComplaintID, String.CASE_INSENSITIVE_ORDER));
        for (Complaint c : complaints) {
            if (startID.equals(c.getComplaintID())) {
                int id = Integer.parseInt(startID);
                id++;
                startID = String.valueOf(id);
            }
        }
        return startID;
    }

    private void updateComplaint() {
        List<Complaint> complaintList = Complaint.loadComplaint();
        Boolean found = false;
        for (Complaint complaint : complaintList) {
            if (complaint.getComplaintID().equals(this.getComplaintID())) {
                complaintList.remove(complaint);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Complaint not found");
        } else {
            complaintList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("complaints.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(complaintList);
                System.out.println("Complaint saved to complaints.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private void saveComplaint() {
        List<Complaint> complaintList = Complaint.loadComplaint();
        // Check if the complaint ID of the current complaint already exists in the list
        boolean exists = false;
        for (Complaint complaint : complaintList) {
            if (complaint.getDetails().equals(this.getDetails()) && complaint.getCustomerID().equals(this.getCustomerID())) {
                exists = true;
                break;
            }
        }
        // If the complaint ID already exists, show an error message and do not save the complaint
        if (exists) {
            System.out.println("Complaint already exists");
        } else {
            // Otherwise, add the complaint to the list and save the list to the file
            complaintList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("complaints.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(complaintList);
                System.out.println("Complaint saved to complaints.bin file");
            } catch (IOException e) {
                System.out.println("Error saving complaint to file");
            }
        }
    }

    private static List<Complaint> loadComplaint() {
        List<Complaint> complaints = new ArrayList<>();
        try {
            try ( // Read the list of complaints from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("complaints.bin"))) {
                complaints = (List<Complaint>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return complaints;
    }
}
