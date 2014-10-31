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
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree( )
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
	public AnyType findMin( )
	{
		if( isEmpty( ) )
			throw new UnderFlowException( );
		return findMin( root ).element;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax( )
	{
		if( isEmpty( ) )
			throw new UnderFlowException( );
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


	//-------------------------------
	public int numLeaves( )
	{
		if( isEmpty( ) )
		{
			System.out.println( "Empty tree" );
			return 0;
		}
		else
		{
			int k = numLeaves(root);
			return k;
		}
	}
	
	//-------------------------------
	public int numNodes( )
	{
		if( isEmpty( ) )
		{
			System.out.println( "Empty tree" );
			return 0;
		}
		else
		{
			int k = numNodes(root);
			return k;
		}
	}
	
	//-------------------------------
	public int numFullNodes( )
	{
		if( isEmpty( ) )
		{
			System.out.println( "Empty tree" );
			return 0;
		}
		else
		{
			int k = numFullNodes(root);
			return k;
		}
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
		if(t.element == null)
			t = t.left;
		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children (two subtrees)
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right; // ternary operator
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
		else if( t.left == null )
			return t;
		return findMin( t.left ); // tail recursion, can be rewritten as a while loop
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t ) //bigO is the depth of the largest node O(logn), worst case O(N)
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
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
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	//_____________________________________________________________________
	private int numLeaves(BinaryNode<AnyType> t)
	{
		if(t == null)
			return 0;
		else if(t.left == null && t.right == null)
			return 1;
		else
			return(numLeaves(t.left) + numLeaves(t.right));
	}

	//_____________________________________________________________________
	private int numNodes(BinaryNode<AnyType> t)
	{
		if(t == null)
			return 0;
		else
			return(numNodes(t.left) + numNodes(t.right) + 1);
	}

	//_____________________________________________________________________
	private int numFullNodes(BinaryNode<AnyType> t)
	{
		if(t == null)
			return 0;
		else if(t.left != null && t.right != null)
			return(numFullNodes(t.left) + numFullNodes(t.right) + 1);
		else
			return(numFullNodes(t.left) + numFullNodes(t.right));
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
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
	}


	/** The tree root. */
	private BinaryNode<AnyType> root;


	// Test program
	public static void main( String [ ] args )
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>( );
		final int NUMS = 8;
		final int GAP  = 7;

		//System.out.println( "Checking... (no more output means success)" );

		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
			t.insert( i );

		for( int i = 1; i < NUMS; i+= 2 )
			t.remove( i );

		int leaves = t.numLeaves( );
		System.out.println("Number of leaves: " + leaves);
		int nodes = t.numNodes( );
		System.out.println("Number of nodes: " + nodes);
		int fullNodes = t.numFullNodes( );
		System.out.println("Number of full nodes: " + fullNodes);
		
		t.printTree( );
		

		if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
			System.out.println( "FindMin or FindMax error!" );

		for( int i = 2; i < NUMS; i+=2 )
			if( !t.contains( i ) )
				System.out.println( "Find error1!" );

		for( int i = 1; i < NUMS; i+=2 )
		{
			if( t.contains( i ) )
				System.out.println( "Find error2!" );
		}
	}
}