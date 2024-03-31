import java.awt.*;
public class LavaLamp
{
	public static void main(String[] args)
	{
		DrawableFrame df = new DrawableFrame("Lava Lamp");
		df.setVisible(true);
		df.setSize(200, 300);
		Graphics g = df.getGraphicsContext();
		g.fillOval(75, 100, 25, 25);
		g.fillOval(100, 125, 25, 25);
		g.drawLine(50, 175, 75, 75);
		g.drawLine(125, 75, 150, 175);
		int[] xCoordinates1 = {50, 75, 50, 150, 125, 150};
		int[] yCoordinates1 = {175, 225, 275, 275, 225, 175};
		g.fillPolygon(xCoordinates1, yCoordinates1, xCoordinates1.length);
		int[] xCoordinates2 = {75, 125, 113, 88};
		int[] yCoordinates2 = {75, 75, 25, 25};
		g.fillPolygon(xCoordinates2, yCoordinates2, xCoordinates2.length);
	}
}
