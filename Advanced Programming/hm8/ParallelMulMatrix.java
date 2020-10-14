// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: 39-2 Thread Synchronization
// Homework 8

import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMulMatrix {

    /**
     * Multiplies two square matrices with the dimensions provided by the user. The
     * number of logical threads on the given machine is found and that number is
     * used to split up the task in that many parts to run in parallel.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Enter dimension of square matrix: ");
        int dimension = readInt(System.in);

        System.out.println("Generating values for m1 and m2...");
        Random rand = new Random(2L);
        int[][] m1 = generateMatrix(dimension, rand);
        int[][] m2 = generateMatrix(dimension, rand);

        int logicalThreads = Runtime.getRuntime().availableProcessors();
        System.out.printf("Found %d logical threads on your machine.\n", logicalThreads);

        if (logicalThreads > dimension) {
            System.out.printf("Only %d requested as the dimension so only %d threads will be used\n\n", dimension,
                    dimension);
            logicalThreads = dimension;
        } else {
            System.out.printf("Splitting the task into %d parts...\n\n", logicalThreads);
        }

        ExecutorService es = Executors.newCachedThreadPool();
        int[][] result = new int[dimension][dimension];

        System.out.println("Starting tasks at " + Time.getCurrent());
        int increment = dimension / logicalThreads;
        int start = 0;
        int end = increment;
        while (end + increment <= dimension) {
            es.execute(new MultiplyMatrixTask(m1, m2, result, start, end));

            start += increment;
            end += increment;
        }

        // We might still have [start-dimension) left because end + increment went over
        // dimension.
        if (start < dimension) {
            es.execute(new MultiplyMatrixTask(m1, m2, result, start, dimension));
        }

        es.shutdown();
        boolean threadsDone = es.awaitTermination(10, TimeUnit.MINUTES);
        if (threadsDone) {
            System.out.println("Finished tasks at " + Time.getCurrent());
            System.out.println("\nProduct of m1 and m2:");
            System.out.println(dimension < 20 ? stringifyMatrix(result) : "***********matrix too big for printing");
        } else {
            System.out.println("Threads haven't finished yet in 10 minutes");
        }
    }

    /**
     * Retrieves the next int from a stream.
     *
     * Args: in: The stream to read an integer from.
     *
     * Returns: The next int from that stream.
     */
    private static int readInt(InputStream in) {
        Scanner input = new Scanner(in);
        int result = input.nextInt();
        input.close();

        return result;
    }

    /**
     * Generate the values for a square matrix.
     *
     * Args: dimension: The dimension of the square matrix. rand: The random number
     * generator.
     *
     * Returns: A new matrix with it's values set between [0-10)
     */
    private static int[][] generateMatrix(int dimension, Random rand) {
        int[][] result = new int[dimension][dimension];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = rand.nextInt(10);
            }
        }

        return result;
    }

    /**
     * Retrieves the matrix as a string.
     *
     * Args: m: The matrix to stringify.
     * 
     * Returns: The matrix stringified. i.e. If the matrix is a square matrix with
     * dimensions 2, it would look like the following, with the 1 swapped for the
     * real numbers in m:
     *
     * 1 1 1 1
     */
    private static String stringifyMatrix(int[][] m) {
        StringBuilder output = new StringBuilder();

        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
                output.append(String.format("%5d", m[row][col]));
            }

            output.append('\n');
        }

        return output.toString();
    }
}

class Time {

    /**
     * Retrieves the current time in minutes and seconds.
     * 
     * Returns: The current time in the format "mm:ss"
     */
    public static String getCurrent() {
        Calendar now = Calendar.getInstance();

        return String.format("%02d:%02d", now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
    }
}

class MultiplyMatrixTask implements Runnable {
    private final int[][] result;
    private final int[][] firstMatrix;
    private final int[][] secondMatrix;
    private final int startRow;
    private final int endRow;

    public MultiplyMatrixTask(int[][] firstMatrix, int[][] secondMatrix, int[][] result, int startRow, int endRow) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * Multiplies a slice of the rows in firstMatrix and secondMatrix, namely
     * [startRow to endRow), and writes the result of those calculations into the
     * corresponding rows in result, the result matrix.
     */
    public void run() {
        System.out.printf("Starting task for row(s) %s at %s\n", getRowSlice(), Time.getCurrent());

        for (int row = startRow; row < endRow; ++row) {
            for (int col = 0; col < firstMatrix[row].length; ++col) {
                int sum = 0;

                for (int i = 0; i < firstMatrix[row].length; ++i) {
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                }

                result[row][col] = sum;
            }
        }

        System.out.printf("Ending task for row(s) %s at %s\n", getRowSlice(), Time.getCurrent());
    }

    /**
     * Retrieves the row slice range.
     *
     * Returns: If our row slice is only over 1 row, that row. Otherwise a range in
     * the format [start-end]
     */
    private String getRowSlice() {
        if (startRow == endRow - 1) {
            return Integer.toString(startRow);
        }

        return String.format("[%s-%s]", startRow, endRow - 1);
    }
}
