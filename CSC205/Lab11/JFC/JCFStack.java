import java.util.*;

public class JCFStack
{
   public static void main(String[] args) throws CloneNotSupportedException
   {
        	Stack stack1 = new Stack();
		stack1.push(new Integer(27));
		stack1.push(new Integer(0));
		stack1.push(new Integer(-3));
		stack1.push(new Integer(-18));
		stack1.push(new Integer(99));
        	printStack (stack1);
		Scanner in = new Scanner(System.in); 
		System.out.println("Enter Key Value =");
		int key = in.nextInt();
		System.out.print("Key Value at position =");
		System.out.println(stack1.search(key));
  }

  private static void printStack (Stack s) throws CloneNotSupportedException
  {
        Stack tempStack = (Stack) (s.clone());
        if (! tempStack.isEmpty())
                System.out.println("*** Printing Out Stack:  ");

        while (! tempStack.isEmpty())
        {
               System.out.print(tempStack.peek()+ " ");
               tempStack.pop();
        }
	System.out.println();
  }
}
