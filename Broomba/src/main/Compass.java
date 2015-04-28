package main;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Compass {
	LightSensor light;
	GUI gui;
	DifferentialPilot marvin;
	int south;
	int north;
	
	/** 
	 * Creates a Compass Object<br>
	 * 
	 * @param l1
	 *          The LightSensor to be used with the compass.
	 * @param mad
	 * 			The DifferentialPilot to be used;
	 * @author Joey Spillers
	 */
	public Compass(LightSensor l1, DifferentialPilot mad){
		light = l1;
		gui = new GUI();
		marvin = mad;
		
	}
	
	/** 
	 * Makes the robot face north<br>
	 * @author Joey Spillers
	 */
	public void goNorth(){ ////make this!@
			while(light.readNormalizedValue() < north-10){
				marvin.rotate(2);
				Delay.msDelay(20);
			}
	}
	
	/** 
	 * Calibrates the Compass Sensor<br>
	 * @author Joey Spillers
	 */
	public void calibrate(){
		light.setFloodlight(true);
		int[] data = new int[220];

		for(int i = 0; i< 220; i++){
				Delay.msDelay(200);
				data[i] = light.getNormalizedLightValue();
				System.out.println(data[i]);
				marvin.rotate(2);
		}
		NorthAndSouth(data);
	}
	
	/** 
	 * Used to avoid calibration; Can set if values are already known<br>
	 * 
	 * @param highs
	 *          North Value
	 * @param lows
	 * 			South Value
	 * @author Joey Spillers
	 */	
	public void setCalibration(int highs, int lows){
		north = highs;
		south = lows;
	}
	
	/** 
	 * Reads numbs and sets the North and South values of the Compass object<br>
	 * 
	 * @param numbs
	 *          Should be sent from the calibration method only!
	 * @author Joey Spillers
	 */
	public void NorthAndSouth(int[] numbs){
        int count = 0;
        north = 0;
        south = 10000;

        for(int i=0; i< 180; i++){
        	count = 0;
	        while(count < numbs.length)
	        {
	            if(numbs[count]< south) {
	                south = numbs[count];
	            }
	
	            if(numbs[count] > north) {
	                north = numbs[count];      
	            }
	
	            count++;   
	        }
	        
        }
	}
	/** 
	 * returns the North Value of the Light Sensor<br>
	 * @author Joey Spillers
	 */
	public int getNorth(){
		return north;
	}
	
	/** 
	 * returns the South Value of the Light Sensor<br>
	 * @author Joey Spillers
	 */
	public int getSouth(){
		return south;
	}
	
	/** 
	 * returns the Current Value of the Light Sensor<br>
	 * @author Joey Spillers
	 */
	public int getValue(){
		return light.readNormalizedValue();
	}
}
