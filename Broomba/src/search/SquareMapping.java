package search;

import lejos.nxt.ADSensorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class SquareMapping {

	// The drive is global
	private DifferentialPilot marvin;
	private RoomMappingA rma;
	
	
	// Init
	public SquareMapping(DifferentialPilot dp, ADSensorPort ts1, ADSensorPort ts2) {
		marvin = dp;
		rma = new RoomMappingA(marvin, ts1,ts2);

	
		
	}
	
	public void goNinty(){
		marvin.rotate(90);
		/*TimerListener el = null;
		Timer ts = new Timer(0, el);
		ts.start();
		marvin.setWheels(-10, 10);
		while(ts.getDelay() < 525){}
		marvin.setWheels(0, 0);
		*/
	}
	
	public void sweepinSquares(){
		float[] map = findPerimeter();
			findCenter(map);
			spinCircles(map);
		}
	

	public float[] findPerimeter(){ //Finds Perimeter then resets to origin; map = [0]xmax, [1]ymax
		float map[] = new float[2];
		
			// Check if corner
			for(int i = 0; i<2; i++){ //find up and left
				marvin.forward();
				rma.waitForBumperPress(); 
				map[i] = marvin.getMovementIncrement();
				goNinty(); 
				marvin.stop();
			}
			marvin.travel(map[0]);
			goNinty();
			marvin.travel(map[1]);
			return map;
		}
	
	public float[] findCenter(float[]  map){ //returns center[]; center[0] = x coord, center[1] = y coord
		float[] center = new float[1];
		
		center[0] = map[0]/2;
		center[1] = map[1]/2;

		return center;
	}
	
	public void spinCircles(float[] map){
		int area = (int) (map[0] * map[1]); 
		rma.mapping(0, area);
	}
}
