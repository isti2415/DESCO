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

public class Bill implements Serializable {

    private String billMonth;
    private String billYear;
    private float usage;
    private float rate = 4.14f;
    private float total;
    private String userID;
    private Boolean dispute;

    public Bill(String userID, String billMonth, String billYear, float usage) {
        this.userID = userID;
        this.billMonth = billMonth;
        this.billYear = billYear;
        this.usage = usage;
        this.total = this.usage * rate;
        this.dispute = false;
        saveBill();
    }

    public Boolean getDispute() {
        return dispute;
    }

    public void setDispute(Boolean dispute) {
        this.dispute = dispute;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public String getBillYear() {
        return billYear;
    }

    public void setBillYear(String billYear) {
        this.billYear = billYear;
    }

    public float getUsage() {
        return usage;
    }

    public void setUsage(float usage) {
        this.usage = usage;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getNetBill() {
        return total;
    }

    public void setNetBill(float total) {
        this.total = total;
    }

    public String viewBill(String userID, String month, String year) {
        // TODO: Implement viewBill method
        return null;
    }

    private void saveBill() {
        List<Bill> billList = Bill.loadBill();
        // Check if the user ID of the current user already exists in the list
        boolean exists = false;
        for (Bill bill : billList) {
            if (bill.getUserID().equals(this.getUserID()) && bill.getBillMonth().equals(this.getBillMonth()) && bill.getBillYear().equals(this.getBillYear())) {
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

    private static List<Bill> loadBill() {
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

    public void updateBill(String userID, String billMonth, String billYear, float usage) {
        List<Bill> billList = Bill.loadBill();
        boolean updated = false;

        for (Bill bill : billList) {
            if (bill.getUserID().equals(userID) && bill.getBillMonth().equals(billMonth) && bill.getBillYear().equals(billYear)) {
                bill.setUsage(usage);
                bill.setTotal(usage * bill.getRate());
                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("Bill not found");
        } else {
            try (FileOutputStream fileOut = new FileOutputStream("bills.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(billList);
                System.out.println("Bill updated and saved to bills.bin file");
            } catch (IOException e) {
                System.out.println("Error updating bill");
            }
        }
    }

}
