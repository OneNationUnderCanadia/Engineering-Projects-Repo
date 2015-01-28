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
	
	public void setForward(int power, int lTurn, int rTurn) {
		
		int lSpeed = power - lTurn;
		int rSpeed = power - rTurn;
		
		lMot.setPower(lSpeed);
		rMot.setPower(rSpeed);
		
		lMot.forward();
		rMot.backward();
		
	}
	
	public void setBackward(int power, int lTurn, int rTurn) {
		
		int lSpeed = power - lTurn;
		int rSpeed = power - rTurn;
		
		lMot.setPower(lSpeed);
		rMot.setPower(rSpeed);
		
		lMot.backward();
		rMot.forward();
		
	}
	
	public void stop() {
		
		lMot.stop();
		rMot.stop();
		
	}

}
