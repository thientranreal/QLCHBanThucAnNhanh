package DAO;

import DTO.Access_DTO;

import java.sql.*;
import java.util.ArrayList;

public class Access_DAO {
    private JDBC conn = new JDBC();

    public ArrayList<Access_DTO> getAllAccesses() {
        ArrayList<Access_DTO> Accesslist = new ArrayList<>();
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Access;";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Access_DTO access = new Access_DTO();
                    access.setAccessID(rs.getString("AccessID"));
                    access.setName(rs.getString("Name"));
                    access.setAuthority(rs.getString("Authority"));

                    Accesslist.add(access);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return Accesslist;
    }

    public Access_DTO getAccessFromID(String AccessID) {
        Access_DTO access = new Access_DTO();
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select * from Access Where AccessID='" + AccessID + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                rs.next();
                access.setAccessID(AccessID);
                access.setName(rs.getString("Name"));
                access.setAuthority(rs.getString("Authority"));

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return access;
    }

    public String getIDFromAuthority(String Authority) {
        String AccessID = "";
        if (conn.openConnection()) {
            try {
                String sqlQuery = "Select AccessID from Access Where Authority='" + Authority + "';";
                Statement stmt = conn.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                rs.next();
                AccessID = rs.getString("AccessID");

            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.closeConnection();
            }
        }
        return AccessID;
    }
}
