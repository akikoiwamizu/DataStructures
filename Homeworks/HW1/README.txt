/*
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #1
 */

IDE: Eclipse Kepler, using Java 7
_____________________________________________________________________
 
 PROBLEM 1:
 
 Rectangle.java
 - Contains the methods getLength() and getWidth(), which return
	the values of length and width for the Rectangles that were 
	hard-coded in main.
 - The toString method is used to return the Rectangle object
	itself, instead of its' location in memory.
 
 Problem1.java
 - This is the file that should be compiled.
 - Contains the methods main and findMax.
 - The findMax method was taken from the book, and I changed it from
	a generic to type Rectangle b/c it was easier for me to read.
 - This file also contains the two Comparator classes: cmpPerimeter
	and cmpArea.
 - Both Comparator classes will either return a negative integer, 0, 
	or positive integer as the 1st argument is less than, 
	equal to, or greater than the 2nd.
 - If the return value is greater than 0, then findMax will return
	the Rectangle with the largest Perimeter or Area.
 - The formulas for Perimeter and Area were used in both of the 
	return statements to compare the hard-coded Rectangles.
_____________________________________________________________________
 
 PROBLEM 3: 
 - I used the array.txt file example from the course website
	to test my program.
	
 Problem3.java
 - In the constructor, a try, catch block is used to check
	for valid user input.
 - The readArray method takes in the file the user specifies on the
	command line and returns an array of type Integer. 
	- I used an ArrayList to store the integers that were read in
		line by line from the file, which is why the array is
		of type Integer and not the primitive type int.
	- This made it easier to store the elements without a 
		predetermined size, so that if there are numerous lines, 
		then the ArrayList will increase in size accordingly.
	- After the program reaches the end of the file, the program
		sets the size of the ArrayList to the size of the array.
	- I then converted the ArrayList into an array, which was 
		required for the binarySearch recursive method.
 - The binarySearch method returns the index at which the desired
	element is located. 
	- I used most of the code from what we used in class except that
		I made this recursive by using the same method inside of the	
		return statements.
	- The else if statement calls the binarySearch method, but only 
		uses the first half of the array.
	- Likewise, the else statement calls the binarySearch method, but
		only uses the latter half of the array.
	- These statements will continue running until either the index
		of the element is found or not found.

 Problem3Tester.java
 - This is the file that should be compiled.
 - Takes the user's first argument on the command line and checks
	if the file is in the directory. If it isn't in the directory,
	then an exception is thrown.

	
 
 
 
 
 
 
 
 
 