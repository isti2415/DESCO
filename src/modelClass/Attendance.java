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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Attendance implements Serializable {

    private String employeeID;
    private LocalDate date;
    private Boolean present;
    private String reason;
    private Integer absence;
    private static final String FILENAME = "attendance.bin";

    public Attendance(String employeeID, LocalDate date, Boolean present) {
        this.employeeID = employeeID;
        this.date = date;
        this.present = present;
        this.absence = getPreviousAbsence()+1;
        this.reason = "";
        saveAttendance();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
        updateAttendance();
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
        int absence = 0;
        if (present) {
            absence = --this.absence;
        } else {
            absence = ++this.absence;
        }
        for (Attendance a : loadAttendance()) {
            if (a.getDate().getYear() == this.getDate().getYear()) {
                a.setAbsence(absence);
            }
        }
        updateAttendance();
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
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

    public static List<Attendance> loadAttendance() {
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

    private Integer getPreviousAbsence() {
        List<Attendance> attendanceList = loadAttendance();
        int totalAbsence = 0;
        if (!attendanceList.isEmpty()) {
            // Sort the attendance list in descending order of date
            Collections.sort(attendanceList, Collections.reverseOrder(Comparator.comparing(Attendance::getDate)));

            // Get the most recent attendance record
            Attendance mostRecentRecord = attendanceList.get(0);

            // Check if the most recent record is from a previous year
            if (mostRecentRecord.getDate().getYear() < this.getDate().getYear()) {
                return 1;
            }

            // Calculate total absence for the current year
            for (Attendance a : attendanceList) {
                if (a.getEmployeeID().equals(this.employeeID) && a.getDate().getYear() == this.getDate().getYear()) {
                    totalAbsence = a.getAbsence();
                    a.setAbsence(totalAbsence+1);
                }
            }
        }

        return totalAbsence;
    }

}
