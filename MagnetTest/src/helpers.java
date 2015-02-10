import lejos.nxt.Button;
import lejos.nxt.I2CSensor;
import lejos.nxt.SensorPort;


public class helpers {

	/**
	 * @param args
	 */
		
		public void dataTest(){
		byte[] byteme = null;
		I2CSensor iii = new I2CSensor(SensorPort.S2);
			iii.sendData(0, (byte) 50);
			
			int data = iii.getData(iii.getAddress(), byteme, 100);
			System.out.println("ProductID: " + iii.getProductID()+ "\n Vendor: "+ iii.getVendorID() +"\n Version: " +iii.getVersion() +" \n Address: " + iii.getAddress() + "\n port: " +iii.getPort());
			System.out.println("DATA: " + data);
	    Button.waitForAnyPress();
		}
		
		public void exp1(){
			
		}


}
