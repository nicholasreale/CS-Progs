public class Employee extends Person{
	private double salary;
	private Date empDate;
	public Employee(String lName, String fName, Date birthDate, double salary, Date empDate){
		super(lName, fName, birthDate);
		this.salary = salary;
		this.empDate = empDate;
	}
	public double getSalary() {
		return salary;
	}
	public Date getEmploymentDate(){
		return empDate;
	}
	public String toString(){
		String output ="";
		output = "Name = " + getLastName() + "," + getFirstName() + "\nSalary = " + salary + "\nBirth = " + getBirthDate() + "\nHired = " + empDate;
		return output;
	}
	
}
