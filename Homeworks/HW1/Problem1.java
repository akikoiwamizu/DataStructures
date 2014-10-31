/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #1
 * PROGRAMMING PROBLEM #1
 * 
 * Problem1.java
 */

import java.util.Comparator;

public class Problem1
{
	public static void main(String[] args)
	{
		Rectangle[] arr = {new Rectangle(5,10), new Rectangle(6,10), new Rectangle(13,4)};

		Rectangle maxPerimeter = findMax(arr, new cmpPerimeter());
		Rectangle maxArea = findMax(arr, new cmpArea());
		
		System.out.println("The Rectangles: " + arr[0].toString() + ", " + arr[1].toString() + ", " + arr[2].toString());
		System.out.println("Largest Rectangle Based on Perimeter: " + maxPerimeter);
		System.out.println("Largest Rectangle Based on Area: " + maxArea);
	}
	
//***************************************findMax(Rectangle[], Comparator<Rectangle>)***************************************
	public static <Rectangle>Rectangle findMax(Rectangle[] arr, Comparator<Rectangle> cmp)
	{
		int maxIndex = 0;

		for(int i = 1; i < arr.length; i++)
		{
			if(cmp.compare(arr[i], arr[maxIndex]) > 0)
				maxIndex = i;
		}

		return arr[maxIndex];
	}

}

//***************************************cmpPerimeter(Comparator<Rectangle>)**********************************
class cmpPerimeter implements Comparator<Rectangle> 
{
	// Will return a neg int, 0, or pos int as the 1st argument is less than, equal to, or greater than the 2nd.
	public int compare(Rectangle rec1, Rectangle rec2)
	{
		return ((rec1.getLength() + rec1.getWidth() - rec2.getLength() - rec2.getWidth()));
	}
}

//***************************************cmpArea(Comparator<Rectangle>)***************************************
class cmpArea implements Comparator<Rectangle>
{
	// Will return a neg int, 0, or pos int as the 1st argument is less than, equal to, or greater than the 2nd.
	public int compare(Rectangle rec1, Rectangle rec2)
	{
		return ((rec1.getLength() * rec1.getWidth() - rec2.getLength() * rec2.getWidth()));
	}
}
