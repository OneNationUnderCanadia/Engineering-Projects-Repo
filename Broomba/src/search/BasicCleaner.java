package search;

/** This is the  class BasicCleaner
 *  Created by OneNationUnderCanadia
 *  To provide a backup to our current room cleaning solution, in case it doesn't work in time
 *  
 *  Created on Apr 20, 2015 at 12:15:08 AM
 */

import lejos.robotics.navigation.DifferentialPilot;



public class BasicCleaner {
	
	private DifferentialPilot marvin;
	private RoomMappingA mapper;
	
	public BasicCleaner(DifferentialPilot pilot, RoomMappingA map) {
		
		marvin = pilot;
		mapper = map;
		
	}
	
	public void cleanRoom(int number) {
		
		for(int i = 0; i < number; i++) {
			
			marvin.forward();
			mapper.waitForBumperPress();
			marvin.travel(-10);
			marvin.stop();
			marvin.rotate((Math.random() * 280) + 40);
			
		}
		
	}

}
