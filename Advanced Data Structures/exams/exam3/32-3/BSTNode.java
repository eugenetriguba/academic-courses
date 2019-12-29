

public class BSTNode<T> implements java.io.Serializable
{
   	public T value;
   	public BSTNode<T> left;
   	public BSTNode<T> right;
    public BSTNode<T> parent;	
       	
   	public  BSTNode(T item, BSTNode<T> parent)
   	{
        this.value = item;
        this.parent = parent;
        this.left = null;
        this.right = null;
   	}
}
