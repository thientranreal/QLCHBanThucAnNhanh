package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC {
    private static Connection con;

    public static Connection getCon() {
        return con;
    }

    public static boolean openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            jdbc:mysql://hostname:portNumber/databaseName
            String databaseURL = "jdbc:mysql://localhost:3306/FastFood";
            String username = "root"; String password = "Dai280903@";
            con = DriverManager.getConnection(databaseURL,username,password);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    public static void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

