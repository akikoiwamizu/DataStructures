/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #1
 * 
 * Palindrome.java
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Palindrome
{
	private LinkedList<String> palindromeList;

	public Palindrome(String fileName)
	{
		palindromeList = new LinkedList<String>();
		isPalindrome(readFile(palindromeList, fileName));
	}

	private static LinkedList<String> readFile(LinkedList<String> palindromeList, String fileName)
	{
		File file = new File(fileName);

		try{
			Scanner input = new Scanner(file);
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				palindromeList.add(line);
			}
		}catch(FileNotFoundException e){
			System.out.println("File: " + fileName + " does not exist. Program terminated.");
		}	
		
		return palindromeList;
	}

	// Uses a queue to compare the palindrome stack
	private void isPalindrome(LinkedList<String> palindromeList)
	{
		Queue<Character> reverseLineStack = new LinkedList<Character>();
		MyStack<Character> lineStack = new MyStack<Character>();
		String topString = "";
		int differences = 0;
		int palindromes = 0;
		int nonPalindromes = 0;
		char reverseLetter;
		
		while(palindromeList.size() != 0)
		{
			topString = palindromeList.remove();
			for(int i = 0; i < topString.length(); i++)
			{
				reverseLetter = topString.charAt(i);
				if(Character.isLetter(reverseLetter))
				{
					reverseLineStack.add(reverseLetter);
					lineStack.push(reverseLetter);
				}
			}
			
			while(!lineStack.isEmpty())
			{
				if(Character.toLowerCase(lineStack.pop()) != Character.toLowerCase(reverseLineStack.remove()))
					differences++;
			}
			
			if(differences == 0)
			{
				System.out.println("\"" + topString + "\" is a palindrome.");
				palindromes++;
			}
			else
			{
				System.out.println("\"" + topString + "\" is not a palindrome.");
				nonPalindromes++;
			}
			differences = 0;
		}
		System.out.println("Number of Palindromes: " + palindromes);
		System.out.println("Number of Non-Palindromes: " + nonPalindromes);
	} 
}