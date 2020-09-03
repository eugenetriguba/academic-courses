//Example 35-1-10
/*
public class Hash<T> implements MyCollection<T>, Iterable<T>, java.io.Serializable
Supports a collection of objects with no duplicates in a hash table, with initial size 17.  The table grows as rehashing occurs.  Type T must provide method hashCode.

Usage:	public Hash();
Post: 	Hash table initialized to empty with size 17.

Usage:	public String toString ()
Return:	A comma-separated list of elements enclosed in square brackets.  This list is not ordered. 
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hash<T> implements MyCollection<T>, Iterable<T>, java.io.Serializable
{
	private class Entry<T>
	{
	   	private T value;
	  	private int hashValue;
        private Entry<T> next;

	   	public Entry(T value, int hashValue, Entry<T> next)
	   	{
	      		this.value = value;
	      		this.hashValue = hashValue;
	      		this.next = next;
	   	}
}

	private Entry<T>[] table;	
	private int numElements;
	private final double MAX_LOAD_FACTOR = .75;	
	private int tableThreshold;	

	// add needs to check if the num of elements is bigger than tableThreshold and rehash
	private void rehash(int newTableSize)
	{
		Entry<T>[] newTable = new Entry[newTableSize]; 	
		Entry<T> entry, nextEntry;			
		int index;					
		for (int i=0; i < table.length; i++)
		{
			entry = table[i];
			if (entry != null)
			{
				table[i] = null;
				do
				{
					nextEntry = entry.next;
					index = entry.hashValue % newTableSize;
					entry.next = newTable[index];	//insert front
					newTable[index] = entry;
					entry = nextEntry;
				} while (entry != null);
			}
		}
		table = newTable;
		tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
    }
    
	public Hash()
	{
		table = new Entry[17];
		numElements = 0;
		tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
    }
    
	public int size()
	{
		return numElements;
    }
    
	public boolean add(T item)
	{
		int hashValue = item.hashCode() & Integer.MAX_VALUE;
		int index = hashValue % table.length;
		Entry<T> entry = table[index];
		while (entry != null)
		{
			if (entry.value.equals(item)) return false;
			entry = entry.next;
		}
		entry =new Entry<T>(item, hashValue, table[index]);		
		table[index] = entry;
		numElements++;
		if (numElements >= tableThreshold) rehash(2*table.length+1); 
		return true;
    }
    
	public void clear()
	{
		for (int i=0;i < table.length;i++)
			table[i] = null;
		numElements = 0;
    }
    
	public boolean isEmpty()
	{
		return numElements == 0;
    }
    
	public boolean contains(Object item)
	{
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		Entry<T> entry = table[index];
		while (entry != null)
		{
			if (entry.value.equals(item)) return true;
			entry = entry.next;
		}
		return false;
    }
    
	public boolean remove(Object item)
	{
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		Entry<T> curr = table[index];
		Entry<T> prev = null;
		while (curr != null)
			if (curr.value.equals(item))
			{
				if (prev != null) prev.next = curr.next;
				else table[index] = curr.next;
				numElements--;
				return true;
			 }
			 else
			 {
				prev = curr;
				curr = curr.next;
			}
		return false;
    }
    
   	public Object[] toArray()
   	{
		Object[] arr = new Object[numElements];
		Iterator<T> iter = iterator();
		int i = 0;
		while (iter.hasNext())
		{
			arr[i] = iter.next();
			i++;
		}
		return arr;
    }
    
    public String toString()
   	{
		Iterator<T> iter = iterator();
  		String s = "[";
        for (int i=0; i<numElements; ++i)
        {
            s+= iter.next();
			if (i<numElements-1) s+=", ";
		}
        s+= "]";
        return s;
       }
       
	public Iterator<T> iterator()
	{
		return new HashIterator();
	}
	private class HashIterator implements Iterator<T>
	{
		private int index;         		//the current bucket where nextNode is in
		private Entry<T> nextNode;				
		private T lastReturned;
        //Post: index is the first index in table where table[index]!=null; 
        //      index==table.length if table is empty nextNode is the first 
        //      node in table[index] if table is not empty; 
        //      nextNode==null if table is empty lastReturned ==null			
		public HashIterator()
		{
			if (numElements == 0)
			{
				nextNode = null;
				index = table.length;
			}
			else
            {
                index = 0;
				while ((nextNode = table[index]) == null)
					index ++;
			}
			lastReturned = null;
        }
        
		public boolean hasNext()
		{
			return nextNode!= null;
        }
        
		public T next()
		{
			if (nextNode == null) throw new NoSuchElementException();
			lastReturned = nextNode.value;
			Entry<T> n = nextNode.next;
			if (n == null)
			{
				index++;
				while (index < table.length && (n = table[index]) == null)
					index++;
			}
			nextNode = n;
			return lastReturned;
        }
        
		public void remove()
		{
		   	if (lastReturned == null) throw new IllegalStateException(
		         			"Iterator call to next() "+"required before calling remove()");
			Hash.this.remove(lastReturned);
			lastReturned = null;
		}
	}
}
