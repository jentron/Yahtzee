package yahtzee;


public class YahtzeeGUIDriver {
	private static YahtzeeGUI myGui;
	public static Dice myDice;
	
	public static void main(String [] args)
	{
		myGui = new YahtzeeGUI();
		myDice = new Dice();
		
		myDice.rollDice();
		updateGuiDice();
	}
	
	public static void updateGuiDice()
	{
		for(int i=0; i<5; i++)
		{
			myGui.setDie(i, myDice.dice[i]);
		}
		
	}
}
