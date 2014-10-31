/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #1
 * PROGRAMMING PROBLEM #3
 * 
 * Problem3Tester.java
 */

import java.io.FileNotFoundException;

public class Problem3Tester{
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String fileName = args[0]; // this is the object of the file specified on the command line 
		new Problem3(fileName);
	}
}