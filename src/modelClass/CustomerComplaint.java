/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.time.LocalDate;

/**
 *
 * @author Syed
 */
public class CustomerComplaint {
    private String customerID;
    private String complaintID;
    private String address;
    private LocalDate date;
    private String contact;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public CustomerComplaint(String customerID, String complaintID, String address, LocalDate date, String contact) {
        this.customerID = customerID;
        this.complaintID = complaintID;
        this.address = address;
        this.date = date;
        this.contact = contact;
    }   
}
