/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Version implements Serializable{

    private LocalDate date;
    private String version;

    private static final String FILENAME = "versions.bin";

    public Version() {
        this.date = LocalDate.now();
        this.version = newVersion();
        saveVersion();
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    private void setVersion(String version) {
        this.version = version;
    }

    private String newVersion() {
        List<Version> versionList = loadVersion();
        if (versionList.isEmpty()) {
            return "1.0";
        } else {
            String lastVersion = "";
            if (versionList.size() > 0) {
                lastVersion = versionList.get(versionList.size() - 1).getVersion();
            }
            float floatVersion = Float.parseFloat(lastVersion);
            floatVersion += 0.1f;
            return String.format("%.1f", floatVersion);
        }
    }

    private void saveVersion() {
        List<Version> versionList = loadVersion();
        versionList.add(this);
        try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(versionList);
            System.out.println("Version saved to versions.bin file");
        } catch (IOException e) {
            System.out.println("Error saving version to file");
        }
    }

    private List<Version> loadVersion() {
        List<Version> versionList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            versionList = (List<Version>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading version from file: " + e.getMessage());
        }
        return versionList;
    }

}
