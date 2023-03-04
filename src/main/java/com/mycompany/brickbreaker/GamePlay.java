/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

/**
 *
 * @author Yugalraj
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false, pause = false, titleScreen = true, over = false, win = false;
    private int score = 0, titleChoice = 0, pauseChoice = 1;
    private int totalBricks = 32;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 350;
    private int ballposY = 350;
    private int ballXdir = -2;
    private int ballYdir = -3;
    private int micro = 0, seconds = 0, minutes = 0;
    private String ddSeconds, ddMinutes; 
    DecimalFormat dFormat = new DecimalFormat("00"); 
    private MapGenerator map;
    
    public GamePlay(){
        map = new MapGenerator(4, 8);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    
     public void paint(Graphics g) {
        
        if(titleScreen){

            g.setColor(Color.black);
            g.fillRect(3, 3, 679, 557);
    
            g.setFont(new Font("Arial Black", Font.BOLD, 60));
            String text = "BRICK BREAKER";
            
             //Shadow
            g.setColor(Color.gray);
            g.drawString(text, 70, 205);
            
            //Title
            g.setColor(Color.white);
            g.drawString(text,65, 200);
            
            //Menu
            text = "NEW GAME";
            g.setFont(new Font("Times New Roman", Font.BOLD, 25));
            int x = 260, y = 350;
            g.drawString(text, x, y);
            
            if(titleChoice == 0)
                g.drawString(">", x-25, y);
            
            text = "EXIT";
            g.setFont(new Font("Times New Roman", Font.BOLD, 25));
            x = 305;
            y = 400;
            g.drawString(text, x, y);
            
            if(titleChoice == 1)
                g.drawString(">", x-25, y);
        }
        else{
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);
            map.draw((Graphics2D) g);

            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(691, 0, 3, 592);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("" + score, 590, 30);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            ddSeconds = dFormat.format(seconds); 
            ddMinutes = dFormat.format(minutes);
            g.drawString(ddMinutes + " : " + ddSeconds, 10, 30);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("" + totalBricks +"/"+32, 510, 30);

            g.setColor(Color.gray);
            g.fillRect(playerX-10, 550, 20, 8);
            g.setColor(Color.gray);
            g.fillRect(playerX+10, 550, 20, 8);
            g.setColor(Color.gray);
            g.fillRect(playerX-30, 550, 20, 8);
            g.setColor(Color.gray);
            g.fillRect(playerX-50, 550, 20, 8);
            g.setColor(Color.gray);
            g.fillRect(playerX+30, 550, 20, 8);

//            ball
            g.setColor(Color.GREEN);
            g.fillOval(ballposX, ballposY, 20, 20);

            if(pause){
                g.setColor(Color.black);
                g.fillRect(1, 1, 692, 592);

                g.setFont(new Font("Arial Black", Font.BOLD, 40));
                String text = "PAUSED";
                if(win)
                    text = "YOU WIN";
                if(over)
                    text = "GAME OVER";
                 //Shadow
                if(!win && !over){
                    g.setColor(Color.gray);
                    g.drawString(text, 245, 245);

                    //Title
                    g.setColor(Color.white);
                    g.drawString(text,240, 240);
                }
                if(over){
                    g.setColor(Color.gray);
                    g.drawString(text, 195, 245);

                    //Title
                    g.setColor(Color.white);
                    g.drawString(text,190, 240);
                }
                if(win){
                    g.setColor(Color.gray);
                    g.drawString(text, 195, 245);

                    //Title
                    g.setColor(Color.white);
                    g.drawString(text,190, 240);
                }
                //Menu
                int x , y;
                if(!over && !win){
                    text = "RESUME GAME";
                    g.setFont(new Font("Times New Roman", Font.BOLD, 25));
                    x = 240; 
                    y = 350;
                    g.drawString(text, x, y);
                

                if(pauseChoice == 0)
                    g.drawString(">", x-25, y);
                }
                
                text = "NEW GAME";
                g.setFont(new Font("Times New Roman", Font.BOLD, 25));
                x = 260;
                if(!win && !over)
                    y = 400;
                else
                    y = 350;
    
                g.drawString(text, x, y);

                if(pauseChoice == 1)
                    g.drawString(">", x-25, y);

                text = "EXIT";
                g.setFont(new Font("Times New Roman", Font.BOLD, 25));
                x = 300;
                if(!win && !over)
                    y = 450;
                else
                    y = 400;
                
                g.drawString(text, x, y);

                if(pauseChoice == 2)
                    g.drawString(">", x-25, y);
            }
            if (ballposY > 570) {
                play = false;
                over = true;
                pause = true;
                g.setColor(Color.red);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Score: " + score, 270, 300);
            }
            if(totalBricks == 0){
                play = false;
                win = true;
                pause = true;
                ballYdir = -2;
                ballXdir = -1;
                g.setColor(Color.red);
                g.setFont(new Font("serif",Font.BOLD,30));
                g.drawString("Score: " +  score,270,300);
            }
        }
        g.dispose();
   }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(titleScreen){
            if(code == KeyEvent.VK_DOWN){
                titleChoice = (titleChoice + 1)%2;
                
            }
            if(code == KeyEvent.VK_UP){
                titleChoice--;
                if(titleChoice < 0)
                    titleChoice = 1;
            }
            if(code == KeyEvent.VK_ENTER){
                if(titleChoice == 0){
                    titleScreen = false;
                    play = true;
                }
                else
                    System.exit(0);
            }
        }
        
        if(code == KeyEvent.VK_RIGHT){
            if(playerX >= 630)
                playerX = 630;
            else
                moveRight();
        }
        if(code == KeyEvent.VK_LEFT){
            if(playerX < 60)
                playerX = 52;
            else
                moveLeft();
        }
        if(code == KeyEvent.VK_ESCAPE){
            if(play){
                play = false;
                pause = true;
            }
            else{
                play = true;
                pause = false;
            }
        }
        if(pause){
            if(code == KeyEvent.VK_DOWN){
                pauseChoice++;
                if(pauseChoice > 2){
                    if(!win && !over)
                        pauseChoice = 0;
                    else
                        pauseChoice = 1;
                }
            }
                 
            if(code == KeyEvent.VK_UP){
                pauseChoice--;
                int k = 1;
                if(!win && !over)
                    k = 0;
                if(pauseChoice < k)
                    pauseChoice = 2;
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(pauseChoice == 0){
                    play = true;
                    pause = false;
                }
                else if(pauseChoice == 1){
                    if(over)
                        over = false;
                    if(win)
                        win = false;
                    pause = false;
                    play = true;
                    ballposX = 120;
                    ballposY = 350;
                    ballXdir = -2;
                    ballYdir = -3;
                    score = 0;
                    playerX = 310;
                    map = new MapGenerator(4, 8);
                    seconds = 0;
                    micro = 0;
                    minutes = 0;
                    totalBricks = 32;
                    repaint();
                }
                
                else
                    System.exit(0);
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override   
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play)
            micro++;
        if(micro == 50){
            seconds++;
            micro = 0;
        }
        if(seconds == 60){
            seconds = 0;
            minutes = 1;
        }
          
        if(play){
            if(new Rectangle(ballposX, ballposY, 15, 15).intersects(new Rectangle(playerX-10, 535, 20, 8))){
                ballYdir = -ballYdir;
                ballXdir = 0;
            }
//                
            if(new Rectangle(ballposX, ballposY, 15, 15).intersects(new Rectangle(playerX+10, 540, 20, 8))){
                ballYdir *= -1;
                ballXdir = 1;
            }
            if(new Rectangle(ballposX, ballposY, 15, 15).intersects(new Rectangle(playerX-30, 540, 20, 8))){
                ballYdir *= -1;
                ballXdir = -1;
            }
            if(new Rectangle(ballposX, ballposY, 15, 15).intersects(new Rectangle(playerX-50, 545, 20, 8))){
                ballYdir *= -1;
                ballXdir = -2;
            }
            if(new Rectangle(ballposX, ballposY, 15, 15).intersects(new Rectangle(playerX+30, 545, 20, 8))){
                ballYdir *= -1;
                ballXdir = 2;
            }
            A:
            for(int i = 0; i<map.map.length; i++)
                for(int j = 0; j<map.map[0].length; j++)
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int bricksWidth = map.brickWidth;
                        int bricksHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect; 
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth)
                                ballXdir = -ballXdir;
                            else
                                ballYdir = -ballYdir;
                            break A;
                        }
                    }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0 || ballposX > 670)
                ballXdir *= -1;
            if(ballposY < 0)
                ballYdir *= -1;  
        }
        repaint();
    }
    
    public void moveRight(){
        playerX += 20;
    }
    
    public void moveLeft(){
        playerX -= 20;
    }
}
