/** Created by OneNationUnderCanadia
 *  On Jan 28, 2015 at 9:18:01 AM
 *  To make the robot drive with better control
 */
package main;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixEncoderMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;

/**
 * @author iasmh2015
 *
 */
public class Drive {
	
	private TetrixControllerFactory cf;
	private TetrixMotorController mc;
	private static TetrixEncoderMotor rMot;
	private static TetrixEncoderMotor lMot;
	
	public Drive() {
		
		cf = new TetrixControllerFactory(SensorPort.S1);
		mc = cf.newMotorController();
		
		rMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_2);
		lMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_1);
		
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
	
}
