
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class Player extends JComponent
{
    double pX;
    double pY;
    ImageIcon playerTex;
    Rectangle2D.Double pRect;
    int hp;
    public Player(double x)
    {
        hp = 100;
        pX = x;
        playerTex = new ImageIcon("TEXTURE/stickman.png");
        pY = Constants.GROUND - playerTex.getIconHeight();
        pRect = new Rectangle2D.Double(x+playerTex.getIconWidth()/2, pY,1, playerTex.getIconHeight());
    }

    public Rectangle2D.Double getRect()
    {
        return pRect;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        playerTex.paintIcon(this,g2,(int)pX,(int)pY);
    }
    public void hit() {
        hp = hp - Constants.DAMAGE;
    }
    public int getHP() {
        return hp;
    }
}
