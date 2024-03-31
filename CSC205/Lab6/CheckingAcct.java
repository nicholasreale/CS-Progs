public class CheckingAcct extends Account{
	private int checksWritten;
	public CheckingAcct(double initialBalance){
		super(initialBalance);
		checksWritten = 0;
	}
	public void writeCheck(double amount){
		withdraw(amount);
		checksWritten++;
	}
	public int getChecksWritten(){
		return checksWritten;
	}
}
