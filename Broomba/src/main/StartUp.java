package main;

import search.SqRoomExploration;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.nxt.addon.tetrix.TetrixRegulatedMotor;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.robotics.navigation.DifferentialPilot;

/** This is the  class StartUp
 *  Created by OneNationUnderCanadia
 *  To start the robot
 *  
 *  Created on Feb 16, 2015 at 8:48:15 AM
 */

public class StartUp {
	
	private static TetrixControllerFactory cf;
	private static TetrixMotorController mc;

	public static void main(String[] args) {
		
		// TODO headlights
		
		cf = new TetrixControllerFactory(SensorPort.S1);
		mc = cf.newMotorController();
		
		TetrixRegulatedMotor motor1 = new TetrixRegulatedMotor(mc, TetrixMotorController.MOTOR_1);
		TetrixRegulatedMotor motor2 = new TetrixRegulatedMotor(mc, TetrixMotorController.MOTOR_2);
		DifferentialPilot pilot = new DifferentialPilot(7.5, 31.9, motor1, motor2);
		
		SqRoomExploration mapper = new SqRoomExploration(pilot, 60, 60);
		
		mapper.exploreRoom();
		
	}

}
