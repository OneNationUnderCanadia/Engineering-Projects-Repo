package main;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Compass {
	LightSensor light;
	GUI gui;
	DifferentialPilot marvin;
	int high;
	int low;
	
	public Compass(LightSensor l1, DifferentialPilot mad){
		light = l1;
		gui = new GUI();
		marvin = mad;
		
	}
	
	public void goNorth(){
			MotorPort.A.controlMotor(BasicMotorPort.MAX_POWER, 1); // turn light on
			while(light.readNormalizedValue() > low){
				marvin.rotate(2);
				Delay.msDelay(20);
			}
	}
	
	public void calibrate(){
		gui.execute("Put to North & <button> pls");
		Button.waitForAnyPress();
		light.calibrateLow();
		low = light.readNormalizedValue();
		marvin.rotate(25);
		light.calibrateHigh();
		high = light.readNormalizedValue();
	}
}
