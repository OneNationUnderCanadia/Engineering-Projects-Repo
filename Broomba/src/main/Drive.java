package main;

import lejos.nxt.I2CPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixEncoderMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.util.Delay;


/**
 * Makes the robot drive with better control<br>
 * Depreciated, please do not use this class, it is outdated and useless<br><br>
 * 
 * Created on Jan 28, 2015 at 9:18:01 AM
 * 
 * @author OneNationUnderCanadia
 * 
 * @deprecated
 */
public class Drive {
	
	
	private TetrixControllerFactory cf;
	private TetrixMotorController mc;
	private static TetrixEncoderMotor rMot;
	private static TetrixEncoderMotor lMot;
	public TouchSensor touch1;
	public TouchSensor touch2;
	
	
	public Drive(I2CPort controlerPort, int left, int right, TouchSensor touchA, TouchSensor touchB) {
		
		cf = new TetrixControllerFactory(controlerPort);
		mc = cf.newMotorController();
		
		rMot = mc.getEncoderMotor(right);
		lMot = mc.getEncoderMotor(left);
		
		touch1 = touchA;
		touch2 = touchB;
		
	}
	
	
	public void setDrive(float power, float turn) {
		
		stop();
		
		if (power == 0.0) {
			stop();
		}
		else if (power > 0.0) {
			setForward(Math.abs(power), (turn + 1) / 2);
		}
		else {
			setBackward(Math.abs(power), (turn + 1) / 2);
		}
		
	}
	
	
	private static void setForward(float power, float turn) {
		
		float lTurn = turn + 1;
		float rTurn = turn * -1 + 1;
		
		int lSpeed = (int) (power * lTurn * 50);
		if (lSpeed > 100) {
			lSpeed = 100;
		} else if (lSpeed < 0) {
			lSpeed = 0;
		}
		int rSpeed = (int) (power * rTurn * 50);
		if (rSpeed > 100) {
			rSpeed = 100;
		} else if (rSpeed < 0) {
			rSpeed = 0;
		}
		
		lMot.setPower(lSpeed);
		rMot.setPower(rSpeed);
		
		lMot.backward();
		rMot.forward();
		
	}
	
	
	private static void setBackward(float power, float turn) {
		
		float lTurn = turn + 1;
		float rTurn = turn * -1 + 1;
		
		int lSpeed = (int) (power * lTurn * 50);
		if (lSpeed > 100) {
			lSpeed = 100;
		} else if (lSpeed < 0) {
			lSpeed = 0;
		}
		int rSpeed = (int) (power * rTurn * 50);
		if (rSpeed > 100) {
			rSpeed = 100;
		} else if (rSpeed < 0) {
			rSpeed = 0;
		}
		
		lMot.setPower(lSpeed);
		rMot.setPower(rSpeed);
		
		lMot.forward();
		rMot.backward();
		
	}
	
	
	private static void stop() {
		
		lMot.stop();
		rMot.stop();
		
	}
	
	
	public void setWheels(int left, int right) {
		
		stop();
		
		setLeftWheel (left);
		setRightWheel (right);
		
	}
	
	
	public void setLeftWheel (int left) {
		
		lMot.stop();
		
		lMot.setPower(Math.abs(left));
		
		if (left == 0) {
			lMot.stop();
		}
		else if (left > 0) {
			lMot.backward();
		}
		else if (left < 0) {
			lMot.forward();
		}
		
	}
	
	
	public void setRightWheel (int right) {
		
		rMot.stop();
		
		rMot.setPower(Math.abs(right));
		
		if (right == 0) {
			rMot.stop();
		}
		else if (right > 0) {
			rMot.forward();
		}
		else if (right < 0) {
			rMot.backward();
		}
		
	}
	
	
	public void waitForBumperPress() {
		
		while (!touch1.isPressed() && !touch2.isPressed()) {
			Delay.msDelay(20);
		}
		
	}
	
}
