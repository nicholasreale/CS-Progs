import java.util.*;
import java.io.*;
public class Library{
	private static int numBooks;
	public static void main(String args[]){
		ArrayList<LibraryBook> lib = new ArrayList(0); 
		menu(lib); 
	}
	public static void filLib(String fileName, ArrayList<LibraryBook> lib){
		try{
			Scanner fileScan = new Scanner(new File(fileName));
			while (fileScan.hasNextLine()){
				Scanner lineScan = new Scanner(fileScan.nextLine()).useDelimiter(";");
				String title = lineScan.next();	
				String author = lineScan.next();
				int cpright = lineScan.nextInt();
				double price = lineScan.nextDouble();
				String genre = lineScan.next();
				LibraryBook chkTarget = new LibraryBook(title, author, cpright, price, genre);	
				if (libSerch(lib, chkTarget) == -1){
					lib.add(chkTarget);
					numBooks++;
				} 
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		//Selection Sort algorithm
		for (int i = 0; i<numBooks;i++){
			int smallestIndex = i;
			for(int j= i + 1; j < numBooks;j++){
				if(lib.get(smallestIndex).compareTo(lib.get(j))>0){
					smallestIndex=j;
				}
			}
			LibraryBook swp = lib.get(i);
			lib.set(i, lib.get(smallestIndex));
			lib.set(smallestIndex, swp); 
		}
		lib.trimToSize();	
	}
	public static int libSerch(ArrayList<LibraryBook> lib, LibraryBook bookSerch){
		int index = -1;
		for(int i = 0; i<lib.size(); i++){
			if(lib.get(i).compareTo(bookSerch) == 0){
				index= i;
			}
		}	
		return index;
	}
	public static int libSerch(ArrayList<LibraryBook> lib, String titleSerch){
		int index= -1;
		for(int i = 0; i<lib.size(); i++){
			if(titleSerch.compareTo(lib.get(i).getTitle())==0){
				index = i;
			}
		}
		return index;	
	}
	public static void menu(ArrayList<LibraryBook> lib){
		Scanner wait = new Scanner(System.in);
		Scanner menu = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		int userChoice = 3;
		boolean end = false;
		while(end == false){
			try{	
				switch(userChoice){
					case 0:
						clearScreen();
						System.out.println("\t\t\tTHE GREAT BOOKS SEARCH PROGRAM");
						System.out.println("\t\t1) Display all book records");
						System.out.println("\t\t2) Serch for a book by Title");
						System.out.println("\t\t3) Add another Directory");
						System.out.println("\t\t4) Exit Serch Program");
						System.out.print("\t\tPlease Enter Your Choice>>");
						userChoice = menu.nextInt();
						break;
					case 1:
						int numPerPage = 4;
						int firIndex = 0;
						while (true){
							clearScreen();
							for(int i = 0; i<numPerPage; i++){
								printRecord(lib, firIndex + i);	
							}
							System.out.println("Press Enter For Next Page");
							wait.nextLine();
							firIndex = firIndex + numPerPage;
							if (firIndex > lib.size()){
								break;
							} 
						}
						userChoice = 0;
						break;
					case 2:
						String SerchTarget;
						int foundIndex;
							clearScreen();
							System.out.println("\t\t\t Enter your book you want to find");
							SerchTarget = in.nextLine();
							foundIndex = libSerch(lib, SerchTarget);
							if (foundIndex >= 0){
								clearScreen();
								printRecord(lib,foundIndex);
								System.out.println("Press Enter To Return To Menu");
							}
							else{
								clearScreen();
								System.out.println("Target not found, press enter to go back to the menu"); 
							}
							wait.nextLine();
							userChoice = 0; 
						break;
					case 3:
						boolean fileFound = false; 
						File curDir = new File(".");
						String[] fileNames = curDir.list();
						ArrayList<String> data = new ArrayList<String>();
				
						for (String s:fileNames)
							if(s.endsWith(".dat"))
								data.add(s);
						while(fileFound != true){
							clearScreen();
							System.out.println("\n\n\n\n\t\tHere are the files in your current directory");
							System.out.print("\t\t");
							for (int i = 0; i<data.size(); i++){
								System.out.print(data.get(i)+" ");
							}
							System.out.println("\nFile Name:");
							String fName = in.nextLine();
							if(data.indexOf(fName) >= 0){
								filLib(fName,lib);
								fileFound = true;	
							}
							else{
								clearScreen();
								System.out.println("File Not Found Please Press Enter To Try Again");
								wait.nextLine();
							}
						}
						clearScreen();
						System.out.println("\t\t\t a total of "+ numBooks + " have been input & sorted\n\t\t Please Hit Enter to Continue....");
						userChoice = 0;
						wait.nextLine();  
						break;
					case 4:
						clearScreen();
						System.out.println("Thank you Press Enter To Close");
						end = true;
						wait.nextLine();
						break;
					default:
						userChoice = 0;
						break;
				} 		
			}
			catch(Exception e){
				System.out.println("here?");
				userChoice = 5;		
			}
		}
	}
	public static void printLibrary(ArrayList<LibraryBook> lib,int srtI, int endI){
		clearScreen();
		for (int i = srtI; i<endI; i++){
			printRecord(lib, i);
		}
	}
	public static void printRecord(ArrayList<LibraryBook> lib, int location){
		if(lib.size()>location){
			System.out.println("*************************************************************************");
			System.out.println("Record #" + (location + 1) + " :\n");
			System.out.println("Title:          " + lib.get(location).getTitle());
			System.out.println("Author's Name:  " + lib.get(location).getAuthor());
			System.out.println("Copyright:   " + lib.get(location).getCopyright());
 			System.out.println("Price:          " + lib.get(location).getPrice());
 			System.out.println("Genre:   " + lib.get(location).getGenre() + "\n");
		}
	}
	public static void clearScreen(){
		System.out.println("\u001b[H\u001b[2J");
	}
}
