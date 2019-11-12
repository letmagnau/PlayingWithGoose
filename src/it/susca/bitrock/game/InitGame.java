package it.susca.bitrock.game;

import java.util.Scanner;

public class InitGame {
protected static boolean started = false;
	
	
   public static void Init() {
		
	     Scanner scanner = new Scanner(System.in);
	     System.out.println("Hello, game must be initilized. Please insert the players. \r\n" + "When you finish write 'Start!' ");
	     while (!started) {
	    	 GameFunction.AddPlayers(scanner);  	 
	     }
	    
	}
}
