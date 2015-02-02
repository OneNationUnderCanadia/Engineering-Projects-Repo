/** Created by OneNationUnderCanadia
 *  On Jan 21, 2015 at 8:52:37 AM
 *  To test the robot
 */
package main;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class TestA {

	public static void main(String[] args) throws InterruptedException {
		
		Drive driveBase = new Drive();
		TouchSensor sensor1 = new TouchSensor(SensorPort.S4);
		
		driveBase.setDrive((float)1.0, (float)0.0);
		System.out.println("Step 1");
		
		while (!sensor1.isPressed()) {
			Thread.sleep(20);
		}
		
		driveBase.setDrive((float)-1.0, (float)0.0);
		Thread.sleep(2000);
		System.out.println("Step 2");
		
		driveBase.setDrive(0,0);
		Button.waitForAnyPress();
	}

}
