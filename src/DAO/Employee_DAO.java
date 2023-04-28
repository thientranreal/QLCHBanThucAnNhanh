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
                String sql = "Select emp.*,UserName=acc.Username, Password=acc.Password from Employee emp left join Account acc on emp.AccountID = acc.AccountID where status=1";

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
                String sql = "INSERT INTO Employee values (?,?,?,?,?,?,?,?,1)";
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



    public boolean deleteEmp(String id){
        boolean flag = false;
        if (JDBC.openConnection()){
            try {
                String sql = "UPDATE Employee SET Status=0 WHERE EmployeeID = ?";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                stmt.setString(1, id);
                if(stmt.executeUpdate() >= 1){
                    flag= true;
                }
                stmt.close();

            }catch (SQLException ex){
                System.out.println("Error when trying to delete Employee! ");
            }finally {
                JDBC.closeConnection();
            }
        }
        return flag;
    }

    public boolean updateEmp(String employeeID,String name,String address,String phone,Date date,String Sex,String Role){
        boolean flag = false;
        if(JDBC.openConnection()){
            try{
                String sql = "UPDATE Employee SET  Name= ?,Address = ?, Phone = ?, DateOfBirth = ?, Sex = ?, Role = ? " + " WHERE EmployeeID = ? and Status=1";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);

//                stmt.setString(2,accountID);
                stmt.setString(1,name);
                stmt.setString(2,address);
                stmt.setString(3,phone);
//                stmt.setString(6,supplierID);
                stmt.setDate(4,date);
                stmt.setString(5,Sex);
                stmt.setString(6,Role);
                stmt.setString(7,employeeID);
                if(stmt.executeUpdate() >= 1){
                    flag= true;
                }
                stmt.close();
            }catch (SQLException ex){
                System.out.println("Error when trying to update data from database");
            }finally {
                JDBC.closeConnection();
            }
        }
        return flag;
    }


    public ArrayList<String> getAColumn(String columnName,String table){
        ArrayList<String> list = new ArrayList<String>();
        if (JDBC.openConnection()){
            try {
                String sql = "SELECT" + " " +columnName + " " + "FROM"+ " " + table;
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    list.add(rs.getString(columnName));
                }
                stmt.close();
            }catch (SQLException ex){
                System.out.println("Error when trying to get ArrayList Column");
            }finally {
                JDBC.closeConnection();
            }
        }
        return list;
    }

    public ArrayList<Employee_DTO>getEmpFromCondition(String condition,String columnName){
        String sql=null;
        ArrayList<Employee_DTO> arr = new ArrayList<Employee_DTO>();
        if(JDBC.openConnection()){
            try{
                Employee_DTO emp = new Employee_DTO();
                if(columnName.equals("Mã nhân viên")){
                    sql = "SELECT * FROM Employee WHERE EmployeeID = " + "N'"+condition+"'";
                }else if(columnName.equals("Tên nhân viên")){
                    sql= "SELECT * FROM Employee WHERE Name = "+ "N'"+condition+"'";
                }
                Statement stmt = JDBC.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    emp.setEmployeeID(rs.getString("EmployeeID"));
                    emp.setName(rs.getString("Name"));
                    emp.setAddress(rs.getNString("Address"));
                    emp.setPhone(rs.getString("Phone"));
                    emp.setDateOfBirth(rs.getDate("DateOfBirth"));
                    emp.setSex(rs.getString("Sex"));
                    emp.setRole(rs.getNString("Role"));
                    arr.add(emp);
                }
                stmt.close();
            }catch (SQLException ex){
                System.out.println("Error when trying to get data from condition to database");
            }finally {
                JDBC.closeConnection();
            }
        }
        return arr;
    }






}
