package main;

import search.RoomMappingA;
import search.SqRoomExploration;
import search.SquareMapping;
import lejos.nxt.BasicMotorPort;
import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/** This is the  class StartUp
 *  Created by OneNationUnderCanadia
 *  To start the robot
 *  
 *  Created on Feb 16, 2015 at 8:48:15 AM
 */

public class StartUp {
	
	// private static TetrixControllerFactory cf;
	// private static TetrixMotorController mc;

	public static void main(String[] args) {
		
		/** TODO headlights */
		
		/**  Brian do the thing
		 *  Joey do the thing
		 *  Jeff don't do the thing, you'd screw it up, learn to Java
		 */
		
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		
		DifferentialPilot pilot = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		Magnets magnet = new Magnets();
		SquareMapping spinner = new SquareMapping(pilot, SensorPort.S3, SensorPort.S4, magnet);

		
		SqRoomExploration mapper = new SqRoomExploration(pilot, spinner, 30, 30);
		
		GUI gui = new GUI();
		Button.waitForAnyPress();
		
		gui.execute("Calibration");
		magnet.calibrate(spinner);
		
		gui.execute("Exploring");
		mapper.exploreRoom();
		
		gui.execute("All Done!");
	}
	
	
	public void angleTesting(int Range, DifferentialPilot pilot){
		RoomMappingA rma = new RoomMappingA(pilot, SensorPort.S3, SensorPort.S4);
		for(int angel = Range; Range<200; Range+= 5){
			pilot.rotate(angel);
			System.out.println("Rotation Degree: " + angel);
			rma.waitForBumperPress();
		}
	}	
}
