package main;

import search.RoomMappingA;
import search.SqRoomExploration;
import search.SquareMapping;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

/** This is the  class StartUp<br>
 *  Created by OneNationUnderCanadia<br>
 *  Starts the robot<br><br>
 *  
 *  Created on Feb 16, 2015 at 8:48:15 AM
 */
public class StartUp {
	

	public static void main(String[] args) {
		
		/*  Brian do the thing
		 *  Joey do the thing
		 *  Jeff don't do the thing, you'd screw it up, learn to Java
		 */
		
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		
		DifferentialPilot pilot = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		
		LightSensor lighter= new LightSensor(SensorPort.S1, true);
		
		Compass norty = new Compass(lighter, pilot);
		
		GUI gui = new GUI();
		
		gui.setStrobeDelay(10);
		Button.waitForAnyPress();
		gui.setStrobeDelay(100);
		
		gui.execute("Calibration: 1");
		SquareMapping spinner = new SquareMapping(pilot, norty);
		
		gui.execute("Calibration: 2");
		norty.calibrate();
		norty.goNorth();
		
		gui.execute("Exploring");
		SqRoomExploration mapper = new SqRoomExploration(pilot, spinner, 30, 30);
		mapper.exploreRoom();
		
		gui.execute("All Done!");
		
		// PANIC CODE
		//BasicCleaner bc = new BasicCleaner(pilot, rma);
		//bc.cleanRoom(50);
		
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
