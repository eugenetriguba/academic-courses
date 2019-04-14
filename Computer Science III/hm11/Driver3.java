// Eugene Triguba
// ytriguba17@ole.augie.edu
// Driver3.java
// Homework 11: Generics

public class Driver3
{
    public static void main(String[] args) 
    {
        Object[] strObjArr = {"red", "green", "blue", "orange"};
        String[] strArr = {"red", "green", "blue", "orange"};
        Object[] intObjArr = {40, 70, 50, 30};
        Object[] intObjArr2 = {50};
        Integer[] intArr = {40, 70, 50, 30};
        Integer[] intArr2 = {50};

        Generic.bubbleSort(intObjArr);
        System.out.printf("The result of Object bubbleSort on [40, 70, 50, 30] is %s.\n", 
            Generic.arrToString(intObjArr));
        
        Generic.bubbleSort(intArr);
        System.out.printf("The result of T bubbleSort on [40, 70, 50, 30] is %s.\n", 
            Generic.arrToString(intArr));

        Generic.bubbleSort(intObjArr2);
        System.out.printf("The result of Object bubbleSort on [50] is %s.\n", 
            Generic.arrToString(intObjArr2));
        
        Generic.bubbleSort(intArr2);
        System.out.printf("The result of T bubbleSort on [50] is %s.\n", 
            Generic.arrToString(intArr2));

        Generic.bubbleSort(strObjArr);
        System.out.printf("The result of Object bubbleSort on " + 
			"['red', 'green', 'blue', 'orange'] is %s.\n", 
            Generic.arrToString(strObjArr));
            
        Generic.bubbleSort(strArr);
        System.out.printf("The result of T bubbleSort on " + 
			"['red', 'green', 'blue', 'orange'] is %s.\n", 
            Generic.arrToString(strArr));

    }
}