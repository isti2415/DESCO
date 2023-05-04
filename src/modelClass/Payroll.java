package modelClass;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Payroll implements Serializable {
    private static final String FILENAME = "payroll.bin";

    private String employeeID;
    private String department;
    private String amount;
    private YearMonth yearMonth;
    private Boolean status;

    public Payroll(String employeeID, String department, String amount, Boolean status) {
        this.employeeID = employeeID;
        this.department = department;
        this.amount = amount;
        this.yearMonth = YearMonth.from(LocalDate.now());
        this.status = status;
        savePayroll();
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        updatePayroll();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
        updatePayroll();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
        updatePayroll();
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
        updatePayroll();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        updatePayroll();
    }

    public void savePayroll() {
        List<Payroll> payrollList = loadPayroll();
        boolean exists = false;
        for (Payroll payroll : payrollList) {
            if (payroll.getEmployeeID().equals(this.getEmployeeID()) && payroll.getYearMonth().equals(this.getYearMonth())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Payroll already exists");
        } else {
            payrollList.add(this);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                outputStream.writeObject(payrollList);
                System.out.println("Payroll saved to payroll.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    public void updatePayroll() {
        List<Payroll> payrollList = loadPayroll();
        Boolean found = false;
        for (Payroll payroll : payrollList) {
            if (payroll.getEmployeeID().equals(this.getEmployeeID()) && payroll.getYearMonth().equals(this.getYearMonth())) {
                payrollList.remove(payroll);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Payroll not found");
        } else {
            payrollList.add(this);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                outputStream.writeObject(payrollList);
                System.out.println("Payroll updated to payroll.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    public static List<Payroll> loadPayroll() {
        List<Payroll> payrollList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            payrollList = (List<Payroll>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading payroll from file");
        }
        return payrollList;
    }
}
