package search;

import lejos.util.Delay;
import lejos.util.Stopwatch;
import lejos.nxt.ADSensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

/** 
 * This is the class RoomMappingA
 * To have the methods to explore a room
 *  
 * Created on Feb 20, 2015 at 8:40:26 AM
 *  
 * @author OneNationUnderCanadia, Joey Spillers
 */
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
	
	
	/**
	 * Waits for the bumper to be pressed
	 * 
	 * @author OneNationUnderCanadia
	 */
	public void waitForBumperPress() {
		
		// Loops for as long as the bumper is not pressed
		while (!isBumperPressed()) {
			
			// Slight time delay before checking again
			Delay.msDelay(20);
			
		}
		
	}
	
	
	/**
	 * Waits for either the bumper to be pressed OR a certain ammount of time to have passed
	 * 
	 * @param timeOut
	 * 			The time, in milliseconds, that has to pass before the loop times out
	 * 
	 * @author OneNationUnderCanadia
	 */
	public void waitForBumperPress(int timeOut) {
		
		// Resets the timer
		time.reset();
		
		// Loops while the time has not reached timeOut and the bumper has not been hit
		while (!isBumperPressed() && time.elapsed() < timeOut) {
			
			// Slight time delay before checking again
			Delay.msDelay(5);
			
		}
		
	}
	
	
	/**
	 * Waits for the bumper to be pressed, a certain ammount of time to pass, or the robot to travel a certain distance
	 * 
	 * @param timeOut
	 * 			The time, in milliseconds, before the robot times out
	 * @param distance
	 * 			The distance, in whatever units your DifferentialPilot uses, before the robot stops
	 * 
	 * @author OneNationUnderCanadia
	 */
	public double waitForBumperPress(int timeOut, double distance) {
		
		// Resets the timer
		time.reset();
		
		// Starts the robot forward
		marvin.forward();
		
		// Loops while the bumper has not been hit, the robot has not timed out, and the set distance has not been reached
		while(!isBumperPressed() && time.elapsed() < timeOut && marvin.getMovementIncrement() < distance) {
			
			// Slight time delay before checking again
			Delay.msDelay(5);
			
		}
		
		// Stops the robot
		marvin.stop();
		
		// Return the distance the robot has traveled
		return marvin.getMovementIncrement();
		
	}
	
	
	/**
	 * Checks and returns whether or not the bumper is pressed
	 * 
	 * @author OneNationUnderCanadia
	 */
	public boolean isBumperPressed() {
		
		// Returns whether or not the bumper is pressed
		return (touch1.isPressed() || touch2.isPressed());
		
	}
	
	
}
