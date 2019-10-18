package application.test;

import org.junit.Before;
import org.junit.Test;

import application.model.Park;
import application.model.Zone;

/**
 * @author Ivy Vasquez Sandoval (egi444)
 * UTSA CS 3443 - Lab 8
 * Spring 2019
 */
public class ParkTest {

	Park testPark;
	
	@Before
	public final void setUp() {
		testPark = new Park("Test");
		testPark.addZone("Test", "Test", "Test");
		testPark.addZone("Test2", "Test2", "Test2");
	}
	
	/**
	 * Test method for {@link Park#addDinosaur(Zone, String, String, boolean)}.
	 */
	@Test
	public final void testAddDinosaur() {
		// Assume there's a Park with one Zone
		
		// Check: Zone in Park has no dinosaurs
		int number = testPark.getParkMap().get(testPark.getZone("Test")).size();
		assert( number==0 ) : "Invalid HashMap object or assumption! Number of Dinosaurs: " + number;
		
		// Good behavior: add a dinosaur
		testPark.addDinosaur( testPark.getZone("Test"), "Test", "Test", true);
		
		// Check: Zone in Park has 1 dinosaur
		number = testPark.getParkMap().get(testPark.getZone("Test")).size();
		assert( number==1 ) : "Dinosaur not added! Number or dinosaurs: " + number;
	}
	
	/**
	 * Test method for {@link Park#getNumberOfDinosaursInZone(Zone)}.
	 * Relies on testAddDinosaur() being successful
	 */
	@Test
	public final void testGetNumberOfDinosaursInZone() {
		// Assume there's a Park with one Zone
		
		// Check: Zone in Park has no dinosaurs
		int number = testPark.getParkMap().get(testPark.getZone("Test")).size();
		assert( number==0 ) : "Invalid HashMap object or assumption! Number of Dinosaurs: " + number;
		
		// Good behavior: check number of dinosaurs using method
		int number2 = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test"));
		
		// Check: Counts are equivalent
		assert( number==number2 ) : "Results not equivalent. Number: " + number 
		+ "!= Number from method: " + number2;
		
		// Good behavior: add a dinosaur to park and refresh counts
		testPark.addDinosaur( testPark.getZone("Test"), "Test", "Test", true);
		number = testPark.getParkMap().get(testPark.getZone("Test")).size();
		number2 = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test"));
		
		// Check: Counts are equivalent
		assert( number==number2 ) : "Results not equivalent. Number: " + number 
		+ "!= Number from method: " + number2;
	}
	
	/**
	 * Test method for {@link Park#relocateDinosaur(String, Zone, Zone)}.
	 * Relies on testAddDinosaur() and testGetNumberOfDinosaursInZone() being successful
	 */
	@Test
	public final void testRelocateDinosaur() {
		// Assume there's a Park with two Zones
		
		// Good behavior: add a dinosaur to first zone
		testPark.addDinosaur( testPark.getZone("Test"), "Test", "Test", true);
		
		// Check: Zone Test has 1 dinosaur
		int number = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test"));
		assert( number==1 ) : "Zone Test does not contain correct pre-test Dinosaurs.  Number: " + number;
		
		// Check: Zone Test2 has 0 dinosaurs
		int number2 = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test2"));
		assert( number2==0 ): "Zone Test2 does not contain correct pre-test Dinosaurs.  Number: " + number2;
		
		// Good Behavior: relocate dinosaur Test from Zone Test to Zone Test2
		testPark.relocateDinosaur("Test", testPark.getZone("Test"), testPark.getZone("Test2"));
		
		// Check: Zone Test has 1 dinosaur
		number = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test"));
		assert( number==0 ) : "Zone Test does not contain correct post-test Dinosaurs.  Number: " + number;
				
		// Check: Zone Test2 has 0 dinosaurs
		number2 = testPark.getNumberOfDinosaursInZone(testPark.getZone("Test2"));
		assert( number2==1 ): "Zone Test2 does not contain correct post-test Dinosaurs.  Number: " + number2;
	}

}
