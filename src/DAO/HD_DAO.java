package DAO;

import DTO.CT_HD_Product_DTO;
import DTO.HD_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HD_DAO {
    public ArrayList<HD_DTO> getAllOrders() {
        JDBC.openConnection();
        ArrayList<HD_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select OrderID, Customer.CustomerID CusID, Employee.EmployeeID EmID" +
                    ", OrderDate, Customer.Name CusName, Employee.Name EmName " +
                    "From Orders, Customer, Employee " +
                    "Where Customer.CustomerID = Orders.CustomerID and Employee.EmployeeID = Orders.EmployeeID";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new HD_DTO(rs.getString("OrderID"),
                        rs.getString("EmID"),
                        rs.getNString("EmName"),
                        rs.getString("CusID"),
                        rs.getNString("CusName"),
                        rs.getString("OrderDate")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    public int addNewHD(String OrderId, String emId, String cusId, String dateTime) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Insert Into Orders Values (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, cusId);
            st.setString(3, emId);
            st.setString(4, dateTime);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public ArrayList<HD_DTO> getAllEmCus() {
        JDBC.openConnection();
        ArrayList<HD_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select OrderID, Customer.CustomerID CusID, Employee.EmployeeID EmID" +
                    ", OrderDate, Customer.Name CusName, Employee.Name EmName " +
                    "From Orders, Customer, Employee " +
                    "Where Customer.CustomerID = Orders.CustomerID and Employee.EmployeeID = Orders.EmployeeID";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new HD_DTO(rs.getString("OrderID"),
                        rs.getString("CusID"),
                        rs.getNString("CusName"),
                        rs.getString("EmID"),
                        rs.getNString("EmName"),
                        rs.getString("OrderDate")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }
}
