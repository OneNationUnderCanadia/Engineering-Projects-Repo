package search;

import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import search.SquareMapping;

/** This is the class SqRoomExploration
 *  Created by OneNationUnderCanadia
 *  To explore a room
 *  
 *  Created on Mar 9, 2015 at 8:20:16 AM
 */


public class SqRoomExploration {
	
	
	// Global variables because I can
	public DifferentialPilot marvin;
	public SquareMapping sqm;
	public RoomMappingA mapper;
	// within room, 0 means unexplored, 1 means cleaned, 2 means inaccessable (or wall), and 3 means partially open
	public int[][] room;
	public boolean[][] wasHere;
	// Within the third dimension of accessable, 0 = right, 1 = left, 2 = top, 3 = bottom
	public boolean[][][] accessable;
	public int startX, startY;
	public int width;
	public int height;
	
	
	public SqRoomExploration(DifferentialPilot pilot, SquareMapping sq, int w, int h) {
		
		// The drivebase, so the robot can move
		marvin = pilot;
		sqm = sq;
		
		// The width and height of the room
		width = w;
		height = h;
		
		// The room arrays
		room = new int[w][h];
		wasHere = new boolean[w][h];
		accessable = new boolean[w][h][4];
		
		// Sets the robot in the center of the room
		startX = w/2;
		startY = h/2;
		
		mapper = new RoomMappingA(pilot, SensorPort.S3, SensorPort.S4);
		
	}
	
	
	public void exploreRoom() {
		
		for (int row = 0; row < room.length; row++){
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < room[row].length; col++){
	        	
	            wasHere[row][col] = true;
	            
	        }
	        
		}
		
	    recursiveExplore(startX, startY);
		
	}
	
	
	private boolean recursiveExplore(int x, int y) {
		
		double back = 0;
		
		if(room[x][y] == 0) room[x][y] = 1;
		
		if(wasHere[x][y]) return false;
		
		wasHere[x][y] = true;
		
		/** TODO clean square, use Joey's code (once it's done) */
		sqm.spinSquares(53, 50);
		
		// Try going left
		if(x != 0) {
			
			/** TODO go left */
			sqm.goNinty(1);
			nextBox();
			nextBox();
			
			back = mapper.waitForBumperPress(30000, 60);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(1);
				recursiveExplore(x-1, y);
				
			}
			else {
				
				accessable[x-1][y][0] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// Try going up
		if(y != height - 1) {
			
			/** TODO go up */
			nextBox();
			back = mapper.waitForBumperPress(30000, 10);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(1);
				recursiveExplore(x, y+1);
				
			}
			else {
				
				accessable[x][y+1][3] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// Try going right
		if(x != width - 1) {
			
			/** TODO go right */
			nextBox();
			nextBox();
			sqm.goNinty(-1);
			back = mapper.waitForBumperPress(30000, 10);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(-1);
				recursiveExplore(x+1, y);
				
			}
			else {
				
				accessable[x+1][y][1] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// Try going down
		if(y != 0) {
			
			/** TODO go down */
			sqm.goNinty(1);
			nextBox();
			sqm.goNinty(-1);
			back = mapper.waitForBumperPress(30000, 60);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(1);
				sqm.goNinty(1);
				recursiveExplore(x, y-1);
				
			}
			else {
				
				accessable[x][y-1][2] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		return false;
		
	}
	
	
	private void nextBox() {
		
		sqm.goNinty(1);
		marvin.travel(50);
		
	}

}
