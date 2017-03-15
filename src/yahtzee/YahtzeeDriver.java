package yahtzee;

import java.util.Random;

/*******************
 * Yahtzee game for CS1400
 * Operation7 Assignment
 * CS 1400 ONL Spr 17 33235
 *  
 * This is the Driver class containing the main class code to control everything else.
 *  
 * 20 Feb 2017
 * @author Ronald Jensen
 *******************/
public class YahtzeeDriver {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int[] score;          // array to hold interim score calculations
		boolean spock = true; // debug mode
		
		Dice myDice = new Dice(); // Dice is a Class holding an array with methods to interact with array
		UserInput myInput = new UserInput();
		
		myDice.rollDice();
		myDice.printDice();
		
		System.out.println();

		score = Scoring.calculateScore(myDice.dice);
		
//		System.out.println("\nPossible Scores");
//		Scoring.dumpScore(score);
//		System.out.println();
		Scoring.printBestLowerScore(score);

		for( int loop = 0; loop <2; loop++){
			for(int i=0; i< 5 ; i++)
			{
				int result = myInput.inputInt("Hold Die " + (i+1)+"? (0 for No) ");
				if(result == 0){
					myDice.held[i] = false;
				} else {
					myDice.held[i] = true;				
				}
			} 
			myDice.reRollDice();
			myDice.printDice();

			score = Scoring.calculateScore(myDice.dice);
			Scoring.printBestLowerScore(score);
		}

		if(spock)
		{
			System.out.println("We are cheating! Please enter the values for the dice.");
			for(int i=0; i< 5 ; i++)
			{
				int result = myInput.inputInt("Enter DIE Value " + (i+1)+"? (1-6): ");
				myDice.dice[i] = result;				
			} 

			myDice.printDice();

			score = Scoring.calculateScore(myDice.dice);
			Scoring.printBestLowerScore(score);
		}

		System.out.println("\nPossible Scores");
		Scoring.dumpScore(score);
		System.out.println();

	} // main

}
