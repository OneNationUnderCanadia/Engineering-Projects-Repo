package main;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.MagneticSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * Abstraction for Magnets
 * 
 * @author Joey Spillers
 */
public class Magnets { ///[0] Forward, [1] Right, [2] Back, [3] Left
	
    static int[] low = {10000,10000,10000,10000};
    static int[] high = {0,0,0,0};
	static MagneticSensor compass = new MagneticSensor(SensorPort.S2);
	
	
	/** 
	 * Calibrates using the useless Magnet Sensor<br>
	 * 
	 * @param pilot
	 * 			DifferentialPilot used
	 * 
	 * @author Joey Spillers
	 */
	public void calibrate(DifferentialPilot pilot){
		/*I2CSensor magnet = new I2CSensor(SensorPort.S1);
		byte[] buf = null;
		int len=10;
		magnet.getData(0x41, buf, len);
		magnet.*/
		GUI gui = new GUI();
		int[][] data = new int[4][10];

		for(int i = 0; i< 4; i++){
			for(int k = 0; k<10; k++){
				Delay.msDelay(500);
				data[i][k] = compass.readValue();
				gui.valuePrint(data[i][k]);
			}
			pilot.rotate(95.5);
		}
		highAndLow(data);
	}
	
	/** 
	 * Searches Numbs and sets the lowest and highest value in each second-level array<br>
	 * 
	 * @param numbs
	 * 			To be used with the Calibration method only; Contains a ton of values
	 * 
	 * @author Joey Spillers
	 */
	public static void highAndLow(int[][] numbs){
        int count = 0;

        for(int i=0; i< 4; i++){
        	count = 0;
	        while(count < numbs[i].length)
	        {
	            if(numbs[i][count]< low[i]) {
	                low[i] = numbs[i][count];
	            }
	
	            if(numbs[i][count] > high[i]) {
	                high[i] = numbs[i][count];
	            }
	
	            count++;   
	        }
	        
        }
	}
	/** 
	 * returns the North Value of the Magnet Sensor<br>
	 * 
	 * @author Joey Spillers
	 */
	public int[] getHigh(){
		return high;
	}
	
	/** 
	 * returns the South Value of the Magnet Sensor<br>
	 * 
	 * @author Joey Spillers
	 */
	public int[] getLow(){
		return low;
	}
	
	
	/** 
	 * returns the Current Value of the Magnet Sensor<br>
	 * 
	 * @author Joey Spillers
	 */
	public int getValue(){
		return compass.readValue();
	}
}
