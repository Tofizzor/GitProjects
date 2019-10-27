/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author karal
 */
public class Tic_Tac_Toe_Interface extends javax.swing.JFrame {

    //Game action
    //x_or_o display X or O 
    private int x_or_o = 0;
    private boolean gameWon = false;
    private boolean vsComputer = false;
    private boolean turnTaken = false;
    
    /**
     * Creates new form Tic_Tac_Toe_Interface
     */
    public Tic_Tac_Toe_Interface() {
        initComponents();
        decideTurn();
        addAction();

    }

    private void decideTurn(){
        Random rand = new Random();
        x_or_o = rand.nextInt(2);
        takeTurn();
        
    }
    
    //Checks if all fields are full
    private int getFieldsLength(){
       String fLength = "";
        Component[] comps = jPanel1.getComponents();
      for(Component comp : comps)
      {
          if(comp instanceof JButton)
          {
            JButton button = (JButton)comp;
            fLength += button.getText();
          }
      }
      return fLength.length();
    }
    
    private void takeTurn(){
        if(!gameWon){
                if((x_or_o % 2) == 0){
                     jLabel3.setText("X");
                     jLabel3.setForeground(Color.BLUE);
                }else{
                     jLabel3.setText("O");
                     jLabel3.setForeground(Color.RED);
                }
        }
    }
    
   
    
    public ActionListener createAction(JButton button)
    {
       ActionListener a1 = new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
             
             if(button.getText().equals("") && !gameWon)
             {
                
                    if((x_or_o % 2) == 0){
                         button.setText("X");
                         button.setForeground(Color.BLUE);

                    }else{
                         button.setText("O");
                         button.setForeground(Color.RED);

                    }
                

                x_or_o++;
                getTheWinner();
                takeTurn();
                if(vsComputer){
                turnTaken = false;
                activateBot();
                }

             }
         }
         
       };
       return a1;
    }
    
    public void addAction(){
      Component[] comps = jPanel1.getComponents();
      for(Component comp : comps)
      {
          if(comp instanceof JButton)
          {
            JButton button = (JButton)comp;
            button.addActionListener(createAction(button));
          }
      }  
    }
    
    private void activateBot()
    {
        if(vsComputer){
          Component[] comps = jPanel1.getComponents();
          Random rand = new Random();
          int ranField = rand.nextInt(9);
          if(comps[ranField] instanceof JButton)
          {
          JButton button = (JButton)comps[ranField];
          if(getFieldsLength()<9 && !button.getText().equals("")){
            boolean found = false;
              while(!button.getText().equals("")&&!found){
                ranField = rand.nextInt(9);
                JButton thisButton = (JButton)comps[ranField];
                if(thisButton.getText().equals("")){
                    found = true;
                }
            }
          }
          JButton newButton = (JButton)comps[ranField];
          if(!gameWon && !turnTaken){
              if((x_or_o % 2) == 0){
                         newButton.setText("X");
                         newButton.setForeground(Color.BLUE);

                    }else{
                         newButton.setText("O");
                         newButton.setForeground(Color.RED);

                    }

                    x_or_o++;
                    getTheWinner();
                    takeTurn();
                    turnTaken = true;
          }
          }
        }
    }  

    
     private void winHighligh(JButton b1, JButton b2, JButton b3){
        gameWon = true;
        jLabel3.setText(b1.getText());
        jLabel1.setText("Wins!");
        jButtonReplay.setBackground(Color.GREEN);
        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.GREEN);
        b3.setBackground(Color.GREEN);
        
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
    }
    
    public void getTheWinner(){
        //[*][*][*]
        //[ ][ ][ ] Win condition
        //[ ][ ][ ]
        if(!jButton1.getText().equals("")&&jButton1.getText().equals(jButton2.getText())&&jButton1.getText().equals(jButton3.getText())){
            winHighligh(jButton1, jButton2, jButton3);
            
        }
        //[ ][ ][ ]
        //[*][*][*] Win condition
        //[ ][ ][ ]
        if(!jButton4.getText().equals("")&&jButton4.getText().equals(jButton5.getText())&&jButton4.getText().equals(jButton6.getText())){
            winHighligh(jButton4, jButton5, jButton6);

        }
        //[ ][ ][ ]
        //[ ][ ][ ] Win condition
        //[*][*][*]
        if(!jButton7.getText().equals("")&&jButton7.getText().equals(jButton8.getText())&&jButton7.getText().equals(jButton9.getText())){

            winHighligh(jButton7, jButton8, jButton9);

        }
        //[*][ ][ ]
        //[ ][*][ ] Win condition
        //[ ][ ][*]
        if(!jButton1.getText().equals("")&&jButton1.getText().equals(jButton5.getText())&&jButton1.getText().equals(jButton9.getText())){

            winHighligh(jButton1, jButton5, jButton9);

        }
        //[ ][ ][*]
        //[ ][*][ ] Win condition
        //[*][ ][ ]
        if(!jButton3.getText().equals("")&&jButton3.getText().equals(jButton5.getText())&&jButton3.getText().equals(jButton7.getText())){

            winHighligh(jButton3, jButton5, jButton7);

        }
        //[*][ ][ ]
        //[*][ ][ ] Win condition
        //[*][ ][ ]
        if(!jButton1.getText().equals("")&&jButton1.getText().equals(jButton4.getText())&&jButton1.getText().equals(jButton7.getText())){

            winHighligh(jButton1, jButton4, jButton7);

        }
        //[ ][*][ ]
        //[ ][*][ ] Win condition
        //[ ][*][ ]
        if(!jButton2.getText().equals("")&&jButton2.getText().equals(jButton5.getText())&&jButton2.getText().equals(jButton8.getText())){
            winHighligh(jButton2, jButton5, jButton8);

        }
        //[ ][ ][*]
        //[ ][ ][*] Win condition
        //[ ][ ][*]
        if(!jButton3.getText().equals("")&&jButton3.getText().equals(jButton6.getText())&&jButton3.getText().equals(jButton9.getText())){

            winHighligh(jButton3, jButton6, jButton9);

        }
        
        if(getFieldsLength() == 9){
            jLabel1.setText("Draw");
            jButtonReplay.setBackground(Color.GREEN);
            jLabel3.setVisible(false);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButtonReplay = new javax.swing.JButton();
        jButtonBot = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Tic Tac Toe Game");

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Turn");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Nirmala UI", 1, 40)); // NOI18N
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonReplay.setBackground(new java.awt.Color(255, 255, 255));
        jButtonReplay.setText("Replay");
        jButtonReplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReplayActionPerformed(evt);
            }
        });

        jButtonBot.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBot.setText("vs Computer");
        jButtonBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonReplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBot, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBot, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReplayActionPerformed
      vsComputer = false;
      gameWon = false;
      jLabel3.setVisible(true);
      jButtonReplay.setBackground(Color.WHITE);
      Component[] comps = jPanel1.getComponents();
      for(Component comp : comps)
      {
          if(comp instanceof JButton)
          {
            JButton button = (JButton)comp;
            button.setText("");
            button.setBackground(Color.WHITE);
            jLabel1.setText("Turn");
            decideTurn();
            takeTurn();
          }
      }
    }//GEN-LAST:event_jButtonReplayActionPerformed

    private void jButtonBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBotActionPerformed
        vsComputer = true;
        activateBot();
    }//GEN-LAST:event_jButtonBotActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tic_Tac_Toe_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tic_Tac_Toe_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tic_Tac_Toe_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tic_Tac_Toe_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tic_Tac_Toe_Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonBot;
    private javax.swing.JButton jButtonReplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
