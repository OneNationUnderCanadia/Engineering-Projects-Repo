import lejos.nxt.Button;
import lejos.nxt.Motor;


public class helloWorld {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		float max = Motor.A.getMaxSpeed();
		for(int i=1; i<max; i++)
		{
        Motor.A.setSpeed(i);
        Thread.sleep(100);
		}
		for(int i=1; i>2; i--)
		{
        Motor.A.setSpeed(i);
        Thread.sleep(100);
		}
		
		Button.waitForAnyPress();
	}

}
