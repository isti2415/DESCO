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

    public void saveMeter() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
