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
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class Task implements Serializable {

    public String taskID;
    public String title;
    public String employeeID;
    public String description;
    public LocalDate date;
    public Boolean status;
    
    private static String FILENAME = "tasks.bin";

    public Task(String employeeID, String title, String description, LocalDate date) {
        this.taskID = generateTaskID();
        this.employeeID = employeeID;
        this.description = description;
        this.date = date;
        this.status = false;
        this.title=title;
        saveTask();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        updateTask();
    }

    private String generateTaskID() {
        List<Task> tasks = new ArrayList<>();
        String startID = "1";
        try {
            try ( // Read the list of tasks from the file
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tasks.bin"))) {
                tasks = (List<Task>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
        // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
        }
        tasks.sort(Comparator.comparing(Task::getTaskID, String.CASE_INSENSITIVE_ORDER));
        for (Task t : tasks) {
            if (startID.equals(t.getTaskID())) {
                int id = Integer.parseInt(startID);
                id++;
                startID = String.valueOf(id);
            }
        }
        return startID;
    }

    private void updateTask() {
        List<Task> taskList = loadTask();
        Boolean found = false;
        for (Task task : taskList) {
            if (task.getTaskID().equals(this.getTaskID())) {
                taskList.remove(task);
                found = true;
                break;
            }
        }
        if (found == false) {
            System.out.println("Task not found");
        } else {
            taskList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(taskList);
                System.out.println("Task saved to tasks.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    private void saveTask() {
        List<Task> taskList = loadTask();
        boolean exists = false;
        for (Task task : taskList) {
            if (task.getTaskID().equals(this.getTaskID())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Task already exists");
        } else {
            taskList.add(this);
            try (FileOutputStream fileOut = new FileOutputStream(FILENAME, false); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(taskList);
                System.out.println("Task saved to tasks.bin file");
            } catch (IOException e) {
                System.out.println("Error saving reading to file");
            }
        }
    }

    public static List<Task> loadTask() {
        List<Task> taskList = new ArrayList<>();
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                taskList = (List<Task>) inputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            // Ignore the exception if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file");
        }
        return taskList;
    }

}
