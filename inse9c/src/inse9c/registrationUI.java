/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inse9c;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.lang.Object;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author up777621
 */
public class registrationUI extends javax.swing.JFrame {

    private String email, fName, sName, dob, password;

    /**
     * Creates new form registrationUI
     */
    public registrationUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        checkBg();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        userFirstName = new javax.swing.JTextField();
        userSurname = new javax.swing.JTextField();
        userEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        acceptTerms = new javax.swing.JCheckBox();
        userPassword = new javax.swing.JPasswordField();
        userConfirmPassword = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        dobDay = new javax.swing.JComboBox<>();
        dobMonth = new javax.swing.JComboBox<>();
        dobYear = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Register");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("First Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Last Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Date of Birth:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Password:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Confirm Password:");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        userFirstName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        userFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFirstNameActionPerformed(evt);
            }
        });

        userSurname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        userEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Terms and Conditions");
        jScrollPane1.setViewportView(jTextArea1);

        acceptTerms.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        acceptTerms.setText("I agree to the terms and conditions");
        acceptTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptTermsActionPerformed(evt);
            }
        });

        userPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        userConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        dobDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        dobMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        dobYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userEmail)
                                    .addComponent(userConfirmPassword)
                                    .addComponent(userSurname)
                                    .addComponent(userPassword)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(userFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(dobDay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(dobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(dobYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptTerms)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(304, 304, 304)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(userSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dobDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(userConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(acceptTerms))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFirstNameActionPerformed
    }//GEN-LAST:event_userFirstNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Test all of the inputs are full

        //getSelectedItem
        if (userFirstName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter a First Name");
        } else if (userSurname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter a Surname");
        } else if (!dobIsValid().equals("")) {
            JOptionPane.showMessageDialog(null, dobIsValid());
        } else if (userEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter an Email");
        } else if (userPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter a Password");
        } else if (userConfirmPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Confirm your Password");
        } else if (!userConfirmPassword.getText().equals(userPassword.getText())) {
            JOptionPane.showMessageDialog(null, "Please Confirm the correct Password");
        } else if (!acceptTerms.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please accept the terms and conditions");
        } else if (userPassword.getText().length() <= 7 || userPassword.getText().length() >= 33) {
            JOptionPane.showMessageDialog(null, "Password must be 8 or more characters");
        } else if (!userEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "The email entered is not acceptable");
        } else {

            //Store the information
            //locally for now Database support and DAO intergration later 15/02/17
            email = userEmail.getText();
            fName = userFirstName.getText();
            sName = userSurname.getText();
            dob = dobYear.getSelectedItem() + "-" + determineMonth(dobMonth.getSelectedItem().toString()) + "-" + dobDay.getSelectedItem();
            try {
                password = DAO.byteArraytoHexString(DAO.computeHash(userPassword.getText()));
            } catch (Exception ex) {
                Logger.getLogger(registrationUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                DAO.registerUser(fName, sName, dob, email, password);
            } catch (SQLException ex) {
                Logger.getLogger(registrationUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        if (readCol.equals("Default")) {
            this.getContentPane().setBackground(null);
        }

    }

    private void acceptTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptTermsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptTermsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }

    public String getDOB() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String dobIsValid() {
        /*if there's something wrong with their dob, a string is returned with
        the exact error
         */
        if (dobDay.getSelectedItem().equals("Day") || dobMonth.getSelectedItem().equals("Month") || dobYear.getSelectedItem().equals("Year")) {
            return "Date not entered correctly";
        } else if (2001 <= Integer.parseInt(dobYear.getSelectedItem().toString())) {
            return "You must be 16 years of age to apply for this application";
        }
        return "";
    }

    public String determineMonth(String textMonth) {
        if (textMonth.equals("January")) {
            return "01";
        }
        if (textMonth.equals("February")) {
            return "02";
        }
        if (textMonth.equals("March")) {
            return "03";
        }
        if (textMonth.equals("April")) {
            return "04";
        }
        if (textMonth.equals("May")) {
            return "05";
        }
        if (textMonth.equals("June")) {
            return "06";
        }
        if (textMonth.equals("July")) {
            return "07";
        }
        if (textMonth.equals("August")) {
            return "08";
        }
        if (textMonth.equals("September")) {
            return "09";
        }
        if (textMonth.equals("October")) {
            return "10";
        }
        if (textMonth.equals("November")) {
            return "11";
        }
        if (textMonth.equals("December")) {
            return "12";
        }

        return "1";
    }

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
            java.util.logging.Logger.getLogger(registrationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registrationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registrationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registrationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registrationUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox acceptTerms;
    private javax.swing.JComboBox<String> dobDay;
    private javax.swing.JComboBox<String> dobMonth;
    private javax.swing.JComboBox<String> dobYear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPasswordField userConfirmPassword;
    private javax.swing.JTextField userEmail;
    private javax.swing.JTextField userFirstName;
    private javax.swing.JPasswordField userPassword;
    private javax.swing.JTextField userSurname;
    // End of variables declaration//GEN-END:variables
}
