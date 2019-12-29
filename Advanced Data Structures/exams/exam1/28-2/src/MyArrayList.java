// Eugene Triguba
// ytriguba@ole.augie.edu
// Exam 1: MyArrayList.java

/**
 * Models a Java ArrayList
 */
public class MyArrayList<T> implements MyList<T>
{
   	private int listSize;
    private T[] listArr;
    
    // Post: The MyArrayList initialized to empty
    public MyArrayList()
    {
        listArr = (T[])new Object[10];
        listSize = 0;
    }

    // Return: The number of elements in this collection is returned.
    public int size()
   	{
      	return listSize;
    }
       
    // Return: True if this collection contains no elements; false otherwise.
   	public boolean isEmpty()
   	{
      	return listSize == 0;
    }
       
    // Post: All elements from this collection removed.
   	public void clear()
   	{
      	listSize = 0;
    }
       
    // Return: The index of first occurrence of item in List if item exists; 
    //         -1 if item does not exist
   	public int indexOf(Object item)
   	{
        for (int i=0;i < listSize; i++)
            if (item.equals(listArr[i])) return i;
    
        return -1;
    }
       
    // Return: True if this collection contains the specified item; 
    //         false otherwise.
   	public boolean contains(Object item)
   	{
        return indexOf(item) >= 0;
    }
       
    // Pre: 0 <= index < size()
    // Post: item at index removed from List; elements shifted up 
    //       to fill vacant position; size decremented
	// Return: The item removed
	// Excptn: if pre is not met, throws IndexOutOfBoundsException
	public T remove(int index)
	{
		rangeCheck(index, listSize-1);
		T temp = listArr[index];
		for (int j=index; j< listSize-1; j++)
			listArr[j] = listArr[j+1];
		listSize--;
		return temp;
    }

    // Return: A string listing the elements in the MyArrayList's 
    //         separated by commas enclosed by [].
	public String toString()
	{
		if (listSize == 0) return "[]";

		String str = "[" + listArr[0];
		for (int i = 1; i < listSize; i++)
			str +=  ", " + listArr[i];
		str += "]";

		return str;
	}
    
    // Post: A single instance of item removed from this collection 
    //       if item exists
	// Return: True if item removed; false otherwise
	public boolean remove(Object item)
	{
        remove(indexOf(item));
        return true;
    }
    
    // Return: 	An array which contains all elements in this 
    //          collection (references only).
   	public Object[] toArray()
   	{
        return (Object[]) listArr; 
    }
    
    // Pre: 0 <= index <= size()
    // Post: item added to List at index; elements shifted 
    //       down to make room; size incremented
	// Excptn: if pre is not met, throws IndexOutOfBoundsException
    public void add(int index, T item)
	{
		rangeCheck(index, listSize);
        if (listSize >= listArr.length) 
            ensureCapacity(2 * listArr.length);
        
        for (int i = listSize; i >= index; --i)
        {
            if (i == index)
            {
                listArr[i] = item;
                break;
            }
            listArr[i + 1] = listArr[i]; 
        }
        listSize++;
    }
    
    // Post: item added to this collection if item does not exist or 
    //   if it exists but the collection permits duplicates
    // Return: True if the operation inserts a new element in this 
    //   collection; false if this collection already contains the 
    //   specified element and does not permit duplicates.
	public boolean add(T item)
	{
        add(listSize, item);
        return true;
    }
    
    // Pre: 0 <= index < size()
	// Return: The item at index of List
	// Excptn: if pre is not met, throws IndexOutOfBoundsException
   	public T get(int index)
   	{
        rangeCheck(index, listSize - 1);
        return listArr[index];
    }
       
    // Pre: 0 <= index < size()
	// Post: element at index replaced by item
	// Return: The element replaced
	// Excptn: if pre is not met, throws IndexOutOfBoundsException
	public T set(int index, T item)
	{
        rangeCheck(index, listSize - 1);

        T replacedEle = listArr[index];
        listArr[index] = item;
        return replacedEle;
    }

    /**
     * @throw IndexOutOfBoundsException if index is not in [0..upperBound]
     */
	private void rangeCheck(int index, int upperBound)
	{
		if (index < 0 || index > upperBound)
			throw new IndexOutOfBoundsException("Index " + index +
				" out of bounds. Should be in range 0 to " + upperBound);
    }
    
	private void ensureCapacity(int n)
	{
		if (n > listArr.length)
		{
			T[] oldListArr = listArr;
			listArr = (T[]) new Object[n];
			for (int i = 0; i < listSize; i++)
				listArr[i] = oldListArr[i];
		}
    }
}
