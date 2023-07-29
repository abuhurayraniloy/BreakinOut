package BlockBreaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play= false;
    private int score= 0;
    private int totalBricks= 21;

    private Timer time;

    private int delay= 8;

    private int playerX= 310;

    private int ballPosX= 120;

    private int ballPosY=350;

    private int ballXdir= -1;

    private int ballYdir= -2;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time= new Timer(delay, this);
        time.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
