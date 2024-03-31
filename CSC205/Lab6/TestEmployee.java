public class TestEmployee{
	public static void main(String args[]){
		Date bDay = new Date(1980, 3, 8);
		Date hDay = new Date();
		Employee morBrown = new Employee("Brown", "Morris", bDay, 40000, hDay);
		System.out.println(morBrown);
	}


}
