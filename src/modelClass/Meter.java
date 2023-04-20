package modelClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Meter implements Serializable {

    private String meterID;
    private String userID;
    private Reading readings;
    private Float lastReading;
    private String FILENAME = "meters.bin";

    public Meter(String meterID, String month, String year) {
        this.meterID = meterID;
        readings = new Reading(month,year,0f,meterID);
        saveMeter();
    }

    public Float getLastReading() {
        return lastReading;
    }

    public void setLastReading(Float lastReading) {
        this.lastReading = lastReading;
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Reading getReadings() {
        return readings;
    }

    public void setReadings(Reading readings) {
        this.readings = readings;
    }

    private void saveMeter() {
        List<Meter> meterList = Meter.loadMeter();
        // Check if the meter ID of the current meter already exists in the list
        boolean exists = false;
        for (Meter meter : meterList) {
            if (meter.getMeterID().equals(this.getMeterID())) {
                exists = true;
                break;
            }
        }
        // If the meter ID already exists, show an error message and do not save the meter
        if (exists) {
            System.out.println("Meter already exists");
        } else {
            // Otherwise, add the meter to the list and save the list to the file
            meterList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("meters.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(meterList);
                System.out.println("Meter saved to meters.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private static List<Meter> loadMeter() {
        List<Meter> meters = new ArrayList<>();
        try {
            try ( // Read the list of meters from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("meters.bin"))) {
                meters = (List<Meter>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return meters;
    }

}
