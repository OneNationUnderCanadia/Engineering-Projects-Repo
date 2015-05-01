package search;

import lejos.robotics.navigation.DifferentialPilot;
import main.Compass;

/**
 * SquareMapping class, contains many methods relating to moving the robot around the room, particularly goNinty and sweepinSquares
 * 
 * @author Joey Spillers, Brian Kolozsvari
 */
public class SquareMapping {

	
	/**
	 * DifferentialPilot object that allows this class to move the robot
	 */
	private DifferentialPilot marvin;
	
	
	/**
	 * Compass object that allows the robot to detect magnetic north and south
	 */
	private Compass compass;
	
	
	/**
	 * Keeps track of the direction the robot is facing<br>
	 * 0 == north, 1 == east, 2 == south, 3 == west
	 */
	private int numTurned = 0;
	
	
	/**
	 * Contains many methods to help the robot navigate and clean the room
	 * 
	 * @param dp
	 *          A DifferentialPilot object that allows this class to move the robot
	 * @param cps
	 *          A Compass object that will allow this class to read the "compass sensor"
	 *@author Joey Spillers
	 */
	public SquareMapping(DifferentialPilot dp, Compass cps) {
		
		// Instantiates global variables
		marvin = dp;
		compass = cps;
		
	}
	

	/**
	 * Turns the robot approximately 90 degrees
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author Joey Spillers
	 */
	public void magicNinty(int i){
		
		// Stops the robot
		marvin.stop();
		
		// Turn the robot right if i > 0
		if (i > 0) marvin.rotate(78);
		
		// Otherwise, turn the robot left
		else marvin.rotate(-78);
		
	}
	
	
	/**
	 * Turns the robot 90 degrees to north
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author OneNationUnderCanadia
	 */
	private void north(int i) {
		
		// Sets high and low angle values
		int high = 120;
		int low = 60;
		
		// Goes right
		if (i > 0)
			findingNorthPositive(turning(low), high, low);
		
		// Goes left
		else 
			findingNorthNegative(turning(-low), -low, -high);
		
	}
	
	
	/**
	 * Turns the robot 90 degrees to south
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 * @author OneNationUnderCanadia
	 */
	private void south(int i) {
		
		// Sets high and low angle values
		int high = 120;
		int low = 60;
		
		// Goes right
		if (i > 0) 
			findingSouthPositive(turning(low), high, low);
		
		// Goes left
		else
			findingSouthNegative(turning(-low), -low, -high);
		
	}
	
	
	/**
	 * Will turn the robot 90 degrees, correcting using the "compass sensor"
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void goNinty(int i) {
		
		// Adjust the value of numTurned
		adjustDirection(i);
		
		// Turn based on what it is
		if(numTurned == 0)
			north(i);
		
		else if(numTurned == 1)
			magicNinty(i);
		
		else if(numTurned == 2)
			south(i);
		
		else if(numTurned == 3)
			magicNinty(i);
		
	}
	
	
	/**
	 * Will adjust numTurned to match the situation
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left 
	 *@author OneNationUnderCanadia
	 */
	private void adjustDirection(int i) {
		
		// Adjusts numTurned up, looping
		if (i > 0){
			
			numTurned = (numTurned + 1) % 4;
			
		}
		// Adjusts numTurned down, looping and correcting if necescary
		else {	
			
			numTurned = (numTurned - 1) % 4;
			if (numTurned < 0) numTurned += 4;
			
		}
		
	}
	
	
	/**
	 * Initiates sweepinSquares with the proper values for our robot
	 * 
	 * @author Joey Spillers
	 */
	public void sweepinSquares(){
		
		// Starts spinSquares with the standard values
		spinSquares(200, 200);
			
	}
	
	
	/**
	 * Cleans a defined area
	 * 
	 * @param xbounds
	 *          The width of the area
	 * @param ybounds
	 *          The length of the area
	 *          
	 *@author Joey Spillers
	 */
	public void spinSquares(int xbounds, int ybounds){
		
		// Starts leftOrRight
		int leftOrRight = -1;
		
		// loops a certain number of times, based on the y-value and the size of the robot
		for(int i = ybounds; i >= 0; i-=20){ // 20 = length of robot
			
			// Alternates leftOrRight
		    leftOrRight *= -1;
		    
		    // Moves the length of the x-value, turns
			marvin.travel(xbounds); 						
			goNinty(leftOrRight);
			
			// Moves to the next row to clean, turns
			marvin.travel(20);
			goNinty(leftOrRight);
			
		}
		
	}
	
	
	/**
	 * Recursively tries to find magnetic north using the Compass class
	 * Rotates in a positive direction
	 * 
	 * @param degreesTurned
	 *          The number of degrees the robot has already turned from zero
	 * @param limitUp
	 *          The upper boundry on where you think north might possibly be
	 * @param limitDown
	 *          The lower boundry on where you think north might possibly be
	 *@author OneNationUnderCanadia
	 */
	public void findingNorthPositive(int degreesTurned, int limitUp, int limitDown) {
		
		// Turns slightly
		degreesTurned += turning(2);
		
		// If the value is close enough to north, stops method
		if (northWithinX(5)) 
			return;
		
		// If degreesTurned is less than limitUp, continues positively
		else if (degreesTurned < limitUp) 
			findingNorthPositive(degreesTurned, limitUp, limitDown);
		
		// If degreesTurned is greater than or equal to limitUp, switches to negative
		else if (degreesTurned >= limitUp)
			findingNorthNegative(degreesTurned, limitUp, limitDown);
		
	}
	
	
	/**
	 * Recursively tries to find magnetic north using the Compass class
	 * Rotates in a negative direction
	 * 
	 * @param degreesTurned
	 *          The number of degrees the robot has already turned from zero
	 * @param limitUp
	 *          The upper boundry on where you think north might possibly be
	 * @param limitDown
	 *          The lower boundry on where you think north might possibly be
	 *@author OneNationUnderCanadia
	 */
	public void findingNorthNegative(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(-2);
		
		if (northWithinX(5)) return;
		
		// If degreesTurned is greater than limitDown, continues negatively
		else if (degreesTurned > limitDown) 
			findingNorthNegative(degreesTurned, limitUp, limitDown);
		
		// If degreesTurned is less than or equal to limitDown, switches to positive
		else if (degreesTurned <= limitDown) 
			findingNorthPositive(degreesTurned, limitUp, limitDown);
		
	}
	
	
	/**
	 * Recursively tries to find magnetic south with the Compass class
	 * Rotates in a positive direction
	 * 
	 * @param degreesTurned
	 *          The number of degrees the robot has already turned from zero
	 * @param limitUp
	 *          The upper boundry on where you think south might possibly be
	 * @param limitDown
	 *          The lower boundry on where you think south might possibly be
	 *@author OneNationUnderCanadia
	 */
	public void findingSouthPositive(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(2);
		
		if (southWithinX(5)) 
			return;
		
		// If degreesTurned is less than limitUp, continues positively
		else if (degreesTurned < limitUp) 
			findingSouthPositive(degreesTurned, limitUp, limitDown);
		
		// If degreesTurned is greater than or equal to limitUp, switches to negative
		else if (degreesTurned >= limitUp)
			findingSouthNegative(degreesTurned, limitUp, limitDown);
		
	}
	
	
	/**
	 * Recursively tries to find magnetic south with the Compass class
	 * Rotates in a negative direction
	 * 
	 * @param degreesTurned
	 *          The number of degrees the robot has already turned from zero
	 * @param limitUp
	 *          The upper boundry on where you think north might possibly be
	 * @param limitDown
	 *          The lower boundry on where you think north might possibly be
	 *@author OneNationUnderCanadia
	 */
	public void findingSouthNegative(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(-2);
		
		if (southWithinX(5))
			return;
		
		// If degreesTurned is greater than limitDown, continues negatively
		else if (degreesTurned > limitDown)
			findingSouthNegative(degreesTurned, limitUp, limitDown);
		
		// If degreesTurned is less than or equal to limitDown, switches to positive
		else if (degreesTurned <= limitDown)
			findingSouthPositive(degreesTurned, limitUp, limitDown);
		
	}
	
	
	/**
	 * Determines if the current reading of the light sensor
	 * is within a given value of the value for north
	 * 
	 * @param limit
	 *          This close to north or closer causes the function to reurn
	 *          positive.  Set lower to avoid false positives, set higher
	 *          to avoid not noticing north.
	 *          
	 *@author OneNationUnderCanadia
	 */
	public boolean northWithinX(int limit) {
		
		return (Math.abs(compass.getValue() - compass.getNorth()) <= limit);
		
	}
	
	
	/**
	 * Determines if the current reading of the light sensor
	 * is within a given value of the value for south
	 * 
	 * @param limit
	 *          This close to south or closer causes the function to reurn
	 *          positive.  Set lower to avoid false positives, set higher
	 *          to avoid not noticing south.
	 * @author OneNationUnderCanadia
	 */
	public boolean southWithinX(int limit) {
		
		return (Math.abs(compass.getValue() - compass.getSouth()) <= limit);
		
	}
	
	
	/**
	 * Turns the robot a certain number of degrees then returns that value
	 * 
	 * @param turn
	 *          The number of degrees you would like the robot to turn
	 *@author OneNationUnderCanadia
	 */
	public int turning(int turn) {
		
		marvin.rotate(turn);
		
		return turn;
		
	}
	
}
