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
 * This class is the environment for all quizzes
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

    /**
     * DO NOT USE
     */
    public QuizUI() {
        initComponents();
    }

    /**
     * initialises the UI, as well as retrieving all the questions from the
     * database This will also display the correct quiz title
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
        //determines time needed for quiz
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
        questionCounter.setText("Question:  0 / " +maxQuestions);
        //This will display the title
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
        ResultSet rs;
        i = 0;
        score = 0;
        try {
            //if this test is a mock or quiz, no categorising needs to happen
            if (quizTopic.matches("Mock Test") || quizTopic.matches("Quiz")) {
                rs = DAO.retrieveQuiz();
            } else {
                //this retrieves 10 questions in random order of a given topic
                rs = DAO.retrieveQuizByTopic(quizTopic);
            }
            //if this is a moc test, there will be 50 questions. Therefore, the
            //array size will need changing
            if (quizTopic.matches("Mock Test")) {
                question1 = new String[50][5];
                correctans = new String[50];
            } else {
                question1 = new String[10][5];
                correctans = new String[10];
            }
            //this populates the created arrays with questions returned from DB
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

        //This creates and starts a countdown timer
        countdownTimer = new Timer(1000, new CountdownTimerListener(this));
        countdownTimer.start();
    }

    /**
     * This class is used to run a countdown in the background. If the timer
     * reaches 0, the quiz is automatically shut off
     */
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

    /**
     * changes background colour based on settings
     */
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
        ButtonExit = new javax.swing.JButton();
        lbScore = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextfieldQuestion = new javax.swing.JTextArea();
        timer = new javax.swing.JLabel();
        questionCounter = new javax.swing.JLabel();

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

        questionCounter.setText("Question: /");

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
                                .addGap(18, 18, 18)
                                .addComponent(questionCounter)
                                .addGap(160, 160, 160)
                                .addComponent(headerLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(questionCounter))
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

    /**
     * If the next button is pressed, then the next question must be loaded. If
     * this is the last question, then the test must end
     *
     * @param evt
     */
    private void ButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNextActionPerformed
        if (AnswerA.isSelected() == false && AnswerB.isSelected() == false && AnswerC.isSelected() == false && AnswerD.isSelected() == false) {

            JOptionPane.showMessageDialog(null, "Please select an answer before continuing.");
        } else {

            // Gets data from radio button. If same as answer, reward a point
            String submit = bg.getSelection().getActionCommand().toString();
            if (submit.equals(correctans[i])) {
                score = score + 1;
                lbScore.setText("Score: " + score);

                String sound = "true";
                //If the answer is correct, and the sound settings are set to "on" then
                //a ding sound is to be played to tell the user he's correct
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
            
            questionCounter.setText("Question:  "+(i+1) + " / " + correctans.length);
            //if the test is a mock, and the last question has been answered, then
            //the quiz must end when submit is pressed. The timer is also stopped here
            if (quizTopic.matches("Mock Test") && i == 49) {
                End t = new End(score, correctans.length, userID, quizTopic);
                t.setVisible(true);
                this.setVisible(false);
                countdownTimer.stop();
                dispose();
            } else if (!quizTopic.matches("Mock Test") && i == 9) {
                //This code does the same, but for non mocks
                End t = new End(score, correctans.length, userID, quizTopic);
                t.setVisible(true);
                this.setVisible(false);
                countdownTimer.stop();
                dispose();
            }
            // Increment i by 1 
            i++;
            //to prevent overflow
            if (quizTopic.matches("Mock Test") && i > 49) {
                i = 49;
            } else if (!quizTopic.matches("Mock Test") && i > 9) {
                i = 9;
            }

            Random ran = new Random();
            // Display next question

            int a1 = ran.nextInt(4) + 1;
            
            //This code was built to prevent any overflow of text on the UI
            //the first blank space after character 65 is replaced with a new line
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

            //Displays new questions for the user
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
        //changes next question button to display "Submit" when answering the last
        //question in the quiz
        if (i == 9 && !quizType.matches("Mock")) {
            ButtonNext.setText("Submit");
        } else if (i == 49) {
            ButtonNext.setText("Submit");
        }
        //fail safe for timer conditions not working
        if (timer.getText().matches("Time's up!")) {
            this.setVisible(false);
            End t = new End(score, correctans.length, userID, quizTopic);
            t.setVisible(true);
            countdownTimer.stop();
            dispose();
        }
    }//GEN-LAST:event_ButtonNextActionPerformed

    /**
     * This returns the user to the menu, and terminates this UI, as well as the timer
     * @param evt 
     */
    private void ButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExitActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "alert", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Menu m = new Menu(userID);
            countdownTimer.stop();
            m.setVisible(true);
            this.setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_ButtonExitActionPerformed

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
    private javax.swing.JButton ButtonNext;
    private javax.swing.JTextArea TextfieldQuestion;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel questionCounter;
    private javax.swing.JLabel timer;
    // End of variables declaration//GEN-END:variables
}
