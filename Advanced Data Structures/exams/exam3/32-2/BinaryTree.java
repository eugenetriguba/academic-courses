
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree
{
	//Output: The values of the binary tree rooted by t printed on the screen in preorder
    public static <T> void preorderOutput(TNode<T> t)
 	{
		if (t != null)
		{
            System.out.print (t.value + " ");
            preorderOutput(t.left);
            preorderOutput(t.right);
	    }
    }

	//Output: The values of the binary tree rooted by t printed on the screen in inorder
    public static <T> void inorderOutput(TNode<T> t)
	{
		if (t != null)
		{
            preorderOutput(t.left);
            System.out.print (t.value + " ");
            preorderOutput(t.right);
        }
	}

	//Output: The values of the binary tree rooted by t printed on the screen in postorder
    public static <T> void postorderOutput(TNode<T> t)
	{
		if (t != null)
		{
            preorderOutput(t.left);
            preorderOutput(t.right);
            System.out.print (t.value + " ");
        }
	}

	//Output: The values of the binary tree rooted by t printed on the screen in levelorder
    public static <T> void levelorderOutput(TNode<T> t)
	{
		if (t == null) return;

		Queue<TNode<T>> nodes = new LinkedList<>();
		nodes.offer(t);
		
		while (!nodes.isEmpty())
		{
			TNode node = nodes.poll();
			System.out.print(node.value + " ");
			if (node.left != null) nodes.offer(node.left);
			if (node.right != null) nodes.offer(node.right);
		}
    }
    
    //Desc: 	Find height of a binary tree
//Pre: 	t points to a binary tree
//Return:Height of the binary tree pointed to by t (-1 if tree is empty)
	// recursive case: height(t) = 1 + max(height(t.left), height(t.right)) when t is not null
// base case: -1 when t is null
	public static <T> int height(TNode<T> t)
	{
		if (t == null) return -1;
		else return 1 + Math.max(height(t.left), height(t.right));
	}

    /**
     * Count the total number of leaf nodes.
     * 
     * @param t - 
     * @return the total number of leaf nodes;
     *         1 if there is only a root node;
     *         0 if t is null.
     */
	// public static <T> int leafCount(TNode<T> t)
	// {
    //     if (t == null) return 0;
    //     else if ((t.left == null) && (t.right == null)) return 1;
    //     else leafCount(t.left) + leafCount(t.right);
    // }

//Desc: 	Make a copy of a binary tree
//Pre: 	t points to a binary tree
//Return:A binary tree that is an exact copy of the one pointed to by t
	public static <T> TNode<T> copyTree(TNode<T> t)
	{
		if (t == null) return null;

		TNode<T> left = copyTree(t.left);
		TNode<T> right = copyTree(t.right);
		TNode<T> root = new TNode<T>(t.value, left, right);
		return root;
    }
    
    //Desc: 	Delete all nodes in a binary tree
//Pre: 	t points to a binary tree
//Post: 	All nodes in binary tree referenced by t released.  
//Note: 	The caller must assign the return value of clearTree to the root of the binary tree the 
//	caller is trying to clear
public static <T> TNode<T> clearTree(TNode<T> t)
{
if (t != null)
       {
              t.left = clearTree (t.left);
              t.right = clearTree (t.right);
              return null;
       }
       else return null;
}
}