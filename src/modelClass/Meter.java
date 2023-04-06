package modelClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Meter implements Serializable {

    private String meterID;
    private String userID;
    private List<Reading> readings;

    public Meter(String meterID, String userID) {
        this.meterID = meterID;
        this.userID = userID;
        this.readings = new ArrayList<>();
        saveMeter();
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

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    public void saveMeter() {
        List<Meter> meters = Meter.loadMeters();
        if (meters == null) {
            meters = new ArrayList<>();
        }

        // find the meter in the list and update its readings
        boolean found = false;
        for (Meter m : meters) {
            if (m.getMeterID().equals(this.meterID)) {
                m.setReadings(this.readings);
                found = true;
                break;
            }
        }

        // if meter is not found, add it to the list
        if (!found) {
            meters.add(this);
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("meters.bin"))) {
            outputStream.writeObject(meters);
            System.out.println("Meter saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving meter to file: " + e.getMessage());
        }
    }

    public static List<Meter> loadMeters() {
        List<Meter> meters = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("meters.bin"))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Meter) {
                    meters.add((Meter) obj);
                }
            }
        } catch (EOFException e) {
            // Reached end of file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading meters from file: " + e.getMessage());
        }
        return meters;
    }

    public static Meter findMeter(String meterID) {
        List<Meter> meters = loadMeters();
        for (Meter meter : meters) {
            if (meter.getMeterID().equals(meterID)) {
                return meter;
            }
        }
        return null;
    }

    public void updateReading(String month, String year, float value) {
        for (Reading reading : readings) {
            if ((reading.getMonth() == null ? month == null : reading.getMonth().equals(month)) && reading.getYear() == year) {
                reading.setValue(value);
                return;
            }
        }
        readings.add(new Reading(month, year, value));
    }

    public float getLastReading() {
        List<Reading> readings = this.getReadings();
        if (readings.isEmpty()) {
            return 0f;
        }

        Reading latestReading = readings.get(0);
        for (Reading reading : readings) {
            if (reading.isAfter(latestReading)) {
                latestReading = reading;
            }
        }

        return latestReading.getValue();
    }

    public static void deleteMeter(String meterID) {
        List<Meter> meters = loadMeters();
        boolean found = false;
        for (Meter meter : meters) {
            if (meter.getMeterID().equals(meterID)) {
                meters.remove(meter);
                found = true;
                break;
            }
        }
        if (found) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("meters.bin"))) {
                outputStream.writeObject(meters);
                System.out.println("Meter with ID " + meterID + " has been deleted.");
            } catch (IOException e) {
                System.out.println("Error deleting meter from file: " + e.getMessage());
            }
        } else {
            System.out.println("Meter with ID " + meterID + " was not found.");
        }
    }

}
