package yahtzee;

import java.util.Random;

/*******************
 * Yahtzee game for CS1400
 * Operation7 Assignment
 * CS 1400 ONL Spr 17 33235
 *  
 * This Class contains the dice array, and methods to interact with it.
 *  
 * 20 Feb 2017
 * @author Ronald Jensen
 *******************/

public class Dice {
	int[] dice = new int[5];
	boolean[] held = new boolean[5];	
	Random r = new Random();
	
	public void rollDice() 
	{
		// The first roll
		for (int i = 0; i < 5; i++) {
			this.held[i] = false;
			this.dice[i] = r.nextInt(6) + 1;
		}		
	}
	
	public void reRollDice()
	{
	// second and third rolls
		for (int i = 0; i < 5; i++) {
			if(! held[i]){
				this.dice[i] = r.nextInt(6) + 1;
			}
		}
	} //rollDice
	
	public void printDice()
	{
	//display the dice rolls
		for (int i = 0; i < 5; i++) {
			System.out.print((i+1) + " ");
		}
		System.out.println("");
		
		for (int i = 0; i < 5; i++) {
			System.out.print(this.dice[i] + " ");
		}
		System.out.println("");
		
		for (int i = 0; i < 5; i++) {
			if(held[i]){
				System.out.print("H ");				
			} else {
				System.out.print("  ");
			}
		}
		System.out.println("");
		
	} // printDice
}
