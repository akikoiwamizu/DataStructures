import java.util.LinkedList;
import java.util.List;

// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

/**
 * Separate chaining table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class SeparateChainingHashTable<AnyType>
{
	/**
	 * Construct the hash table.
	 */
	public SeparateChainingHashTable( )
	{
		this( DEFAULT_TABLE_SIZE );
	}

	/**
	 * Construct the hash table.
	 * @param size approximate table size.
	 */
	public SeparateChainingHashTable( int size )
	{
		theLists = new LinkedList[ nextPrime( size ) ]; //List is actually an interface, but you can set it to anything that implements the list
		for( int i = 0; i < theLists.length; i++ )
			theLists[ i ] = new LinkedList<>( ); // creating the individual lists
	}

	/**
	 * Insert into the hash table. If the item is
	 * already present, then do nothing.
	 * @param x the item to insert.
	 */
	public void insert( AnyType x )
	{
		List<AnyType> whichList = theLists[ myhash( x ) ]; // which extracts the location of the list and stores it
		if( !whichList.contains( x ) ) // use the existing contains method of the LinkedList, O(N) operation
		{
			whichList.add( x );

			// Rehash; see Section 5.5
			if( ++currentSize > theLists.length ) // if the currentSize is greater than the length (load factor of 1)
				rehash( );
		}
	}

	/**
	 * Remove from the hash table.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x ) // no need to lazy delete anything
	{
		List<AnyType> whichList = theLists[ myhash( x ) ];
		if( whichList.contains( x ) )
		{
			whichList.remove( x );
			currentSize--;
		}
	}

	/**
	 * Find an item in the hash table.
	 * @param x the item to search for.
	 * @return true if x isnot found.
	 */
	public boolean contains( AnyType x )
	{
		List<AnyType> whichList = theLists[ myhash( x ) ];
		return whichList.contains( x );
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty( )
	{
		for( int i = 0; i < theLists.length; i++ )
			theLists[ i ].clear( );
		currentSize = 0;    
	}

	/**
	 * A hash routine for String objects.
	 * @param key the String to hash.
	 * @param tableSize the size of the hash table.
	 * @return the hash value.
	 */
	public static int hash( String key, int tableSize )
	{
		int hashVal = 0;

		for( int i = 0; i < key.length( ); i++ )
			hashVal = 37 * hashVal + key.charAt( i );

		hashVal %= tableSize;
		if( hashVal < 0 )
			hashVal += tableSize;

		return hashVal;
	}

	private void rehash( ) // creates a new reference so we can temp store an array of lists and make a new one
	{
		List<AnyType> [ ]  oldLists = theLists;

		// Create new double-sized, empty table
		theLists = new List[ nextPrime( 2 * theLists.length ) ];
		for( int j = 0; j < theLists.length; j++ )
			theLists[ j ] = new LinkedList<>( );

			// Copy table over
			currentSize = 0;
			for( List<AnyType> list : oldLists )
				for( AnyType item : list )
					insert( item );
	}

	private int myhash( AnyType x )
	{
		int hashVal = x.hashCode( ); // makes use of the existing hashCode() function on the Object (built-in)

		hashVal %= theLists.length;
		if( hashVal < 0 )
			hashVal += theLists.length; // if this ends up being negative then it will wrap it around

		return hashVal;
	}

	private static final int DEFAULT_TABLE_SIZE = 101; // a constant, 101 is a prime number

	/** The array of Lists. */
	private List<AnyType> [ ] theLists; // an array of lists
	private int currentSize; // keeping track of the current size of the hash table

	/**
	 * Internal method to find a prime number at least as large as n.
	 * @param n the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime( int n )
	{
		if( n % 2 == 0 )
			n++;

		for( ; !isPrime( n ); n += 2 )
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime.
	 * Not an efficient algorithm.
	 * @param n the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime( int n )
	{
		if( n == 2 || n == 3 )
			return true;

		if( n == 1 || n % 2 == 0 )
			return false;

		for( int i = 3; i * i <= n; i += 2 )
			if( n % i == 0 )
				return false;

		return true;
	}


	// Simple main
	public static void main( String [ ] args )
	{
		SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>( );

		long startTime = System.currentTimeMillis( );

		final int NUMS = 2000000;
		final int GAP  =   37;

		System.out.println( "Checking... (no more output means success)" );

		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
			H.insert( i );
		for( int i = 1; i < NUMS; i+= 2 )
			H.remove( i );

		for( int i = 2; i < NUMS; i+=2 )
			if( !H.contains( i ) )
				System.out.println( "Find fails " + i );

		for( int i = 1; i < NUMS; i+=2 )
		{
			if( H.contains( i ) )
				System.out.println( "OOPS!!! " +  i  );
		}

		long endTime = System.currentTimeMillis( );

		System.out.println( "Elapsed time: " + (endTime - startTime) );
	}

}
