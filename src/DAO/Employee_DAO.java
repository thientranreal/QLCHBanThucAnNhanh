package DAO;
import DTO.Employee_DTO;
import DTO.product_DTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee_DAO {


    public ArrayList<Employee_DTO>getAllEmployee(){
        ArrayList<Employee_DTO> arr = new ArrayList<Employee_DTO>();
        if (JDBC.openConnection()) {
            try {
                String sql = "Select emp.*,UserName=acc.Username, Password=acc.Password from Employee emp left join Account acc on emp.AccountID = acc.AccountID";
                Statement stml = JDBC.getCon().createStatement();
                ResultSet rs = stml.executeQuery(sql);
                while (rs.next()) {
                    Employee_DTO emp = new Employee_DTO();
                    emp.setEmployeeID(rs.getString("EmployeeID"));
                    emp.setAccountID(rs.getString("AccountID"));
                    emp.setName(rs.getNString("Name"));
                    emp.setAddress(rs.getNString("Address"));
                    emp.setPhone(rs.getString("Phone"));
                    emp.setDateOfBirth(rs.getDate("DateOfBirth"));
                    emp.setSex(rs.getNString("Sex"));
                    emp.setRole(rs.getString("Role"));
                    emp.setUserName(rs.getString("UserName"));
                    emp.setPassword(rs.getString("Password"));
                    arr.add(emp);
                }
                stml.close();
            } catch (SQLException ex) {
                System.out.println("Error when get data employee from SQL Server");
            } finally {
                JDBC.closeConnection();
            }
        }
        return arr;
    }


    public boolean addEmployee(Employee_DTO emp) {
        boolean flag = false;
        if (JDBC.openConnection()) {
            try {
                String sql = "INSERT INTO Employee values (?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                stmt.setString(1, emp.getEmployeeID());
                stmt.setString(2, emp.getAccountID());
                stmt.setString(3, emp.getName());
                stmt.setString(4, emp.getAddress());
                stmt.setString(5, emp.getPhone());
                stmt.setDate(6, emp.getDateOfBirth());
                stmt.setString(7, emp.getSex());
                stmt.setString(8, emp.getRole());
                if (stmt.executeUpdate() >= 1) {
                    flag = true;
                }
                stmt.close();

            } catch (SQLException ex) {
                System.out.println("Lỗi khi thực hiện thêm nhân viên");
            } finally {
                JDBC.closeConnection();
            }
        }
        return flag;
    }



    public boolean hasEmployeeID(String id){
        if (JDBC.openConnection()){
            try{
                ArrayList<String> listEmployeeID = new ArrayList<>();
                String sql = "Select EmployeeID from Employee";
                Statement stmt = JDBC.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    listEmployeeID.add(rs.getString("EmployeeID"));
                }
                for (String item : listEmployeeID){
                    if(item.equals(id)){
                        return true;
                    }
                }

            }catch (SQLException ex){
                System.out.println("Lỗi khi thực hiện kiểm tra nhân viên tồn tại");
            }
        }
        return  false;
    }








}
