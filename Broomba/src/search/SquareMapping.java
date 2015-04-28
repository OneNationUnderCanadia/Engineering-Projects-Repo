package search;

import lejos.robotics.navigation.DifferentialPilot;
import main.Compass;

/**
 * SquareMapping class, contains many methods relating to moving the robot around the room, particularly goNinty and sweepinSquares
 */
public class SquareMapping {

	/**
	 * DifferentialPilot object that allows this class to move the robot
	 * 
	 * @author Joey Spillers, OneNationUnderCanadia
	 */
	private DifferentialPilot marvin;
	
	/**
	 * Compass object that allows the robot to detect magnetic north and south
	 */
	private Compass compass;
	
	/**
	 * Keeps track of the direction the robot is facing<br>
	 * 0 == north, 1 == east, 2 == south, 3 == west
	 * @author Joey Spillers, OneNationUnderCanadia
	 */
	private int numTurned = 0;
	
	
	/**
	 * Contains many methods to help the robot navigate and clean the room
	 * 
	 * @param dp
	 *          A DifferentialPilot object that allows this class to move the robot
	 * @param cps
	 *          A Compass object that will allow this class to read the "compass sensor"
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public SquareMapping(DifferentialPilot dp, Compass cps) {
		
		marvin = dp;
		compass = cps;
		
	}
	

	/**
	 * Turns the robot approximately 90 degrees
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void magicNinty(int i){
		
		marvin.stop();
		
		if (i > 0) marvin.rotate(78);
		
		else marvin.rotate(-78);
		
	}
	
	
	/**
	 * Turns the robot 90 degrees to north
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	private void north(int i) {
		
		int high = 120;
		int low = 60;
		
		if (i > 0)
			findingNorthPositive(turning(low), high, low);
		
		else 
			findingNorthNegative(turning(-low), -low, -high);
		
	}
	
	
	/**
	 * Turns the robot 90 degrees to south
	 * 
	 * @param i
	 *          An integer value, set to 1 or -1<br>
	 *          1 to make the robot turn right or -1 to make it turn left
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	private void south(int i) {
		
		int high = 120;
		int low = 60;
		
		if (i > 0) 
			findingSouthPositive(turning(low), high, low);
		
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
		
		adjustDirection(i);
		
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	private void adjustDirection(int i) {
		
		if (i > 0){
			
			numTurned = (numTurned + 1) % 4;
			
		}
		else {	
			
			numTurned = (numTurned - 1) % 4;
			if (numTurned < 0) numTurned += 4;
			
		}
		
	}
	
	
	/**
	 * Initiates sweepinSquares with the proper values for our robot
	 * 
	 * @author Joey Spillers, OneNationUnderCanadia
	 */
	public void sweepinSquares(){
		
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void spinSquares(int xbounds, int ybounds){
		
		int leftOrRight = -1;
		
		for(int i = ybounds; i >= 0; i-=20){ // 20 = length of robot
			
		    leftOrRight *= -1;
		    
			marvin.travel(xbounds); 						
			goNinty(leftOrRight);
			
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void findingNorthPositive(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(2);
		
		if (northWithinX(5)) 
			return;
			
		else if (degreesTurned < limitUp) 
			findingNorthPositive(degreesTurned, limitUp, limitDown);
			
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void findingNorthNegative(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(-2);
		
		if (northWithinX(5)) return;
		
		else if (degreesTurned > limitDown) 
			findingNorthNegative(degreesTurned, limitUp, limitDown);
		
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void findingSouthPositive(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(2);
		
		if (southWithinX(5)) 
			return;
			
		else if (degreesTurned < limitUp) 
			findingSouthPositive(degreesTurned, limitUp, limitDown);
		
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
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public void findingSouthNegative(int degreesTurned, int limitUp, int limitDown) {
		
		degreesTurned += turning(-2);
		
		if (southWithinX(5))
			return;
		
		else if (degreesTurned > limitDown)
			findingSouthNegative(degreesTurned, limitUp, limitDown);
		
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
	 *@author Joey Spillers, OneNationUnderCanadia
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
	 * @author Joey Spillers, OneNationUnderCanadia
	 */
	public boolean southWithinX(int limit) {
		
		return (Math.abs(compass.getValue() - compass.getSouth()) <= limit);
		
	}
	
	
	/**
	 * Turns the robot a certain number of degrees then returns that value
	 * 
	 * @param turn
	 *          The number of degrees you would like the robot to turn
	 *@author Joey Spillers, OneNationUnderCanadia
	 */
	public int turning(int turn) {
		
		marvin.rotate(turn);
		
		return turn;
		
	}
	
}
