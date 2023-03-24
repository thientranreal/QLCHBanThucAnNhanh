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
                    "user=huy;"+
                    "password=7008;"+
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

//    public static void main(String[] args) {
//        JDBC conn = new JDBC();
//        if (conn.openConnection()) {
//            System.out.printf("Success!");
//        } else {
//            System.out.printf("Failed!");
//        }
//        closeConnection();
//    }
}
