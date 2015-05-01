package main;

import search.SqRoomExploration;
import search.SquareMapping;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * This is the  class StartUp<br>
 * Starts the robot<br><br>
 * 
 * Created on Feb 16, 2015 at 8:48:15 AM
 * 
 * @author OneNationUnderCanadia, Joey Spillers
 */
public class StartUp {
	
	
	/**
	 * GUI object to control the screen of the NXT brick
	 */
	private static GUI gui;
	
	
	/**
	 * DifferentialPilot object to control the wheels of the robot
	 */
	private static DifferentialPilot pilot;
	
	
	/**
	 * LightSensor object, used with the Compass
	 */
	private static LightSensor lighter;
	
	
	/**
	 * Compass object, to read the homemade compass sensor and orient the robot
	 */
	private static Compass norty;
	
	
	/**
	 * SquareMapping object, simplifies complicated tasks to move the robot around the room
	 */
	private static SquareMapping spinner;
	
	
	/**
	 * Runs the code, making the robot clean a room
	 */
	public static void main(String[] args) {
		
		/* Brian do the thing
		 * Joey do the thing
		 * Jeff don't do the thing, you'd screw it up, learn to Java
		 */
		
		instantiate();
		
		calibrate();
		
		run();
		
	}
	
	
	/**
	 * Instantiates the different global variables
	 */
	private static void instantiate() {
		
		// Instantiates the motors & global variables
		NXTRegulatedMotor motorB = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorC = new NXTRegulatedMotor(MotorPort.C);
		pilot = new DifferentialPilot(8, 31.2, motorB, motorC, true);
		lighter= new LightSensor(SensorPort.S1, true);
		norty = new Compass(lighter, pilot);
		gui = new GUI();
		spinner = new SquareMapping(pilot, norty);
		
		// Sets the strobe and waits
		gui.setStrobeDelay(10);
		Button.waitForAnyPress();
		
	}
	
	
	/**
	 * Runs the calibration code
	 */
	private static void calibrate() {
		
		// Sets the strobe to calibration, calibrates the compass sensor, then turns the robot north
		gui.setStrobeDelay(100);
		gui.execute("Calibration");
		norty.calibrate();
		norty.goNorth();
		
	}
	
	
	/**
	 * Runs the code so the robot will clean the room
	 */
	private static void run() {
		
		// Puts "Exploring" on the screen and cleans the room
		gui.execute("Exploring");
		SqRoomExploration mapper = new SqRoomExploration(pilot, spinner, 30, 30);
		mapper.exploreRoom();
		
		// Puts "All Done!" on the screen and waits for a button to be pressed
		gui.execute("All Done!");
		Button.waitForAnyPress();
		
	}
	
}
