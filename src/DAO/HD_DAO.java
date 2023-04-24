package DAO;

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
            JDBC.closeConnection();
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
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public ArrayList<String> getAllEm() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select EmployeeID, Name From Employee Where Status = 1";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("EmployeeID") + ":" + rs.getNString("Name"));
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }
    public ArrayList<String> getAllCus() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select CustomerID, Name From Customer Where Status = 1";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("CustomerID") + ":" + rs.getNString("Name"));
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    public int deleteOrder(String OrderId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From Orders Where OrderID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int deleteOrderAndAllProduct(String OrderId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From OrderDetail Where OrderID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.executeUpdate();

            sql = "Delete From Orders Where OrderID = ?";
            st = con.prepareStatement(sql);
            st.setString(1, OrderId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int updateOrder(String OrderId, String CusId, String EmId, String orderDate) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Update Orders Set CustomerID = ?, EmployeeID = ?, OrderDate = ? " +
                    "Where OrderID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, CusId);
            st.setString(2, EmId);
            st.setString(3, orderDate);
            st.setString(4, OrderId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
        }

        JDBC.closeConnection();
        return updatedRows;
    }

}
