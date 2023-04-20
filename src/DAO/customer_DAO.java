package DAO;

import DAO.JDBC;
import DTO.customer_DTO;
import BUS.customer_BUS;
import java.util.ArrayList;
import java.sql.*;
public class customer_DAO {
    private JDBC connnect = new JDBC();
    public ArrayList<customer_DTO> getAllCustomers(){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try {
                String sqlQuery = "Select * from Customer;";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public customer_DTO getCustomerByID(String customerID){
        customer_DTO customer = new customer_DTO();
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Select * from Customer where CustomerID ='" + customerID + "';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                resultSet.next();
                customer.setCustomerID(customerID);
                customer.setCustomerName(resultSet.getString("Name"));
                customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                customer.setCustomerAddress(resultSet.getString("Address"));
                customer.setCustomerPhone(resultSet.getString("Phone"));
                customer.setCustomerSex(resultSet.getString("Sex"));
                customer.setCustomerPoint(resultSet.getInt("Point"));
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customer;
    }

    public boolean addCustomer(customer_DTO customer){
        boolean result = false;
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Insert into Customer values(?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement stmt = connnect.getCon().prepareStatement(sqlQuery);
                stmt.setString(1,customer.getCustomerID());
                stmt.setString(2,customer.getCustomerName());
                stmt.setString(3,customer.getCustomerDayOfBirth());
                stmt.setString(4,customer.getCustomerAddress());
                stmt.setString(5,customer.getCustomerPhone());
                stmt.setString(6,customer.getCustomerSex());
                stmt.setInt(7,customer.getCustomerPoint());
                if (stmt.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return result;
    }

    public boolean deleteCustomer(String customerID){
        boolean result = false;
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Delete from Customer where CustomerID = ?;";
                PreparedStatement stmt = connnect.getCon().prepareStatement(sqlQuery);
                stmt.setString(1,customerID);
                if (stmt.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return result;
    }

    public boolean updateCustomer(customer_DTO customer){
        boolean result = false;
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Update Customer Set Name=?, DateOfBirth=?, Address=?, Phone=?, Sex=?, Point=? Where CustomerID=?;";
                PreparedStatement stmt = connnect.getCon().prepareStatement(sqlQuery);
                stmt.setNString(1,customer.getCustomerName());
                stmt.setString(2,customer.getCustomerDayOfBirth());
                stmt.setString(3,customer.getCustomerAddress());
                stmt.setString(4,customer.getCustomerPhone());
                stmt.setString(5,customer.getCustomerSex());
                stmt.setInt(6,customer.getCustomerPoint());
                stmt.setString(7,customer.getCustomerID());
                if (stmt.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return result;
    }
    public ArrayList<customer_DTO> searchCustomerByID(String customerID){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Select * from Customer where CustomerID like '%" + customerID + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    };

    public ArrayList<customer_DTO> searchCustomerByName(String customerName){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Select * from Customer where Name like '%" + customerName + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public ArrayList<customer_DTO> searchCustomerByDateOfBirth(String customerDateOfBirth){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try {
                String sqlQuery = "Select * from Customer where DateOfBirth like '%" + customerDateOfBirth + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public ArrayList<customer_DTO> searchCustomerByAddress(String customerAddress){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try {
                String sqlQuery = "Select * from Customer where Address like '%" + customerAddress + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex) {
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public ArrayList<customer_DTO> searchCustomerByPhone(String customerPhone){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()) {
            try {
                String sqlQuery = "Select * from Customer where Phone like '%" + customerPhone + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex) {
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public ArrayList<customer_DTO> searchCustomerBySex(String customerSex){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try {
                String sqlQuery = "Select * from Customer where Sex like '%" + customerSex + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex) {
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public ArrayList<customer_DTO> searchCustomerByPoint(String customerPoint){
        ArrayList<customer_DTO> customers = new ArrayList<>();
        if (connnect.openConnection()){
            try {
                String sqlQuery = "Select * from Customer where Point like '%" + customerPoint + "%';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                while (resultSet.next()){
                    customer_DTO customer = new customer_DTO();
                    customer.setCustomerID(resultSet.getString("CustomerID"));
                    customer.setCustomerName(resultSet.getString("Name"));
                    customer.setCustomerDayOfBirth(resultSet.getString("DateOfBirth"));
                    customer.setCustomerAddress(resultSet.getString("Address"));
                    customer.setCustomerPhone(resultSet.getString("Phone"));
                    customer.setCustomerSex(resultSet.getString("Sex"));
                    customer.setCustomerPoint(resultSet.getInt("Point"));
                    customers.add(customer);
                }
            } catch (SQLException ex) {
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return customers;
    }
    public boolean isExistCustomerID(String customerID){
        boolean result = false;
        if (connnect.openConnection()){
            try{
                String sqlQuery = "Select * from Customer where CustomerID='" + customerID + "';";
                Statement stmt = connnect.getCon().createStatement();
                ResultSet resultSet = stmt.executeQuery(sqlQuery);
                result = resultSet.next();
            } catch (SQLException ex){
                System.out.printf("ex");
            } finally {
                connnect.closeConnection();
            }
        }
        return result;
    }


//    public static void main(String[] args) {
//        customer_DAO dao = new customer_DAO();
//        System.out.print("ket qua: " + dao.getAllCustomers());
//    }











}
