package main;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.util.Delay;

public class GUI extends Thread{
	public GUI(){
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
	
	public void execute(String whatcalled){
		LCD.clear();
		System.out.println("XXXXXXXXXXXXXXXX");
		System.out.println("X              X");
		System.out.println("X  EXECUTING:  X");
		System.out.println("X              X");
		System.out.println("X"+step(whatcalled)+"X");
		System.out.println("X              X");
		System.out.println("XXXXXXXXXXXXXXXX");
		Delay.msDelay(500);
	}
	public void valuePrint(int value){
		LCD.clear();
		System.out.println("XXXXXXXXXXXXXXXX");
		System.out.println("X  MAGNET TEST X");
		System.out.println("X     VALUE:   X");
		System.out.println("X              X");
		System.out.println("X      "+value+"     X");
		System.out.println("X              X");
		System.out.println("XXXXXXXXXXXXXXXX");
	}
    
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
	
	public void run(){
		for(int i=0; i<5000; i++){
				MotorPort.A.controlMotor(BasicMotorPort.MAX_POWER, 1);
				Delay.msDelay(100);
				MotorPort.A.controlMotor(3, 3);
				Delay.msDelay(100);
		}
	}
}
