public class OutputVisitor<T> implements Visitor<T>
{
    // Ouput: This OutputVisitor object prints a node when it visits a node.
	public void visit(T obj)
	{
		System.out.print(obj + "  ");
	}
} 