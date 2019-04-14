// Eugene Triguba
// ytriguba17@ole.augie.edu
// Entry.java
// Homework 11: 24-3 Generics

class Entry<K extends Comparable<? super K>,V extends Comparable<? super V>> 
    implements Comparable<Entry<K, V>>
{
	private K key;				
    private V value;		
    		
	public Entry (K key, V value)
	{ 
        this.key = key; 
        this.value = value; 
    }

	public K getKey()
	{ 
        return key; 
    }   

	public V getValue()
	{ 
        return value; 
    }

	public void setValue(V value)
	{ 
        this.value = value; 
    }

	public String toString()
	{ 
        return "Key = " + key + " | Value = " + value; 
    }

	public boolean equals(Object obj)				
   	{
        if (obj instanceof Entry)
        {
            Entry<K,V> entry = (Entry<K,V>) obj;
            return key.equals(entry.getKey());
        } 
        else return false;
    }
    
    public int compareTo(Entry<K, V> obj)
   	{
   		return key.compareTo(obj.getKey());
   	}
}
