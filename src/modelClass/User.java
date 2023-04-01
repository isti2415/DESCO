package modelClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private String password;
    private String usertype;

    public User(String userid, String password, String usertype) {
        this.userid = userid;
        this.password = password;
        this.usertype = usertype;
        System.out.println(this.toString());
        saveUser();
    }

    public String getUserID() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return usertype;
    }

    public void setPassword(String password) {
        this.password = password;
        saveUser();
    }

    public void setUserType(String usertype) {
        this.usertype = usertype;
        saveUser();
    }

    private void saveUser() {
        try {
            try (FileOutputStream fileOut = new FileOutputStream("users.bin", true); ObjectOutputStream out = new AppendableObjectOutputStream(fileOut)) {
                out.writeObject(this);
            }
            System.out.println("User saved to users.bin file");
        } catch (IOException e) {
            System.out.println("Error saving user to file");
        }
    }

    public String toString() {
        return "User [userId=" + userid + "&&password=" + password + "&&userType=" + usertype + "]";
    }

    public Object getUserId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
