package DAO;

import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTTChung_DTO;
import DTO.CT_HD_ShowTable_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CT_HD_DAO {
    public ArrayList<CT_HD_ShowTable_DTO> getCT_HD_ByOrderID(String orderID) {
        JDBC.openConnection();
        ArrayList<CT_HD_ShowTable_DTO> list = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select Orders.OrderID, Customer.CustomerID, Customer.Name CustomerName, "+
                    "Product.ProductID, Product.Name ProductName, Quantity, Price" +
                    " from OrderDetail, Orders, Customer, Product " +
                    "Where OrderDetail.OrderID = Orders.OrderID " +
                    "and Orders.CustomerID = Customer.CustomerID " +
                    "and OrderDetail.ProductID = Product.ProductID " +
                    "and Orders.OrderID = " + String.format("'%s'", orderID);
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            // add each row to arrayList
            CT_HD_ShowTable_DTO ct;
            while (rs.next()) {
                ct = new CT_HD_ShowTable_DTO();
                ct.setOrderID(rs.getString("OrderID"));
                ct.setCustomerID(rs.getString("CustomerID"));
                ct.setCustomerName(rs.getNString("CustomerName"));
                ct.setProductID(rs.getString("ProductID"));
                ct.setProductName(rs.getNString("ProductName"));
                ct.setQuantity(rs.getInt("Quantity"));
                ct.setPrice(rs.getFloat("Price"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            return null;
        }

        JDBC.closeConnection();
        return list;
    }

    public ArrayList<CT_HD_Product_DTO> getProductInfo() {
        JDBC.openConnection();
        ArrayList<CT_HD_Product_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select ProductID, Name, Category, Stock from Product";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new CT_HD_Product_DTO(rs.getString("ProductID"),
                        rs.getNString("Name"),
                        rs.getNString("Category"),
                        rs.getInt("Stock")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

    // get data to display on TTChung
    public CT_HD_ShowTTChung_DTO getCusEmDetailByOrderId(String orderId) {
        JDBC.openConnection();
        CT_HD_ShowTTChung_DTO show = new CT_HD_ShowTTChung_DTO();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select Customer.CustomerID, Customer.Name CusName, "+
                    "Employee.EmployeeID EmId, Employee.Name EmName, OrderDate, "+
                    "Customer.Address, Customer.Phone " +
                    "from Orders, Employee, Customer " +
                    "where Orders.EmployeeID = Employee.EmployeeID and Orders.CustomerID = Customer.CustomerID " +
                    "and Orders.OrderID = '" + orderId + "'";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                show.setCusID(rs.getString("CustomerID"));
                show.setCusName(rs.getNString("CusName"));
                show.setEmID(rs.getString("EmId"));
                show.setEmName(rs.getNString("EmName"));
                show.setOrderDate(rs.getString("OrderDate"));
                show.setPhone(rs.getString("Phone"));
                show.setAddress(rs.getNString("Address"));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return show;
    }

    public int updateSLByProOrderId(String proId, String OrderId, int sl) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Update OrderDetail Set Quantity = ? Where OrderID = ? and ProductID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, sl);
            st.setString(2, OrderId);
            st.setString(3, proId);
            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }

    public int addNewCT_HD(String proId, String OrderId, int sl) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Insert Into OrderDetail Values (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, proId);
            st.setInt(3, sl);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
    public int updateProductStock(String proId, int stock) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Update Product Set Stock = ? Where ProductID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, stock);
            st.setString(2, proId);
            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
    public int deleteProduct(String OrderId, String proId) {
        JDBC.openConnection();
        int updatedRows = 0;

        try {
            Connection con = JDBC.getCon();
            String sql = "Delete From OrderDetail Where OrderID = ? and ProductID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, OrderId);
            st.setString(2, proId);

            updatedRows = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return updatedRows;
        }

        JDBC.closeConnection();
        return updatedRows;
    }
}
