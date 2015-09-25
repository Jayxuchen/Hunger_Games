import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
public class GUI extends JComponent
{
    Line2D.Double ground;
    int x; 
    int hp1;
    int hp2;
    boolean turn;
    Font yes = new Font("",Font.BOLD,20);
    Font no = new Font("",Font.PLAIN,15);
    Font big = new Font("",Font.PLAIN,70);
    int win;
    public GUI(int x) {
        this.x = x;
        hp1 = 100;
        hp2 = 100;
        turn = true;
        win = 0;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if(turn) {
            g2.setFont(yes);
            g2.drawString("SHOOTING...",20,300);
        }
        else g2.setFont(no);
        g2.drawString("Player 1",20,30);
        g2.drawString("HP:"+hp1,20,50);
        if(!turn) {
            g2.setFont(yes);
            g2.drawString("SHOOTING...",x-200,300);
        }
        else g2.setFont(no);
        g2.drawString("Player 2",x-100,30);
        g2.drawString("HP:"+hp2,x-100,50);

        if(win==1) {
            g2.setFont(big);
            g2.drawString("PLAYER 1 WINS",300,300);
        }
        else if(win==2) {
            g2.setFont(big);
            g2.drawString("PLAYER 2 WINS",300,300);
        }
    }

    public void update(int h1, int h2, boolean t) {
        hp1 = h1;
        hp2 = h2;
        turn = t;
        repaint();
    }

    public void p1Win() {
        win = 1;
        repaint();
    }

    public void p2Win() {
        win = 2;
        repaint();
    }
}
