package main;

import lejos.nxt.LCD;
import lejos.util.Delay;

public class GUI {
	public GUI(){
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
    
	public String step(String whichpart){
		int inbetween = 0;
		if(whichpart.length() <14){
			inbetween = (14 - whichpart.length())/2;
		}else if(whichpart.length() == 14){
			inbetween = 0;
		}else if (whichpart.length() > 14){
			inbetween =0;
		}
		String spaces = "";
		for(int i = 0; i<=inbetween; i++){
			spaces = spaces+" ";
		}
		return spaces + whichpart + spaces.subSequence(1, spaces.length());
	}
}
