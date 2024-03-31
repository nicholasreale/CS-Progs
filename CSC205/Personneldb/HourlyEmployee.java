import java.io.*;
import java.text.NumberFormat;
public class HourlyEmployee extends Employee implements Serializable{
	public HourlyEmployee (String name, double hourlyWage){
		super(name, hourlyWage);
	}
	public double computePay(double hours){
		double pay = 0;
		if (hours > 40){
			pay = getWage()*40 + ((getWage()*1.5)*(hours-40));
		}
		else {
			pay = getWage()* hours;
		}
		return pay;
	}
	public String toString(){
		NumberFormat MoneyFormat = NumberFormat.getCurrencyInstance();
		String output = getName() + "\t\t" + MoneyFormat.format(getWage()) + "/hourly";
		return output;	
	}
}
