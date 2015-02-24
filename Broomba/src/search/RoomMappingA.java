package search;

import lejos.util.Delay;
import main.Drive;

public class RoomMappingA {
	
	// The drive is global
	private Drive marvin;
	
	// Init
	public RoomMappingA(Drive drive) {
		
		marvin = drive;
		
	}
	
	
	public int mapping(int a) {
		
		// Set the wheels so the left is whatever a is and the right is 30
		marvin.setWheels(a, 30);
		
		// Start a counter i
		// Keep going till either i reaches a certain value or the bumper is pressed
		int i = 0;
		while (!marvin.touch1.isPressed() && !marvin.touch2.isPressed() && i < (a+1)*30) {
			Delay.msDelay(40);
			i++;
		}
		
		// Recursion so it runs 30 times
		if (a < 30) {
			return mapping(a+1);
		}
		else {
			return a;
		}
		
	}
	
	
	public int bouncing(int a) {
		
		// Drive forward until it hits something
		marvin.setWheels(20, 20);
		marvin.waitForBumperPress();
		
		// Back up
		marvin.setWheels(-20, -20);
		Delay.msDelay(200);
		
		// Turn
		marvin.setWheels(0, 20);
		Delay.msDelay(700);
		
		// Thing for recursion
		if (a > 1) {
			return bouncing(a-=1);
		}
		else {
			return 0;
		}
		
	}
	
}
