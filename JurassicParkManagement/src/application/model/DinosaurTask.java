package application.model;

import java.util.ArrayList;

import javafx.concurrent.Task;

/**
 * The Class DinosaurTask.
 *
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class DinosaurTask extends Task<String>{

	/** The dinos. */
	private ArrayList<Dinosaur> dinos;

	/**
	 * Instantiates a new dinosaur task.
	 *
	 * @param dinos the dinos
	 */
	public DinosaurTask(ArrayList<Dinosaur> dinos) {
		this.dinos = dinos;
	}

	@Override
	protected String call() throws Exception {
		while(true) {
			for(Dinosaur dino : dinos) {
				updateMessage(dino.getDinoName());
				updateValue(dino.getDinoType());
				Thread.sleep(2000);
	    	}
		}
	}

}
