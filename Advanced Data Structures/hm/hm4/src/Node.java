public class Node<T>
{
    public T value;
    public Node<T> next;
    public Node()
    {
        value = null;
        next = null;
    }
    public Node(T item)
    {
        value = item;
        next = null;
    }
}
