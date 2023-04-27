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

public class Inventory implements Serializable {
    
    private static final String FILENAME = "inventory.bin";

    private String inventoryID;
    private String name;
    private String quantity;
    private String department;

    public Inventory(String inventoryID, String name, String quantity, String department) {
        this.inventoryID = inventoryID;
        this.name = name;
        this.quantity = quantity;
        this.department = department;
    }

    // Getters and setters

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
        updateInventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateInventory();
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        updateInventory();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
        updateInventory();
    }

    public static List<Inventory> loadInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            inventoryList = (List<Inventory>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file does not exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
        }
        return inventoryList;
    }

    public void saveInventory() {
        List<Inventory> inventoryList = loadInventory();
        inventoryList.add(this);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(inventoryList);
            System.out.println("Inventory saved to " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error saving inventory to file: " + e.getMessage());
        }
    }

    public void updateInventory() {
        List<Inventory> inventoryList = loadInventory();
        boolean updated = false;
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getInventoryID().equals(this.inventoryID)) {
                inventoryList.set(i, this);
                updated = true;
                break;
            }
        }
        if (!updated) {
            System.out.println("Inventory with ID " + this.inventoryID + " not found");
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(inventoryList);
            System.out.println("Inventory updated");
        } catch (IOException e) {
            System.out.println("Error saving inventory to file: " + e.getMessage());
        }
    }
}
