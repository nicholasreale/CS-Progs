/** 
 *  This program draws a Frame with a Tictactoe box.
 * This is a temporary file to hold the template 
 * for students to copy.
**/


import java.awt.*;
// import jpb.*;

public class TicTacToe
{
    //define the basic geometry of the application
    //use these constants to specify geometrical
    //properties as far as possible
    final public static int CELL_WIDTH = 50;
    final public static int CELL_HEIGHT = 50;
    final public static int LABEL_SIZE = 30;

    //define the initial pattern
    boolean [][] isX = {{true, false, true}, 
			{false, true, true}, 
			{true, true, false}};

    public static void main( String[] args )
    {
	TicTacToe ttt = new TicTacToe();
    }

    public TicTacToe() 
    {
	//create a DrawableFrame with a title 
	DrawableFrame df = new DrawableFrame("Tictactoe");
		
	//show frame and then set the size for the frame
	df.setVisible(true);
	df.setSize(3*CELL_WIDTH, 3*CELL_HEIGHT);
		
	//get a Graphics object from the context of this instance
	Graphics g = df.getGraphicsContext();
		
        //draw the four main grid lines
	g.drawLine(50, 0, 50, 150);
	g.drawLine(100, 0, 100, 150);
	g.drawLine(0, 50, 150, 50);
	g.drawLine(0, 100, 150, 100);

	//draw the 9 cells with X's and O's using the drawX and drawY methods
/*	drawX(g, 0, 0); drawO(g, 1, 0); drawX(g, 2, 0);
	drawO(g, 0, 1); drawX(g, 1, 1); drawX(g, 2, 1);
	drawX(g, 0, 2); drawX(g, 1, 2); drawO(g, 2, 2);	*/
	for (int row = 0; row <= 2; row++)
	  for (int col = 0; col <= 2; col++)	
		if (isX[row][col])
		  drawX(g, row, col);
		else
		  drawO(g, row, col);

	//update the empty frame 
	df.repaint();
    }

    private void drawO(Graphics g, int indexX, int indexY) 
    {
	//compute the upper-left corner of cell (i, j)
	int cellRefX = indexX * CELL_WIDTH;
	int cellRefY = indexY * CELL_HEIGHT;
	//compute the upper-left corner of the label
   	int labelRefX = cellRefX + (CELL_WIDTH - LABEL_SIZE)/2;
	int labelRefY = cellRefY + (CELL_WIDTH - LABEL_SIZE)/2;

	//draw the circle (O)
	g.drawOval(labelRefX, labelRefY, LABEL_SIZE, LABEL_SIZE);
    }

    private void drawX(Graphics g, int indexX, int indexY) 
    {
	//compute the upper-left corner of cell (i, j)
	int cellRefX = indexX * CELL_WIDTH;
	int cellRefY = indexY * CELL_HEIGHT;
	//compute the upper-left corner of the label
   	int labelRefX = cellRefX + (CELL_WIDTH - LABEL_SIZE)/2;
	int labelRefY = cellRefY + (CELL_WIDTH - LABEL_SIZE)/2;

	//draw the cross (X)
	g.drawLine(labelRefX, labelRefY, labelRefX+LABEL_SIZE,
                   labelRefY+LABEL_SIZE);
        g.drawLine(labelRefX + LABEL_SIZE, labelRefY, labelRefX,
                   labelRefY+LABEL_SIZE);

    }
}
