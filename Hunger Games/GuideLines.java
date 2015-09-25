import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class GuideLines extends JComponent
{
    private int inX;
    private int inY;
    private int x;
    private int y;
    private Line2D.Double line;
    public GuideLines(int x, int y) {
        inX = x;
        inY = y;
        line = new Line2D.Double(inX,inY,inX,inY);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        line.setLine(inX,inY,x,y);
        g2.drawString((int)getHypot()+"",inX,inY-15);
        g2.drawString((int)getAngle()+"°",x,y+15);
        if(getHypot()==2000) g2.setColor(Color.RED);
        else g2.setColor(Color.MAGENTA);
        g2.draw(line);
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    public double getAngle() {
        double theta = 0;
        if(inX<x) {
            theta = Math.atan((double)(y-inY)/(x-inX));
            theta = 180 - Math.toDegrees(theta);
        }
        else {
            theta = Math.atan((double)(inY-y)/(x-inX));
            theta = Math.toDegrees(theta);
        }
        //System.out.println(theta);
        return theta;
    }

    public double getHypot() {
        double hypot = 5*Math.sqrt(Math.pow((y-inY),2)+Math.pow((x-inX),2));
        if(hypot>2000) return 2000;
        else return hypot;
    }

    public int getInX() {return inX;}

    public int getInY() {return inY;}
    //public void test() {System.out.println("TEST"+x+" "+y);}
}
