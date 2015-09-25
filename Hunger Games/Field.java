import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class Field extends JComponent
{
    Line2D.Double ground;
    ImageIcon bgTex;
    public Field() {
        ground = new Line2D.Double(-1000,Constants.GROUND,2000,Constants.GROUND);
        bgTex = new ImageIcon("TEXTURE/bg.png");
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(ground);
        //bgTex.paintIcon(this,g2,0,0);
    }
}
