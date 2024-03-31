

import java.awt.*;

public class StopSign {
  public static void main(String[] args) {
    DrawableFrame df = new DrawableFrame("Stop Sign");
    df.setVisible(true);
    df.setSize(125, 125);

    Graphics g = df.getGraphicsContext();
    int[] xOuter = {36, 87, 123, 123, 87, 36, 0, 0};
    int[] yOuter = {0, 0, 36, 87, 123, 123, 87, 36};
    int[] xInner = {37, 86, 118, 118, 86, 37, 5, 5};
    int[] yInner = {5, 5, 37, 86, 118, 118, 86, 37};

    g.setColor(Color.black);
    g.drawPolygon(xOuter, yOuter, xOuter.length);

    g.setColor(Color.red);
    g.fillPolygon(xInner, yInner, xInner.length);

    g.setColor(Color.white);
    g.setFont(new Font("SansSerif", Font.BOLD, 36));
    g.drawString("STOP", 6, 76);
    df.repaint();
  }
}

