//this a generic stack and the type can be replaced at the compile time, errors are be detected at compile time rather than 
//run time, 
//implement the stack class using ArrayList 
import java.util.ArrayList;
public class GenericStack implements Stack
{
	
  //instance variables
	ArrayList <String> activestack = new ArrayList<String>();//the stack that will have all the variables
	public GenericStack()
	{//constructor
		activestack.add("#");//shove this to the beginning of the stack(the bottom)
		
	}
	public int size()
	{//show the size of the stack
		if (isEmpty())
		{//if there is nothing in the stack
			return 0;//will be zero since we always add #
		}
		return  activestack.size();//return size of the stack
	}
	public Object  peek()
	{//let's look at the stack
		//System.out.println(activestack.get(activestack.size() - 1));//print the LAST element of the stack(top)debug
		return activestack.get(activestack.size() - 1);//return said element
	}
	public Object  pop()
	{//pop an object off the stack, do this in assembly too
		if (isEmpty())
		{//if there is nothing in the stack
			return "#";//return the bottom of the stack, empty
		}
		String popped = activestack.get(activestack.size() - 1);//the thing to be returned
		activestack.remove(activestack.size() - 1);//remove the top element of the stack
		return popped;//return the top of the stack
	}
	public void  push(Object o)
	{//push an object into the stack
		 activestack.add((String) o);//add this element to the INSTANCE of the arraylist
		 
		 //TODO we will have to pop some elements to properly organize the postfix
	}
	public boolean isEmpty()
	{//if the queue is empty
		if (activestack.size() == 1)
		{//if the active stack has nothing inside it (index is 0 (#) to show this)
			return true;
		}
		return  false;
	}
	 
	 		
}				
	