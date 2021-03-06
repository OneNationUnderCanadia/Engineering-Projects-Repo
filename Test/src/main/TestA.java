package main;

/** Created by OneNationUnderCanadia
 *  On Jan 21, 2015 at 8:52:37 AM
 *  To test the robot
 */

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.util.Delay;

public class TestA {

	public static void main(String[] args) throws InterruptedException {
		
		TouchSensor sensor1 = new TouchSensor(SensorPort.S4);
		TouchSensor sensor2 = new TouchSensor(SensorPort.S3);
		Drive driveBase = new Drive(SensorPort.S1, TetrixMotorController.MOTOR_1, TetrixMotorController.MOTOR_2, sensor1, sensor2);
		
		driveBase.setWheels(100, 100);
		System.out.println("Step 1");
		
		while (!sensor1.isPressed() && !sensor2.isPressed()) {
			Thread.sleep(20);
		}
		
		driveBase.setWheels(-100, -100);
		Delay.msDelay(2000);
		System.out.println("Step 2");
		
		driveBase.setWheels(-100, 100);
		Delay.msDelay(2000);
		
		driveBase.setDrive(0,0);
		Button.waitForAnyPress();
	}

}
