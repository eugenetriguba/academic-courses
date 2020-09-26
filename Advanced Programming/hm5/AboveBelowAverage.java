// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AboveBelowAverage {
    public static void main(String[] args) {
        int[] arr = new Random().ints(15, 1, 101).sorted().toArray();
        double avg = IntStream.of(arr).average().getAsDouble();
        int[] aboveAvg = IntStream.of(arr).filter(x -> x < avg).toArray();
        int[] belowAvg = IntStream.of(arr).filter(x -> x > avg).toArray();

        System.out.println("15 random numbers in ascending order: " + formatIntStream(IntStream.of(arr)));
        System.out.printf("Average: %.2f\n", avg);
        System.out.println(IntStream.of(aboveAvg).count() + " numbers above the average: "
                + formatIntStream(IntStream.of(aboveAvg)));
        System.out.println(IntStream.of(belowAvg).count() + " numbers below the average: "
                + formatIntStream(IntStream.of(belowAvg)));
    }

    // Format a stream of ints into a String of numbers
    // each seperated by a single space.
    private static String formatIntStream(IntStream is) {
        return is.mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}