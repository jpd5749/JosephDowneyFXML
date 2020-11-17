/**
 * Sample Skeleton for 'DetailModelView.fxml' Controller Class
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usermodel;

public class DetailedModelViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader
        
    @FXML // fx:id="IDLabel"
    private Label IDLabel; // Value injected by FXMLLoader

    @FXML // fx:id="UsernameLabel"
    private Label UsernameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="EmailLabel"
    private Label EmailLabel; // Value injected by FXMLLoader

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    //the following code was adapted from the sample source code
    Usermodel selectedModel;
    
    public void initData(Usermodel model) {
        selectedModel = model;
        IDLabel.setText(model.getId().toString());
        UsernameLabel.setText(model.getUsername());
        EmailLabel.setText(model.getEmailaddress());
        //the password of the user is purposely not shown, as that would be a security vulnerability if the data were real

        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + model.getUsername() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert IDLabel != null : "fx:id=\"IDLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert UsernameLabel != null : "fx:id=\"UsernameLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert EmailLabel != null : "fx:id=\"EmailLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        
    }
}
