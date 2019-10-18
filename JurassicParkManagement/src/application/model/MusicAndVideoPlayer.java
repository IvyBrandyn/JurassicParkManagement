package application.model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */

public class MusicAndVideoPlayer extends Thread {
	
	public static MediaPlayer mp, vp;

	/**
	 * 
	 */
	public MusicAndVideoPlayer() {
		Media mp3 = new Media(new File("src/application/data/theme.mp3").toURI().toString());
		Media video = new Media(new File("src/application/data/montage.mp4").toURI().toString());
		mp = new MediaPlayer(mp3);
		vp = new MediaPlayer(video);
	}
	
	@Override
	public void run() {
		mp.setVolume(1);
		mp.play();
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		
		vp.setAutoPlay(true);
		vp.setCycleCount(MediaPlayer.INDEFINITE);
	}
	

}
