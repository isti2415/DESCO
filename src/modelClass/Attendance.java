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
import java.util.List;

public class Attendance implements Serializable {

    private String employeeID;
    private LocalDate date;
    private Boolean present;
    private static final String FILENAME = "attendance.bin";

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
        updateAttendance();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        updateAttendance();
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
        updateAttendance();
    }

    private void updateAttendance() {
        List<Attendance> attendanceList = loadAttendance();
        Boolean found = false;
        for (Attendance attendance : attendanceList) {
            if (attendance.getEmployeeID().equals(this.getEmployeeID()) && attendance.getDate().equals(this.getDate())) {
                attendanceList.remove(attendance);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Attendance record not found");
        } else {
            attendanceList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(attendanceList);
                System.out.println("Attendance record saved to attendance.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private void saveAttendance() {
        List<Attendance> attendanceList = loadAttendance();
        boolean exists = false;
        for (Attendance attendance : attendanceList) {
            if (attendance.getEmployeeID().equals(this.getEmployeeID()) && attendance.getDate().equals(this.getDate())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Attendance record already exists");
        } else {
            attendanceList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(attendanceList);
                System.out.println("Attendance record saved to attendance.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private static List<Attendance> loadAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                attendanceList = (List<Attendance>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading attendance records from file");
        }
        return attendanceList;
    }
}
