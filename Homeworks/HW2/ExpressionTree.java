/*
 * 
 * UNI: AMI2119
 * COURSE: Data Structures in Java, W3134
 * PROFESSOR: Paul Blaer
 * HOMEWORK #2
 * PROGRAMMING PROBLEM #2
 * 
 * ExpressionTree.java
 */

import java.util.ArrayList;

public class ExpressionTree
{
	Object element; // data in the node
	ExpressionTree rightNode; // right child
	ExpressionTree leftNode; // left child

	ExpressionTree(Object elt, ExpressionTree rt, ExpressionTree lt)
	{
		element = elt;
		rightNode = rt;
		leftNode = lt;
	}
	
	public double evaluate()
	{
		double solution = 0;
		Double operand1, operand2;
			
		if(rightNode != null && leftNode != null)
		{
			operand1 = leftNode.evaluate();
			operand2 = rightNode.evaluate();
			
			Double op1 = operand1.doubleValue();
			Double op2 = operand2.doubleValue();			
			
			
			if(element.equals("+"))
				solution = op1 + op2;
			else if(element.equals("-"))
				solution = op1 - op2;
			else if(element.equals("*"))
				solution = op1 * op2;
			else
				solution = op1 / op2;
			return solution;
		}
		else
		{
			double newElement = Double.parseDouble(element.toString());
			return newElement;
		}
		
	}
	
	public Object getElement()
	{
		return element;
	}

	public ExpressionTree getRightNode()
	{
		return rightNode;
	}

	public ExpressionTree getLeftNode()
	{
		return leftNode;
	}
	 

	public String infix() //makes the converted infix expression tree into a printable string
	{
		ArrayList<Object> infix = new ArrayList<Object>();
		convertToInfix(infix);
		int numOfElements = infix.size();
		StringBuilder makeInfix = new StringBuilder(numOfElements);

		for(int i = 0; i < numOfElements; i++)
			makeInfix.append(infix.get(i));

		return(makeInfix.toString());
	}

	private ArrayList<Object> convertToInfix(ArrayList<Object> expression)
	{
		if(element != null)
		{
			if(rightNode == null || leftNode == null)
				expression.add(element);
			else if(element.equals("-") || element.equals("+"))
			{
				expression.add("(");
				leftNode.convertToInfix(expression);
				expression.add(element);
				rightNode.convertToInfix(expression);
				expression.add(")");
			}
			else if(rightNode != null && leftNode != null)
			{
				leftNode.convertToInfix(expression); // operand 1
				expression.add(element); //the operator
				rightNode.convertToInfix(expression); // operand 2
			}
			else
				;
		}

		return expression;
	}	

	public String prefix()
	{
		ArrayList<Object> prefix = new ArrayList<Object>();
		convertToPrefix(prefix);
		int numOfElements = prefix.size();
		StringBuilder makePrefix = new StringBuilder(numOfElements);

		for(int i = 0; i < numOfElements; i++)
			makePrefix.append(prefix.get(i));

		return(makePrefix.toString());
	}

	private ArrayList<Object> convertToPrefix(ArrayList<Object> expression)
	{
		expression.add(element); // adds the operator to the expression in prefix form
		if(rightNode != null)
			rightNode.convertToPrefix(expression); // recurses over right subtree & adds operand2 to the expression
		if(leftNode != null)
			leftNode.convertToPrefix(expression); // recurses over left subtree & adds operand2 to the expression
		return(expression);
	}



}


