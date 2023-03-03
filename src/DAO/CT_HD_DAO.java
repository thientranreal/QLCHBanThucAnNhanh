package DAO;

import DTO.CT_HD_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CT_HD_DAO {
    public ArrayList<CT_HD_DTO> getCT_HD_ByOrderID(String orderID) {
        JDBC.openConnection();
        ArrayList<CT_HD_DTO> list = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select Orders.OrderID, Customer.CustomerID, Customer.Name CustomerName, Product.ProductID, Product.Name ProductName, Quantity, Price" +
                    " from OrderDetail, Orders, Customer, Product " +
                    "Where OrderDetail.OrderID = Orders.OrderID " +
                    "and Orders.CustomerID = Customer.CustomerID " +
                    "and OrderDetail.ProductID = Product.ProductID " +
                    "and Orders.OrderID = " + String.format("'%s'", orderID);
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            // add each row to arrayList
            CT_HD_DTO ct;
            while (rs.next()) {
                ct = new CT_HD_DTO();
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

//    public static void main(String[] args) {
//        CT_HD_DAO t = new CT_HD_DAO();
//        ArrayList<CT_HD_DTO> list = t.getCT_HD_ByOrderID("DH01");
//    }

    public class Product {
        private String id;
        private String name;

        public Product(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public ArrayList<Product> getProductIdAndName() {
        JDBC.openConnection();
        ArrayList<Product> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select ProductID, Name from Product";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Product(rs.getString("ProductID"), rs.getNString("Name")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.openConnection();
        return result;
    }
}
