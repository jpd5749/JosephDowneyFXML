/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josephdowneyfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Joe
 */
public class JosephDowneyFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
                                                            //needed to change this to the new fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/view/CRUD.fxml"));
        
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
