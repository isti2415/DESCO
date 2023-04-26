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

public class Reading implements Serializable {

    private int month;
    private int year;
    private float value;
    private String meterID;

    public Reading(String month, String year, float value, String meterID) {
        this.month = convertMonth(month);
        this.year = convertYear(year);
        this.value = value;
        this.meterID = meterID;
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

    private void saveReadings() {
        List<Reading> readingList = Reading.loadReadings();
        // Check if the user ID of the current user already exists in the list
        boolean exists = false;
        for (Reading reading : readingList) {
            if (reading.getMeterID().equals(this.getMeterID()) && reading.getMonth() == this.getMonth() && reading.getYear() == this.getYear()) {
                exists = true;
                break;
            }
        }
        // If the user ID already exists, show an error message and do not save the user
        if (exists) {
            System.out.println("Reading already exists");
        } else {
            // Otherwise, add the user to the list and save the list to the file
            readingList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("readings.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(readingList);
                System.out.println("Reading saved to readings.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private static List<Reading> loadReadings() {
        List<Reading> readings = new ArrayList<>();
        try {
            try ( // Read the list of users from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("readings.bin"))) {
                readings = (List<Reading>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return readings;
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
