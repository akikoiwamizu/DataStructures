/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #2
 * 
 * Tester2.java
 */

import java.util.Scanner;

public class Tester2
{
	private static String postfix,playAgain;
	private static boolean again;

	public static void main(String[] args) 
	{
		postfix = "";
		again = true;

		try
		{
			Scanner input = new Scanner(System.in);
			while(again)
			{
				System.out.println("Enter a valid post-fix expression: ");
				postfix = input.nextLine();
				Builder expTree = new Builder();
				ExpressionTree expression = expTree.makeTree(postfix);

				System.out.println("The equivalent prefix expression: " + expression.prefix());
				System.out.println("The equivalent infix expression: " + expression.infix());
				System.out.println("The expression you entered equals: " + expression.evaluate());
				System.out.println("Would you like to evaluate another post-fix expression? (y/n): ");
				
				playAgain = input.nextLine();
				if(playAgain.equals("y"))
					again = true;
				else 
					again = false;	
			}
			input.close();
		}catch (Exception IOException)
		{
			System.out.println("Invalid input.");
		}
	}
}
