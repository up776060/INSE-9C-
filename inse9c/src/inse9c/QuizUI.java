package inse9c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

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

    // 2 Dimensional array to store questions and possible answers. Array for correct answer.
    private Thread thread = new Thread();
    private Timer countdownTimer;
    private int maxQuestions;
    private String[][] question1;
    private String[] correctans;
    private String quizTopic;
    private String quizType;
    private int userID;
    private int score;
    private int count;
    private int i = 0;
    private int timeRemaining = 0;
    private ButtonGroup bg = new ButtonGroup();
    // All Radio buttons grouped so 1 active at a time

    public QuizUI() {
        initComponents();
    }

    /**
     * Creates new form NewJFrame
     *
     * @param topic
     */
    public QuizUI(String topic, String quizT, int iD) {
        initComponents();
        this.setLocationRelativeTo(null);
        checkBg();
        maxQuestions = 10;
        quizTopic = topic;
        quizType = quizT;
        userID = iD;

        switch (quizT) {
            case "Topic":
                timeRemaining = 600;
                break;
            case "Quiz":
                timeRemaining = 600;
                break;
            case "Mock":
                timeRemaining = 3600;
                maxQuestions = 50;
                break;
        }

        switch (quizTopic) {
            case "Alertness":
                headerLabel.setText("Topic A: " + quizTopic);
                break;
            case "Attitude":
                headerLabel.setText("Topic B: " + quizTopic);
                break;
            case "Safety and your vehicle":
                headerLabel.setText("Topic C: " + quizTopic);
                break;
            case "Hazard Awareness":
                headerLabel.setText("Topic D: " + quizTopic);
                break;
            case "Safety Margin":
                headerLabel.setText("Topic E: " + quizTopic);
                break;
            case "Motorway Rules":
                headerLabel.setText("Topic F: " + quizTopic);
                break;
            case "Vehicle Handling":
                headerLabel.setText("Topic G: " + quizTopic);
                break;
            case "Quiz":
                headerLabel.setText("Quiz");
                break;
            case "Mock Test":
                headerLabel.setText("Mock Test");
                break;
        }
        ResultSet rs ;
        i = 0;
        score = 0;
        try {
            if(quizTopic.matches("Mock Test") || quizTopic.matches("Quiz") ){
                rs = DAO.retrieveQuiz();
            } else{
                rs = DAO.retrieveQuizByTopic(quizTopic);
            }

            if (quizTopic.matches("Mock Test")) {
                question1 = new String[50][5];
                correctans = new String[50];
            } else {
                question1 = new String[10][5];
                correctans = new String[10];
            }

            for (int i = 0; i < maxQuestions; i++) {
                rs.next();
                question1[i][0] = rs.getString("questionContents");
                question1[i][1] = rs.getString("correctAns");
                question1[i][2] = rs.getString("wrongAns1");
                question1[i][3] = rs.getString("wrongAns2");
                question1[i][4] = rs.getString("wrongAns3");

                correctans[i] = rs.getString("correctAns");;
            }
            //code to ensure the question does not escape the border
            if (question1[0][0].length() > 80) {
                int k = 65;
                boolean changed = false;
                do {
                    if (question1[0][0].charAt(k) == ' ') {
                        StringBuilder question = new StringBuilder(question1[0][0]);
                        question.setCharAt(k, '\n');

                        question1[0][0] = question.toString();
                        changed = true;
                    }
                    k++;
                } while (!changed);
            }

            TextfieldQuestion.setText(question1[0][0]);
            AnswerA.setText(question1[0][1]);
            AnswerA.setActionCommand(question1[0][1]);
            AnswerB.setText(question1[0][2]);
            AnswerB.setActionCommand(question1[0][2]);
            AnswerC.setText(question1[0][3]);
            AnswerC.setActionCommand(question1[0][3]);
            AnswerD.setText(question1[0][4]);
            AnswerD.setActionCommand(question1[0][4]);

        } catch (SQLException ex) {
            Logger.getLogger(QuizUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Array of 3 questions (change 1st number to make bigger array)
        // Assigns textField and radioButton values for 1st question!
        countdownTimer = new Timer(1000, new CountdownTimerListener(this));
        countdownTimer.start();
    }

    class CountdownTimerListener implements ActionListener {

        QuizUI q;

        public CountdownTimerListener(QuizUI ui) {
            q = ui;
        }

        public void actionPerformed(ActionEvent e) {
            if (--timeRemaining > 0) {
                int minutes = 0;
                int seconds = 0;
                minutes = timeRemaining / 60;
                seconds = timeRemaining % 60;
                timer.setText(String.valueOf(minutes + ":" + seconds));
            } else {
                timer.setText("Time's up!");
                q.setVisible(false);
                q.dispose();
                End end = new End(score, correctans.length, userID, quizTopic);
                end.setVisible(rootPaneCheckingEnabled);
                countdownTimer.stop();
            }
        }
    }

    public void checkBg() {
        String readCol = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("colourSettings.txt"));
            readCol = br.readLine();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class
                    .getName()).log(Level.SEVERE, null, ex);
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

        headerLabel = new javax.swing.JLabel();
        AnswerA = new javax.swing.JRadioButton();
        AnswerB = new javax.swing.JRadioButton();
        AnswerD = new javax.swing.JRadioButton();
        AnswerC = new javax.swing.JRadioButton();
        ButtonNext = new javax.swing.JButton();
        ButtonHint = new javax.swing.JButton();
        ButtonExit = new javax.swing.JButton();
        lbScore = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextfieldQuestion = new javax.swing.JTextArea();
        timer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        headerLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        headerLabel.setText("Quiz Name");

        bg.add(AnswerA);
        AnswerA.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerA.setText("A: This is answer A");
        AnswerA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerAActionPerformed(evt);
            }
        });

        bg.add(AnswerB);
        AnswerB.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerB.setText("B: Here's another answer");
        AnswerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerBActionPerformed(evt);
            }
        });

        bg.add(AnswerD);
        AnswerD.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        AnswerD.setText("D: What about this one?");
        AnswerD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerDActionPerformed(evt);
            }
        });

        bg.add(AnswerC);
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

        lbScore.setText("Score: ");

        TextfieldQuestion.setEditable(false);
        TextfieldQuestion.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        TextfieldQuestion.setColumns(20);
        TextfieldQuestion.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        TextfieldQuestion.setLineWrap(true);
        TextfieldQuestion.setRows(3);
        jScrollPane1.setViewportView(TextfieldQuestion);

        timer.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        timer.setText("60:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AnswerB)
                    .addComponent(AnswerA)
                    .addComponent(AnswerD)
                    .addComponent(AnswerC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(212, 212, 212)
                                .addComponent(headerLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                                        .addComponent(lbScore)
                                        .addGap(53, 53, 53))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(timer)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
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
                    .addComponent(ButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbScore)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(headerLabel)
                            .addComponent(timer))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(AnswerA)
                .addGap(18, 18, 18)
                .addComponent(AnswerB)
                .addGap(18, 18, 18)
                .addComponent(AnswerC)
                .addGap(18, 18, 18)
                .addComponent(AnswerD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
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

        if (AnswerA.isSelected() == false && AnswerB.isSelected() == false && AnswerC.isSelected() == false && AnswerD.isSelected() == false) {

            JOptionPane.showMessageDialog(null, "Please select an answer before continuing.");
        } else {

            // Gets data from radio button. If same as answer, reward a point
            String submit = bg.getSelection().getActionCommand().toString();
            if (submit.equals(correctans[i])) {
                score = score + 1;
                lbScore.setText("Score: " + score);

                String sound = "true";

                try {
                    BufferedReader br = new BufferedReader(new FileReader("soundSettings.txt"));
                    sound = br.readLine();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Settings.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Settings.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                if (sound.equals("true")) {
                    try {
                        String soundName = "correctAnswerNoise.wav";
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();

                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(QuizUI.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(QuizUI.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(QuizUI.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            if (quizTopic.matches("Mock Test") && i == 49) {
                End t = new End(score, correctans.length, userID, quizTopic);
                t.setVisible(true);
                this.setVisible(false);
                dispose();
            } else if(!quizTopic.matches("Mock Test") && i == 9){
                End t = new End(score, correctans.length, userID, quizTopic);
                t.setVisible(true);
                this.setVisible(false);
                dispose();
            }
            // Increment i by 1 
            i++;
            
            if(quizTopic.matches("Mock Test") && i > 49){
                i=49;
            }else if(!quizTopic.matches("Mock Test") && i > 9){
                i=9;
            }
            
            Random ran = new Random();
            // Display next question

            int a1 = ran.nextInt(4) + 1;

            if (question1[i][0].length() > 80) {
                int k = 65;
                boolean changed = false;
                do {
                    if (question1[i][0].charAt(k) == ' ') {
                        StringBuilder question = new StringBuilder(question1[i][0]);
                        question.setCharAt(k, '\n');

                        question1[i][0] = question.toString();
                        changed = true;
                    }
                    k++;
                } while (!changed);
            }

            TextfieldQuestion.setText(question1[i][0]);

            AnswerA.setText(question1[i][a1]);
            AnswerA.setActionCommand(question1[i][a1]);

            int a2 = ran.nextInt(4) + 1;
            while (a2 == a1) {
                a2 = ran.nextInt(4) + 1;
            }

            AnswerB.setText(question1[i][a2]);
            AnswerB.setActionCommand(question1[i][a2]);

            int a3 = ran.nextInt(4) + 1;
            while (a3 == a1 || a3 == a2) {
                a3 = ran.nextInt(4) + 1;
            }
            AnswerC.setText(question1[i][a3]);
            AnswerC.setActionCommand(question1[i][a3]);

            int a4 = ran.nextInt(4) + 1;
            while (a4 == a1 || a4 == a2 || a4 == a3) {
                a4 = ran.nextInt(4) + 1;
            }
            AnswerD.setText(question1[i][a4]);
            AnswerD.setActionCommand(question1[i][a4]);

        }

        if (i == 9 && !quizType.matches("Mock")) {
            ButtonNext.setText("Submit");
        } else if(i==49){
            ButtonNext.setText("Submit");
        }
        // WHAT NEEDS TO BE DONE!
        // End of quiz screen displayed
        // Hint (could be as simple as changing text color to red for 2 items)
        if (timer.getText().matches("Time's up!")) {
            this.setVisible(false);
            End t = new End(score, correctans.length, userID, quizTopic);
            t.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_ButtonNextActionPerformed

    private void ButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExitActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "alert", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Menu m = new Menu();
            m.setVisible(true);
            this.setVisible(false);
            dispose();
        }
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
            java.util.logging.Logger.getLogger(QuizUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JTextArea TextfieldQuestion;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel timer;
    // End of variables declaration//GEN-END:variables
}
