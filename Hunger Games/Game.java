import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;
/**
 * Write a description of class Game here.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game implements ActionListener
{
    boolean turn = true;
    JFrame frame;
    Timer t;
    ArrayList<Arrow> arrows;
    Player p1;
    Player p2;
    Crossbow cb1;
    Crossbow cb2;
    GuideLines gl;
    Field f;
    GUI gui;

    public Game()
    {
        frame = new JFrame("Hunger Games");
        frame.setSize(1200,600);
        gui = new GUI(1200);
        frame.setResizable(false);
        t = new Timer(10,this);
        arrows = new ArrayList<Arrow>();
        gl = new GuideLines(0,0);
        p1 = new Player(Constants.p1xPos);
        p2 = new Player(Constants.p2xPos);
        f = new Field();
        cb1 = new Crossbow(Constants.p1xPos+5,1);
        cb2 = new Crossbow(Constants.p2xPos-13,2);
        frame.getContentPane().setBackground(Color.WHITE);
    }

    public void start()
    {
        class clickListener implements MouseListener {
            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                gl = new GuideLines(e.getX(),e.getY());
                frame.add(gl);
                frame.setVisible(true);
            }

            public void mouseReleased(MouseEvent e) {
                SoundPlayer.playSound("SOUND/bow.wav");
                frame.remove(gl);
                gl.setPoint(e.getX(),e.getY());
                int x;
                if(turn) x = Constants.p1xPos+15;
                else x = Constants.p2xPos-15;
                Arrow a= new Arrow(x,Constants.GROUND-30,gl.getHypot(),gl.getAngle());
                arrows.add(a);
                frame.add(a);
                t.addActionListener(a);
                frame.setVisible(true);
                turn = !turn;
            }
        }
        class mouseListener implements MouseMotionListener {
            public void mouseDragged(MouseEvent e) {
                gl.setPoint(e.getX(),e.getY());
                if(turn) cb1.aim(Math.toRadians(gl.getAngle()));
                else cb2.aim(Math.toRadians(gl.getAngle()));
            }

            public void mouseMoved(MouseEvent e) {}
        }
        frame.addMouseListener(new clickListener());
        frame.addMouseMotionListener(new mouseListener());
        frame.add(gui);
        frame.setVisible(true);
        frame.add(cb1);
        frame.setVisible(true);
        frame.add(cb2);
        frame.setVisible(true);
        frame.add(p1);
        frame.setVisible(true);
        frame.add(p2);
        frame.setVisible(true);
        frame.add(f);
        frame.setVisible(true);
        t.start();

    }

    public void actionPerformed(ActionEvent e) {
        if(frame.getContentPane().getBackground()==Color.RED){
            frame.getContentPane().setBackground(Color.WHITE);
        }
        for(int i = 0; i < arrows.size(); i++) {
            if(arrows.get(i).ifFlying() == false) {
                SoundPlayer.playSound("SOUND/ground.wav");  
                t.removeActionListener(arrows.get(i));
                arrows.remove(i);
                checkGame();

            }
            else if(arrows.get(i).bullseye(p1)) {
                // sound.playHit();
                SoundPlayer.playSound("SOUND/hit.wav");
                t.removeActionListener(arrows.get(i));
                p1.hit();
                arrows.remove(i);
                checkGame();
                frame.getContentPane().setBackground(Color.RED);
            }
            else if(arrows.get(i).bullseye(p2)) {
                SoundPlayer.playSound("SOUND/hit.wav");
                t.removeActionListener(arrows.get(i));
                p2.hit();
                arrows.remove(i);
                checkGame();
                frame.getContentPane().setBackground(Color.RED);
            }
            gui.update(p1.getHP(),p2.getHP(),turn);
        }
    }
    
    public void checkGame() {
        if(p1.getHP()<=0) {
            gui.p2Win();
            t.stop();
        }
        else if(p2.getHP()<=0) {
            gui.p1Win();
            t.stop();
        }

    }
}
