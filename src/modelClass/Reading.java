package modelClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Reading implements Serializable {

    private int month;
    private int year;
    private float value;
    private String meterID;
    private String FILENAME = "readings.bin";

    public Reading(String month, String year, float value, String meterID) {
        this.month = convertMonth(month);
        this.year = convertYear(year);
        this.value = value;
        this.meterID=meterID;
        saveReadings();
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = convertMonth(month);
    }

    public int getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = convertYear(year);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public void saveReadings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving reading: " + e.getMessage());
        }
    }

    public int convertMonth(String month) {
        switch (month.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return -1;
        }
    }

    public int convertYear(String year) {
        try {
            return Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String convertMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Invalid Month";
        }
    }

    public String convertYear(int year) {
        return String.valueOf(year);
    }
    
    public boolean isAfter(Reading other) {
        if (this.year > other.year) {
            return true;
        } else if (this.year == other.year && this.month > other.month) {
            return true;
        } else {
            return false;
        }
    }

}
