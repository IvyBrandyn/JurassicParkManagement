package application.controller;

import java.io.IOException;

import application.Main;
import application.model.MusicAndVideoPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

/**
 * MainController is the EventHandler for Main.fxml.
 * Buttons that reflect a zone can be selected to
 * navigate to that zone's specific information view. 
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class MainController {
	
    @FXML
    private Button zoneG;

    @FXML
    private Button zoneD;

    @FXML
    private Button zoneTR;
    
    @FXML
    private Button zoneX;

    @FXML
    private Button zoneB;

    @FXML
    private Button zoneR;
    
    @FXML
    private Button zoneTY;

    @FXML
    private ImageView parkImage;

    @FXML
    private Label selectZone;
    
    @FXML
    private Label welcome;
    
    @FXML
    private MediaView montage;
    
    private ZoneController zoneController;
    private FXMLLoader loader;

	public void initialize() {
		zoneG.setText("G");
		zoneD.setText("D");
		zoneTR.setText("TR");
		zoneX.setText("X");
		zoneB.setText("B");
		zoneR.setText("R");
		zoneTY.setText("TY");
		
		parkImage.setId("parkImage");
		
		selectZone.setText("Select a zone to continue:");
		welcome.setText("Welcome to");
		
    	montage.setMediaPlayer(MusicAndVideoPlayer.vp);
		montage.setScaleX(2);
		montage.setScaleY(2.8);
	}
	
    @FXML
    public void zoneSelection(ActionEvent event) {
    	if(event.getSource().equals(zoneB))
    		zoneController = new ZoneController("B");
    	else if(event.getSource().equals(zoneD))
    		zoneController = new ZoneController("D");
    	else if(event.getSource().equals(zoneG))
    		zoneController = new ZoneController("G");
    	else if(event.getSource().equals(zoneR))
    		zoneController = new ZoneController("R");
    	else if(event.getSource().equals(zoneTR))
    		zoneController = new ZoneController("TR");
    	else if(event.getSource().equals(zoneTY))
    		zoneController = new ZoneController("TY");
    	else if(event.getSource().equals(zoneX))
    		zoneController = new ZoneController("X");
		try {
	    	loader = new FXMLLoader(getClass().getResource("../view/Zone.fxml"));
	    	loader.setController(zoneController);
			Parent root = loader.load();
			Main.stage.setScene(new Scene(root,800,800));
			Main.stage.getScene().getStylesheets().add(getClass().getResource("../Zone.css").toExternalForm());
			Main.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
