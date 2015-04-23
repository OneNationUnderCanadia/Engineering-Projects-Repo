package search;

import lejos.nxt.ADSensorPort;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import main.Compass;

public class SquareMapping {

	// The drive is global
	private DifferentialPilot marvin;
	private RoomMappingA rma;
	private Compass compass;
	
	
	// Init
	public SquareMapping(DifferentialPilot dp, ADSensorPort ts1, ADSensorPort ts2, LightSensor ls, Compass cps) {
		marvin = dp;
		rma = new RoomMappingA(marvin, ts1,ts2);
		compass = cps;
		
	}
	

	
	public void magicNinty(int i){
		marvin.stop();
		if(i>0){
			marvin.rotate(95.5);
		}else{
			marvin.rotate(-95.5);
		}
	}
	
	private int numTurned = 0;
	public void goNinty(int i){ //0=north, 1=east, 2=south, 3west
		if(i>0){
			numTurned = (numTurned + 1) % 4;
		/*	//marvin.rotate(95.5);
			int value = light.readNormalizedValue();
			while(!((compass.getHigh() > value) && (compass.getLow() < value))) {
				marvin.rotate(2);
			}	*/
		}else
		{	
			numTurned = (numTurned - 1) % 4;
			
			if (numTurned < 0) numTurned += 4;
			
			/*
			//marvin.rotate(-95.5);
			int value = light.getNormalizedLightValue();
			while(!((compass.getHigh() > value) && (compass.getLow() < value))) {
				marvin.rotate(2);
			}*/
		}
		if(numTurned == 0){
			
			System.out.println("north");
			Delay.msDelay(1000);
			
			findingNorthPositive(turning(70), 110, 70);
			
		}
		else if(numTurned == 1){
			
			System.out.println("east");
			Delay.msDelay(1000);
			
			marvin.rotate(95.5);
			
		}
		else if(numTurned == 2){
			
			System.out.println("south");
			Delay.msDelay(1000);
			
			findingSouthPositive(turning(70), 110, 70);
			
		}
		else if(numTurned == 3){
			
			System.out.println("west");
			Delay.msDelay(500);
			
			marvin.rotate(95.5);
			
		}
		
	}
	
	
	public void sweepinSquares(){
		//float[] map = findPerimeter();
			spinSquares(200, 200);
	}
	

	public float[] findPerimeter(){ //Finds Perimeter then resets to origin; map = [0]xmax, [1]ymax
		
		float map[] = new float[2];
		
		// Check if corner
		for(int i = 0; i<2; i++){ //find up and left
			marvin.forward();
			rma.waitForBumperPress(); 
			map[i] = marvin.getMovementIncrement();
			goNinty(1); 
			marvin.stop();
		}
		
		marvin.travel(map[0]);
		goNinty(1);
		marvin.travel(map[1]);
		return map;
		
	}
	
	
	public float[] findCenter(float[]  map){ //returns center[]; center[0] = x coord, center[1] = y coord
		float[] center = new float[1];
		
		center[0] = map[0]/2;
		center[1] = map[1]/2;

		return center;
	}
	
	
	public void spinSquares(int xbounds, int ybounds){
		//int area = (int) (map[0] * map[1]); 
		//rma.mapping(0, area);
		int leftOrRight=-1;
		for(int i = ybounds; i >= 0; i-=20){ // 20 = length of robot
		    leftOrRight*=-1;
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
	 */
	public boolean southWithinX(int limit) {
		
		return (Math.abs(compass.getValue() - compass.getSouth()) <= limit);
		
	}
	
	
	/**
	 * Turns the robot a certain number of degrees then returns that value
	 * 
	 * @param turn
	 *          The number of degrees you would like the robot to turn
	 */
	public int turning(int turn) {
		
		marvin.rotate(turn);
		
		return turn;
		
	}
	
}
