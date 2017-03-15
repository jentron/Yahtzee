package yahtzee;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class YahtzeeGUI {
	JFrame homeScreen;
	JPanel dicePanel;
	JLabel [] diceLbl;
//	JTextField txtName;
	JButton btnRoll;
	JButton btnScore;
	ImageIcon [] diceIcon;// = new JButton[5];
	JCheckBox [] chkbxDice;
	
	//Constructor
	public YahtzeeGUI()
	{
		homeScreen = new JFrame();
		dicePanel  = new JPanel();
		diceIcon  = new ImageIcon[6];
		diceLbl   = new JLabel[5];
		chkbxDice = new JCheckBox[5];
		
		homeScreen.setSize(640, 400);
		homeScreen.setTitle("Ron's CS1400 Yahtzee");
		homeScreen.setDefaultCloseOperation(homeScreen.EXIT_ON_CLOSE);
		homeScreen.setLayout(new FlowLayout());
		
		for(int i=0;i<6;i++)
		{
			ImageIcon bab = new ImageIcon("icons/skoll/originals/png/inverted-dice-"+(i+1)+".png");
			Image image = bab.getImage(); // transform it
			Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			diceIcon[i] = new ImageIcon(newimg);  // transform it back
		}
		
		dicePanel.setLayout(new GridLayout(2, 5));
		for(int i=0; i<5; i++){
			diceLbl[i] = new JLabel();
			diceLbl[i].setIcon(diceIcon[i]);
			dicePanel.add(diceLbl[i]);
		}
		for(int i=0; i<5; i++){
			chkbxDice[i] = new JCheckBox("Hold");
			dicePanel.add(chkbxDice[i]);
		}		
		
//		txtName = new JTextField(20);
		btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new MyButtonRollListener());
		btnScore = new JButton("Score");
		btnScore.addActionListener(new MyButtonScoreListener());
		
		homeScreen.add(dicePanel);
//		homeScreen.add(txtName);
		homeScreen.add(btnRoll);
		homeScreen.add(btnScore);
		
		homeScreen.setVisible(true);

	} // end of constructor
	
	
	
	public void setDie(int die, int number)
	{
		diceLbl[die].setIcon(diceIcon[number-1]);
	}
	
	private class MyButtonScoreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int[] score = null;
			try {
				score = Scoring.calculateScore(YahtzeeGUIDriver.myDice.dice);
				Scoring.dumpScore(score);
				System.out.println("");
				Scoring.printBestLowerScore(score);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}  // ButtonScoreListener

	private class MyButtonRollListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
//			System.out.println(e);
			for(int i=0; i<5; i++)
			{
				YahtzeeGUIDriver.myDice.held[i] = chkbxDice[i].isSelected(); 
			}
			
			YahtzeeGUIDriver.myDice.reRollDice();
			YahtzeeGUIDriver.updateGuiDice();
		}
		
	}
} // end class
