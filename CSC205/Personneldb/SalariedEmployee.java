import java.io.*;
import java.text.NumberFormat;
public class SalariedEmployee extends Employee implements Serializable{
	double salary;
	public SalariedEmployee(String name, double salary){
		super(name, 0);
		double hourlyWage = (salary / 40)/52;
		setWage(hourlyWage);
		this.salary = salary;
	}
	public double computePay(double usless){
		return getWage()*40;
	}
	public void setSalry(double salary){
		this.salary = salary;
	}
	public double getSalary(){
		return salary;
	}
	public String toString(){
		NumberFormat MoneyFormat = NumberFormat.getCurrencyInstance();
		String output = getName() +"\t\t" +MoneyFormat.format((getWage()*40*52)) + "/year";
		return output;
	}
}
