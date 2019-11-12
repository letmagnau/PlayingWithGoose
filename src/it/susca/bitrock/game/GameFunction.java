package it.susca.bitrock.game;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class GameFunction {
	private static final String start = "Start!";
	private static int counter =1;
    public static final int winningValue = 63;
    private static final List<Integer> gooseScore =  Arrays.asList( new Integer[] {5,9,14,18,23,27,32,36,41,45,50,54,59}) ;
    private static final List<Integer> bloks =  Arrays.asList( new Integer[] {6,19,31,42,52,58}) ;
    
	protected static void AddPlayers(Scanner scanner) {
		 String name = scanner.nextLine();
		 if(!name.equals(start)) {
			 if(Main.players.stream().filter(t->t.name.equals(name)).findAny().isPresent()) {
				   System.out.println("Player called: "+name+ " is already in yet!"); 
			 }else {
			 Player p = new Player(name, counter++);
		     System.out.println("Player"+p.getPosition()+":  "+p.getName()  + "  Ready!" );	 
		     Main.players.add(p);
		     }
		 }else {
		   InitGame.started= true;
		 }
		
	}
	public static boolean game(Player p, boolean firstTurn) {
		System.out.println(p.name+" is throwing the dice");
		throwTheDice(p,firstTurn);
		landingScore(p,false);
		if (p.score==winningValue) {
			return true;
		}
		return false;
	}
	
	private static void landingScore(Player p,boolean bounced ) {
		
		if(p.score>winningValue) {
			
		    int bounce = p.score - winningValue;
			p.score = winningValue - bounce;
			System.out.println(p.name+" bounced of " + bounce);
			
			landingScore(p,true);
			return;
		}
		Iterator<Player> iter =Main.players.stream().filter(t -> t.score==p.score && !t.name.equals(p.name)).iterator();
	  
	
		if (gooseScore.contains(p.score)) {
			if(bounced) {
			    System.out.println(p.name+" from "+ p.previuscore + " lands on  "+ p.score );
			    p.score = p.score - (p.last);
			    System.out.println(p.name+" find goose, redo the landing from back");	
			}else {
			    System.out.println(p.name +" from "+ p.previuscore + " lands on  "+ p.score);
				p.score = p.score + p.last;
				System.out.println(p.name+" find goose, redo the landing");
			}
			
			landingScore(p,bounced);
			return;
		}
		
		if (bloks.contains(p.score)) {
			
			System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " There's a rule Boy!  " );
			switch (p.score) {
			case 6:	p.score = 12; System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " Use the Bridge and go To 12!  " );	break;
			case 19:p.block = 1;  System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " The Hotel : UnLucky boy! Miss a turn. " );	break;
			case 31:p.block = -1;  System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " The Well : UnLucky boy! Wait until someone comes to pull you out " );
					while(iter.hasNext()) {
						Player i = (Player) iter.next();
						i.setBlock(0); 
						System.out.println(i.name+" now is free");
					} 
			        break;
			case 42:p.score = 39;  System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " The Maze : Go back to space 39. " );	break;
			case 52:p.block = -1;  System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " The Prison : UnLucky boy! Wait until someone comes to release you  " );	break;
			case 58:p.score = 0;  System.out.println("Player"+p.getPosition()+":  "+p.getName()  + " The Death : UnLucky boy! Restart from beginning. " );
					while(iter.hasNext()) {
						Player i = (Player) iter.next();
						i.setBlock(0); 
						System.out.println(i.name+" now is free");
					} 
			        break;
			default:
				break;
			} 
			
			
		}
			 
		
		  if(iter.hasNext()) {
			    System.out.println(p.name+" from "+ p.previuscore + " lands on  "+ p.score);
				System.out.println(p.name+" find some other players on landing , switching position");
				
				while(iter.hasNext()) {
					Player i = (Player) iter.next();
					i.score = p.previuscore;
					System.out.println(i.name+" go to "+ i.score);
				} 
				
			}else {
				if (p.score<=0) {
					p.score = 0;
					System.out.println(p.name+" from "+ p.previuscore + " lands  on 0 ");
				}else {
					System.out.println(p.name+" from "+ p.previuscore + " lands  on  "+ p.score);
				}
				
			}
		
	}
	
	private static void throwTheDice(Player p, boolean firstTurn) {
		 PairOfDice dice = new PairOfDice();
		 dice.launch();
		 System.out.println(p.name+" rolls " + dice.valueDie1 + " and " + dice.valueDie2 + " and get " + dice.getSum());
		 if (firstTurn) {		
			 switch (dice.getSum()) {
			 case 9 : if((dice.valueDie1==5 && dice.valueDie2==4)||(dice.valueDie1==4 && dice.valueDie2==5)) {
				        System.out.println("Player"+p.getPosition()+":  "+p.getName()  + "  Lucky Boy!  9 with 5 and 4!" );	 
				        p.previuscore = p.score;
				        p.score = 53;
				        p.last = dice.sumOfValue;
			           } else if((dice.valueDie1==6 && dice.valueDie2==3)||(dice.valueDie1==3 && dice.valueDie2==6)) {
			        	System.out.println("Player"+p.getPosition()+":  "+p.getName()  + "  Lucky Boy!  9 with 3 and 6!" );	 
					    p.previuscore = p.score;
				        p.score = 26;
				        p.last = dice.getSum();
			           } else {
			        	   p.previuscore=p.score;
			        	   p.last = dice.getSum();
			        	   p.score = dice.getSum(); }
	          		  break;
  
			 default:
				 p.previuscore = p.score;
				 p.last = dice.getSum();
				 p.score = dice.getSum(); 
			 } 
			 
		 }else {
			 p.previuscore = p.score;
			 p.last = dice.getSum();
			 p.score = p.score + dice.getSum(); 
		 }
		
		 
		
	}
	
	
	
    static class PairOfDice 
	{
	 
	   private int valueDie1;   
	   private int valueDie2;  
	   private int sumOfValue;   
	    
	   public PairOfDice() 
	   {
	        launch();  
	   }
	    
	      
	   public void launch() 
	   {
		   valueDie1 = (int)(Math.random()*6) + 1;
		   valueDie2 = (int)(Math.random()*6) + 1;  
	   }
	    
	   public int getValue1() 
	   {
	        return valueDie1;
	   }
	         
	   public int getValue2() 
	   {
	        return valueDie2;
	   }
	         
	   public int getSum() 
	   {
		   sumOfValue = valueDie1 + valueDie2;
	        return sumOfValue;
	   }
	         
	}  

}
