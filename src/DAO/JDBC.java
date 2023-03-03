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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionStr = "jdbc:sqlserver://localhost:1433;"+
                    "databaseName=FastFood;"+
                    "user=sa;"+
                    "password=123456;"+
                    "encrypt=true;trustServerCertificate=true";
            con = DriverManager.getConnection(connectionStr);
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
