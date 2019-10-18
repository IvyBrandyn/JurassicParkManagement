package application.controller;

import java.io.File;
import java.util.ArrayList;

import application.Main;
import application.model.Dinosaur;
import application.model.DinosaurTask;
import application.model.MusicAndVideoPlayer;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;

/**
 * ZoneController is the event handler for Zone.fxml
 * When this view is loaded, the zone name, code,
 * threat level, and number of dinosaurs will be
 * displayed.  In addition, a list of dinosaurs will
 * be shown to the user.
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class DinosaurController {

    /** The zone label. */
    @FXML
    private Label zoneLabel;
    
    /** The dinosaur name. */
    @FXML
    private Label dinosaurName;
    
    /** The dinosaur type. */
    @FXML
    private Label dinosaurType;
    
    /** The home. */
    @FXML
    private Button home;
    
    /** The dinosaur pane. */
    @FXML
    private Pane dinosaurPane;
    
	/** The montage. */
	@FXML
	private MediaView montage;
    
    /** The dinosaur image. */
    @FXML
    private ImageView dinosaurImage;
    
    /** The dinosaur list. */
    private ArrayList<Dinosaur> dinosaurList;
    
    /** The zone name. */
    private String zoneName;
    
    /** The threads. */
    private Thread thread1, thread2;
    
    /** The dinosaurTask. */
    private DinosaurTask dinosaurTask;
    
    /** The task. */
    Task<String> task;
	
	/**
	 * Instantiates a new dinosaur controller.
	 *
	 * @param dinosaurList the dinosaur list
	 * @param zoneName the zone name
	 */
	public DinosaurController(ArrayList<Dinosaur> dinosaurList, String zoneName) {
		this.dinosaurList = dinosaurList;
		this.zoneName = zoneName;
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		zoneLabel.setId("zoneLabel");
		dinosaurPane.setId("pane");
		home.setText("Home");
		
		zoneLabel.setText(zoneName);
		
		montage.setMediaPlayer(MusicAndVideoPlayer.vp);
		montage.setScaleX(2);
		montage.setScaleY(2.8);
		

		
		task = new Task<String>() {
			@Override
			protected String call() throws Exception {
				while(true) {
					for(Dinosaur dino: dinosaurList){
						try {
							File file = new File("src/application/data/images/" + dino.getDinoName().toLowerCase() + ".jpg");
							if(file.length()==0) {
								file = new File("src/application/data/images/noImageFound.png");
							}
							Image image = new Image(file.toURI().toString());
							dinosaurImage.setImage(image);
							try{
								Thread.sleep(2000);
							}catch(InterruptedException a) {
								break;
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		};
		
		try{
			dinosaurTask = new DinosaurTask(dinosaurList);
			dinosaurName.textProperty().bind(dinosaurTask.messageProperty());
			dinosaurType.textProperty().bind(dinosaurTask.valueProperty());
			
			thread1 = new Thread(dinosaurTask);
			thread1.start();
			thread2 = new Thread(task);
			thread2.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Home button.
	 */
	@FXML
    public void homeButton() {
		thread1.interrupt();
		thread2.interrupt();
    	try {
	    	Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			root.setId("background");
			Main.stage.setScene(new Scene(root,800,800));
			Main.stage.getScene().getStylesheets().add(getClass().getResource("../Main.css").toExternalForm());
			Main.stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
