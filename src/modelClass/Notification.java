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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Notification {
    
    private LocalDate date;
    private String subject;
    private String details;
    private String type;
    private String filepath;

    public Notification(LocalDate date, String subject, String details, String type) {
        this.date = date;
        this.subject = subject;
        this.details = details;
        this.type = type;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    private void saveNotification() {
        List<Notification> notificationList = loadNotifications();
        // Check if the notification ID of the current notification already exists in the list
        boolean exists = false;
        for (Notification notification : notificationList) {
            if (notification.getDetails().equals(this.getDetails())) {
                exists = true;
                break;
            }
        }
        // If the notification ID already exists, show an error message and do not save the notification
        if (exists) {
            System.out.println("Notification already exists");
        } else {
            // Otherwise, add the notification to the list and save the list to the file
            notificationList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream("notifications.bin", false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(notificationList);
                System.out.println("Notification saved to notifications.bin file");
            } catch (IOException e) {
                System.out.println("Error saving notification to file");
            }
        }
    }

    private static List<Notification> loadNotifications() {
        List<Notification> notifications = new ArrayList<>();
        try {
            try ( // Read the list of notifications from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("notifications.bin"))) {
                notifications = (List<Notification>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        return notifications;
    }
    
}
