/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #1 & #2
 * 
 * MyStack.java
 */

import java.util.EmptyStackException;
import java.util.ArrayList;

public class MyStack<E> 
{
	private ArrayList<E> stack;

	public MyStack()
	{
		stack = new ArrayList<E>();
	}

	public Integer size()
	{
		return(stack.size());
	}
	
	public void push(E line)
	{
		stack.add(0, line);
	}

	public void pushChar(E c)
	{
		stack.add(0, c);
	}
	
	public E pop() throws EmptyStackException
	{
		if(isEmpty())
		{
			System.out.println("No elements in this stack.");
			throw new EmptyStackException();
		}
		else
			return(stack.remove(0));		
	}


	public E peek() throws EmptyStackException
	{
		if(isEmpty())
		{
			System.out.println("No elements in this stack");
			throw new EmptyStackException();
		}	
		else
			return(stack.get(0));		
	}

	public boolean isEmpty()
	{
		return(stack.size() == 0);
	}

	public String toString()
	{
		System.out.println(stack.toString());
		return("My Stack: " + stack.toString());
	}
	
}
