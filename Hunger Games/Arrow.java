import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Arrow extends JComponent implements ActionListener
{
    final int LENGTH = 19;
    final int WIDTH = 5;

    final double G = -980;
    double inX;
    double inY;

    double inVel;
    double inAng;
    double time;
    boolean flying;

    int ground;

    Rectangle2D.Double arrow;
    ImageIcon arrowTex;
    ImageIcon arrowTex2;
    public Arrow(double x, double y, double v, double a) {

        inX = x;
        inY = y;
        inVel = v/2;
        inAng = a;
        arrow = new Rectangle2D.Double(x,y,LENGTH,WIDTH);
        flying = true;
        arrowTex = new ImageIcon("TEXTURE/arrow.png");
        arrowTex2 = new ImageIcon("TEXTURE/arrow2.png");
        time = 0;
        this.ground = ground;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(calcAngle(),arrow.x,arrow.y);
        if(inAng<=90 && inAng >=-90) {
            arrowTex.paintIcon(this,g2,(int)arrow.x,(int)arrow.y);
        }
        else {
            arrowTex2.paintIcon(this,g2,(int)arrow.x,(int)arrow.y);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(arrow.y < Constants.GROUND) flying = true;
        else flying = false;
        time += 1./100.;
        arrow.x = inX + (inVel* Math.cos(Math.toRadians(inAng)) * time);
        arrow.y = inY - (inVel* Math.sin(Math.toRadians(inAng)) * time + (G * Math.pow(time,2))/2);
        repaint();
    }

    public double calcAngle() {
        double pTime = time - 1./100.;
        double pX = inX + (inVel* Math.cos(Math.toRadians(inAng)) * pTime);
        double pY = inY - (inVel* Math.sin(Math.toRadians(inAng)) * pTime + (G * Math.pow(pTime,2))/2);
        double theta = Math.atan((arrow.y-pY)/(arrow.x-pX));

        return theta;
    }

    public boolean bullseye(Player p)
    {
        if ( arrow.intersects(p.getRect()))
        {
            return true;
        }
        else return false;
    }

    public boolean ifFlying() {
        return flying; 
    }
}
