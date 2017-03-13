/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inse9c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author up777621
 */
public class DAO {

    private static Statement stmt = null;
    
    public DAO() {
    }

    public static Connection connect(){
        Connection conn=null;
        try{
            // Load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            // Open the connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://inse9c.cslzqhqslqaw.eu-west-2.rds.amazonaws.com:3306","admin","Inseteam9c");
            stmt = conn.createStatement();
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
            System.out.println("Exception: "+ce);
        }catch(SQLException sqe){
            sqe.printStackTrace();
            System.out.println("Exception: "+sqe);
        }
        if (conn == null){
            System.out.println("Connection Failed");
        }
        return conn;
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
}
