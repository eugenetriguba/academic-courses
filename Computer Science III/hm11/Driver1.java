// Eugene Triguba
// ytriguba17@ole.augie.edu
// Driver1.java
// Homework 11: Generics

public class Driver1
{
    public static void main(String[] args) 
    {
        Object[] strObjArr = {"red", "green", "blue"};
        String[] strArr = {"red", "green", "blue"};
        Object[] intObjArr = {40, 70, 50, 30};
		Integer[] intArr = {40, 70, 50, 30};
        
        int key = 40;
        System.out.printf("The result of Object binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(intObjArr), key, Generic.binSearch(intObjArr, key));
        
        System.out.printf("The result of T binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(intArr), key, Generic.binSearch(intArr, key));

        key = 100;
        System.out.printf("The result of Object binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(intObjArr), key, Generic.binSearch(intObjArr, key));
            
        System.out.printf("The result of T binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(intArr), key, Generic.binSearch(intArr, key));

        String strKey = "green";
        System.out.printf("The result of Object binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(strObjArr), strKey, Generic.binSearch(strObjArr, strKey));
            
        System.out.printf("The result of T binSearch on %s looking for %s is %d.\n", 
            Generic.arrToString(strArr), strKey, Generic.binSearch(strArr, strKey));

    }
}