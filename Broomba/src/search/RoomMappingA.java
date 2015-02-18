package search;

import lejos.util.Delay;
import main.Drive;

public class RoomMappingA {
	
	private Drive marvin;
	
	public RoomMappingA(Drive drive) {
		
		marvin = drive;
		
	}
	
	public int mapping(int a) {
		
		marvin.setWheels(a, 50);
		
		int i = 0;
		while (!marvin.touch1.isPressed() && !marvin.touch2.isPressed() && i < (a+1)*60) {
			Delay.msDelay(20);
		}
		
		if (a < 50) {
			return mapping(a+=5);
		}
		else {
			return a;
		}
		
	}
	
}
