import java.util.*;
import java.text.*;
import java.io.*;
public class PersonnelDatabase{
	public static void main(String Args[]){
		menu();
	} 
	public static void menu(){
		char select = 'z';
		boolean finished = true;
		ArrayList<Employee> Employees = new ArrayList<Employee>();
		while(finished){
			System.out.println("==================================");
			System.out.println("|Commands: n = New Employee      |");
			System.out.println("|          c = Compute PayChecks |");
			System.out.println("|          r = Raise Wages       |");
			System.out.println("|          p = Print Records     |");
			System.out.println("|          d = Download Data     |");
			System.out.println("|          u = Upload Data       |");
			System.out.println("|          q = quit              |");
			System.out.println("==================================");
			select = scanChar();
			switch(select){
				case'n':
					Employees = addEmployee(Employees);
					System.out.println("Employee Added");
					break;
				case 'c':
					computePay(Employees);
					break;
				case 'r':
					Employees = raiseWages(Employees);
					break;
				case 'p': 
					printRecords(Employees);
					break;
				case 'd':
					downloadData(Employees);
					break;
				case 'u':
					Employees = uploadData();
					break;
				case 'q':
					finished = false;
					break;
				default:
					System.out.println("Invalid Input Please Try Again");
					break;
			}
		}

	}
	public static ArrayList<Employee> addEmployee(ArrayList<Employee> Employee){
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter the Name of the New Employee: ");
		String name = in.nextLine();
		char eType;
		double hPay;
		double sPay;
		boolean eMade = true;
		while(eMade){
			System.out.print("\nHourly (h) or Salaried (s): ");
			eType = scanChar();
			if (eType == 'h'){
				System.out.print("\nPlease Enter the Hourly Pay: ");
				while(true){
					hPay = scanDouble();
					if(hPay > 0) 
						break;
					else
						System.out.println("Input Not Understood Please Try Again");
				}
				Employee.add(new HourlyEmployee(name,hPay));
				eMade = false;
			}
			else if (eType == 's'){
				System.out.print("\nPlease Enter Yearly Salary: ");
				while(true){
					sPay = scanDouble();
					if(sPay > 0)
						break;
					else
						System.out.println("Input Not Understood Please Try Again");	
				}
				Employee.add(new SalariedEmployee(name,sPay));
				eMade = false;
			}
			else{
				System.out.println("Input Not Understood Please Try Again");
			}
		}
		return Employee;
	}
	public static ArrayList<Employee> raiseWages(ArrayList<Employee> adjWage){
		System.out.println("What Percentage Would You Like To Increease pay by: ");
		double payIncreese;
		while(true){
			payIncreese = scanDouble();
			if(payIncreese>0)
				break;
			else
				System.out.println("Input not Understood please try again");
		}
		
		System.out.println("Adjusted Wages");
		System.out.println("--------------");
		double newWage,oldWage;
		for(int i = 0; i < adjWage.size(); i++){
			oldWage = adjWage.get(i).getWage();		
			newWage = (oldWage*(payIncreese/100)) + oldWage;
			adjWage.get(i).setWage(newWage);
			System.out.println(adjWage.get(i));
		}
		return adjWage;  	
	}
	public static void computePay(ArrayList<Employee> weeklyPay){
		double hours;
		NumberFormat MoneyFormat = NumberFormat.getCurrencyInstance();
		for(int i = 0; i< weeklyPay.size(); i++){
			System.out.println("Enter number of hours worked by " + weeklyPay.get(i).getName());
			while(true){
				hours = scanDouble();
				if (hours > 0)
					break;
				else
					System.out.println("Input Not Understood Try Again");
			}
			System.out.println("Pay: " + MoneyFormat.format(weeklyPay.get(i).computePay(hours)));	
		}
	}
	public static void printRecords(ArrayList<Employee> Record){
		System.out.println("Current Wages");
		System.out.println("--------------");
		for(int i = 0; i < Record.size(); i++){
			System.out.println(Record.get(i));
		}
	}
	public static void downloadData(ArrayList<Employee> mkFile){
		String fileName = "employee.dat";
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(mkFile);
			out.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static ArrayList<Employee> uploadData(){
		String fileName = "employee.dat";
		ArrayList<Employee> mkList = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			mkList = (ArrayList<Employee>) in.readObject();
			in.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		return mkList;
	} 
	public static int scanInt(){
		Scanner cmdLn = new Scanner(System.in);
		String input = cmdLn.nextLine();
		Scanner string = new Scanner(input);
		if (string.hasNextInt()){
			return string.nextInt();
		}
		else {
			return -1;
		}
	}
	public static double scanDouble(){
		Scanner cmdLn = new Scanner(System.in);
		String input = cmdLn.nextLine();
		Scanner string = new Scanner(input);
		if(string.hasNextDouble()){
			return string.nextDouble();
		}
		else{
			return -1;
		}
	}
	public static char scanChar(){
		Scanner	cmdLn = new Scanner(System.in);
		String input = cmdLn.nextLine();
		Scanner string = new Scanner(input);
		if(string.hasNext() == true){ 
			String next = string.next();
			if(next.contains("n")){
				return 'n';
			}
			else if(next.contains("c")){
				return 'c';
			}
			else if(next.contains("r")){
				return 'r';
			}
			else if(next.contains("p")){
				return 'p';
			}
			else if (next.contains("d")){
				return 'd';
			}
			else if (next.contains("u")){
				return 'u';
			}
			else if (next.contains("q")){
				return 'q';
			}
			else if( next.contains("h")){
				return 'h';
			}
			else if(next.contains("s")){
				return 's';
			}
			else{
				return 'z';
			}
		}
		else{
			return 'z';
		}
	}
}
