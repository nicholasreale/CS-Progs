import java.awt.*;

public class HeadsVsTails
{
  public static void main(String[] args)
  {
	// Get number of times to flip coin 
	int numFlips = getInput();
	
	// Flip the coin the number of times entered by the user
	int[] results = flipIt(numFlips);
	
	// Display the Results in a bar chart or a pie chart
	// (chosen by the user)
	displayResults(results, numFlips);
  }
  
  private static int getInput()
  // Takes no parameters.
  // Prompts user to enter the number of times to flip the 
  // coin and returns this number as an int
  {
	SimpleIO.prompt("Enter number of flips: ");
	String userInput = SimpleIO.readLine();
	return Integer.parseInt(userInput);
  }
  
  private static int[] flipIt(int numFlips)
  // Takes the number of flips entered by the user as its parameter.
  // Flips the coin and returns the results as an array of two
  // integers representing the numbers of heads and tails
  {
	int heads = 0, tails = 0;
	for (int i = 0; i < numFlips; i++)
	{
	  if ((int) (Math.random() * 2) == 0)  
	    heads++;
	  else tails++;
	}
	int[] results = {heads, tails};
	return results;
  }
  
  private static void displayResults(int[] results, int numFlips)
  // Takes the results array and the number of flips as its parameters.
  // Prompts the user to enter a 1 for bar chart of a 2 for 
  // pie chart, and displays the results of the coin flips in 
  // the type of chart the user chooses
  {
	// Declare DrawableFrame object and set its size
	DrawableFrame df = new DrawableFrame("Heads vs. Tails");
	df.setVisible(true);
	df.setSize(150, 150);
	
	// Get style of chart from user and display it
	boolean entered1or2 = false;
	while (!entered1or2)
	{
	  int style = getStyle();
	  switch (style)
	  {
		case 1: entered1or2 = true; barChart(df, results, numFlips); break;
		case 2: entered1or2 = true; pieChart(df, results, numFlips); break;
		default: System.out.println("Sorry, you didn't enter a 1 or a 2; please try again."); break;
	  }
	}
  }
  
  private static int getStyle()
  // Takes no parameters
  // Prompts user to choose either a bar chart or pie chart
  // Returns a 1 for bar chart or a 2 for pie chart
  {
	SimpleIO.prompt("Enter a 1 for bar chart or a 2 for pie chart: ");
	String userInput = SimpleIO.readLine();
	return Integer.parseInt(userInput);
  }
  
  private static void barChart(DrawableFrame df, int[] results, int numFlips)
  // Takes DrawableFrame object, results array, and number of flips
  // as parameters.
  // Displays the results of the coin flips in a bar chart
  {
	
	// Obtain Graphics object
	Graphics g = df.getGraphicsContext();
	
	// Variable declarations 
	String[] strings = {"" + results[0] + " heads", "" + results[1] + " tails"};
	Color[] colors = {Color.red, Color.blue};
	int height;
	
	// Set Font
	Font f = new Font("SansSerif", Font.PLAIN, 12);
	FontMetrics fm = g.getFontMetrics(f);
	g.setFont(f);
	
	// Draw bar chart and captions
	for (int i = 0; i < 2; i++)
	{
	  height = (int) (((double) results[i]) / numFlips * 150);
	  g.setColor(colors[i]);
	  g.fillRect(75 * i, 150 - height, 75, height);
	  g.setColor(Color.black);
	  g.drawString(strings[i], 75 * i + (75 - fm.stringWidth(strings[i])) / 2, 
	  		150 - height - 5);
	}
	
	df.repaint();
	
	// Print results to screen		
	System.out.println("H = " + results[0]);
	System.out.println("T = " + results[1]); 
  }
  
  private static void pieChart(DrawableFrame df, int[] results, int numFlips)
  // Takes DrawableFrame object, results array, and number of flips
  // as parameters.
  // Displays the results of the coin flips in a pie chart
  {
	// Obtain Graphics object
	Graphics g = df.getGraphicsContext();
	
	// Determine size of pie "slices" 
	int degreeH = (int) (((double) results[0]) / numFlips * 360);
	int degreeT = 360 - degreeH;
	
	// Draw tails "slice"
	g.setColor(Color.blue);
	g.fillArc(25, 25, 100, 100, 180, degreeT);
	
	// Draw heads "slice"
	g.setColor(Color.red);
	g.fillArc(25, 25, 100, 100, 180 - degreeH, degreeH);
	
	// Center heads caption above pie chart and tails caption below
	Font f = new Font("SansSerif", Font.PLAIN, 12);
	FontMetrics fm = g.getFontMetrics(f);
	g.setFont(f);
	String[] strings = {"" + results[0] + " heads", "" + results[1] + " tails"};
	g.setColor(Color.black);
	
	for (int i = 0; i < 2; i++)
	  g.drawString(strings[i], (150 - fm.stringWidth(strings[i])) / 2, 
			125 * i + (25 - (fm.getHeight())) / 2 + 
			fm.getAscent() + fm.getLeading());
	df.repaint();
	
	// Print results to screen		
	System.out.println("H = " + results[0]);
	System.out.println("T = " + results[1]);  
  }
}
