/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Istiaqs-PC
 */



public class Bill {
    private String billMonth;
    private String billYear;
    private float usage;
    private float rate=8.24f;
    private float netBill;
    private String userID;

    public Bill(String userID, String billMonth, String billYear, float usage) {
        this.userID = userID;
        this.billMonth = billMonth;
        this.billYear = billYear;
        this.usage = usage;
        this.netBill = usage*rate;
        addToDB();
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
        return netBill;
    }

    public void setNetBill(float netBill) {
        this.netBill = netBill;
    }
    
    public String viewBill(String userID,String month, String year){
        
        return null;
    }
    
    public void addToDB(){
        
    }

    @Override
    public String toString() {
        return "Bill{" + "billMonth=" + billMonth + ", billYear=" + billYear + ", usage=" + usage + ", rate=" + rate + ", netBill=" + netBill + ", userID=" + userID + '}';
    }
    
}
