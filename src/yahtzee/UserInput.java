package yahtzee;

import java.util.Scanner;

/*******************
 * Yahtzee game for CS1400
 * Operation7 Assignment
 * CS 1400 ONL Spr 17 33235
 *  
 * This is the text-based user input class containing Scanner class and input wrapped in try-catch block.
 *  
 * 20 Feb 2017
 * @author Ronald Jensen
 *******************/

public class UserInput {
	Scanner in = new Scanner(System.in);

	public int inputInt(String prompt)
	{
		int result = -1;
		while(true){
			try{
				System.out.print(prompt);
				result = in.nextInt();
				in.nextLine();
				return result;
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Sorry, I didn't understand that. Please try again.");
				in.nextLine(); // flush the buffer
			}
		}
	} // inputInt

}
