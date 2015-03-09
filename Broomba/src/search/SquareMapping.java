package search;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.util.Delay;
import lejos.util.Timer;
import lejos.util.TimerListener;
import main.Drive;

public class SquareMapping {

	// The drive is global
	private Drive marvin;
	private int map[] = new int[2]; // 0 = x-coord biggest, 1 = y-coord biggest
	
	// Init
	public SquareMapping(Drive drive) {
		
		marvin = drive;
		
	}
	
	public void goNinty(){
		TimerListener el = null;
		Timer ts = new Timer(0, el);
		ts.start();
		marvin.setWheels(-10, 10);
		while(ts.getDelay() < 525){}
		marvin.setWheels(0, 0);
	}
	
	public void sweepinSquares(){
			findCenter(findPerimeter());
			
		}
	

	public int[] findPerimeter(){ //Finds Perimeter then resets to origin; map = [0]xmax, [1]ymax
		Timer checker = new Timer(0, null);
		Timer time = new Timer(0, null);

		
		
			// Check if corner
			for(int i = 0; i<2; i++){ //find up and left
				checker.setDelay(0);
				marvin.setWheels(10, 10);
				checker.start();
				marvin.waitForBumperPress();
				checker.stop();
				map[i] = checker.getDelay();
			}
			for(int i = 0; i<2; i++){
				while(map[i] > time.getDelay()){
					goNinty(); 
				}
			}
			return map;
		}
	
	public int[] findCenter(int[] map){ //returns center[]; center[0] = x coord, center[1] = y coord
		int[] center = new int[1];
		
		center[0] = map[0]/2;
		center[1] = map[1]/2;

		return center;
	}
	
	public void spinCircles(){
		RoomMappingA rma = new RoomMappingA(marvin);
		int area = map[0] * map[1]; 
		rma.mapping(0, area);
	}
}
