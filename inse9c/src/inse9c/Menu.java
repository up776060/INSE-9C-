package inse9c;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author up785062
 */
public class Menu extends javax.swing.JFrame {
    
    private static DAO dao;
    private int userID;
    /**
     * Creates new form NewJFrame
     */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        checkBg();
    }
    
    public Menu(int iD) {
        initComponents();
        this.setLocationRelativeTo(null);
        userID = iD;
        checkBg();
    }

    public void checkBg() {
        String readCol = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("colourSettings.txt"));
            readCol = br.readLine();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (readCol.equals("red")) {
            this.getContentPane().setBackground(Color.red);
        }
        if (readCol.equals("yellow")) {
            this.getContentPane().setBackground(Color.yellow);
        }
        if (readCol.equals("green")) {
            this.getContentPane().setBackground(Color.green);
        }
        if (readCol.equals("blue")) {
            this.getContentPane().setBackground(Color.blue);
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

        Mock_button = new javax.swing.JButton();
        Quiz_button = new javax.swing.JButton();
        Topics_button = new javax.swing.JButton();
        Progress_button = new javax.swing.JButton();
        Setting_button = new javax.swing.JButton();
        Header_label = new javax.swing.JLabel();
        LogOut_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Mock_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Mock_button.setText("Mock Theory test");
        Mock_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mock_buttonActionPerformed(evt);
            }
        });

        Quiz_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Quiz_button.setText("Quiz");
        Quiz_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quiz_buttonActionPerformed(evt);
            }
        });

        Topics_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Topics_button.setText("Topics");
        Topics_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Topics_buttonActionPerformed(evt);
            }
        });

        Progress_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Progress_button.setText("Progress Monitor");
        Progress_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Progress_buttonActionPerformed(evt);
            }
        });

        Setting_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Setting_button.setText("Settings and Help");
        Setting_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Setting_buttonActionPerformed(evt);
            }
        });

        Header_label.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Header_label.setText("Portsmouth Driving Theory");

        LogOut_button.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        LogOut_button.setText("Log out");
        LogOut_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOut_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Mock_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Quiz_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Topics_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Progress_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Setting_button, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LogOut_button, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(Header_label)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Header_label))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LogOut_button, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85)
                .addComponent(Mock_button, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Quiz_button, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Topics_button, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Progress_button, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Setting_button, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Mock_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mock_buttonActionPerformed
        JOptionPane.showMessageDialog(this, "Once you start, you will have 60 minutes to complete 50 questions.\nAfter 60 minutes have passed, or you have completed the test,\nyou will be sent to the results page");
        QuizUI q = new QuizUI("Mock Test", "Mock", userID);
        q.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_Mock_buttonActionPerformed

    private void Quiz_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quiz_buttonActionPerformed
        JOptionPane.showMessageDialog(this, "Once you start, you will have 60 minutes to complete 50 questions.\nAfter 60 minutes have passed, or you have completed the test,\nyou will be sent to the results page");
        QuizUI q = new QuizUI("Quiz", "Quiz", userID);
        q.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_Quiz_buttonActionPerformed

    private void Topics_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Topics_buttonActionPerformed
        Topic_Practice t = new Topic_Practice(userID);
        t.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_Topics_buttonActionPerformed

    private void Setting_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Setting_buttonActionPerformed
        Settings s = new Settings(userID);
        s.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_Setting_buttonActionPerformed

    private void LogOut_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOut_buttonActionPerformed
        LoginUI t = new LoginUI();
        t.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_LogOut_buttonActionPerformed

    private void Progress_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Progress_buttonActionPerformed
        ProgressMonitor pM = new ProgressMonitor(userID);
        pM.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_Progress_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Header_label;
    private javax.swing.JButton LogOut_button;
    private javax.swing.JButton Mock_button;
    private javax.swing.JButton Progress_button;
    private javax.swing.JButton Quiz_button;
    private javax.swing.JButton Setting_button;
    private javax.swing.JButton Topics_button;
    // End of variables declaration//GEN-END:variables
}
