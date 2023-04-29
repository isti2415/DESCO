/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD



        Parent root = FXMLLoader.load(getClass().getResource("sysAd.fxml"));


=======
        
        Parent root = FXMLLoader.load(getClass().getResource("sysAd.fxml"));
>>>>>>> f2c885c080dbe77c7192c302b4400253a04e6a79
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}