// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: 39-1 Parallel Programming
// Homework 7

import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMulMatrix {

    // Multiplies 2 square matrices.
    // The dimension of the matrices is entered in by the user.
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Enter dimension of square matrix: ");
        int dimension = readInt(System.in);

        System.out.println("Generating values for m1 and m2...");
        Random rand = new Random(2L);
        int[][] m1 = generateMatrix(dimension, rand);
        int[][] m2 = generateMatrix(dimension, rand);

        int logicalThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Found " + logicalThreads + " logical threads on your machine.");

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

    // Retrieve the next int from `in`
    private static int readInt(InputStream in) {
        Scanner input = new Scanner(in);
        int result = input.nextInt();
        input.close();

        return result;
    }

    private static int[][] generateMatrix(int dimension, Random rand) {
        int[][] result = new int[dimension][dimension];

        for (int i = 0; i < result.length; ++i)
            for (int j = 0; j < result[i].length; ++j)
                result[i][j] = rand.nextInt(10);

        return result;
    }

    private static String stringifyMatrix(int[][] m) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[i].length; ++j) {
                output.append(String.format("%5d", m[i][j]));
            }

            output.append('\n');
        }

        return output.toString();
    }
}

class Time {

    // Retrieve the current time in the format "mm:ss"
    public static String getCurrent() {
        Calendar now = Calendar.getInstance();

        return String.format("%d:%02d", now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
    }
}

/**
 * MultiplyMatrixTask multiplies a slice of the rows, namely [startRow to
 * endRow), and writes the result of those calculations into the corresponding
 * rows in result.
 */
class MultiplyMatrixTask implements Runnable {
    private int[][] result;
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

    private String getRowSlice() {
        if (startRow == endRow - 1) {
            return Integer.toString(startRow);
        }

        return String.format("[%s-%s]", startRow, endRow - 1);
    }
}