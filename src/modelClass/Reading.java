package modelClass;

import java.io.*;
import java.util.*;

public class Reading implements Serializable {

    private int month;
    private int year;
    private float value;
    private String meterID;

    private static final Map<String, Integer> MONTHS_MAP = new HashMap<>();

    static {
        MONTHS_MAP.put("january", 1);
        MONTHS_MAP.put("february", 2);
        MONTHS_MAP.put("march", 3);
        MONTHS_MAP.put("april", 4);
        MONTHS_MAP.put("may", 5);
        MONTHS_MAP.put("june", 6);
        MONTHS_MAP.put("july", 7);
        MONTHS_MAP.put("august", 8);
        MONTHS_MAP.put("september", 9);
        MONTHS_MAP.put("october", 10);
        MONTHS_MAP.put("november", 11);
        MONTHS_MAP.put("december", 12);
    }

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
        updateReadings();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = convertMonth(month);
        updateReadings();
    }

    public int getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = convertYear(year);
        updateReadings();
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        updateReadings();
    }

    private void saveReadings() {
        List<Reading> readingList = Reading.loadReadings();
        // Check if the reading ID of the current reading already exists in the list
        boolean exists = false;
        for (Reading reading : readingList) {
            if (reading.getMeterID().equals(this.getMeterID()) && reading.getMonth() == this.getMonth() && reading.getYear() == this.getYear()) {
                exists = true;
                break;
            }
        }
        // If the reading ID already exists, show an error message and do not save the reading
        if (exists) {
            System.out.println("Reading already exists");
        } else {
            // Otherwise, add the reading to the list and save the list to the file
            readingList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("readings.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(readingList);
                System.out.println("Reading saved to readings.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private void updateReadings() {
        List<Reading> readingList = Reading.loadReadings();
        boolean found = false;
        for (int i = 0; i < readingList.size(); i++) {
            Reading r = readingList.get(i);
            if (r.getMeterID().equals(this.getMeterID()) && r.getMonth() == this.getMonth() && r.getYear() == this.getYear()) {
                readingList.set(i, this);
                found = true;
                break;
            }
        }
        if (!found) {
            readingList.add(this);
        }
        try (FileOutputStream fileOut = new FileOutputStream("readings.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(readingList);
            System.out.println("Readings updated in readings.bin file");
        } catch (IOException e) {
            System.out.println("Error updating readings in file");
        }
    }

    private static List<Reading> loadReadings() {
        List<Reading> readings = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("readings.bin"))) {
            readings = (List<Reading>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readings;
    }

    public int convertMonth(String month) {
        Integer monthNumber = MONTHS_MAP.get(month.toLowerCase());
        if (monthNumber != null) {
            return monthNumber;
        } else {
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
        for (Map.Entry<String, Integer> entry : MONTHS_MAP.entrySet()) {
            if (entry.getValue() == month) {
                return entry.getKey();
            }
        }
        return "Invalid Month";
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
