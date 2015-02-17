package main;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.tetrix.TetrixMotorController;

/** This is the  class
 *  Created by iasmh2015
 *  To 
 *  
 *  Created on Feb 16, 2015 at 8:48:15 AM
 */

public class StartUp {

	public static void main(String[] args) {
		
		TouchSensor sensor1 = new TouchSensor(SensorPort.S4);
		TouchSensor sensor2 = new TouchSensor(SensorPort.S3);
		Drive marvin = new Drive(SensorPort.S1, TetrixMotorController.MOTOR_1, TetrixMotorController.MOTOR_2, sensor1, sensor2);
		
		
		
		
	}

}
