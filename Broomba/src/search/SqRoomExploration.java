package search;

import main.Drive;

/** This is the  class SqRoomExploration
 *  Created by 
 *  To explore a room
 *  
 *  Created on Mar 9, 2015 at 8:20:16 AM
 */


public class SqRoomExploration {
	
	public Drive marvin;
	public int[][] maze;
	public boolean[][] wasHere;
	public boolean[][][] accessable;
	int startX, startY;
	int width;
	int height;
	
	public SqRoomExploration(Drive drive, int w, int h) {
		
		marvin = drive;
		
		width = w;
		height = h;
		
		maze = new int[w][h];
		wasHere = new boolean[w][h];
		accessable = new boolean[w][h][4];
		
		startX = w/2;
		startY = h/2;
		
	}
	
	public void exploreRoom() {
		
		for (int row = 0; row < maze.length; row++)  
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < maze[row].length; col++){
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
