/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inse9c;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author up777621
 */
public class LoginUI extends javax.swing.JFrame {

    private boolean clicked = false;
    DAO dao;
    registrationUI r = new registrationUI();
    int userID = 0;

    /**
     * Creates new form LoginUI
     */
    public LoginUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        checkBg();
        checkEmail();
    }

    public void checkEmail() {
        String email = "";
        boolean notEmpty = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("email.txt"));
            email = br.readLine();
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (notEmpty || email != null) {
            userEmail.setText(email);
            remEmail.setSelected(true);
            clicked = true;
        }
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

        loginBut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        userEmail = new javax.swing.JTextField();
        userPassword = new javax.swing.JPasswordField();
        registerBut = new javax.swing.JLabel();
        remEmail = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        loginBut.setText("Login");
        loginBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Portsmouth Driving Theory");

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        emailLabel.setText("Email:");

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        passwordLabel.setText("Password:");

        userEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        userEmail.setText("user@email.com");
        userEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userEmailMouseClicked(evt);
            }
        });
        userEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEmailActionPerformed(evt);
            }
        });
        userEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userEmailKeyTyped(evt);
            }
        });

        userPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        userPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPasswordActionPerformed(evt);
            }
        });

        registerBut.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        registerBut.setForeground(new java.awt.Color(0, 0, 255));
        registerBut.setText("CREATE LOGIN");
        registerBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButMouseClicked(evt);
            }
        });

        remEmail.setText("Remember my Email");
        remEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerBut))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(emailLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userEmail)
                            .addComponent(userPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(remEmail)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(remEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBut)
                    .addComponent(registerBut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButActionPerformed
        //current checking fake email account, or something entered in the reg screen
        if (remEmail.isSelected()) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("email.txt"));
                out.write(userEmail.getText());
                out.close();
            } catch (IOException e) {
            }
        } else {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("email.txt"));
                out.write("");
                out.close();
            } catch (IOException e) {
            }
        }
        if (loginValidation()) {
            Menu m = new Menu(dao, userID);
            m.setVisible(true);
            this.setVisible(false);
            dispose();
        }

    }//GEN-LAST:event_loginButActionPerformed

    public boolean loginValidation() {
        //use encryption method on password so it's compatible with the one stored in Database
        String encryptedPass = "";
        try {
            encryptedPass = DAO.byteArraytoHexString(DAO.computeHash(userPassword.getText()));
        } catch (Exception ex) {
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            encryptedPass = DAO.byteArraytoHexString(DAO.computeHash(userPassword.getText()));
        } catch (Exception ex) {
            Logger.getLogger(registrationUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!userEmail.getText().isEmpty() && !userPassword.getText().isEmpty()) {
            try {
                ResultSet rs = DAO.retrieveLoginDetails(userEmail.getText());
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "The email you entered does not exist");
                } else {
                    userID = rs.getInt("userID");
                    String pw = rs.getString("userPassword");
                    if (pw.equals(encryptedPass)) {
                        dao = new DAO(userEmail.getText());
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(this, "The password you entered does not match our records");
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter an email and password");
        }
        return false;
    }

    private void userEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEmailActionPerformed
        loginButActionPerformed(evt);
    }//GEN-LAST:event_userEmailActionPerformed

    private void userPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPasswordActionPerformed
        loginButActionPerformed(evt);
    }//GEN-LAST:event_userPasswordActionPerformed

    private void registerButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButMouseClicked
        //Login window remains open, whilst opening the registration window
        r.setVisible(true);
    }//GEN-LAST:event_registerButMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void userEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userEmailMouseClicked
        if (!clicked) {
            userEmail.setText("");
            clicked = true;
        }
    }//GEN-LAST:event_userEmailMouseClicked

    private void userEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userEmailKeyTyped
        if (!clicked) {
            userEmail.setText("");
            clicked = true;
        }
    }//GEN-LAST:event_userEmailKeyTyped

    private void remEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remEmailActionPerformed
    }//GEN-LAST:event_remEmailActionPerformed

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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginBut;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel registerBut;
    private javax.swing.JCheckBox remEmail;
    private javax.swing.JTextField userEmail;
    private javax.swing.JPasswordField userPassword;
    // End of variables declaration//GEN-END:variables
}
