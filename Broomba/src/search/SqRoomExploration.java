package search;

import main.Drive;

/** This is the  class SqRoomExploration
 *  Created by 
 *  To explore a room
 *  
 *  Created on Mar 9, 2015 at 8:20:16 AM
 */


public class SqRoomExploration {
	
	// Global variables because I can
	public Drive marvin;
	public int[][] room;
	public boolean[][] wasHere;
	public boolean[][][] accessable;
	int startX, startY;
	int width;
	int height;
	
	public SqRoomExploration(Drive drive, int w, int h) {
		
		// The drivebase, so the robot can move
		marvin = drive;
		
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
		
		for (int row = 0; row < room.length; row++)  
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < room[row].length; col++){
	            wasHere[row][col] = false;
	            
	        }
	    boolean b = recursiveExplore(startX, startY);
	    // Will leave you with a boolean array (correctPath) 
	    // with the path indicated by true values.
	    // If b is false, there is no solution to the maze
		
	}
	
	public boolean recursiveExplore(int x, int y) {
		
		return false;
		
	}

}
