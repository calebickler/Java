//Cale Bickler
//11/22/2012
//cs258 lab6
//Stack.java

public class Stack
{
	private int top;
	private double[] stackData;
	
	public Stack(int size)
	{
		stackData = new double[size];
		top = -1;
	}
	public void push(double value) throws StackException
	{
		if(!isFull())
		{
			stackData[++top] = value;
		}
		else
		{
			throw new StackException();
		}
	}
	public double pop () throws StackException
	{
		if (!isEmpty())
		{
			return stackData[top--];
		}
		else
		{
			throw new StackException();
		}
	}
	public double peek() throws StackException
	{
		if(!isEmpty())
		{
			return stackData[top];
		}
		else
		{
			throw new StackException();
		}
	}
	public boolean isEmpty()
	{
		return (top==-1);
	}
	public boolean isFull()
	{
		return (top==stackData.length-1);
	}
	public String toString()
	{
		String s = "";
		for (int i = 0; i<stackData.length; i++)
		{
			s += stackData[i];
		}
		return s;
	}
}