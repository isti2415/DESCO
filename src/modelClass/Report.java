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

public class Report implements Serializable {

    private String employeeID;
    private LocalDate date;
    private String subject;
    private String details;
    private String filePath;

    private static final String FILENAME = "reports.bin";

    public Report(String employeeID, LocalDate date, String subject, String details, String filePath) {
        this.employeeID = employeeID;
        this.date = date;
        this.subject = subject;
        this.details = details;
        this.filePath = filePath;
        saveReport();
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void saveReport() {
        List<Report> reportList = loadReport();
        boolean exists = false;
        for (Report report : reportList) {
            if (report.equals(this)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Report already exists");
        } else {
            reportList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(reportList);
                System.out.println("Report saved to report.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    public static List<Report> loadReport() {
        List<Report> reportList = new ArrayList<>();
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                reportList = (List<Report>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
        // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading reports from file");
        }
        return reportList;
    }
}
