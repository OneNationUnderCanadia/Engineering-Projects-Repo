/** Created by OneNationUnderCanadia
 *  On Jan 21, 2015 at 8:52:37 AM
 *  To test the robot
 */
package main;

import lejos.nxt.*;
import lejos.nxt.addon.tetrix.*;

public class TestA {

	public static void main(String[] args) throws InterruptedException {
		
		Drive driveBase = new Drive();
		
		driveBase.setForward(100, 0, 0);
		Thread.sleep(2000);
		
		driveBase.setBackward(50, 10, 20);
		Thread.sleep(2000);
		
		driveBase.stop();
		
	}

}
