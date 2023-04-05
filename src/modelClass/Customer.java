/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClass;

import java.io.Serializable;

/**
 *
 * @author Istiaqs-PC
 */

public class Customer extends User implements Serializable{

    public Customer(String userID, String password, String userType) {
        super(userID, password, userType);
    }
    
}
