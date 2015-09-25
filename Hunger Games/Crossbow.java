
/**
 * Write a description of class Crossbow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class Crossbow extends JComponent 
{
    int x;
    int y;
    double angle;
    ImageIcon crossbowTex;
    int dir;
    public Crossbow(int x, int dir)
    {
        this.x = x;
        this.y = Constants.GROUND-25;
        this.dir = dir;
        if(dir==1) {
            crossbowTex = new ImageIcon("TEXTURE/crossbow1.png"); 
            angle = 0;
        }
        else {
            crossbowTex = new ImageIcon("TEXTURE/crossbow2.png");
            angle = Math.PI;
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;    

        if(dir==1) g2.rotate(angle,x,y);
        else g2.rotate(Math.PI+angle,x+23,y);
        crossbowTex.paintIcon(this,g2,x,y);
    }

    public void aim(double a) {
        angle = -a;
        repaint();
    }
}
