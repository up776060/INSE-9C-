/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inse9c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author up777621
 */
public class DAO {

    private static Statement stmt = null;
    private static Connection conn = null;
    private static String sql = "";
    private static String email;
    private static int userID;

    public DAO(String em) {
        email = em;
    }

    public DAO(int iD) {
        userID = iD;
    }

    public DAO() {

    }

    public static Connection connect() {

        try {
            // Load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            // Open the connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://inse9c.cslzqhqslqaw.eu-west-2.rds.amazonaws.com:3306/inse9c", "admin", "Inseteam9c");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
            System.out.println("Exception: " + ce);
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            System.out.println("Exception: " + sqe);
        }
        if (conn == null) {
            System.out.println("Connection Failed");
        }
        return conn;
    }

    public static void changePassword(String password)
            throws SQLException {
        conn = connect();
        sql = "UPDATE User SET userPassword = '" + password + "' WHERE userEmail = '" + email + "'";
        stmt.execute(sql);
        conn.close();
    }

    public static void registerUser(
            String fName, String sName, String dob,
            String email, String pass)
            throws SQLException {
        conn = connect();
        sql = "insert into User (userFname, userLname, userEmail, userDOB, userPassword) values("
                + "'" + fName + "'," + "'" + sName + "'," + "'" + email + "'," + "'" + dob + "'," + "'" + pass + "')";
        stmt.execute(sql);
        conn.close();
    }

    public static ResultSet retrieveLoginByEmail(String email)
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from User where userEmail = '" + email + "'");
        return rs;
    }

    public static ResultSet retrieveLoginByUserID(int id)
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from User where userID = '" + id + "'");
        return rs;
    }

    public static ResultSet retrieveQuiz()
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from Question ORDER BY RAND() LIMIT 10");
        return rs;
    }

    public static byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    public static String byteArraytoHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public static void storeTestResult(String topic, int score) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            String date = dtf.format(localDate); //2016/11/16

            conn = connect();
            sql = "insert into testResult (userID, testType, testScore, testDate) values('" + userID + "','" + topic + "','" + score + "','" + date + "')";
            stmt.execute(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ResultSet getTenResultsByTopic(int userID, String topic)
            throws SQLException {
        ResultSet rs;
        if (topic.matches("")) {
            conn = connect();
            rs = stmt.executeQuery("select * from testResult where userID = '" + userID + "' order by testResultID desc limit 10");
            return rs;
        } else {
            conn = connect();
            rs = stmt.executeQuery("select * from testResult where userID = '" + userID + "' and testType = '" + topic + "' order by testResultID desc limit 10");
            return rs;
        }
    }

    public static int getRecentTopicScores(int userID, String topic)
            throws SQLException {
        int scoreAvg = 0;
        int totalScore = 0;
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from testResult where userID = '" + userID + "' and testType = '" + topic + "'");

        int rowcount = 0;
        if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        int scores[] = new int[rowcount];
        int i = 0;
        while (rs.next()) {
            scores[i] = rs.getInt("testScore");
            i++;
        }

        for (int j = 0; j < rowcount; j++) {
            totalScore += scores[j];
        }

        double topScore = getTopScore(topic) * rowcount;
        if (rowcount > 0) {
            double rawAvg = (totalScore / topScore) * 100;
            scoreAvg = (int) rawAvg;
        }

        return scoreAvg;
    }

    public static double getTopScore(String topic) {
        if (topic.matches("Mock")) {
            return 50;
        }
        return 10;
    }

    public static void setEmail(String newEmail) {
        email = newEmail;
    }

    public static String getEmail() {
        return email;
    }

    public void toggleSound(boolean button) {
        String sound = "true";

        try {
            BufferedReader br = new BufferedReader(new FileReader("soundSettings.txt"));
            sound = br.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (sound.equals("true")) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("soundSettings.txt"));
                out.write("False");
                out.close();
            } catch (IOException e) {
            }
        } else {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("soundSettings.txt"));
                out.write("true");
                out.close();
            } catch (IOException e) {
            }
        }
    }
}
