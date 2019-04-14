// Eugene Triguba
// ytriguba17@ole.augie.edu
// RockPaperScissors.java

import java.util.Scanner;

public class RockPaperScissors
{
	// Desc: This program simulates the game "paper, rock, scissors". Although 2 or 
	//   more players can play the game, this program will only consider the case  
	//   when there are 2 players (one is the computer itself).  
	// Input: The player enters p for paper, r for rock, s for scissors when prompted.
	// Output: A message indicating the outcome of the game is displayed after the game.
	public static void main(String[] args)
	{	
		System.out.println("Welcome to Rock, Paper, Scissors!");
		System.out.print("Enter r (for rock), p (for paper), or s (for scissors): ");
		
		Scanner input = new Scanner(System.in);
		String inputtedString = input.next();
		char userChoice = inputtedString.charAt(0);
		
		if (userChoice != 'r' && userChoice != 'p' && userChoice != 's')
		{
			System.out.println("Error: only the single character r, p, or s is allowed.");
			System.exit(1);
		}
		
		int randomNum = (int) (Math.random() * 100);	
		char computerChoice;
		if (randomNum <= 33)
		{
			computerChoice = 'r';
			System.out.println("Computer picks rock.");
		}
		else if (randomNum <= 66)
		{
			computerChoice = 'p';
			System.out.println("Computer picks paper.");
		}
		else
		{
			computerChoice = 's';
			System.out.println("Computer picks scissors.");
		}
		
		if (userChoice == 'r' && computerChoice == 'p')
			System.out.println("Rock beats paper, computer wins!");
		else if (userChoice == 'r' && computerChoice == 's')
			System.out.println("Rock beats scissors, you win!");
		else if (userChoice == 'p' && computerChoice == 'r')
			System.out.println("Paper beats rock, you win!");
		else if (userChoice == 'p' && computerChoice =='s')
			System.out.println("Scissors beats paper, computer wins!");
		else if (userChoice == 's' && computerChoice == 'r')
			System.out.println("Rock beats scissors, computer wins!");
		else if (userChoice == 's' && computerChoice == 'p')
			System.out.println("Scissors beats paper, you win!");
		else 
			System.out.println("Tie!");
	}
}