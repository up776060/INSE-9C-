/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inse9c;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author up777621
 */
public class DAO {
    
    public DAO(){
        //connect();
    }
    /*
    public static Connection connect(){
        try {
        Class.forName("org.postgresql.Driver");
        String dbName = System.getenv("RDS_DB_NAME");
        String userName = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");
        String hostname = System.getenv("RDS_HOSTNAME");
        String port = System.getenv("RDS_PORT");
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        logger.trace("Getting remote connection with connection string from environment variables.");
        Connection con = DriverManager.getConnection(jdbcUrl);
        logger.info("Remote connection successful.");
      return con;
        }
    catch (ClassNotFoundException e) { logger.warn(e.toString());}
    catch (SQLException e) { logger.warn(e.toString());}
        }
    }
    
    */
    
    public static byte[] computeHash(String x) throws Exception{
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }
    
    public static String byteArraytoHexString(byte[] b){
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++){
            int v = b[i] & 0xff;
            if(v < 16)
                sb.append('0');
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
    
    
}
