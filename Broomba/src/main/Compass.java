package main;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Compass {
	LightSensor light;
	GUI gui;
	DifferentialPilot marvin;
	static int high;
	static int low;
	int south;
	int north;
	
	public Compass(LightSensor l1, DifferentialPilot mad){
		light = l1;
		gui = new GUI();
		marvin = mad;
		
	}
	
	public void goNorth(){
			MotorPort.A.controlMotor(BasicMotorPort.MAX_POWER, 1); // turn light on
			while(light.readNormalizedValue() > north){
				marvin.rotate(2);
				Delay.msDelay(20);
			}
	}
	
	public void calibrate(){
		light.setFloodlight(true);
		/*
		gui.execute("Put to North & <button> pls");
		Button.waitForAnyPress();
		light.calibrateLow();
		low = light.readNormalizedValue();
		marvin.rotate(45);
		light.calibrateHigh();
		gui.execute("Found high");
		high = light.readNormalizedValue();
		marvin.rotate(45);
		south = light.readNormalizedValue();
		gui.execute("Found south");*/
		byte[] buf = null;
		int len = 10;
		GUI gui = new GUI();
		int[] data = new int[180];

		for(int i = 0; i< 180; i++){
				Delay.msDelay(500);
				data[i] = light.getNormalizedLightValue();
				System.out.println(data[i]);
				marvin.rotate(2);
		}
		NorthAndSouth(data);
	}
	public static void NorthAndSouth(int[] numbs){
        int count = 0;

        for(int i=0; i< 180; i++){
        	count = 0;
	        while(count < numbs.length)
	        {
	            if(numbs[count]< low) {
	                low = numbs[count];
	            }
	
	            if(numbs[count] > high) {
	                high = numbs[count];      
	            }
	
	            count++;   
	        }
	        
        }
	}
	public int getHigh(){
		return high;
	}
	public int getLow(){
		return low;
	}
	public int getValue(){
		return light.readNormalizedValue();
	}
}
