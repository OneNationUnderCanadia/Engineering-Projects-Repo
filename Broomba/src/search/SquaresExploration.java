/** This is the  class SquaresExploration
 *  Created by iasmh2015
 *  To 
 *  
 *  Created on Feb 25, 2015 at 8:23:26 AM
 */
package search;

import main.Drive;

public class SquaresExploration {
	
	private Drive marvin;
	
	int[][] maze; // The maze
	boolean[][] wasHere;
	boolean[][] correctPath; // The solution to the maze
	int startX, startY; // Starting X and Y values of maze
	int endX, endY;     // Ending X and Y values of maze
	int width;
	int height;
	
	public SquaresExploration(Drive drive, int w, int h) {
		
		marvin = drive;
		
		maze = new int[w][h];
		wasHere = new boolean[w][h];
		correctPath = new boolean[w][h];
		
		width = w;
		height = h;
		
	}
	 
	public void solveMaze() {
	    
	    for (int row = 0; row < maze.length; row++)  
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < maze[row].length; col++){
	            wasHere[row][col] = false;
	            correctPath[row][col] = false;
	        }
	    boolean b = recursiveSolve(startX, startY);
	    // Will leave you with a boolean array (correctPath) 
	    // with the path indicated by true values.
	    // If b is false, there is no solution to the maze
	}
	
	public boolean recursiveSolve(int x, int y) {
	    if (x == endX && y == endY) return true; // If you reached the end
	    if (maze[x][y] == 2 || wasHere[x][y]) return false;  
	    // If you are on a wall or already were here
	    wasHere[x][y] = true;
	    if (x != 0) // Checks if not on left edge
	        if (recursiveSolve(x-1, y)) { // Recalls method one to the left
	            correctPath[x][y] = true; // Sets that path value to true;
	            return true;
	        }
	    if (x != width - 1) // Checks if not on right edge
	        if (recursiveSolve(x+1, y)) { // Recalls method one to the right
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != 0)  // Checks if not on top edge
	        if (recursiveSolve(x, y-1)) { // Recalls method one up
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != height- 1) // Checks if not on bottom edge
	        if (recursiveSolve(x, y+1)) { // Recalls method one down
	            correctPath[x][y] = true;
	            return true;
	        }
	    return false;
	}

}
