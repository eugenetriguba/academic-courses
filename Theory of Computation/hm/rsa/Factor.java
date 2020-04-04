// Eugene Triguba
// ytriguba17@ole.augie.edu
// Factor.java
// Homework 2: Handout 0-6
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * Class Factor is able to find all factors and prime factors
 * of a given BigInteger.
 */
class Factor
{
    private List<SimpleImmutableEntry<BigInteger,BigInteger>> factors;
    private List<SimpleImmutableEntry<BigInteger,BigInteger>> primeFactors;
    private BigInteger number;
    private boolean ranFindFactors;

    public Factor(BigInteger num)
    {
        this.factors = new ArrayList<>();
        this.primeFactors = new ArrayList<>();
        this.number = num;
        this.ranFindFactors = false;
    }

    /**
     * Retrieve all prime factors.
     *
     * Return: If findAllPrimeFactors() has not been run, an empty list.
     *         Otherwise, all prime factors of this.number.
     */
    public List<SimpleImmutableEntry<BigInteger,BigInteger>> getPrimeFactors()
    {
        return this.primeFactors;
    }

    /**
     * Retrieve all factors.
     *
     * Return: If findAllFactors() has not been run, an empty list.
     *         Otherwise, all factors of this.number.
     */
    public List<SimpleImmutableEntry<BigInteger,BigInteger>> getFactors()
    {
        return this.factors;
    }

    /**
     * Retrieve the number given to factor.
     */
    public BigInteger getNumber()
    {
        return this.number;
    }

    /**
     * Finds all the factors of this number, fills the internal ArrayList
     * storing the factors, and returns that ArrayList.
     */
    public List<SimpleImmutableEntry<BigInteger,BigInteger>> findAllFactors()
    {
        BigInteger upperLimit = this.number.sqrt();

        for (BigInteger i = BigInteger.valueOf(1);
             i.compareTo(upperLimit) <= 0;
             i = i.add(BigInteger.valueOf(1))
        ) {
            if (this.number.mod(i).equals(BigInteger.valueOf(0))) {
                this.factors.add(
					new SimpleImmutableEntry<BigInteger, BigInteger>(
						i, this.number.divide(i)));
            }
        }

        this.ranFindFactors = true;
        return this.factors;
    }

    /**
     * Determine if any of the factors that were found are prime pairs and return
     * the ArrayList of prime pairs.
     *
     * If the factors of the numbers have not been found, it will run that first
     * before finding the prime pairs.
     */
    public List<SimpleImmutableEntry<BigInteger,BigInteger>> findAllPrimeFactors()
    {
        if (!this.ranFindFactors) {
            this.findAllFactors();
        }

        for (SimpleImmutableEntry<BigInteger,BigInteger> entry : this.factors) {
            if (this.isPairPrime(entry)) {
                this.primeFactors.add(entry);
                break;
            }
        }

        return this.primeFactors;
    }

    /**
     * Determine whether a factor pair is a prime pair with 100% certainty.
     */
    private boolean isPairPrime(SimpleImmutableEntry<BigInteger,BigInteger> entry)
    {
        return entry.getKey().isProbablePrime(100) && 
			entry.getValue().isProbablePrime(100);
    }

    /**
     * Set the string representation of this Factor to be a list of 
	 * all the pairs of factors in this format (num1, num2), each 
	 * on a new line. Next, a line indicating the total factors. 
	 * Then, a final line showing the prime pair if there was one or 
	 * indicating there wasn't any prime pairs.
     */
    public String toString()
    {
        String out = "";
        int total = this.factors.size();

        for (int i = 0; i < total; i++) {
            SimpleImmutableEntry<BigInteger,BigInteger> entry = this.factors.get(i);
            out += String.format("(%d, %d)\n", entry.getKey(), entry.getValue());
        }
        out += String.format("%d total factors\n", total);

        if (this.primeFactors.size() >= 1) {
            out += String.format(
                    "Prime pair: (%d, %d)",
                    this.primeFactors.get(0).getKey(), 
					this.primeFactors.get(0).getValue()
            );
        } else {
            out += "There were no prime pairs.";
        }

        return out;
    }

    /**
     * Given a start and end time, find the elapsed time and format it accordingly.
     *
     * If the duration is greater than 1000ms, the time will be 
	 * presented with s as the suffix. Otherwise, ms will be the suffix.
     */
    private static String elapsedTime(long startTime, long endTime)
    {
        long elapsedTime = endTime - startTime;

        if ((elapsedTime / 1000.0) > 1) {
            return (elapsedTime / 1000.0) + "s";
        }

        return elapsedTime + "ms";
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean continueFindingFactors = true;

        while (continueFindingFactors) {
            System.out.print("Enter a positive integer to factor: ");
            Factor fact = new Factor(input.nextBigInteger());

            long startTime = System.currentTimeMillis();
            fact.findAllFactors();
            fact.findAllPrimeFactors();
            long endTime = System.currentTimeMillis();

            System.out.println(fact);
            System.out.println(
                    "Total execution time: " + 
					Factor.elapsedTime(startTime, endTime)
            );

            System.out.print("Factor another number (y/n): ");
            char answer = input.next().trim().charAt(0);
            if (Character.toLowerCase(answer) == 'n') {
                continueFindingFactors = false;
            }
        }
    }
}