/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blockbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author karal
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private Timer timer;
    private int delay = 10;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int blockRows = 1;
    private int blockColumns = 1;
    private int totalBricks = blockRows*blockColumns;
    
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(blockRows, blockColumns);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        
        timer.start();
        
    }
    
    //function that draws the objects
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        //borders
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(685, 0, 3, 592);
        //player
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);
        //ball
        g.setColor(Color.RED);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        //draw blocks
        map.draw((Graphics2D)g);
        
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("calibri", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);
        
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.WHITE);
            g.setFont(new Font("calibri", Font.BOLD, 30));
            g.drawString("You Won!", 245, 300);
            
            g.setFont(new Font("calibri", Font.BOLD, 20));
            g.drawString("Your Final Score: "+score, 230, 325);
            
            g.setFont(new Font("calibri", Font.BOLD, 20));
            g.drawString("Enter to play again", 230, 350);
        }
        
        //End game
        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.WHITE);
            g.setFont(new Font("calibri", Font.BOLD, 30));
            g.drawString("Game Over, Score: "+score, 190, 300);
            
            g.setFont(new Font("calibri", Font.BOLD, 20));
            g.drawString("Enter to Restart", 230, 350);
        }
        
        g.dispose();
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >=590){
                playerX = 590;
            }else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 7){
                playerX = 7;
            }else {
                moveLeft();
            }
        }
        //When enter pressed restart the game
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(blockRows, blockColumns);
                repaint();
            }
        }
    }
    
    public void moveRight(){
        play = true;
        playerX+=20;
    }
    public void moveLeft(){
        play = true;
        playerX-=20;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         timer.start();
         if(play){
             if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX, 550, 100,8))){
                 ballYdir = -ballYdir;
             }
             
             A: for(int i = 0; i<map.map.length; i++){
                 for(int j = 0; j<map.map[0].length; j++){
                     if(map.map[i][j]>0){
                         int blockX = j* map.blockWidth + 80;
                         int blockY = i* map.blockHeight + 50;
                         int blockWidth = map.blockWidth;
                         int blockHeigth = map.blockHeight;
                         
                         Rectangle rect = new Rectangle(blockX, blockY, blockWidth, blockHeigth);
                         Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                         Rectangle blockRect = rect;
                         
                         if(ballRect.intersects(blockRect)){
                             map.setBrickValue(0, i, j);
                             totalBricks--;
                             score += 5;
                         
                         if(ballposX + 19 <=blockRect.x || ballposX + 1 >= blockRect.x + blockRect.width){
                            ballXdir = -ballXdir; 
                         } else{
                             ballYdir = -ballYdir;
                         }
                         
                         break A;
                         }
                     }
                 }
             }
             
             
             ballposX += ballXdir;
             ballposY += ballYdir;
             if(ballposX <0){
                 ballXdir = -ballXdir;
             }
             if(ballposY <0){
                 ballYdir = -ballYdir;
             }
             if(ballposX > 670){
                 ballXdir = -ballXdir;
             }
         }
         repaint();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
