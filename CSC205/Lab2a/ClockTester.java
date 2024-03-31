import java.util.*;
public class ClockTester{
	public static void main(String args[]){
		int sec, min, hor;
		Scanner in = new Scanner(System.in);
		System.out.println("Seconds:");
		sec = in.nextInt();
		System.out.println("Minutes:");
		min = in.nextInt();
		System.out.println("Hours:");
		hor = in.nextInt();
		Clock clk = new Clock(hor,min,sec);
		System.out.println("The time before advance is:" + clk.toString());
		clk.advance();
		System.out.println("The time after advance is:" + clk.toString());
		in.close();
	}
}
