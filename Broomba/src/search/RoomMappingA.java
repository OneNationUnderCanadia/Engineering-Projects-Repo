package search;

import lejos.util.Delay;
import lejos.util.Stopwatch;
import lejos.nxt.ADSensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class RoomMappingA {
	
	// The drive is global
	private DifferentialPilot marvin;
	private TouchSensor touch1;
	private TouchSensor touch2;
	private Stopwatch time;
	
	// Initiating the class
	public RoomMappingA(DifferentialPilot drive, ADSensorPort a, ADSensorPort b) {
		
		marvin = drive;
		touch1 = new TouchSensor(a);
		touch2 = new TouchSensor(b);
		time = new Stopwatch();
		
	}
	
	
	public int mapping(int a, int area) {
		
		// Set the wheels so the left is whatever a is and the right is 30
		marvin.steer(100);
		
		// Start a counter i
		// Keep going till either i reaches a certain value or the bumper is pressed
		int i = 0;
		while (!touch1.isPressed() && !touch2.isPressed() && i < (a+1)*30) {
			Delay.msDelay(40);
			i++;
		}
		
		// Recursion so it runs 30 times
		if (a < area) {
			return mapping(a+1, area);
		}
		else {
			return a;
		}
		
	}
	
	
	public int bouncing(int a) {
		
		// Drive forward until it hits something
		marvin.setTravelSpeed(10);
		marvin.steer(0);
		waitForBumperPress();
		
		// Back up
		marvin.backward();
		Delay.msDelay(200);
		
		// Turn
		marvin.rotateLeft();
		Delay.msDelay(700);
		
		// Thing for recursion
		if (a > 1) {
			return bouncing(a-=1);
		}
		else {
			return 0;
		}
		
	}
	
	public void waitForBumperPress() {
		
		while (!touch1.isPressed() && !touch2.isPressed()) {
			
			Delay.msDelay(20);
			
		}
		
	}
	
	public void waitForBumperPress(int timeOut) {
		
		time.reset();
		int elapsed = 0;
		
		while (!touch1.isPressed() && !touch2.isPressed() && elapsed < timeOut) {
			
			elapsed = time.elapsed();
			
		}
		
	}
	
	public boolean isBumperPressed() {
		
		if(touch1.isPressed() || touch2.isPressed()) return true;
		
		else return false;
		
	}
	
}
