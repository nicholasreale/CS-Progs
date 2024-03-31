
public class CheckTest {

	public static void main(String[] args){
		CheckingAcct acct = new CheckingAcct(500.0);
		System.out.println("beg balance = $" + acct.getBalance());
		System.out.println("");
		System.out.println("Now writing a check!");
		acct.writeCheck(127.99);
		System.out.println("Total Checks = " + acct.getChecksWritten());
		System.out.println("balance = $" + acct.getBalance());
		System.out.println("");
		System.out.println("Now Closing the Acct");
		acct.close();
	}
}
