package BlockBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play= false;
    private int score= 0;
    private int totalBricks= 100;

    private Timer timer;

    private int delay= 8;

    private int playerX= 150;
    
    
    public int level =1 ;

    private int ballPosX= 50;

    private int ballPosY=400;

    private int ballXdir= -1;

    private int ballYdir= -2;

    private mapGenerator map;
    private mapGenerator mp; 
    
    public Gameplay(){
        map= new mapGenerator(9,20);
  
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer= new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
    	
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        map.draw((Graphics2D) g);
        
        g.setColor(Color.white);
        
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: "+score, 540, 30);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("totalBricks: "+totalBricks, 100, 30);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Level: " +level  , 300, 30);

        g.setColor(Color.white);
        g.fillRect(playerX, 550, 150, 50);

        g.setColor(Color.white);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if(totalBricks<=0){
            play= false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" Congratulation , Score: "+score, 260, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        if(ballPosY>570){
            play= false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" Game Over, Score: "+score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        g.dispose();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 150, 8))){
                ballYdir= -ballYdir;
            }

            A: for(int i=0 ;  i<map.map.length ; i++){
                for(int j=0 ; j<map.map[0].length ; j++){
                    if(map.map[i][j]>0){
                        int brickX= j*map.brickWidth+80;
                        int brickY= i* map.brickHeight+50;
                        int brickWidth= map.brickWidth;
                        int brickHeight= map.brickHeight;

                        Rectangle rect= new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect= new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect= rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            
                            
                            
                            // total 3 ta level deua holo   
                            
                            if (totalBricks == 80 ) {
                        		delay--; 
                        		timer = new Timer(delay,this);
                        		timer.start();
                        		score += 500;
                        		level++;
                            }
                            else if(totalBricks == 40 )
                            {
                            	delay--; 
                       		 	timer = new Timer(delay,this);
                       		 	timer.start();
                       		 	score += 500;
                       		 	level++;
                       		 	moveright(100);
                       		 	moveleft(100);
                            }
                            else if(totalBricks<10)
                            {
                            	delay -- ; 
                       		 	timer = new Timer(delay,this);
                       		 	timer.start();
                       		 	score += 1000;
                       		 	level++;
                       		 	moveright(150);
                    		 	moveleft(150);
                            }
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            score+=5;
                            if(ballPosX+19<= brickRect.x || ballPosX+1>= brickRect.x + brickRect.width){
                                ballXdir= -ballXdir;
                            }
                            else{
                                ballYdir= -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX+= ballXdir;
            ballPosY+= ballYdir;

            if(ballPosX<0){
                ballXdir= -ballXdir;
            }
            if(ballPosY<0){
                ballYdir= -ballYdir;
            }
            if(ballPosX>670){
                ballXdir= -ballXdir;
            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX= 600;
            }
            else{
                moveright(50);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX= 10;
            }
            else{
                moveleft(50);
            }
        }
        
        //     ekhan theke khela suru level 1 er 

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
            	
            	
            	play= true;
                ballPosX=50;
                ballPosY=400;
                ballXdir=-1;
                ballYdir=-2;
                playerX=150;
                score = 0;
                totalBricks= 100;
                map= new mapGenerator(9,20);
                delay=8;

                repaint();
            }
        }
    }


    public void moveright(int x ){
        play= true;
        playerX+=x;
    }
    public void moveleft(int x ){
        play= true;
        playerX-=x;
    }



}
