/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #1
 * PROGRAMMING PROBLEM #3
 * 
 * Problem3.java
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Problem3
{
	
//***************************************Problem3(String)***************************************
	public Problem3(String fileName)
	{
		try
		{
			System.out.println("The value you are looking for: ");
			Scanner in = new Scanner(System.in);
			int elt = in.nextInt(); // the element the user is looking for

			Integer[] array = readArray(fileName); // reads elements from the file into an Integer array
			int start = 0; 
			int stop = array.length - 1 ; 

			if(binarySearch(array, elt, start, stop) == -1) // if the method returns a -1, then elt is not in the file
				System.out.println(elt + " is not found");
			else 
			{
				int elt1 = binarySearch(array, elt, start, stop);
				System.out.println(elt + " is found at index " + binarySearch(array, elt, start, stop));
			}
		}
		catch (InputMismatchException e)
		{
			System.out.println("Invalid input. Integers only.");
		}	
	}
	
//***************************************readArray(String)***************************************
	private Integer[] readArray(String fileName)
	{
		File file = new File(fileName);
		ArrayList<Integer> lines = new ArrayList<Integer>(); 

		// try catch block to check if the file (entered in the command line) exists in the directory
		try { 
			Scanner input = new Scanner(file);
			while(input.hasNext()){ // reads in txt file until there are no lines left
				int line = input.nextInt();
				lines.add(line); // adds each integer from the file into the ArrayList called "lines"
			}
		} catch (FileNotFoundException e) {
			System.out.println("File: " + fileName + " does not exist. Program terminated.");

		}	

		Integer arr[] = new Integer[lines.size()]; // creates an array with the size of # elements in the file
		return (arr = lines.toArray(arr)); // returns an array of type Integer
	}

//***************************************binarySearch(String)***************************************
	private static int binarySearch (Integer[] a, int num, int first, int last) // num is the desired element
	{
		if(first > last) // if the array is empty, then exit the method
			return -1;
		int mid = (first + last)/2; // this truncation works b/c it rounds down for when we are dealing with even #s
		if(num == a[mid])
			return mid;
		else if (num < a[mid])
			return binarySearch(a, num, first, mid - 1); // changes the search to the first half of the array
		else 
			return binarySearch(a, num, mid + 1, last); // changes the search to the latter half of the array
	}
}
