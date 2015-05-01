package main;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import search.BasicCleaner;
import search.RoomMappingA;

/** 
 *  This is the  class StartUpE<br>
 *  It runs the emergency fallback code for the robot<br><br>
 *  
 *  Created on Apr 27, 2015 at 10:12:12 PM
 *  
 *  @author OneNationUnderCanadia
 */
public class StartUpE {

	/**
	 * Starts the emergency fallback code
	 * 
	 * @param args
	 * 			Arguments, in case you want to start this from a text file
	 * 
	 * @author OneNationUnderCanadia
	 */
	public static void main(String[] args) {
		
		// Creates motors and pilot, so the robot can move
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		DifferentialPilot marvin = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		
		// Creates mapper, giving access to its methods and allowing the creation of a BasicCleaner
		RoomMappingA mapper = new RoomMappingA(marvin, SensorPort.S3, SensorPort.S4);
		
		// Creates cleaner, allowing use of the BasicCleaner methods
		BasicCleaner cleaner = new BasicCleaner(marvin, mapper);
		
		// Cleans the room
		cleaner.cleanRoom(1000);

	}

}
