/** Created by OneNationUnderCanadia
 *  On Feb 4, 2015 at 9:37:01 AM
 *  To make the robot drive with better control
 */
import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;
import lejos.nxt.SensorPort;




public class MagnetSensor {

	private I2CSensor magnetSensor;
	
	public MagnetSensor(I2CPort port) {
		
		magnetSensor = new I2CSensor(port);
		
	}
	
	public int getSensorValue() {
		
		int value = 0;
		
		
		
		return value;
		
	}

}
