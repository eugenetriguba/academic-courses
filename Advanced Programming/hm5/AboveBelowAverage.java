// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: Homework 5

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AboveBelowAverage {
    public static void main(String[] args) {
        int[] arr = new Random().ints(15, 1, 101).sorted().toArray();
        double avg = IntStream.of(arr).average().getAsDouble();
        int[] aboveAvg = IntStream.of(arr).filter(x -> x > avg).toArray();
        int[] belowAvg = IntStream.of(arr).filter(x -> x < avg).toArray();

        System.out.println("15 random numbers in ascending order: " + formatArr(arr));
        System.out.printf("Average: %.2f\n", avg);
        System.out.println(aboveAvg.length + " numbers above the average: " + formatArr(aboveAvg));
        System.out.println(belowAvg.length + " numbers below the average: " + formatArr(belowAvg));
    }

    // Format an int array into a String of numbers
    // each seperated by a single space.
    private static String formatArr(int[] arr) {
        return IntStream.of(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}