/** Created by OneNationUnderCanadia
 *  On Jan 21, 2015 at 8:52:37 AM
 *  To test the robot
 */
package main;

import lejos.nxt.Button;

public class TestA {

	public static void main(String[] args) throws InterruptedException {
		
		Drive driveBase = new Drive();
		
		driveBase.set((float)1.0, (float)0.0);
		Thread.sleep(2000);
		System.out.println("Step 1");
		
		driveBase.set(0,0);
		Thread.sleep(2000);
		System.out.println("Wait");		
		
		driveBase.set((float)-0.5, (float)0.25);
		Thread.sleep(2000);
		System.out.println("Step 2");
		
		driveBase.set(0,0);
		Button.waitForAnyPress();
	}

}
