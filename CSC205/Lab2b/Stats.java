import java.util.*;
public class Stats
{
	private static int numItems= -1;
	public static void main(String[] args)
	{
			//prompting the user for the length of the list
			Scanner in = new Scanner(System.in);
        	//filling the list with the fillUp method
			int[] List= Arrays.copyOf(fillUp(),numItems);
        	// out put of our 2 methods
			System.out.println("\n\10\7" + " The range of your " +
                                   List.length + " items is :  " + 
				   range(List));

        	System.out.println("\n\10\7" + " The mean of your " + 
                                   List.length + " items is  :  " +
				   mean(List));
        	in.close();
	}
	static int[] fillUp(){
		//filing out an array and sending it as an output
		Scanner in2 = new Scanner(System.in);
		int[] FillTarget = new int[0];
		while(true) {
			FillTarget = Arrays.copyOf(FillTarget, FillTarget.length+1);
			numItems++;
			System.out.println("Enter Int for index " + numItems);
			int num = in2.nextInt();
			if (num == 0)
				break;
			FillTarget[numItems]= num;
		}
		in2.close();
		return FillTarget;
	}
	static int range(int[] RangeTarget){
		//sending the value of the range as an int as the method output
		int low = 100;
		int high = 0;
		for(int i = 0; i < RangeTarget.length ;i++){
			if (RangeTarget[i]<low){
				low = RangeTarget[i];
			}
			if (RangeTarget[i]>high) {
				high = RangeTarget[i];
			}
		}
		int range = high-low;
		return range;
	}
	static double mean(int[]  MeanTarget){
		//sending the mean as an double as the method output
		double sum = 0;
		double mean;
		for (int i=0; i< MeanTarget.length;i++){
			sum+= MeanTarget[i];	
		}
		mean = sum / MeanTarget.length;
		return mean;
	}
}
