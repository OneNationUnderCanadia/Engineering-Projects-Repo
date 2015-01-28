/** Created by iasmh2015
 *  On Jan 21, 2015 at 8:52:37 AM
 *  To 
 */
package main;

import lejos.nxt.*;
import lejos.nxt.addon.tetrix.*;

public class TestA {

	public static void main(String[] args) throws InterruptedException {
		
		TetrixControllerFactory cf = new TetrixControllerFactory(SensorPort.S1);
		TetrixMotorController mc = cf.newMotorController();
		
		TetrixEncoderMotor rMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_2);
		TetrixEncoderMotor lMot = mc.getEncoderMotor(TetrixMotorController.MOTOR_1);
		
		rMot.setPower(100);
		lMot.setPower(100);
		
		rMot.forward();
		lMot.forward();
		Thread.sleep(1000);
		
		lMot.backward();
		Thread.sleep(1000);
		
		rMot.stop();
		lMot.stop();
		
	}

}
