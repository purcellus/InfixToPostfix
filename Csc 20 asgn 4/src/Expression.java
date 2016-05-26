import java.util.*;
	//tokenizing basically means tear the string word by word
public class Expression
{
	public static void main(String[] args)
	{
		
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);//user input
		@SuppressWarnings("unused")
		StringTokenizer st = new StringTokenizer("");//no value until user inputs stuff
		@SuppressWarnings("unused")
		GenericStack gs = new GenericStack();//this is a stack class, kindly given to us.
		System.out.println("What is the math? ");//says what user input should be
		String equation = kb.nextLine();
		st = new StringTokenizer(equation);//put equation in tokenizer
		
		try
		{//see if the math is wrong
			//gs.push("#");//shove this element, the dummy value, first
			//while (st.hasMoreTokens())
			//{//while the phrase has more tokens
				//System.out.println(st.nextToken());//print out the next token
				//TODO modify push so it pushes via infix to postfix procedure
				//gs.push(st.nextToken());//push the next token into the stack.
				
			//}
			//gs.peek();//look at the stack (array list)
			String fixed = postFix(equation);
			//System.out.println(fixed);//shove equation phrase into 
			//look at the modified postfix stack(array list)
			System.out.println("This evaluates to " + postfixEvaluate(fixed));//now to get a number from all this
			
			
			
			
		}
		catch (Exception e)
		{//you done goofed on that math
			System.err.println("Mother of God.  Major error.");
		}
   //your code
   
		 		
	}	
		
		 			
			
 	public static String postFix(String s)
	{//this will translate the infix equation into postfix
   
 		StringTokenizer st = new StringTokenizer(s);//we will modify this piece by piece
 		GenericStack gs = new GenericStack();//make stack
 		String postfix = "";//this is all of the numbers/operators that come from st
 		//Scanner kb = new Scanner(System.in);
 		try
 		{
 			while (st.hasMoreTokens())
 			{//while there are still pieces
 				String curtoken = st.nextToken();//to prevent consuming the next token
 				//putting st.nextToken() in loops consumes tokens
 				//System.out.println("in loop now with " + curtoken); //debug
 				
 				
 				if (curtoken.equals("("))
 				{//if token is the beginning (
 					//System.out.println("pushing beginning (");//TODO debug
 					gs.push(curtoken);//unconditionally push the beginning (
 					
 				} else if (curtoken.equals(")"))
 				{//if token is ending ) TODO something here consumes an extra token, which is no good
 					
 					String phrase = "";//everything from right to left parentheses
 					String checkleftp = "";//checking for one operator
 					checkleftp = (String)gs.pop();//pop the first element out
 					//System.out.println("popped " + checkleftp);
 					while (!(checkleftp.equals("(")))
 					{//while we haven't reached the beginning ( use EQUALS
 						//System.out.println(gs.peek());
 						if (checkleftp.equals("#"))
 						{
 							throw new Exception();
 						}
 						//System.out.println("popped " + checkleftp);//TODO debug
 						phrase += " " + checkleftp;//put the values into this to be put into postfix
 						//kb.next();
 						checkleftp = (String)gs.pop();//pop the stack and place as this string
 					}
 					//to pop off the "(" without putting it into the operation
 					postfix += phrase;//add the popped operators to the equation with numbers
 					System.out.println(postfix);
 					
 				} else if (curtoken.equals("+") || curtoken.equals("-"))
 				{//if the token is a third tier operator (+ or -)
 					
 					//System.out.println("+ or - was found");//debug
 					Object peeked = gs.peek();//peek the top element for comparison
 					if (!(peeked.equals("#") || peeked.equals( "(")))
 					{//if the top element is a higher precedence(higher number), use .equals instead of == (string)
 						//System.out.println("time to pop");
 						String popped = (String)gs.peek();//peek instead of pop to prevent losing (
 						//System.out.println("popped: "+ popped);//debug
 						String phrase = "";//phrase to be added to postfix
 						
 						
 						while (!(popped.equals("#") || popped.equals("(")))
 						{//while a lower operator hasn't been found(# for bottom of stack)
 							//System.out.println("popped: " + popped);//debug
 							gs.pop();//pop off the term
 							phrase += " " + popped;//put popped value into phrase for postfix
 							popped = (String)gs.peek();//pop the next operator
 							//System.out.println(gs.peek());
 						}
 						postfix += phrase;//pop the * or /
 						gs.push(curtoken);//push the current + or - into stack
 					} else
 					{//if there is a lower precedence, pop until it finds a lower precedence(lower #)
 						gs.push(curtoken);//push the token in without worrying about anything

 					}
 				} else if (curtoken.equals("*") || curtoken.equals("/"))
 				{//if the token is a last tier operator
 					//System.out.println("* or / was found");
 					String popped = (String)gs.peek();
 					String phrase = "";
 					
 					while (popped.equals("*") || popped.equals("/"))
 					{//while a lower or equal operator hasn't been found
 						System.out.println("popped: " + popped);//debug
							gs.pop();//pop off the term
							phrase += " " + popped;//put popped value into phrase for postfix
							popped = (String)gs.peek();//pop the next operator
							//System.out.println(gs.peek());
 					}
 					postfix += phrase;
 					gs.push(curtoken);//push multiplier or divider in stack
 					
 					
 					
 				} else if (Integer.valueOf(curtoken) instanceof Integer)
 				{//TODOhow to not consume next token?  if the token equals a number have to do at end, otherwise treats + as a number
 					//System.out.println("number was found");//DEBUG
 					//String peeked = (String)gs.peek();//to see what was before this number
 					//System.out.println(peeked);//debug
 					/*if (Integer.valueOf(peeked) instanceof Integer)TODO might not need, int next to int is technically postfix
 					{//if there was a number before this number
 						Exception numtonum = new Exception();
 						throw numtonum;
 					}*/
 					
 					postfix = postfix + " " + curtoken;//shove the next token to postfix
 				} else
 				{
 					throw new Exception();
 				}
 				//System.out.println("Current postfix equation:");
 				//System.out.println(postfix);//see the equation
 				//System.out.println("current top of stack:");
 				//if (!(gs.peek().equals("#")))
 				//{
 					//System.out.println(gs.peek());//see the top of the stack
 				//}

 			}
 			
 			while (!(gs.peek().equals("#")))
 			{//while there are other terms still in stack
 				postfix += " " + gs.pop();
 				System.out.println("in final loop " + postfix);
 			}
 			System.out.println("finished postfix:");//show the postfix
 			System.out.println(postfix);
 		}
 		catch (Exception e)
 		{//TODO catch certain, specific exceptions
 			/* types of exceptions:
 			 * 1.two numbers next to each other
 			 * 2.two operators next to each other
 			 * 3.no closing parenthesis ("(" is ok)
 			 * 4.illegal argument("a", double, etc.)
 			 * 
 			 */
 			System.err.println("Something went wrong when converting.");
 		}
 		
 		
     //your code
     return postfix;//return postfixed equation
    }
   
   
   
   
	public static double postfixEvaluate(String s)
	{
      //your code
		StringTokenizer st = new StringTokenizer(s);//the postfix equation to be evaluated
		GenericStack gs = new GenericStack();//stack to do stuff with
		Double result = (double)0;//the result of the equation, treat this as a class so it can be put as a string
		try {
			while (st.hasMoreTokens())
			{//while there are more tokens to consume
				String curtoken = st.nextToken();//again to prevent consuming this thing.
				System.out.println(curtoken);//debug
				if (curtoken.equals("*"))
				{//multiply by taking two things out of the stack
					double num1 = Double.valueOf((String)gs.pop());//pop the first number
					double num2 = Double.valueOf((String)gs.pop());//pop the secont number
					result = (double)num1 * num2;//we won't add to result, as part of result will be pushed after equation is done,
					//so we don't have to add, as it is redundant.
					gs.push(result.toString());//push the result into the stack for future evaluation, must make to string so
					//array can shove it in
				} else if (curtoken.equals("/"))
				{//divide
					double num1 = Double.valueOf((String)gs.pop());//pop the first number
					double num2 = Double.valueOf((String)gs.pop());//pop the secont number
					result = (double)num2 / num1;//type cast cause faroughi said so
					gs.push(result.toString());
				} else if (curtoken.equals("+"))
				{//add
					double num1 = Double.valueOf((String)gs.pop());//pop the first number
					double num2 = Double.valueOf((String)gs.pop());//pop the secont number
					result = (double) (num1 + num2);
					gs.push(result.toString());
				} else if (curtoken.equals("-"))
				{//subtract
					double num1 = Double.valueOf((String)gs.pop());//pop the first number
					double num2 = Double.valueOf((String)gs.pop());//pop the secont number
					result = (double)num2 - num1;//minus 2 from 1
					gs.push(result.toString());//TODO issue pushing double into string
				} else
				{//we assume it is a number, as it is to postfix, so there can't be any strings based on previous work
					gs.push(curtoken);//push the next value into there
				}
				
				

			}
			
			result = (double)Double.valueOf((String)gs.pop());//should just be one number
		}
			catch (Exception m)
		{
			System.err.println("Something bad happened when evaluating the equation.");
		}

		return result;	 			 
			
	}		
				
			
}	
						
 
 
		