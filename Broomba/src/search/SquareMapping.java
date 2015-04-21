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
	private LightSensor light;
	private Compass compass;
	
	
	// Init
	public SquareMapping(DifferentialPilot dp, ADSensorPort ts1, ADSensorPort ts2, LightSensor ls, Compass cps) {
		marvin = dp;
		rma = new RoomMappingA(marvin, ts1,ts2);
		light = ls;
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
	
	int numTurned = 0;
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
			if (numTurned < 0) {
				numTurned += 4;
			}
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
			while(compass.getHigh() > light.readNormalizedValue()){
				marvin.rotate(2);
			}
		}
		if(numTurned == 1){
			System.out.println("east");
			Delay.msDelay(1000);
			marvin.rotate(95.5);
		}else
		if(numTurned == 2){
			System.out.println("south");
			Delay.msDelay(1000);
			while(compass.getLow() < light.readNormalizedValue()){
				marvin.rotate(2);
			}
			
		}else
		if(numTurned == 3){
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
}
