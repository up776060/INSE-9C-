package inse9c;

import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brad King
 */
public class QuizUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public QuizUI() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TextfieldQuestion = new javax.swing.JTextField();
        AnswerA = new javax.swing.JRadioButton();
        AnswerB = new javax.swing.JRadioButton();
        AnswerD = new javax.swing.JRadioButton();
        AnswerC = new javax.swing.JRadioButton();
        ButtonNext = new javax.swing.JButton();
        ButtonHint = new javax.swing.JButton();
        ButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel1.setText("Quiz Name");

        TextfieldQuestion.setEditable(false);
        TextfieldQuestion.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        TextfieldQuestion.setText("Oh Look, here's a famy looking area to store questions in");
        TextfieldQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextfieldQuestionActionPerformed(evt);
            }
        });

        AnswerA.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerA.setText("A: This is answer A");
        AnswerA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerAActionPerformed(evt);
            }
        });

        AnswerB.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerB.setText("B: Here's another answer");
        AnswerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerBActionPerformed(evt);
            }
        });

        AnswerD.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerD.setText("D: What about this one?");
        AnswerD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerDActionPerformed(evt);
            }
        });

        AnswerC.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerC.setText("C: Is this the correct answer?");
        AnswerC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerCActionPerformed(evt);
            }
        });

        ButtonNext.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        ButtonNext.setText("Next Question");
        ButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNextActionPerformed(evt);
            }
        });

        ButtonHint.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        ButtonHint.setText("Hint");
        ButtonHint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonHintActionPerformed(evt);
            }
        });

        ButtonExit.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        ButtonExit.setText("Exit");
        ButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(TextfieldQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 112, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AnswerB)
                            .addComponent(AnswerA)
                            .addComponent(AnswerD)
                            .addComponent(AnswerC)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(ButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(ButtonHint, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(819, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(ButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(TextfieldQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(AnswerA)
                .addGap(18, 18, 18)
                .addComponent(AnswerB)
                .addGap(18, 18, 18)
                .addComponent(AnswerC)
                .addGap(18, 18, 18)
                .addComponent(AnswerD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(496, Short.MAX_VALUE)
                    .addComponent(ButtonHint, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextfieldQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextfieldQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextfieldQuestionActionPerformed

    private void AnswerAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerAActionPerformed

    private void AnswerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerBActionPerformed

    private void AnswerDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerDActionPerformed

    private void AnswerCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerCActionPerformed

    private void ButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonNextActionPerformed

    private void ButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExitActionPerformed
        Menu m = new Menu();
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ButtonExitActionPerformed

    private void ButtonHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonHintActionPerformed
        JOptionPane.showMessageDialog(null, "HINT PLACEHOLDER");
    }//GEN-LAST:event_ButtonHintActionPerformed

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
            java.util.logging.Logger.getLogger(QuizUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AnswerA;
    private javax.swing.JRadioButton AnswerB;
    private javax.swing.JRadioButton AnswerC;
    private javax.swing.JRadioButton AnswerD;
    private javax.swing.JButton ButtonExit;
    private javax.swing.JButton ButtonHint;
    private javax.swing.JButton ButtonNext;
    private javax.swing.JTextField TextfieldQuestion;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
