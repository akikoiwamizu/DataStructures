public class myHash
{
	public myHash( )
	{
		this( TABLE_SIZE );
	}


	public myHash( int size )
	{
		createArray( size );
		clear( );
	}

	public boolean insert( String x )
	{
		// Insert x as active
		int currentPos = findPos( x ); 
		if( isActive( currentPos ) ) // currentPos is either null or a location where x already exists 
			return false; // if true then it is already there and you move on

		array[ currentPos ] = new HashEntry<>( x, true );
		theSize++;

		// Rehash; see Section 5.5
		if( ++occupied > array.length / 2 )
			rehash( );

		return true;
	}

	private void rehash( )
	{
		HashEntry<String> [ ] oldArray = array;

		// Create a new double-sized, empty table
		createArray( 2 * oldArray.length );
		occupied = 0;
		theSize = 0;

		// Copy table over
		for( HashEntry<String> entry : oldArray )
			if( entry != null && entry.isActive )
				insert( entry.element );
	}

	private int findPos( String x ) 
	{
		int offset = 1;
		int currentPos = myhash( x ); // same hash formula that we used h(x) + f(i) mod TS

		while( array[ currentPos ] != null &&
				!array[ currentPos ].element.equals( x ) ) 
		{
			currentPos += offset;  // Compute ith probe
			offset += 2; // same as computing the i^2 
			if( currentPos >= array.length ) // checking modulus
				currentPos -= array.length;
		}

		return currentPos; // want to return either the new location to insert to or the location in which it already exists
	}

	public boolean remove( String x )
	{
		int currentPos = findPos( x );
		if( isActive( currentPos ) ) // if there is an active element in the list then delete it, rehashing deletes it completely
		{
			array[ currentPos ].isActive = false;
			theSize--;
			return true;
		}
		else
			return false;
	}

	public int size( )
	{
		return theSize;
	}

	public int capacity( )
	{
		return array.length;
	}

	public boolean contains( String x )
	{
		int currentPos = findPos( x );
		return isActive( currentPos );
	}

	private boolean isActive( int currentPos )
	{
		return array[ currentPos ] != null && array[ currentPos ].isActive; 
	}

	public void makeEmpty( )
	{
		clear( );
	}

	private void clear( )
	{
		occupied = 0;
		for( int i = 0; i < array.length; i++ )
			array[ i ] = null;
	}

	private int myhash( String x )
	{
		int hashVal = x.hashCode( );

		hashVal %= array.length;
		if( hashVal < 0 )
			hashVal += array.length;

		return hashVal;
	}

	private static class HashEntry<String> 
	{
		public String  element;   // the element
		public boolean isActive;  // false if marked deleted

		public HashEntry( String e )
		{
			this( e, true );
		}

		public HashEntry( String e, boolean i )
		{
			element  = e;
			isActive = i;
		}
	}

	private static final int TABLE_SIZE = 113;

	private HashEntry<String> [ ] array; // The array of elements
	private int occupied;                 // The number of occupied cells
	private int theSize;                  // Current size

	private void createArray( int arraySize )
	{
		array = new HashEntry[ nextPrime( arraySize ) ];
	}

	private static int nextPrime( int n )
	{
		if( n % 2 == 0 )
			n++;

		for( ; !isPrime( n ); n += 2 )
			;

		return n;
	}

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

}