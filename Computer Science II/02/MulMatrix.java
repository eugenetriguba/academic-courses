// Eugene Triguba
// ytriguba17@ole.augie.edu
// MulMatrix.java
// Homework 2: 6-4 MulDimArray

import java.util.Scanner;

public class MulMatrix
{
	// Desc: Multiply 2 square matrices
	// Input: Keyboard supplies the dimensions of the matrix, followed by the values 
	// 	of the first matrix, followed by the values of the second matrix. The values 
	//	are entered in row-major (the values of row 1 followed by values of row 2 and
	//  so on) and are separated by white space.  
	// Output: The product of the 2 input matrices 
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);		
		
		System.out.print("Enter dimension of square matrix: ");		
		int n = input.nextInt();
		
		int[][] matrix1 = new int[n][n];
		System.out.println("Enter values for matrix1: ");		
		readMatrix(matrix1, input);		
		
		int[][] matrix2 = new int[n][n];
		System.out.println("Enter values for matrix2: ");		
		readMatrix(matrix2, input);	
			
		int[][] matrix3 = mulMatrix (matrix1,matrix2);	
		System.out.println("Product of matrix1 and matrix2: ");		
		printMatrix(matrix3);	
		
		input.close();
	}
	
	// Desc: Read in values from a keyboard to fill a matrix
	// Input: Scanner input supplies integers separated by white space in 
	// 	row-major to fill matrix 
	// Post: matrix filled with values from input
	public static void readMatrix(int[][] matrix, Scanner input)
	{
		for (int row = 0; row < matrix.length; ++row)
		{
			System.out.printf("Enter integers for row %d separated by white space: ", row);
			for (int column = 0; column < matrix[row].length; ++column)
				matrix[row][column] = input.nextInt();
		}
	}

	// Desc: Print a matrix
	// Output: matrix printed on the screen
	public static void printMatrix(int[][] matrix)
	{
		for (int row = 0; row < matrix.length; ++row)
		{
			for (int column = 0; column < matrix[0].length; ++column)
				System.out.printf("%5d", matrix[row][column]);
			
			System.out.println();
		}
	}

	// Desc: Multiply 2 matrices
	// Pre:	matrix1 and matrix2 have the same dimensions 
	// Return: A matrix that is the product of matrix1 and matrix2
	public static int[][] mulMatrix(int[][] matrix1, int[][] matrix2)
	{
		int[][] result = new int[matrix1.length][matrix1[0].length];
		
		for (int row = 0; row < result.length; ++row)
			for (int column = 0; column < result[0].length; ++column)
				result[row][column] = getMatrixEntry(matrix1, matrix2, row, column);

		return result;
	}
	
	// Desc: Calculate one entry for the resulting matrix
	//	when multiplying two matrices
	// Pre:	matrix1 and matrix2 have the same dimensions 
	// Return: The product for one matrix entry
	public static int getMatrixEntry(int[][] matrix1, int[][] matrix2, int row, int column)
	{
		int sum = 0;
		
		for (int i = 0; i < matrix1[0].length; ++i)
			sum += matrix1[row][i] * matrix2[i][column];
		
		return sum;
	}
	
}
