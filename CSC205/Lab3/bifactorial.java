import java.math.BigInteger;
public class bifactorial 
{
   public static void main (String [] args)
   {
      BigInteger value;
      //  Check that there is a command line parameter
      if (args.length == 1)
      {
         //  Get the corresponding integer or use 7 if the string is badly
	 //  formatted

	 try 
	 {
	    //  Convert the first parameter to an integer
	    value = BigInteger.valueOf (Integer.parseInt(args[0]));
	 }
	 catch (Exception e)
	 {
	    //  If anything goes wrong, use 7
	    System.err.println ("The integer wasn't recognized, using 7");
	    value = BigInteger.valueOf(7);
	 }
      }
      else  //  There's not one parameter
      {
         System.err.println ("You need one command line parameter, using 7.");
	 value = BigInteger.valueOf(7);
      }

      BigInteger result = fact (value);
      System.out.println (value + "! = " + result);
   }


   private static BigInteger fact (BigInteger n)
   {
      BigInteger product = BigInteger.ONE;
      for (BigInteger i = 2; i <= n; i++)
      {
         product = product.multiply(BigInteger.valueOf(i));
      }
      return product;
   }
}
