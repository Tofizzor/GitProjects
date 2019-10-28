/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blockbreaker;

import javax.swing.JFrame;

/**
 *
 * @author karal
 */
public class main {

 public static void main(String[] args){
     JFrame obj = new JFrame();
     GamePlay gamePlay = new GamePlay();
     obj.setBounds(10,10,700,600);
     obj.setTitle("Block Breaker");
     obj.setResizable(false);
     obj.setVisible(true);
     obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     obj.add(gamePlay);
 }
    
}
