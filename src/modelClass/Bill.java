package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;

public class Bill implements Serializable {

    private YearMonth yearMonth;
    private float usage;
    private float rate = 4.14f;
    private float total;
    private String userID;
    private Boolean dispute;
    private LocalDate dueDate;
    private String billID;
    private Boolean status;

    public Bill(String userID, float usage,YearMonth yearmonth) {
        this.userID = userID;
        this.yearMonth = yearmonth;
        this.usage = usage;
        this.dispute = false;
        this.dueDate = this.yearMonth.atEndOfMonth();
        this.status = false;
        this.total = this.usage*rate;
        this.billID = generateBillID();
        saveBill();
    }

    public Boolean getDispute() {
        return dispute;
    }

    public void setDispute(Boolean dispute) {
        this.dispute = dispute;
        updateBill();
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
        updateBill();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        updateBill();
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
        updateBill();
    }

    public float getUsage() {
        return usage;
    }

    public void setUsage(float usage) {
        this.usage = usage;
        updateBill();
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
        updateBill();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        updateBill();
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
        updateBill();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        updateBill();
    }

    private String generateBillID() {
        List<Bill> bills = new ArrayList<>();
        String startID = "1";
        try {
            try ( // Read the list of bills from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("bills.bin"))) {
                bills = (List<Bill>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
// Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        bills.sort(Comparator.comparing(Bill::getBillID, String.CASE_INSENSITIVE_ORDER));
        for (Bill b : bills) {
            if (startID.equals(b.getBillID())) {
                int id = Integer.parseInt(startID);
                id++;
                startID = String.valueOf(id);
            }
        }
        return startID;
    }

    private void saveBill() {
        List<Bill> billList = Bill.loadBill();
        // Check if the user ID of the current user already exists in the list
        boolean exists = false;
        for (Bill bill : billList) {
            if (bill.getUserID().equals(this.getUserID()) && bill.getYearMonth().equals(this.getYearMonth())) {
                exists = true;
                break;
            }
        }
        // If the user ID already exists, show an error message and do not save the user
        if (exists) {
            System.out.println("Bill already exists");
        } else {
            // Otherwise, add the user to the list and save the list to the file
            billList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("bills.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(billList);
                System.out.println("Bill saved to bills.bin file");
            } catch (IOException e) {
                System.out.println("Error saving bill to file");
            }
        }
    }

    public static List<Bill> loadBill() {
        List<Bill> bills = new ArrayList<>();
        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("bills.bin"))) {
                bills = (List<Bill>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return bills;
    }

    private void updateBill() {
        List<Bill> billList = loadBill();
        boolean updated = false;
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getUserID().equals(this.getUserID())&& billList.get(i).getYearMonth().equals(this.getYearMonth())) {
                billList.set(i, this);
                updated = true;
                break;
            }
        }
        if (!updated) {
            System.out.println("Bill with ID " + this.billID + " not found");
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("bills.bin"))) {
            outputStream.writeObject(billList);
            System.out.println("Bill updated");
        } catch (IOException e) {
            System.out.println("Error saving bill to file: " + e.getMessage());
        }
    }
}
