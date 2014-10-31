/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #2
 * 
 * Builder.java
 */

import java.util.StringTokenizer;

public class Builder
{
	private MyStack<ExpressionTree> stack;

	public Builder()
	{
		stack = new MyStack<ExpressionTree>();
	}

	private boolean isAnOperator(String token)
	{
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
			return(true);
		else
			return(false);
	}	

	public ExpressionTree makeTree(String expression)
	{
		StringTokenizer tokenizer = new StringTokenizer(expression);
		Object token;
		ExpressionTree operand1, operand2;


			while(tokenizer.hasMoreTokens())
			{
				token = tokenizer.nextElement();

				if(isAnOperator(token.toString()))
				{
					operand2 = stack.pop();
					operand1 = stack.pop();
					stack.push(new ExpressionTree(token, operand1, operand2));
				}
				else 
					stack.push(new ExpressionTree(token, null, null));

			}
		
		if(stack.size() > 1)
		{
			System.out.println("Expression has too many operands.");
			return(null);
		}
		return(stack.pop());// pops the resulting expression tree off of the stack
	}
}
