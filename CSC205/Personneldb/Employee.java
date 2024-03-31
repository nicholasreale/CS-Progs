import java.io.*;
public abstract class Employee implements Serializable{
private String name= "";
private double wage = 0;
	public Employee(){
		name = "N/A";
		wage = -1;
	}
	public Employee(String empName, double hwage){
		name = empName;
		wage = hwage; 
	}
	public void setName(String newName){
		name = newName;
	}
	public void setWage(double newWage){
		wage = newWage;
	}
	public String getName(){
		return name;
	}
	public double getWage(){
		return wage;
	}
	public abstract double computePay(double hoursWorked);
}
