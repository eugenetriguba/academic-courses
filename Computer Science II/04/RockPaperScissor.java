// Eugene Triguba
// ytriguba17@ole.augie.edu
// RockPaperScissor.java
// Homework 4: 7-2 ClassesAndObjects

import java.util.Scanner;
import java.util.Random;
import java.util.Date;

// Desc: This program simulates the game "paper, rock, scissors". Although 2 or more
//   players can play the game, this program will only consider the case when there
//   are 2 players (one is the computer itself). The program asks the player to call.
//   The player can enter p for paper, r for rock, s for scissors. The winner is
//   determined by the following rule: paper covers rock, rock breaks scissors, and
//   scissors cut paper. If both players play the same thing, it is a tie.
// Input: The player enters p for paper, r for rock, s for scissors when prompted.
// 	 The player can keep playing by entering y when prompted.
// Output: Various messages helping the user to play the game. A summary report is
//   displayed before the program ends which states how many games the user won,
// 	 how many games the user lost, and how many tie games. The duration of the game
// 	 is also displayed before the program ends.
public class RockPaperScissor
{  
	public static void main(String[] args) 
	{		
		Scanner keyboard = new Scanner(System.in);
     	Random randomNumGenerator = new Random();	
		char userCall = 'p', computerCall = 'p', playAgain = 'y', result = 'u';
		int userWins = 0, userLosses = 0, userTies = 0;
		Date startTime = new Date();
		System.out.printf("Welcome! It is %s\n", startTime.toString());
					
		while (playAgain == 'y')
		{
			System.out.print("Please call (p, r, s): ");		
			userCall = keyboard.nextLine().charAt(0);
			while (userCall != 'p' && userCall != 'r' && userCall != 's')
			{
				System.out.print("Error: you must call 'p', 'r', or 's': ");
				userCall = keyboard.nextLine().charAt(0);
			}
			
			computerCall = generateComputerCall(randomNumGenerator);			
			result = whoWin(computerCall, userCall);   
						
			switch (result)
			{
				case 'u': 	
					System.out.printf("\tI have %c, you won.\n", computerCall);	
					userWins++;
					break;
				case 'c': 	
					System.out.printf("\tI have %c, you lost.\n", computerCall);	
					userLosses++;
					break;
				case 't': 	
					System.out.printf("\tI have %c, we tied.\n", computerCall);	
					userTies++;
					break;
			}
			
			System.out.printf("\tPlay again? (y/n): ");
			playAgain = keyboard.nextLine().charAt(0);
		}
		printReport(userWins, userLosses, userTies);
		printTimeReport(startTime);
	}
	
	// Desc: Randomly generates a choice of rock, paper, or scissors for 
	//	 the computer.
	// Return: the char 'p' for paper, 'r' for rock, or 's' for scissors.
	public static char generateComputerCall(Random randomNumGenerator)
	{
		char result = '\u0000';
		int randomNum = (int) (randomNumGenerator.nextDouble() * 100.0);
		
		if (randomNum <= 33) result = 'p';
		else if (randomNum <= 66) result = 'r';
		else result = 's';
		
		return result;
	}
	
	// Desc: Determines who won the round by comparing both choices. 
	// Pre: inputted choices must be either 'r', 'p', or 's'
	// Return: the char 'u' if the user won, 'c' if the computer won,
	//	 and 't' if it was a tie.
	public static char whoWin(char computerChoice, char userChoice)
	{	
		if (userChoice == computerChoice) return 't';
		
		switch(userChoice)
		{
			case 'r':
				if (computerChoice == 'p') return 'c';
				break;
			case 'p':
				if (computerChoice == 's') return 'c';
				break;
			case 's':
				if (computerChoice == 'r') return 'c';
				break;
		}
		
		return 'u';
	}
	
	// Desc: Show the total amount of wins, losses, and ties.
	// Output: total wins, losses, and ties printed to the screen.
	public static void printReport(int totalWins, int totalLosses, int totalTies)
	{
		System.out.printf("You won %d games\n", totalWins);
		System.out.printf("You lost %d games\n", totalLosses);
		System.out.printf("You tied %d games\n", totalTies);
	}
	
	// Desc: Calculate and print how long the game took.
	// Output: total time the game took in seconds printed to the screen.
	public static void printTimeReport(Date startTime)
	{
		Date endTime = new Date();
		double totalTime = (endTime.getTime() - startTime.getTime()) / 1000.0;
		System.out.printf("The game took %.2f seconds.\n", totalTime);
	}
}
