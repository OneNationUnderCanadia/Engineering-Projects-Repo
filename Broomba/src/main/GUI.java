package main;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class GUI extends Thread{
	
	//LightSensor light = new LightSensor(SensorPort.S1, false);
	Sound sd;
	private int delay = 100;
	
	/** 
	 * Allocates a GUI object<br>
	 * 
	 */
	public GUI(){
		Sound.setVolume(50);
		
		this.start();
		LCD.clear();
		System.out.println("XXXXXXXXXXXXXXXX");
		System.out.println("X              X");
		System.out.println("X PRETTY PRINT X");
		System.out.println("X              X");
		System.out.println("X WE CAN DO IT X");
		System.out.println("X^^^^^WOOT!^^^^X");
		System.out.println("XXXXXXXXXXXXXXXX");
		Delay.msDelay(500);
	}
	
	/** 
	 * Allocates a GUI object, and doesn't have a start splash<br>
	 * 
	 * @param s
	 *          Arbitrary; used to differentiate from main constructor
	 */
	public GUI(String s){
		//I don't want it
		this.start();
		LCD.clear();
	}
	
	/** 
	 * Prints a pretty 'executing' screen, with whatcalled below it<br>
	 * 
	 * @param whatcalled
	 *          String to be displayed below executing; should be < 14.
	 */
	public void execute(String whatcalled){
		LCD.clear();
		//Sound.beep();
		System.out.println("XXXXXXXXXXXXXXXX");
		System.out.println("X              X");
		System.out.println("X  EXECUTING:  X");
		System.out.println("X              X");
		System.out.println("X"+step(whatcalled)+"X");
		System.out.println("X              X");
		System.out.println("XXXXXXXXXXXXXXXX");
		Delay.msDelay(500);
	}
	
	/** 
	 * For use in magnet test; prints magnet test value<br>
	 * 
	 * @param value
	 *          the value to be printed as part of calibrating the magnet sensor; 
	 *          should be a 3 digit integer
	 */
	public void valuePrint(int value){
		LCD.clear();
		//Sound.beep();
		System.out.println("XXXXXXXXXXXXXXXX");
		System.out.println("X  MAGNET TEST X");
		System.out.println("X     VALUE:   X");
		System.out.println("X              X");
		System.out.println("X      "+value+"     X");
		System.out.println("X              X");
		System.out.println("XXXXXXXXXXXXXXXX");
	}
	

	/** 
	 * Used to center whatever string within the X's for any GUI; Only used internally<br>
	 * 
	 * @param whichpart
	 *          String to be given padding
	 */ 
	public String step(String whichpart){
		
		int inbetween = 0;
		
		if(whichpart.length() <14){
			inbetween = (14 - whichpart.length())/2;
		}
		else if(whichpart.length() == 14){
			inbetween = 0;
		}
		else if (whichpart.length() > 14){
			inbetween =0;
		}
		
		String spaces = "";
		
		for(int i = 0; i<=inbetween; i++){
			spaces = spaces+" ";
		}
		
		return spaces + whichpart + spaces.subSequence(1, spaces.length());
		
	}
	
	/** 
	 * Sets the interval for which the strobe light flickers<br>
	 * 
	 * @param i
	 *          the interval for which the strobe light flickers; Miliseconds
	 */
	public void setStrobeDelay(int i){
		delay = i;
	}
	
	/** 
	 * Starts the secondary thread; begins the strobe light<br>
	 * 
	 */
	public void run(){
		for(int i=0; i<5000; i++){
			if(true){
				MotorPort.A.controlMotor(BasicMotorPort.MAX_POWER, 1);
				Delay.msDelay(delay);
				MotorPort.A.controlMotor(3, 3);
				Delay.msDelay(100);
			}
		}
	}
}
