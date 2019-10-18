package application;
	
import application.model.MusicAndVideoPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

/**
 * Main launches the application and sets the stage
 * to the zone selection screen.
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {

		MusicAndVideoPlayer mp = new MusicAndVideoPlayer();
		mp.start();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root,800,800));
			primaryStage.getScene().getStylesheets().add(getClass()
					.getResource("Main.css").toExternalForm());
			primaryStage.show();
			
			stage = primaryStage;
			
		} catch(Exception e) {
			System.out.println("Error loading the files.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}
}
