// Eugene Triguba
// ytriguba17@ole.augie.edu
// Dice.java
// Homework 5: 7-4 MoreOnClasses

import java.util.Random;

// Desc: Class Dice supports objects representing throwing a 
//   pair of dice. Each die knows its face value and each pair
//   of dice knows its total.
public class Dice
{
	private int firstDie;
	private int secondDie;
	private int total;
	private Random randomNumGenerator;
	
	// Post: firstDie and secondDie initialized randomly to a value in the  
	//  range [1..6]. total is set to the sum of firstDie and secondDie.
	public Dice()
	{
		this.randomNumGenerator = new Random();
		this.toss();
		this.calculateTotal();
	}
	
	// Post: firstDie and secondDie initialized randomly to a value in the 
	//   range [1..6]. total is set to the sum of firstDie and secondDie.
	// Return: This Dice object
	public Dice toss()
	{
		this.firstDie = this.randomNumGenerator.nextInt(6) + 1;
		this.secondDie = this.randomNumGenerator.nextInt(6) + 1;
		this.calculateTotal();
		return this;
	}
	
	// Return: The value of firstDie.
	public int getFirstDieValue()
	{
		return this.firstDie;
	}
	
	// Return: The value of secondDie.
	public int getSecondDieValue()
	{
		return this.secondDie;
	}
	
	// Return: the sum of firstDie and secondDie.
	public int getTotal()
	{
		return this.total;
	}
	
	// Return: the String "First Die: firstDie, Second Die: secondDie, Total: total"
	//   where firstDie, secondDie, and total are replaced with their values. 
	public String toString()
	{
		return "First Die: " + this.firstDie + ", Second Die: "
			+ this.secondDie + ", Total: " + this.total;
	}
	
	// Return: true if both die values in this Dice object and secondPair are equal.
	//   false otherwise.
	public Boolean equals(Dice secondPair)
	{
		if (this.getFirstDieValue() == secondPair.getFirstDieValue()
			 && this.getSecondDieValue() == secondPair.getSecondDieValue())
				 return true;
		
		return false;
	}
	
	// Post: total is set to the sum of firstDie and secondDie.
	private void calculateTotal()
	{
		this.total = this.firstDie + this.secondDie;
	}
}