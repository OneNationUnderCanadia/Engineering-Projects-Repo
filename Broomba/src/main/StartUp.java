package main;

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
		
		DifferentialPilot pilot = new DifferentialPilot(8, 31.5, motorB, motorC, true);
		
		//SqRoomExploration mapper = new SqRoomExploration(pilot, 60, 60);
		
		//mapper.exploreRoom();
		SquareMapping spinner = new SquareMapping(pilot, SensorPort.S3, SensorPort.S4);
		spinner.spinSquares(50, 50);
		
	}

}
