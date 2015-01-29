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
	private TetrixEncoderMotor rMot;
	private TetrixEncoderMotor lMot;
	
	public Drive() {
		
		cf = new TetrixControllerFactory(SensorPort.S1);
		mc = cf.newMotorController();
		
		rMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_2);
		lMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_1);
		
	}
	
	public void set(float power, float turn) {
		
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
	
	private void setForward(float power, float turn) {
		
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
	
	private void setBackward(float power, float turn) {
		
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
	
	private void stop() {
		
		lMot.stop();
		rMot.stop();
		
	}

}
