package search;


import lejos.robotics.navigation.DifferentialPilot;


/** This is the  class BasicCleaner
 *  To provide a backup to our current room cleaning solution, in case it doesn't work in time
 *  
 *  Created on Apr 20, 2015 at 12:15:08 AM
 *  
 *  @author OneNationUnderCanadia
 */
public class BasicCleaner {
	
	
	/**
	 * DifferentialPilot object that allows this class to manipulate the robot
	 */
	private DifferentialPilot marvin;
	
	
	/**
	 * RoomMappingA object that gives this class access to its methods
	 */
	private RoomMappingA mapper;
	
	
	/**
	 * Initiates the BasicCleaner class
	 * 
	 * @param pilot
	 * 			DifferentialPilot object that allows this class to manipulate the robot
	 * @param map
	 * 			RoomMappingA object that gives this class access access to its methods
	 * 
	 * @author OneNationUnderCanadia
	 */
	public BasicCleaner(DifferentialPilot pilot, RoomMappingA map) {
		
		marvin = pilot;
		mapper = map;
		
	}
	
	
	/**
	 * Executes the actual useful code for BasicCleaner, which is the physical equivalent of BogoSort
	 * 
	 * @param number
	 * 			The number of times the code will repeat itself
	 * 
	 * @author OneNationUnderCanadia
	 */
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
