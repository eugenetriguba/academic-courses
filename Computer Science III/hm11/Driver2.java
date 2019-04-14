// Eugene Triguba
// ytriguba17@ole.augie.edu
// Driver2.java
// Homework 11: Generics

public class Driver2
{
    public static void main(String[] args) 
    {
        Object[] strObjArr = {"red", "green", "blue", "orange"};
        String[] strArr = {"red", "green", "blue", "orange"};
        Object[] intObjArr = {40, 70, 50, 30};
        Object[] intObjArr2 = {50};
        Integer[] intArr = {40, 70, 50, 30};
        Integer[] intArr2 = {50};
        

        Generic.insertionSort(intObjArr);
        System.out.printf("The result of Object insertionSort on [40, 70, 50, 30] is %s.\n", 
            Generic.arrToString(intObjArr));
        
        Generic.insertionSort(intArr);
        System.out.printf("The result of T insertionSort on [40, 70, 50, 30] is %s.\n", 
            Generic.arrToString(intArr));

        Generic.insertionSort(intObjArr2);
        System.out.printf("The result of Object insertionSort on [50] is %s.\n", 
            Generic.arrToString(intObjArr2));
        
        Generic.insertionSort(intArr2);
        System.out.printf("The result of T insertionSort on [50] is %s.\n", 
            Generic.arrToString(intArr2));

        Generic.insertionSort(strObjArr);
        System.out.printf("The result of Object insertionSort on " + 
			"['red', 'green', 'blue', 'orange'] is %s.\n", 
            Generic.arrToString(strObjArr));
            
        Generic.insertionSort(strArr);
        System.out.printf("The result of T insertionSort on " + 
			"['red', 'green', 'blue', 'orange'] is %s.\n", 
            Generic.arrToString(strArr));

    }
}