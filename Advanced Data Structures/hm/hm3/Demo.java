// Eugene Triguba
// ytriguba17@ole.augie.edu
// 28-3 Cloneable: Demo.java

public class Demo
{   
    /**
     * Demonstrates deep copy using clone() 
     * with an ArrayList
     */
    public static void main(String[] args) 
    {
        MyArrayList<Time24> arr = new MyArrayList <>();
        MyArrayList<Time24> arrCopy = null;

        arr.add(new Time24(1,1));
        arr.add(new Time24(2,2));
 
        arrCopy = (MyArrayList<Time24>) arr.clone();
        int arrCopySize = arrCopy.size();
        for (int i = 0; i < arrCopySize; i++)
        {
            int hour = arr.get(i).getHour();
            int minute = arr.get(i).getMinute();

            arrCopy.set(i, new Time24(hour, minute));
        }

        arr.add(new Time24(3,3));
        System.out.println("arr    : " + arr);
        System.out.println("arrCopy: " + arrCopy);

        arr.get(1).addTime(10);
        System.out.println("arr    : " + arr);
        System.out.println("arrCopy: " + arrCopy);
    }
}
