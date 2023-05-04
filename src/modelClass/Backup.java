/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Backup implements Serializable {

    private LocalDateTime timestamp;
    private String size;
    private String fileCount;

    public Backup() throws IOException {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String folderName = timestamp.format(formatter);

        // Create backup folder
        File backupFolder = new File(folderName);
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }

        // Copy .txt and .bin files from root directory to backup folder
        File rootDirectory = new File(".");
        File[] filesToCopy = rootDirectory.listFiles((dir, name) -> (name.endsWith(".txt") || name.endsWith(".bin")) && !name.equals("backups.bin"));
        for (File file : filesToCopy) {
            Path source = file.toPath();
            Path destination = new File(backupFolder, file.getName()).toPath();
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        }

        // Get size and number of files of backup folder
        try {
            BasicFileAttributes attributes = Files.readAttributes(backupFolder.toPath(), BasicFileAttributes.class);
            long size = attributes.size() / 1024;
            int fileCount = (int) Files.walk(backupFolder.toPath()).count() - 1; // exclude the backup folder itself

            // Create Backup object with timestamp, size, and file count
            this.timestamp = LocalDateTime.now();
            this.size = String.valueOf(size) + "KB";
            this.fileCount = String.valueOf(fileCount);
            saveBackup();
            // TODO: Write backup information to a file or database
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    private void saveBackup() {
        List<Backup> backups = Backup.loadBackup();
        backups.add(this);
        try (FileOutputStream fileOut = new FileOutputStream("backups.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(backups);
            System.out.println("Backup saved to backups.bin file");
        } catch (IOException e) {
            System.out.println("Error saving backup to file");
        }
    }

    public static List<Backup> loadBackup() {
        List<Backup> backups = new ArrayList<>();
        try {
            try ( // Read the list of backups from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("backups.bin"))) {
                backups = (List<Backup>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return backups;
    }

}
