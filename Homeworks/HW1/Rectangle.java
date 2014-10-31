/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #1
 * PROGRAMMING PROBLEM #1
 * 
 * Rectangle.java
 */

public class Rectangle
{
	private int length = 0;
	private int width = 0;
	
	public Rectangle(int len, int wid)
	{
		length = len;
		width = wid;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public String toString() // returns the Rectangle object instead of the object's location in memory
	{
		return("[Rectangle: length=" + length + ", width=" + width + "]");
	}
}