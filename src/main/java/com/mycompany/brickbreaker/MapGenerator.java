/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Yugalraj
 */
public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int[] val : map)
            for(int i = 0; i<col; i++)
                val[i] = i+1;
        brickWidth = 540/col;
        brickHeight = 150/row;
    }
    
    public void draw(Graphics2D g){
        for(int i = 0; i<map.length; i++)
            for(int j = 0; j<map[0].length; j++)
                if(map[i][j] > 0){
                    if(i == 0)
                        g.setColor(new Color(135, 206, 235));
                    else if(i == 1)
                        g.setColor(new Color(0,0,128));
                    else if(i == 2)
                        g.setColor(Color.pink);
                    else
                        g.setColor(Color.green);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
    }
    
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
