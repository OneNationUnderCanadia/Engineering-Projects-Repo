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
	 * @param args
	 */
	public static void main(String[] args) {
		
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		DifferentialPilot marvin = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		
		RoomMappingA mapper = new RoomMappingA(marvin, SensorPort.S3, SensorPort.S4);
		
		BasicCleaner cleaner = new BasicCleaner(marvin, mapper);
		
		cleaner.cleanRoom(1000);

	}

}
