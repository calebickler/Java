//Cale Bickler
//11/22/2012
//cs258 lab6
//Calculator.java

import java.util.*;

public class Calculator 
{
	private static Stack operator;
	private static Stack operand;
	
	public static void main (String args[])
	{
		boolean end = false;
		while (!end)
		{
			System.out.println("Enter your equation: ");
			Scanner scan = new Scanner(System.in);
			String in = scan.nextLine();
			StringTokenizer st = new StringTokenizer(in, "+-*/()\t ", true);
			operator = new Stack(in.length());
			// 1 = +, 2 = -, 3 = *, 4 = /, 5 = (, 6 = )
			operand = new Stack(in.length());
			boolean signOK = true;
			try
			{
			while(st.hasMoreTokens())
			{
				String token = st.nextToken();
				while (token.equals(" "))
				{
					token = st.nextToken();
				}
				switch (token)
				{
					case "(":
						operator.push(5);
						signOK = true;
						break;
					case ")":
						while (operator.peek()!=5)
						{
							if (operator.isEmpty())
							{
								 new StackException();
							}
							eval();
						}
						operator.pop();
						signOK = true;
						break;
					case "+":
						if (!operator.isEmpty())
						{
							while (operator.peek()== 1 || operator.peek()== 2 || operator.peek()== 3 || operator.peek()== 4 )
							{
								eval();
								if (operator.isEmpty()) {break;}
							}
						}
						operator.push(1);
						signOK = true;
						break;
					case "-":
						
						if (signOK)
						{
							token = st.nextToken();
							operand.push((Double.parseDouble(token))*(-1));
							signOK = false;
						}
						else
						{
							if (!operator.isEmpty())
							{
								while (operator.peek()== 1 || operator.peek()== 2 || operator.peek()== 3 || operator.peek()== 4 )
								{
									eval();
									if (operator.isEmpty()) {break;}
								}
							}
							operator.push(2);
							signOK = true;
						}
						break;
					case "*":
						if (!operator.isEmpty())
						{
							while ( operator.peek()== 3 || operator.peek()== 4 )
							{
								eval();
								if (operator.isEmpty()) {break;}
							}
						}
						operator.push(3);
						signOK = true;
						break;
					case "/":
						if (!operator.isEmpty())
						{
							while ( operator.peek()== 3 || operator.peek()== 4 )
							{
								eval();
								if (operator.isEmpty()) {break;}
							}
						}
						
						operator.push(4);
						signOK = true;
						break;
					default:
						operand.push(Double.parseDouble(token));
						signOK = false;
						break;
						
				}
			}
			while (!operator.isEmpty())
			{
				eval();
			}
			System.out.println(operand.pop() + " is the result");
			}
			catch (StackException e) 
			{
				System.out.println(e);
			}
			catch (NumberFormatException n)
			{
				System.out.println("Input error: please enter numbers");
			}
			
			System.out.println("Calculate another equation? (y/n): ");
			if (scan.nextLine().equals("n")) {end = true;}
		}
	}
	
	private static void eval()
	{
		try {
		double a = operand.pop();
		double b = operand.pop();
		switch ((int) operator.pop())
		{
			case 1: operand.push((b+a));
				break;
			case 2: operand.push((b-a));
				break;
			case 3: operand.push((b*a));
				break;
			case 4: operand.push((b/a));
				break;
		}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

}
