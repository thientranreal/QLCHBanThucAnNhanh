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
