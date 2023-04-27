/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.time.LocalDate;

/**
 *
 * @author Istiaqs-PC
 */
public class Attendance {

    private String employeeID;
    private LocalDate date;
    private Boolean present;

    public Attendance(String employeeID, LocalDate date, Boolean present) {
        this.employeeID = employeeID;
        this.date = date;
        this.present = present;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
    
}
