import java.io.*;
import java.util.*;
public class Pez{
	public static void main(String Args[]){
		Stack pez = new Stack();
		addPez(pez);
		System.out.println("The Pez Stack is");
		print(pez);
		removeGreen(pez);
		System.out.println("The Pez Stack After removeGreen is");
		print(pez);
	}
	public static void addPez(Stack tgt){
		tgt.push("yellow");
		tgt.push("red");
		tgt.push("green");
		tgt.push("green");
		tgt.push("yellow");
		tgt.push("yellow");
		tgt.push("red");
		tgt.push("green");
	}
	public static void removeGreen(Stack tgt){
		Stack temp = new Stack();
		while(tgt.isEmpty() == false){
			if (tgt.peek().equals("green"))
				tgt.pop();
			else
				temp.push(tgt.pop());
		}
		while(temp.isEmpty() == false){
			tgt.push(temp.pop());
		}
	}
	public static void print(Stack tgt){
		Stack printTgt = (Stack) tgt.clone();
		while(printTgt.isEmpty() == false){
			System.out.println(printTgt.pop());
		}
	}
}
