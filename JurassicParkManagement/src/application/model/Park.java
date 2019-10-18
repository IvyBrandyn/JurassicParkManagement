package application.model;

import java.io.*;
import java.util.*;

/**
 * The Park class represents a real world park with a finite
 * number of zones.  Uses Zone and Dinosaur classes. Each
 * Park class has a name and utilizes a HashMap to store
 * zones as keys and ArrayLists of Dinosaurs as a value.
 * Each Park class has the following methods:
 * Override of Object.toString(), loadZones, loadDinosaurs,
 * relocate, save, findZoneName, dinoExists, and zoneExists.
 * 
 * Accessors and Mutators are the following:
 * getParkName, setParkName, getParkMap, setParkMap and getZone.
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class Park {
	private String parkName;
	private HashMap<Zone, ArrayList<Dinosaur>> parkMap;
	
	// Constructor
	
	/**
	 * Constructs an instance of Park using a String
	 * as a parameter for the park name.  Initializes
	 * the HashMap for the park.
	 * 
	 * @param parkName (String)
	 */
	public Park(String parkName) {
		this.parkName = parkName;
		parkMap = new HashMap<>();
	}
	
	// Methods
	
	/**
	 * @param fileName (String) used to specify CSV file to load zones
	 * from to store in a HashMap with the zones representing the keys
	 * and a new ArrayList of dinosaurs as the values stored.
	 * @throws IOException
	 */
	public void loadZones(String fileName) throws IOException {
		String line = "";
		String delimiter = ",";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		while ((line = br.readLine()) != null) {
			String[] zoneInfo = line.split(delimiter);
			addZone(zoneInfo[0], zoneInfo[1], zoneInfo[2]);
		}
		br.close();
	}
	
	/**
	 * @param fileName (String) used to specify CSV file to load
	 * dinosaurs from to store in HashMap cycling through the zones
	 * to find the right ArrayList to store each dinosaur in.
	 * @throws IOException
	 */
	public void loadDinosaurs(String fileName) throws IOException {
		String line = "";
		String delimiter = ",";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		while((line = br.readLine()) != null) {
			String[] dinoInfo = line.split(delimiter);
			Dinosaur newDino = new Dinosaur(dinoInfo[0], dinoInfo[1],
					Boolean.parseBoolean(dinoInfo[2]));
			for(Zone zone : parkMap.keySet()) {
				if(zone.getZoneCode().equals(dinoInfo[3])) {
					parkMap.get(zone).add(newDino);
					break;
				}
			}
		}
		br.close();
	}
	
	/**
	 * Adds zone to park
	 * @param name (String) Name of zone
	 * @param threatLevel (String) Threat of zone
	 * @param code (String) Code of zone
	 */
	public void addZone(String name, String threatLevel, String code) {
		Zone zone = new Zone(name, threatLevel, code);
		if(!parkMap.containsKey(zone)) {
			parkMap.put(zone, new ArrayList<Dinosaur>());
		}else {
			System.out.println("Zone already exists in park.");
		}
	}
	
	/**
	 * Adds Dinosaur to zone
	 * @param zone (Zone) Zone to add Dinosaur to
	 * @param name (String) Name of Dinosaur
	 * @param type (String) Type of Dinosaur
	 * @param vegetarian (boolean) Vegetarian or not
	 */
	public void addDinosaur(Zone zone, String name, String type, boolean vegetarian) {
		Dinosaur newDino = new Dinosaur(name, type, vegetarian);
		parkMap.get(zone).add(newDino);
	}
	
	/**
	 * Relocates Dinosaur from one Zone to another
	 * @param dinoName (String) Name of Dinosaur
	 * @param oldZone (Zone) Zone of Origin
	 * @param newZone (Zone) Zone of Destination
	 */
	public void relocateDinosaur(String dinoName, Zone oldZone, Zone newZone) {
		for(Dinosaur dino : parkMap.get(oldZone)) {
			if(dino.getDinoName().toLowerCase().equals(dinoName.toLowerCase())){
				parkMap.get(newZone).add(dino);
				parkMap.get(oldZone).remove(dino);
				return;
			}
		}
	}
	
	/**
	 * Writes all Park info into the two CSV files
	 * representing the zones and dinosaurs.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/application/data/zones.csv"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/application/data/dinos.csv"));
		for(Zone zone : parkMap.keySet()) {
			bw.write(zone.getZoneName() + "," + zone.getThreatLevel() 
			+ "," + zone.getZoneCode() + "\n");
			for(Dinosaur dino : parkMap.get(zone)) {
				bw2.write(dino.getDinoName() + "," + dino.getDinoType() 
				+ "," + dino.isVegetarian() + "," + zone.getZoneCode() + "\n");
			}
		}
		bw.close();
		bw2.close();
	}
	
	/**
	 * @return String representation of the Park
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Welcome to " + parkName + "!\n" 
		+ "-------------------------\n");
		for(Zone zone : parkMap.keySet()) {
			sb.append(zone.toString());
			for(Dinosaur dino : parkMap.get(zone)) {
				sb.append(dino.toString());
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * @param zoneCode (String) that is used to compare against
	 * all zones in HashTable.
	 * @return Returns zone that has the same zoneCode as the
	 * passed parameter. Can return null if not found.
	 */
	public String findZoneName(String zoneCode) {
		for(Zone zone : parkMap.keySet()) {
			if(zone.getZoneCode().equals(zoneCode)) {
				return zone.getZoneName();
			}
		}
		return null;
	}
	
	/**
	 * @param name (String) used to compare against all dinosaurs
	 * contained in ArrayList.
	 * @param zone (Zone) used to localize search to one zone.
	 * @return (boolean) depending on whether dinosaur was found
	 * in specified Zone.
	 */
	public boolean dinoExists(String name, Zone zone) {
		for(Dinosaur dino : parkMap.get(zone)) {
			if(dino.getDinoName().toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param zoneCode (String) used to compare against all zones'
	 * zoneCodes in HashMap
	 * @return (boolean) depending on whether Zone was found.
	 */
	public boolean zoneExists(String zoneCode) {
		for(Zone zone : parkMap.keySet()) {
			if(zone.getZoneCode().equals(zoneCode)) {
				return true;
			}
		}
		return false;
	}
	
	// Accessors and Mutators
	
	/**
	 * @param zone (Zone) used to specify zone to count
	 * @return (int) number of dinosaurs in zone
	 */
	public int getNumberOfDinosaursInZone(Zone zone) {
		return parkMap.get(zone).size();
	}
	
	/**
	 * @return (String) name of park.
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * @param parkName (String) used to change park name.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * @return (HashMap) HashMap representing the park.
	 */
	public HashMap<Zone, ArrayList<Dinosaur>> getParkMap() {
		return parkMap;
	}
	
	/**
	 * @param parkMap (HashMap) used to replace entire park representation.
	 */
	public void setParkMap(HashMap<Zone, ArrayList<Dinosaur>> parkMap) {
		this.parkMap = parkMap;
	}
	
	/**
	 * @param zoneCode (String) used to compare against all zones' zone codes.
	 * @return (Zone) that has the same type of zone code. Can return null
	 * if not found.
	 */
	public Zone getZone(String zoneCode) {
		for(Zone zone : parkMap.keySet()) {
			if(zone.getZoneCode().equals(zoneCode)) {
				return zone;
			}
		}
		return null;
	}
	
}
