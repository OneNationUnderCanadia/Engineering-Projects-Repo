package search;

import lejos.robotics.navigation.DifferentialPilot;

/** This is the class SqRoomExploration
 *  Created by OneNationUnderCanadia
 *  To explore a room
 *  
 *  Created on Mar 9, 2015 at 8:20:16 AM
 */


public class SqRoomExploration {
	
	// Global variables because I can
	public DifferentialPilot marvin;
	// within room, 0 means unexplored, 1 means cleaned, 2 means inaccessable (or wall), and 3 means partially open
	public int[][] room;
	public boolean[][] wasHere;
	// Within the third dimension of accessable, 0 = right, 1 = left, 2 = top, 3 = bottom
	public boolean[][][] accessable;
	int startX, startY;
	int width;
	int height;
	
	public SqRoomExploration(DifferentialPilot pilot, int w, int h) {
		
		// The drivebase, so the robot can move
		marvin = pilot;
		
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
		
	}
	
	public void exploreRoom() {
		
		for (int row = 0; row < room.length; row++){
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < room[row].length; col++){
	            wasHere[row][col] = false;
	            
	        }
	        
		}
		
	    recursiveExplore(startX, startY);
		
	}
	
	private boolean recursiveExplore(int x, int y) {
		
		if(room[x][y] == 0) room[x][y] = 1;
		
		if(wasHere[x][y]) return false;
		
		wasHere[x][y] = true;
		
		// TODO clean square
		
		if(x != 0) {
			
			// TODO go left
			// TODO if(!hit bumper) do next square;
			// TODO else accessable[x-1][y][0] = false;
			
		}
		if(x != width - 1) {
			
			/// TODO go right
			// TODO if(!hit bumper) do next square;
			// TODO else accessable[x+1][y][1] = false;
			
		}
		
		return false;
		
	}

}
