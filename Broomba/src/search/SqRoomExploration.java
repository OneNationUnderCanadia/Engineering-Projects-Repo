package search;


import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import search.SquareMapping;


/** This is the class SqRoomExploration<br>
 *  Explores a room<br><br>
 *  
 *  Created on Mar 9, 2015 at 8:20:16 AM
 *  
 *  @author OneNationUnderCanadia
 */
public class SqRoomExploration {
	
	
	/**
	 * DifferentialPilot object that allows this class to manipulate the physical robot
	 */
	private DifferentialPilot marvin;
	
	
	/**
	 * SquareMapping object that gives access to its methods
	 */
	private SquareMapping sqm;
	
	
	/**
	 * RoomMappingA object that gives access to its methods
	 */
	private RoomMappingA mapper;
	
	
	/**
	 * Two-dimentional array that contains the information about the room
	 * 0 means unexplored, 1 means cleaned, 2 means inaccessable (or wall), and 3 means partially open
	 */
	private int[][] room;
	
	
	/**
	 * Two-dimentional array that has whether or not the robot has been to any particular square
	 */
	private boolean[][] wasHere;
	
	
	/**
	 * Three-dimentional array that has information about where any particular square can be accessed from
	 * Within the third dimension of accessable, 0 = right, 1 = left, 2 = top, 3 = bottom
	 */
	private boolean[][][] accessable;
	
	
	/**
	 * Integers for where the robot starts in the room
	 */
	private int startX, startY;
	
	
	/**
	 * Integer for the width of the room
	 */
	private int width;
	
	
	/**
	 * Integer for the height of the room
	 */
	private int height;
	
	
	/** 
	 * Allocates a SqRoomExploration object, and provides the necessary parameters for
	 * this object to run properly<br>
	 * 
	 * @param pilot
	 *          A DifferentialPilot object which will be used to drive the physical robot
	 * @param sq
	 *          A SquareMapping object that is used to have access to several of the methods
	 *          within SquareMapping, including goNinty
	 * @param w
	 *          An integer that represents the width of the maximum potential room
	 *          Measured in units of approximately two feet
	 * @param h
	 *          An integer that represents the length of the maximum potential room
	 *          Measured in units of approximately two feet
	 *          
	 * @author OneNationUnderCanadia
	 */
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
	
	
	/**
	 * Prepares for and begins the recursiveExplore method
	 */
	public void exploreRoom() {
		
		for (int row = 0; row < room.length; row++) {
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < room[row].length; col++) {
	        	
	            wasHere[row][col] = false;
	            
	        }
	        
		}
		
	    recursiveExplore(startX, startY);
		
	}
	
	
	/**
	 * Recursive method, leads the robot around the room as though it were a grid
	 * and cleans each square
	 * 
	 * @param x
	 *          The x-value the robot is in the grid as it starts
	 * @param y
	 *          The y-value the robot is in the grid as it starts
	 *          
	 * @author OneNationUnderCanadia
	 */
	private boolean recursiveExplore(int x, int y) {
		
		double back = 0;
		
		if(room[x][y] == 0) room[x][y] = 1;
		
		if(wasHere[x][y]) return false;
		
		wasHere[x][y] = true;
		
		// clean square, use Joey's code
		sqm.spinSquares(53, 50);
		
		// go left
		sqm.goNinty(1);
		nextBox();
		nextBox();
		
		// Try going left
		if(x != 0 && accessable[x-1][y][0]) {
			
			back = mapper.waitForBumperPress(30000, 60);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(1);
				recursiveExplore(x-1, y);
				sqm.goNinty(-1);
				marvin.travel(back * -1);
				
			}
			else {
				
				accessable[x-1][y][0] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// go up
		nextBox();
		
		// Try going up
		if(y != height - 1 && accessable[x][y+1][3]) {
			
			back = mapper.waitForBumperPress(30000, 10);
			
			if(!mapper.isBumperPressed()) {
				
				recursiveExplore(x, y+1);
				marvin.travel(back * -1);
				
			}
			else {
				
				accessable[x][y+1][3] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// go right
		nextBox();
		nextBox();
		sqm.goNinty(-1);
		
		// Try going right
		if(x != width - 1 && accessable[x+1][y][1]) {
			
			back = mapper.waitForBumperPress(30000, 10);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(-1);
				recursiveExplore(x+1, y);
				sqm.goNinty(1);
				marvin.travel(back * -1);
				
			}
			else {
				
				accessable[x+1][y][1] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		// go down
		sqm.goNinty(1);
		nextBox();
		sqm.goNinty(-1);
		
		// Try going down
		if(y != 0 && accessable[x][y-1][2]) {
			
			back = mapper.waitForBumperPress(30000, 60);
			
			if(!mapper.isBumperPressed()) {
				
				sqm.goNinty(1);
				sqm.goNinty(1);
				recursiveExplore(x, y-1);
				sqm.goNinty(-1);
				sqm.goNinty(-1);
				
			}
			else {
				
				accessable[x][y-1][2] = false;
				marvin.travel(back * -1);
				
			}
			
		}
		
		return false;
		
	}
	
	
	/**
	 * Used as a shortcut within recursiveExplore to get the robot to the next box it needs to clean
	 * 
	 * @author OneNationUnderCanadia
	 */
	private void nextBox() {
		
		sqm.goNinty(1);
		marvin.travel(50);
		
	}

}
