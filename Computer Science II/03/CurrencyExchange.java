// Eugene Triguba
// ytriguba17@ole.augie.edu
// CurrencyExchange.java
// Homework 3: 7-1 NonTypeClasses

public class CurrencyExchange
{	
	// Desc: Converts a US currency amount to HK
	// Return: the HK equivalent of the inputted US amount
	public static double usToHk(double amountInUS)
	{
		return amountInUS * 7.8;
	}
	
	// Desc: Converts a HK currency amount to US
	// Return: the US equivalent of the inputted HK amount
	public static double hkToUs(double amountInHK)
	{
		return amountInHK / 7.8;
	}
}