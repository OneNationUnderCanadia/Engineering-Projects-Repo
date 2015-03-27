package search;

import lejos.nxt.ADSensorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Stopwatch;

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
		int[] map = findPerimeter();
			findCenter(map);
			spinCircles(map);
		}
	

	public int[] findPerimeter(){ //Finds Perimeter then resets to origin; map = [0]xmax, [1]ymax
		Stopwatch sw = new Stopwatch();
		int map[] = new int[2];
		
			// Check if corner
			for(int i = 0; i<2; i++){ //find up and left
				sw.reset();
				marvin.forward();
				rma.waitForBumperPress(); 
				map[i] = sw.elapsed();
				goNinty(); 
			}
			marvin.travel(map[0]);
			goNinty();
			marvin.travel(map[1]);
			return map;
		}
	
	public int[] findCenter(int[]  map){ //returns center[]; center[0] = x coord, center[1] = y coord
		int[] center = new int[1];
		
		center[0] = map[0]/2;
		center[1] = map[1]/2;

		return center;
	}
	
	public void spinCircles(int[] map){
		int area = map[0] * map[1]; 
		rma.mapping(0, area);
	}
}
