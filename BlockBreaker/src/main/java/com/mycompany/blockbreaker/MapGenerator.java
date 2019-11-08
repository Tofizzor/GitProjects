/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blockbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author karal
 */
public class MapGenerator {
    public int map[][];
    public int blockWidth;
    public int blockHeight;
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        blockWidth = 540/col;
        blockHeight = 150/row;
    }
    public void draw(Graphics2D g){
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
              if(map[i][j] > 0){
                  g.setColor(Color.WHITE);
                  g.fillRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);
                  
                  g.setStroke(new BasicStroke(3));
                  g.setColor(Color.BLACK);
                  g.drawRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);
              }
            }  
        }
    }
    
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
