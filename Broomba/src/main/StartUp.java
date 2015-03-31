package main;

import search.RoomMappingA;
import search.SqRoomExploration;
import search.SquareMapping;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
// import lejos.nxt.SensorPort;
// import lejos.nxt.addon.tetrix.TetrixMotorController;
// import lejos.nxt.addon.tetrix.TetrixRegulatedMotor;
// import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.robotics.navigation.DifferentialPilot;

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
		
		/** TODO headlights
		 *  Brian do the thing
		 *  Joey do the thing
		 *  Jeff don't do the thing, you'd screw it up, learn to Java
		 */
		
		// cf = new TetrixControllerFactory(SensorPort.S1);
		// mc = cf.newMotorController();
		
		// TetrixRegulatedMotor motor1 = new TetrixRegulatedMotor(mc, TetrixMotorController.MOTOR_1);
		// TetrixRegulatedMotor motor2 = new TetrixRegulatedMotor(mc, TetrixMotorController.MOTOR_2);
		
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		
		DifferentialPilot pilot = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		MotorPort.A.controlMotor(MotorPort.A.MAX_POWER,1);
		//mapper.exploreRoom();
		SquareMapping spinner = new SquareMapping(pilot, SensorPort.S3, SensorPort.S4);
		spinner.spinSquares(53, 50);
		
		SqRoomExploration mapper = new SqRoomExploration(pilot, spinner, 60, 60);
		
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
