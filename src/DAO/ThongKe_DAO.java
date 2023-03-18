package DAO;

import DTO.ThongKe_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKe_DAO {
    public ArrayList<String> getAllProduct() {
        JDBC.openConnection();
        ArrayList<String> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select ProductID, Name From Product";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("ProductID") + ":" + rs.getNString("Name"));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

//    Lay doanh thu theo ngay
    public ArrayList<ThongKe_DTO> thongKe(String fromDate, String toDate) {
        JDBC.openConnection();
        ArrayList<ThongKe_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select P.ProductID, Name, Price, Sum(Quantity) AS SLMua, Sum(Quantity) * Price AS Total\n" +
                    "From OrderDetail OD, Product P, Orders O\n" +
                    "Where OD.ProductID = P.ProductID And O.OrderID = OD.OrderID And Convert(Date, OrderDate) BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)\n" +
                    "Group By P.ProductID, Name, Price";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, fromDate);
            st.setString(2, toDate);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new ThongKe_DTO(rs.getString("ProductID"),
                        rs.getNString("Name"),
                        rs.getInt("SLMua"),
                        rs.getFloat("Price"),
                        rs.getFloat("Total")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }

//      Lay doanh thu theo ngay va ma SP
    public ArrayList<ThongKe_DTO> thongKeDieuKien(String fromDate, String toDate, String proId) {
        JDBC.openConnection();
        ArrayList<ThongKe_DTO> result = new ArrayList<>();

        try {
            Connection con = JDBC.getCon();
            String sql = "Select P.ProductID, Name, Price, Sum(Quantity) AS SLMua, Sum(Quantity) * Price AS Total\n" +
                    "From OrderDetail OD, Product P, Orders O\n" +
                    "Where OD.ProductID = P.ProductID And O.OrderID = OD.OrderID And Convert(Date, OrderDate) BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) " +
                    "And P.ProductID = ?\n"+
                    "Group By P.ProductID, Name, Price";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, fromDate);
            st.setString(2, toDate);
            st.setString(3, proId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new ThongKe_DTO(rs.getString("ProductID"),
                        rs.getNString("Name"),
                        rs.getInt("SLMua"),
                        rs.getFloat("Price"),
                        rs.getFloat("Total")));
            }
        } catch (SQLException e) {
            System.out.println("Không lấy được dữ liệu");
            return null;
        }

        JDBC.closeConnection();
        return result;
    }
}
