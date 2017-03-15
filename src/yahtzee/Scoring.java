package yahtzee;

import java.security.InvalidAlgorithmParameterException;

/*******************
 * Yahtzee game for CS1400
 * Operation7 Assignment
 * CS 1400 ONL Spr 17 33235
 *  
 * This is the Class to handling score keeping for the game.
 * The main method, calculateScore, takes a 5 element dice array
 * and returns a 14 element score array. The elements of the score
 * array are spelled out in the final static ints the class defines.
 * There is also a String array with appropriate labels.
 *  
 * 20 Feb 2017
 * @author Ronald Jensen
 *******************/
public class Scoring {
	final static int BONUS      = 0;
	final static int ACE        = 1;
	final static int TWO        = 2;
	final static int THREE      = 3;
	final static int FOUR       = 4;
	final static int FIVE       = 5;
	final static int SIX        = 6;
	final static int YAHTZEE    = 7;
	final static int L_STRAIGHT = 8;
	final static int S_STRAIGHT = 9;
	final static int FOUROFKIND = 10;
	final static int FULLHOUSE  = 11;
	final static int THREEOFKIND= 12;
	final static int CHANCE     = 13;
	
	final static String [] LABEL = {
			"Bonus",
			"Ace",
			"Two",
			"Three",
			"Four",
			"Five",
			"Six",
			"Yahtzee",
			"Large Straight",
			"Small Straight",
			"Four Of A Kind",
			"Full House",
			"Three Of A Kind",
			"Chance"
	};
	
	public static int[] calculateScore(int dice[] ) throws Exception
	{
		int[] score = new int[14];
		int straight = 0;
		int maximum = 0;
		int minimum = 6;
		
		if(dice.length !=5 ) { throw new InvalidAlgorithmParameterException();}
		// http://www.yahtzee.org.uk/rules.html
		// Upper Section - Scores sum of dice of correct number

		
		/*
		 * This loop builds a histogram of die numbers to be used in 
		 * determining n of a kind, full house, and large straight
		 * 
		 * To test for a small straight the "straight" variable will
		 * have its bits 1-6 set based on the die value,
		 * this will generate a number for testing for small straight
		 * From binary math, bit n = 2^n, the following patterns yield 
		 * a small straight:
		 * 1, 2, 3, 4 = 2+4+8+16       = 30
		 * 2, 3, 4, 5 =   4+8+16+32    = 60
		 * 3, 4, 5, 6 =     8+16+32+64 = 120
		 */
		for(int d : dice )
		{
			score[CHANCE] += d; // This will take the sum of all the dice
			score[d] ++;  // This generates a histogram of all 5 dice
			straight = straight | 1<<d; 
		}

		for(int i = 1; i<=6; i++) // extract the min and max histogram values
		{
			minimum = minimum < score[i] ? minimum : score[i];
			maximum = maximum > score[i] ? maximum : score[i];
		}
		// Lower Section
		// Yahtzee (Five of Kind) 50 points
		if(maximum == 5)
		{
			score[YAHTZEE] = 50;
		}
		// large straight (five in a row) 40 points
		/* if there is only one of each die value, and 1 or 6
		 * is missing, then it must be a large straight
		 */
		if(maximum == 1 && (score[1] == 0 || score[6] == 0))
		{
			score[L_STRAIGHT] = 40;
			score[S_STRAIGHT] = 30; // implied
		}
		// small straight (a four in a row exists) 30 points see comment above for logic
		if(straight == 30 || straight == 60 || straight == 120)
		{
			score[S_STRAIGHT] = 30;		
		}
		// four of a kind - sum of all dice
		if(maximum >= 4)
		{
			score[FOUROFKIND] = score[CHANCE];
		}
		// full house (two of a kind and a three of a kind) - 25 points
		if(maximum == 3)
		{
			for(int d : dice )
			{
				if(score[d] == 2){
					score[FULLHOUSE] = 25;
					break;
				}
			}
		}		
		// three of a kind.  - sum of all dice
		if(maximum >= 3)
		{
			score[THREEOFKIND] = score[CHANCE];
		}
		// ! chance.  - sum of all dice
		
		// done with histogram so bump up the values to be score
		for(int i = 2; i <= 6; i++)
		{
			score[i] *= i;
		}
		
		return score;
	} // calculateScore
	
	public static void dumpScore(int [] score) throws Exception
	{
		if(score.length != 14 ) { throw new InvalidAlgorithmParameterException();}
		for(int i=0; i < score.length; i++)
		{
			System.out.printf("%15s = %2d\n", LABEL[i], score[i]);
		}
	} // dumpScore
	
	public static void printBestLowerScore(int [] score) throws Exception
	{
		if(score.length != 14 ) { throw new InvalidAlgorithmParameterException();}
		for(int i = 7; i < score.length; i++)
		{
			if(score[i] > 0 ){
				System.out.printf("Best lower score is %s, %2d points\n", LABEL[i], score[i]);
				return;
			}
		}
		
	}
	
}
