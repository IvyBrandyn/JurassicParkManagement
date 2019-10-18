package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import application.model.Dinosaur;
import application.model.Park;
import application.model.Zone;
import application.model.MusicAndVideoPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class ZoneController {
	
    /** The relocate name. */
    @FXML
    private Label relocateName;
    
    /** The zone label. */
    @FXML
    private Label zoneLabel;

    /** The add name. */
    @FXML
    private Label addName;

    /** The add success. */
    @FXML
    private Label addSuccess;

    /** The add label. */
    @FXML
    private Label addLabel;

    /** The add carnivore. */
    @FXML
    private Label addCarnivore;
    
    /** The num dinos fill. */
    @FXML
    private Label numDinosFill;
    
    /** The threat level. */
    @FXML
    private Label threatLevel;
    
    /** The num dinos. */
    @FXML
    private Label numDinos;
    
    /** The add type. */
    @FXML
    private Label addType;

    /** The relocate success. */
    @FXML
    private Label relocateSuccess;
    
    /** The relocate zone code. */
    @FXML
    private Label relocateZoneCode;
    
    /** The threat level fill. */
    @FXML
    private Label threatLevelFill;

    /** The relocate label. */
    @FXML
    private Label relocateLabel;

    /** The relocate. */
    @FXML
    private Button relocate;

    /** The add. */
    @FXML
    private Button add;
    
    /** The home. */
    @FXML
    private Button home;
    
    /** The view button. */
    @FXML
    private Button viewButton;

    /** The add name text field. */
    @FXML
    private TextField addNameTextField;
    
    /** The relocate zone code text field. */
    @FXML
    private TextField relocateZoneCodeTextField;

    /** The add type text field. */
    @FXML
    private TextField addTypeTextField;
    
    /** The relocate name text field. */
    @FXML
    private TextField relocateNameTextField;

    /** The add no. */
    @FXML
    private RadioButton addNo;
    
    /** The add yes. */
    @FXML
    private RadioButton addYes;
    
    /** The add pane. */
    @FXML
    private Pane addPane;
    
    /** The relocate pane. */
    @FXML
    private Pane relocatePane;

    /** The dinosaurs list. */
    @FXML
    private ListView<String> dinosaursList;
    
    /** The yes no radios. */
    @FXML
    private ToggleGroup yesNoRadios;
    
    /** The montage. */
    @FXML
    private MediaView montage;
    
    /** The jurassic park. */
    private Park jurassicPark;
    
    /** The selected zone. */
    private Zone selectedZone;
    
    /** The dinos. */
    private ArrayList<Dinosaur> dinos;
    
    /** The zone code. */
    private String zoneCode;
    
    /**
     * Instantiates a new zone controller.
     *
     * @param zoneCode the zone code
     */
    public ZoneController(String zoneCode) {
    	this.zoneCode = zoneCode;
    }
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	zoneLabel.setId("zoneLabel");
    	addPane.setId("pane");
    	relocatePane.setId("pane");
    	addSuccess.setId("success");
    	relocateSuccess.setId("success");
    	addLabel.setId("paneLabels");
    	relocateLabel.setId("paneLabels");
    	
    	home.setText("Home");
    	numDinos.setText("# Dinosaurs:");
    	threatLevel.setText("Threat Level:");
    	viewButton.setText("View Dinosaurs");
    	
    	addLabel.setText("Add New Dinosaur to this Zone");
    	addName.setText("Name:");
    	addType.setText("Type:");
    	addCarnivore.setText("Carnivore?");
    	addYes.setText("Yes");
    	addNo.setText("No");
    	add.setText("Add");
    	addSuccess.setText("");
    	
    	relocateLabel.setText("Relocate Dinosaur from this Zone");
    	relocateName.setText("Name:");
    	relocateZoneCode.setText("Zone Code:");
    	relocate.setText("Relocate");
    	relocateSuccess.setText("");
    	
    	montage.setMediaPlayer(MusicAndVideoPlayer.vp);
		montage.setScaleX(2);
		montage.setScaleY(2.8);
		
		jurassicPark = new Park("Jurassic Park");
		try {
    		jurassicPark.loadZones("src/application/data/zones.csv");
    		jurassicPark.loadDinosaurs("src/application/data/dinos.csv");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		
		selectedZone = jurassicPark.getZone(zoneCode);
    	dinos = jurassicPark.getParkMap().get(selectedZone);
    	
    	zoneLabel.setText(selectedZone.getZoneName() + " Zone (" + zoneCode + ")");
    	numDinosFill.setText(String.valueOf(jurassicPark.getNumberOfDinosaursInZone(selectedZone)));
    	threatLevelFill.setText(selectedZone.getThreatLevel());
    	for(Dinosaur dino : dinos) {
    		dinosaursList.getItems().add(dino.toString());
    	}
    }
    
    /**
     * Home button.
     */
    @FXML
    public void homeButton() {
    	try {
	    	Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root,800,800));
			Main.stage.getScene().getStylesheets().add(getClass().getResource("../Main.css").toExternalForm());
			Main.stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Adds the button.
     */
    @FXML
    public void addButton() {
    	String dinoName = addNameTextField.getText();
		String dinoType = addTypeTextField.getText();
		if(dinoName.isEmpty() || dinoType.isEmpty() 
				|| yesNoRadios.getSelectedToggle()==null) {
			addSuccess.setText("Please check fields for correctness");
			return;
		}
		boolean vegetarian = true;
		if(yesNoRadios.getSelectedToggle().equals(addYes)) {
			vegetarian = false;
		}
		
		jurassicPark.addDinosaur(selectedZone, dinoName, dinoType, vegetarian);
		dinosaursList.getItems().add(jurassicPark.getParkMap().get(selectedZone)
				.get(jurassicPark.getParkMap().get(selectedZone).size()-1).toString());
		
		
		try {
			jurassicPark.save();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		addSuccess.setText(dinoName + " was successfully added to zone!");
		numDinosFill.setText(String.valueOf(jurassicPark
				.getNumberOfDinosaursInZone(selectedZone)));
		addNameTextField.clear();
		addTypeTextField.clear();
		yesNoRadios.selectToggle(null);
    }
    
    /**
     * Relocate button.
     */
    @FXML
    public void relocateButton() {
    	String dinoName = relocateNameTextField.getText();
		String dinoZoneTargetZoneCode = relocateZoneCodeTextField.getText();
		
		if(dinoName.isEmpty() || dinoZoneTargetZoneCode.isEmpty()) {
			relocateSuccess.setText("Please check fields for correctness");
			return;
		}
		if(jurassicPark.dinoExists(dinoName, selectedZone) 
				&& jurassicPark.zoneExists(dinoZoneTargetZoneCode.toUpperCase())) {
			Zone dinoZoneTarget = jurassicPark.getZone(dinoZoneTargetZoneCode.toUpperCase());
			jurassicPark.relocateDinosaur(dinoName, selectedZone, dinoZoneTarget);
			
			numDinosFill.setText(String.valueOf(jurassicPark
					.getNumberOfDinosaursInZone(selectedZone)));
			relocateSuccess.setText(dinoName 
					+ " successfully relocated to zone " 
					+ dinoZoneTargetZoneCode.toUpperCase());
			dinosaursList.getItems().clear();
	    	for(Dinosaur dino : dinos) {
	    		dinosaursList.getItems().add(dino.toString());
	    	}
			try {
				jurassicPark.save();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			relocateSuccess.setText("Please check if dinosaur exists in zone");
		}
		
		relocateNameTextField.clear();
		relocateZoneCodeTextField.clear();
    }
    
    /**
     * View dinos.
     */
    @FXML
    public void viewDinos() {
    	try {
    		FXMLLoader loader;
        	DinosaurController dinosaurController = new DinosaurController(dinos, zoneLabel.getText());
	    	loader = new FXMLLoader(getClass().getResource("../view/Dinosaur.fxml"));
	    	loader.setController(dinosaurController);
			Parent root = loader.load();
			Main.stage.setScene(new Scene(root,800,800));
			Main.stage.getScene().getStylesheets().add(getClass().getResource("../Dinosaur.css").toExternalForm());
			Main.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
