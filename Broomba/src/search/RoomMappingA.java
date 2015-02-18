package search;

import lejos.util.Delay;
import main.Drive;

public class RoomMappingA {
	
	private Drive marvin;
	
	public RoomMappingA(Drive drive) {
		
		marvin = drive;
		
	}
	
	public int mapping(int a) {
		
		marvin.setWheels(a, 30);
		
		int i = 0;
		while (!marvin.touch1.isPressed() && !marvin.touch2.isPressed() && i < (a+1)*30) {
			Delay.msDelay(40);
			i++;
		}
		
		if (a < 30) {
			return mapping(a+1);
		}
		else {
			return a;
		}
		
	}
	
}
