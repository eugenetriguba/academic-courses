// Eugene Triguba
// ytriguba17@ole.augie.edu
// CurrencyApp.java
// Homework 3: 7-1 NonTypeClasses

import java.util.Scanner;

public class CurrencyApp
{	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter a $US amount: ");
		double usAmount = input.nextDouble();
		System.out.printf("US$%.2f=HK$%.2f\n", usAmount, CurrencyExchange.usToHk(usAmount));
		
		System.out.print("Please enter a $HK amount: ");
		double hkAmount = input.nextDouble();
		System.out.printf("HK$%.2f=US$%.2f\n", hkAmount, CurrencyExchange.hkToUs(hkAmount));
	}
}