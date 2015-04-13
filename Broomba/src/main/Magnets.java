package main;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.MagneticSensor;
import lejos.util.Delay;
import search.SquareMapping;

public class Magnets { ///[0] Forward, [1] Right, [2] Back, [3] Left
	
    static int[] low = {10000,10000,10000,10000};
    static int[] high = {0,0,0,0};
	static MagneticSensor compass = new MagneticSensor(SensorPort.S2);
	
	public void calibrate(SquareMapping spinner){
		/*I2CSensor magnet = new I2CSensor(SensorPort.S1);
		byte[] buf = null;
		int len=10;
		magnet.getData(0x41, buf, len);
		magnet.*/
		int[][] data = new int[4][10];

		for(int i = 0; i< 4; i++){
			for(int k = 0; k<10; k++){
				Delay.msDelay(500);
				data[i][k] = compass.readValue();
				System.out.println(data[i][k]);
			}
			spinner.magicNinty(1);
		}
		highAndLow(data);
	}
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
	public int[] getHigh(){
		return high;
	}
	public int[] getLow(){
		return low;
	}
	public int getValue(){
		return compass.readValue();
	}
}
