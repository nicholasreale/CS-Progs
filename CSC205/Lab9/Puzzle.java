
public class Puzzle
{
	public static void main(String[] args)
        {	
		int puzzle =  puzzle(9);
	}

	private static int puzzle (int n)
	{
		int sol = 0;
		for( int i=1; i <= 4-n; i++)
			System.out.print("\t");
        	if ( (n % 3) == 2 ){
			System.out.println("\t\tpuzzle(" + n + ") = returns 1");
			return sol;
		}
        	else if ( (n % 3) == 1 ){
			System.out.println("puzzle("+ n +") = puzzle(" + (n + 1) + ") + 2");
           		sol = ( puzzle (n + 1) + 2 );
			for( int i=1; i <= 4-n; i++)
                        	System.out.print("\t");
			System.out.println("puzzle(" + n + ") = returns " + sol);
			return sol;
		}
        	else{
			System.out.println("puzzle("+ n +") = puzzle(" + (n/3) +") +2");
           		sol = ( puzzle (n / 3) + 1 );
			for( int i=1; i <= 4-n; i++)
                        	System.out.print("\t");
			System.out.println("puzzle(" + n + ") = returns "+ sol);
			return sol;
		} 
	}
}
