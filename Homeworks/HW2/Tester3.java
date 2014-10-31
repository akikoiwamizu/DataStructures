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

public class Tester3
{
	public static void main( String [ ] args ) throws UnderflowException
	{
		LazyBST<Integer> t = new LazyBST<>( );
		final int NUMS = 18;
		final int GAP  = 17;

		System.out.println( "Binary Search Tree with Lazy Deletion:" );

		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
			t.insert( i );

		for( int i = 1; i < NUMS; i+= 2 )
			t.remove( i );

		if(t.isEmpty())
			System.out.println( "The tree is empty." );
		else
			t.printTree( );

		System.out.println( "Minimum: " + t.findMin() );
		System.out.println( "Maximum: " + t.findMax() );

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