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
 * The purpose of the DAO is to interact with hard datastores, such as the
 * database server, or the local settings files
 *
 * @author up777621
 */
public class DAO {

    private static Statement stmt = null;
    private static Connection conn = null;
    private static String sql = "";
    private static String email;
    private static int userID;

    /**
     * DAO can be initialised with a user's email
     *
     * @param em
     */
    public DAO(String em) {
        email = em;
    }

    /**
     * DAO can be initialised with a userID
     *
     * @param iD
     */
    public DAO(int iD) {
        userID = iD;
    }

    /**
     * DAO can be initialised without an input
     */
    public DAO() {

    }

    /**
     * The purpose of this function is to establish a connection with our
     * database, to be returned so that an SQL command can be run
     *
     * @return
     */
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

    /**
     * This will update a user's password with a new one when called
     *
     * @param password
     * @throws SQLException
     */
    public static void changePassword(String password, int iD)
            throws SQLException {
        conn = connect();
        sql = "UPDATE User SET userPassword = '" + password + "' WHERE userID = '"+ iD +"'";
        stmt.execute(sql);
        conn.close();
    }

    /**
     * This function will add a new user to the User table.
     *
     * @param fName
     * @param sName
     * @param dob
     * @param email
     * @param pass
     * @throws SQLException
     */
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

    /**
     * This function will retrieve a user by their email To be used for login
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public static ResultSet retrieveLoginByEmail(String email)
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from User where userEmail = '" + email + "'");
        return rs;
    }

    /**
     * This will retrieve a user by their userID
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static ResultSet retrieveLoginByUserID(int id)
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from User where userID = '" + id + "'");
        return rs;
    }

    /**
     * This will randomly retrieve questions from the Question table in our
     * database with limit 50, but can be cut short in case of a general quiz
     *
     * @return
     * @throws SQLException
     */
    public static ResultSet retrieveQuiz()
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from Question ORDER BY RAND() LIMIT 50");
        return rs;
    }

    /**
     * This will retrieve in a random order, 10 questions from the Question
     * table in our database
     *
     * @param topic
     * @return
     * @throws SQLException
     */
    public static ResultSet retrieveQuizByTopic(String topic)
            throws SQLException {
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from Question where questionTopic = '" + topic + "' ORDER BY RAND() LIMIT 50");
        return rs;
    }

    /**
     * This method will compute the hash of a given string. For the use of
     * encrypting passwords
     *
     * @param x
     * @return
     * @throws Exception
     */
    public static byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    /**
     * This will take the hash made from computeHash, and convert it into an
     * encrypted string, ready to be sorted with or into the database
     *
     * @param b
     * @return
     */
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

    /**
     * When a test ends, the test result will always be stored to the database
     * through this method
     *
     * @param topic
     * @param score
     */
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

    /**
     * This method will return 10 results, based on the topic parsed with it. In
     * the event of the topic being blank, the ten most recent results
     * regardless of topics will be taken
     *
     * @param userID
     * @param topic
     * @return
     * @throws SQLException
     */
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

    /**
     * This function is only called by Topic_Practice This takes a userID, and
     * calculates an average based on the user's last 5 tests for a given topic
     *
     * @param userID
     * @param topic
     * @return
     * @throws SQLException
     */
    public static int getRecentTopicScores(int userID, String topic)
            throws SQLException {
        int scoreAvg = 0;
        int totalScore = 0;
        conn = connect();
        ResultSet rs = stmt.executeQuery("select * from testResult where userID = '" + userID + "' and testType = '" + topic + "' limit 5");

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

    /**
     * Returns the possible top score for a test based on it's topic
     *
     * @param topic
     * @return
     */
    public static double getTopScore(String topic) {
        if (topic.matches("Mock")) {
            return 50;
        }
        return 10;
    }

    /**
     * Sets the email stored in the DAO;
     * @param newEmail 
     */
    public static void setEmail(String newEmail) {
        email = newEmail;
    }
    /**
     * Retrieves the email stored in this DAO
     * @return 
     */
    public static String getEmail() {
        return email;
    }
    /**
     * this function is called by Settings only
     * it's purpose is to toggle the value of soundSettings.txt 
     * @param button 
     */
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
