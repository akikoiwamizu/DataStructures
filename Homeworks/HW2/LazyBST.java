/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #3
 * 
 * Tester3.java
 */

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class LazyBST<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public LazyBST( )
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */
	public void insert( AnyType x )
	{
		root = insert( x, root );
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x )
	{
		root = remove( x, root );
	}

	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin( ) throws UnderflowException
	{
		if( isEmpty( ) )
			throw new UnderflowException( );
		return findMin( root ).element;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax( ) throws UnderflowException
	{
		if( isEmpty( ) )
			throw new UnderflowException();
		return findMax( root ).element;
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return true if not found.
	 */
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return new BinaryNode<>( x, null, null );

			int compareResult = x.compareTo( t.element );

			if( compareResult < 0 )
				t.left = insert( x, t.left );
			else if( compareResult > 0 )
				t.right = insert( x, t.right );
			else if(t.lazyDelete == true)
				t.lazyDelete = false;
			else
				;  // Duplicate; do nothing
				return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else
			t.lazyDelete = true;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t == null )
			return null;
		if(t.left != null)
		{
			BinaryNode<AnyType> s = findMin(t.left);
			if(s != null)
				return s;
		}
		if(t.lazyDelete == false)
			return t;
		return findMin( t.right); // if t.left is not null then find the min of the right subtree
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t ) 
	{
		if( t == null )
			return null;
		if(t.right != null)
		{
			BinaryNode<AnyType> s = findMax(t.right);
			if(s.right != null)
				return s;
		}
		if(t.lazyDelete == false)
			return t;
		return findMax( t.left ); //if t.right is null then find the max of the left subtree
	}

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return false;

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			return contains( x, t.left );
		else if( compareResult > 0 )
			return contains( x, t.right );
		else if(t.lazyDelete == true)
			return false;
		else
			return true;    // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the subtree.
	 */
	private void printTree( BinaryNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			if(t.lazyDelete == false)
				System.out.println( t.element );
			else
				;
			printTree( t.right );
		}
	}

	/**
	 * Internal method to compute height of a subtree.
	 * @param t the node that roots the subtree.
	 */
	private int height( BinaryNode<AnyType> t )
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );    
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType>
	{
		// Constructors
		BinaryNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
			lazyDelete = false;
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
		boolean lazyDelete; // The node that will be lazily deleted
	}


	/** The tree root. */
	private BinaryNode<AnyType> root;

}