// Eugene Triguba
// ytriguba17@ole.augie.edu
// Demo.java
// Homework 10: Java Bitwise

class Demo
{
    // Desc: a driver to test the rotateLeft method
	public static void main(String[] args) 
	{
        int x1 = 3;
        int x2 = -11;
        
        System.out.print("x1:                ");			
        BitWise.printbit(x1);			

        System.out.print("rotateLeft(x1,2):  ");					
        BitWise.printbit(rotateLeft(x1, 2));	

        System.out.print("x2:                ");			
        BitWise.printbit(x2);			

        System.out.print("rotateLeft(x2,2):  ");					
        BitWise.printbit(rotateLeft(x2, 2));	
    }
    
    // Return: The value obtained by rotating the 2's complement binary
    // representation of i left by distance number of bits. (Bits shifted out of
    // the left hand, or high-order, side reenter on the right, or low-order.)
    // For example, if i is 3, 6 is returned.
    public static int rotateLeft(int num, int distance)
    {
        int leftPart = (num << distance);
        int rightPart = (num >>> (32 - distance));
        int result = leftPart | rightPart;

        return result;
    }
}
