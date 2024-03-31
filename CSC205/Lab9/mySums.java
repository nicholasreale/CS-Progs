public class mySums{
	public static void main(String [] Args){
		double iterativeSumation = iterativeSum(4);
		System.out.println(iterativeSumation);
		double recurSumation = recurSum(4);
		System.out.println(recurSumation);
	}
	public static double iterativeSum(int n){
		double output = 0;
		for(int i = 1; i <= n; i++){
			output += (Math.pow(2, i) + 1); 
		}
		return output;
	}
	public static double recurSum(int n){
		if (n <= 1){
			return 3;
		}
		else{
			return (Math.pow(2, n) + 1) + recurSum(n-1);
		}
	}
}
