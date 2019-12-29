/*
 * Eugene Triguba <ytriguba17@ole.augie.edu>
 * Exam 3: 32-3 BSTree
 * BSTree.java
 */

import java.io.Serializable;

public class BSTree<T extends Comparable<? super T>> implements Cloneable, Serializable
{
	private BSTNode<T> root;
    private int treeSize;
    
    /**
     * Finds the node in this BSTree that 
     * contains item.
     * 
     * @param item - the value of the node to find
     * @return A reference to the node containing item;
     *         null if the search fails
     */
	private BSTNode<T> findNode(T item)	 
	{				  
        BSTNode<T> node = this.root;
           
	   	while (node != null)
	   	{
            int result = item.compareTo(node.value);
            
            if (result == 0) return node;
            else if (result < 0) node = node.left;
            else node = node.right;
        }
           
	   	return null;
    }
    
    /**
     * Initialize this BSTree
     * 
     * root is set to null; treeSize is set to 0.
     */
	public BSTree()
	{
	   	this.root = null;
	   	this.treeSize = 0;
	}
    
    /**
     * Find the size of this BSTree
     * 
     * @return the size of this BSTree
     */
    public int size()
   	{
      	return this.treeSize;
    }
       
    /**
     * Check whether this BSTree is empty
     * 
     * @return true if this BSTree is empty; false otherwise.
     */
   	public boolean isEmpty()
   	{
      	return this.treeSize == 0;
   	}

    /**
     * Clears this BSTree
     * 
     * Note: nodes will be automatically garbage collected 
     *       level by level, which is a slow process
     */
	public void clear()
   	{
        this.treeSize = 0;
        this.root = null;
    }
       
    /**
     * Adds an item to this BSTree if it
     * does not already exist.
     * 
     * @param item - the value of the node to add
     * @return true if item inserted to this tree; false otherwise.
     * 
     * Note: This add does not do tree balancing so a non-degenerate
     *       tree is not guaranteed.
     */
	public boolean add(T item)
	{
        BSTNode<T> node = root;
        BSTNode<T> parent = null;
        int result = 0;
        
        // Find the node
        // We need to save the parent so 
        // we can't use findNode() here.
		while(node != null)
		{
			parent = node;
			result = item.compareTo(node.value);
			if (result == 0) return false;
			else if (result < 0) node = node.left;
			else node = node.right;
        }
        
        // Insert item into where the null pointer is at
        BSTNode<T> newNode = new BSTNode<T>(item, parent);

        // if(parent == null) root = newNode;
        // else if(result < 0) parent.left = newNode;
        // else parent.right = newNode;

		if (result < 0) parent.left = newNode;
		else if (result > 0) parent.right = newNode;
		else root = newNode;

		treeSize++;
		return true;
    }

    /**
     * Removes a node from this BSTree.
     * 
     * @param item - the value of the node to remove
     * @return true if the node was removed; false otherwise.
     */
    public boolean remove(Object item)
	{
		BSTNode<T> t  = findNode((T)item);
		if (t == null) return false;
		removeNode(t);
		treeSize--;
		return true;
    }
    
    /**
     * Removes a node from this BSTree
     * and handles the re-linking of the nodes.
     * 
     * @param D - the node to remove
     */
	private void removeNode(BSTNode<T> D)
	{
		BSTNode<T> pOfD = D.parent;
		BSTNode<T> R;
        BSTNode<T> pOfR;
        
		if ((D.left == null) && (D.right == null))  // D is a leaf
			R = null;						
		else if ((D.left == null) && (D.right != null))	// D has 1 son, right
		{
            R = D.right;					
            R.parent = pOfD;					 
		}							
		else if ((D.left != null) && (D.right == null))	// D has 1 son, left
		{
            R = D.left;					
            R.parent = pOfD;					
		}							
		else if (D.right.left== null)   // D has 2 sons, right son has no left
		{
			R = D.right;				
			R.left = D.left;				
			R.parent = pOfD;					
			D.left.parent = R;					
		}							
		else    // D has 2 sons, right son has left
		{
			// Find R
			pOfR = D;				
			R = D.right;				
			while(R.left != null)			
			{					
				pOfR = R;			 
				R = R.left;			 
			}		

			// relink			
			pOfR .left = R.right;			
			if (R.right != null) R.right.parent = pOfR;		
			R.left = D.left;				
			R.right = D.right;				
			R.parent = pOfD;				
			R.left.parent = R;				
			R.right.parent = R;			
        }			
        			
		// Set pOfD to reference R for all cases.  
		if (pOfD == null) root = R;	// D is the root
		else if ((D.value).compareTo(pOfD.value)<0)
            pOfD.left = R;				
		else pOfD.right = R;
    }

    /**
     * Finds the height of this BSTree
     * 
     * @return Height of the BSTree; -1 if tree is empty.
     */
    public int height()
    {
        return height(this.root);
    }

    /**
     * Finds the height of this BSTree.
     * 
     * Used as a helper since it is implemented 
     * recursively.
     * 
     * @param node - the root node of this BSTree
     * @return Height of the BSTree; -1 if tree is empty.
     */
    private int height(BSTNode<T> node)
    {
        if (node == null) return -1;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (leftHeight > rightHeight) return leftHeight + 1;
        else return rightHeight + 1;
    }

    /**
     * Finds the level of a particular node in this BSTree
     * 
     * @return The level of the node that contains item; 
     *         -1 if item is not in BSTree.
     */
    public int findLevelOf(T item)
    {
        BSTNode<T> node = this.root;
        int level = 0;
           
	   	while (node != null)
	   	{
            int result = item.compareTo(node.value);
            
            if (result == 0) return level;
            else if (result < 0) 
            {
                node = node.left;
                level++;
            }
            else 
            {
                node = node.right;
                level++;
            }
        }
           
	   	return -1;
    }
    
    /**
     * Determines whether an item is in this BSTree.
     * 
     * @param item - the value of the node to check
     * @return true if item is in this BSTree; false otherwise.
     */
   	public boolean contains(Object item)
   	{
        BSTNode<T> node = findNode((T)item);	
        return node != null;
    }
       
    /**
     * Output this BSTree to the console in inorder.
     */
    public void inorderOutput()
	{
        inorder(this.root);	
        System.out.println();	
    }
    
    /**
     * Outputs this BSTree to the console in inorder.
     * 
     * Used as a helper for inorderOutput()
     * since it is implemented recursively.
     * 
     * @param node - the root node
     */
    private void inorder(BSTNode<T> node)
	{
        if (node == null) return;
        
        inorder(node.left);		
        System.out.print(node.value + " ");  			
        inorder(node.right);	
    }
    
    /**
     * Creates a copy of this BSTree.
     * 
     * @return a shallow copy of this BSTree
     */
	public Object clone()
	{
        BSTree<T> copy = null;
        
		try
		{
			copy = (BSTree<T>)super.clone();
		}
        catch (CloneNotSupportedException e) {}
        
		copy.root = copyTree(root);
		return copy;
    }
    
    /**
     * Make a copy of a BSTree.
     * 
     * Pre: t points to a BSTree
     * @return: A BSTree that is an exact copy of the one pointed to by t.
     */
	private BSTNode<T> copyTree(BSTNode<T> t)
	{
		if (t == null) return null;
		BSTNode<T> left = copyTree(t.left);
		BSTNode<T> right = copyTree(t.right);
		BSTNode<T> copyRoot = new BSTNode<T>(t.value, null);

		copyRoot.left = left;
		copyRoot.right = right;

		if (left != null) left.parent = copyRoot;
		if (right != null) right.parent = copyRoot;

		return copyRoot;
	}
}
