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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Istiaqs-PC
 */
public class CurrUserID implements Serializable {

    public String userID;

    public void setCurrUserID(String userID) throws FileNotFoundException, IOException {
        this.userID = userID;
        saveSession();
        saveLog();
    }

    public String getCurrUserID() {
        return userID;
    }

    private void saveSession() throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream("session.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    private void saveLog() {
        String[] session = {getCurrUserID(), LocalDateTime.now().toString()};
        List<String[]> sessions;
        try {
            // Read existing sessions from log.bin file if it exists
            FileInputStream fileIn = new FileInputStream("log.bin");
            try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
                sessions = (List<String[]>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            // If log.bin file does not exist, create new sessions list
            sessions = new ArrayList<>();
        }

        // Add current session to sessions list
        sessions.add(session);

        // Save updated sessions list to log.bin file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("log.bin"))) {
            out.writeObject(sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
