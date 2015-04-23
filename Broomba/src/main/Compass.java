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
		int[] data = new int[180];

		for(int i = 0; i< 180; i++){
				Delay.msDelay(500);
				data[i] = light.getNormalizedLightValue();
				System.out.println(data[i]);
				marvin.rotate(2);
		}
		NorthAndSouth(data);
	}
	public void setCalibration(int highs, int lows){
		north = highs;
		south = lows;
	}
	
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
	public int getNorth(){
		return north;
	}
	public int getSouth(){
		return south;
	}
	public int getValue(){
		return light.readNormalizedValue();
	}
}
