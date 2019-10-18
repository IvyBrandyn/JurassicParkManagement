package application.model;

/**
 * The Zone class is a representation of a zone that
 * is used by the Park class.  Each zone has a name,
 * a code, and a threat level.  The zone class contains
 * an Override of the toString() method and has the
 * following accessors and mutators:
 * getZoneName, setZoneName, getZoneCode, setZoneCode,
 * getThreatLevel, setThreatLevel.
 * 
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class Zone {
	
	private String zoneName;
	private String zoneCode;
	private String threatLevel;
	
	// Constructor
	
	/**
	 * Constructs an instance of Zone class
	 * utilizing a zone name, a threat level,
	 * and a zone code.
	 * 
	 * @param zoneName (String)
	 * @param threatLevel (String)
	 * @param zoneCode (String)
	 */
	public Zone(String zoneName, String threatLevel, String zoneCode) {
		this.zoneName = zoneName;
		this.threatLevel = threatLevel;
		this.zoneCode = zoneCode;
	}
	
	// Methods
	
	/**
	 * @return (String) representation of the zone.
	 */
	@Override
	public String toString() {
		return zoneCode + ": " + zoneName + " Zone (" + threatLevel + " risk):\n";
	}

	/**
	 * @return (String) zone's name.
	 */
	public String getZoneName() {
		return zoneName;
	}

	/**
	 * @param zoneName (String) is used to change zone's name.
	 */
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	/**
	 * @return (String) zone's code.
	 */
	public String getZoneCode() {
		return zoneCode;
	}

	/**
	 * @param zoneCode (String) is used to change zone's code.
	 */
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	/**
	 * @return (String) threat level rating
	 */
	public String getThreatLevel() {
		return threatLevel;
	}

	/**
	 * @param threatLevel (String) is used to change zone's threat level.
	 */
	public void setThreatLevel(String threatLevel) {
		this.threatLevel = threatLevel;
	}
}