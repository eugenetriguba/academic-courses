/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Exam 3: 32-2 BinaryTree
 * BTree.java 
 */

public class BTree
{
    public static void main(String[] args) 
    {
        TNode<Integer> root = buildTree();
        FindSumVisitor<Integer> visitor = new FindSumVisitor<>();

        outputTree(root, "Tree in inorder: ");
        outputSum(root, visitor, "Sum of entire tree: ");
        System.out.println();

        outputTree(root.left, "Left subtree in inorder: ");
        outputSum(root.left, visitor, "Sum of left subtree: ");
        System.out.println();

        outputTree(root.right, "Right subtree in inorder: ");
        outputSum(root.right, visitor, "Sum of right subtree: ");
        System.out.println();
    }

    /**
     * Outputs this tree with a message
     * 
     * @param node    - the starting node
     * @param message - the message to output with the tree
     */
    public static <T> void outputTree(TNode<T> node, String message)
    {
        System.out.print(message);
        BinaryTree.inorderOutput(node);
        System.out.println();
    }

    /**
     * Outputs the sum of this tree
     * 
     * @param node    - the starting node
     * @param visitor - the visitor which keeps track of the sum on each visit
     * @param message - the message to display with the sum
     */
    public static <T extends Number> void outputSum(TNode<T> node, FindSumVisitor<T> visitor, 
        String message)
    {
        inorder(node, visitor);
        System.out.println(message + visitor.getSum());
        visitor.clearSum();
    }

    /**
     * Builds a Binary Tree according to figure A of
     * 32-2BinaryTree.doc
     * 
     * @return the root node of the tree
     */
    public static TNode<Integer> buildTree()
   	{
        TNode<Integer> root = new TNode<>(50);

        // Left
        root.left =  new TNode<Integer>(30);
        root.left.left =  new TNode<Integer>(10);
        root.left.left.right =  new TNode<Integer>(20);

        // Right
        root.right = new TNode<Integer>(80);
        root.right.left = new TNode<Integer>(60);
        root.right.right = new TNode<Integer>(120);
        root.right.right.left = new TNode<Integer>(100);
        root.right.right.right = new TNode<Integer>(130);

        return root;
       }
       
    /**
    * Visit a binary tree in inorder
    *
    * @param t - the starting node
    * @param v - the visitor which decides what to do when
                 we visit each node.
    */
    public static <T> void inorder(TNode<T> t, Visitor<T> v)
    {
        if (t == null) return;
        
        inorder(t.left, v);
        v.visit(t.value);
        inorder(t.right, v);
        
    }
}

class FindSumVisitor<T extends Number> implements Visitor<T>
{
    private double sum;
    
    /**
     * Adds the value of obj to sum
     * 
     * @param obj - the Number to add the value of to sum
     */
	public void visit(T obj)
	{
		this.sum += obj.doubleValue();
    }
    
    /**
     * Get the current sum
     * 
     * @return the current sum
     */
	public double getSum()
	{
		return this.sum;
    }
    
    /**
     * Clears the sum by setting it to 0.
     */
    public void clearSum()
    {
        this.sum = 0;
    }
}