package application.model;

/**
 * The Dinosaur class is a representation of a dinosaur 
 * that is used by the Park class.  Each dinosaur has a 
 * name, a type, and diet type.  The dinosaur class contains
 * an Override of the toString() method and has the
 * following accessors and mutators:
 * getZoneName, setZoneName, getZoneCode, setZoneCode,
 * getThreatLevel, setThreatLevel.
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class Dinosaur {
	
	private String dinoName;
	private String dinoType;
	private boolean vegetarian;
	
	// Constructors
	
	/**
	 * Constructs and instance of Dinosaur using
	 * a name, a type, and diet info.
	 * 
	 * @param dinoName (String) 
	 * @param dinoType (String)
	 * @param vegetarian (boolean)
	 */
	public Dinosaur(String dinoName, String dinoType, boolean vegetarian) {
		this.dinoName = dinoName;
		this.dinoType = dinoType;
		this.vegetarian = vegetarian;
	}
	
	/**
	 * @return (String) representation of the dinosaur.
	 */
	@Override
	public String toString() {
		String carnivoreOrNot = vegetarian ? "not carnivore" : "carnivore";
		return dinoName + " - " + dinoType + " - " + carnivoreOrNot;
	}

	/**
	 * @return (String) dinosaur's name
	 */
	public String getDinoName() {
		return dinoName;
	}

	/**
	 * @param dinoName (String) used to change dinosaur's name.
	 */
	public void setDinoName(String dinoName) {
		this.dinoName = dinoName;
	}

	/**
	 * @return (String) dinosaur's type 
	 */
	public String getDinoType() {
		return dinoType;
	}

	/**
	 * @param dinoType (String) used to change dinosaur's type.
	 */
	public void setDinoType(String dinoType) {
		this.dinoType = dinoType;
	}

	/**
	 * @return (boolean) whether dinosaur is vegetarian or not.
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * @param vegetarian (boolean) is used to change dinosaur's diet.
	 */
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
}
