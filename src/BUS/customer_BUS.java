package BUS;
import DAO.customer_DAO;
import DTO.customer_DTO;

import java.util.ArrayList;
public class customer_BUS {
    private customer_DAO customerDAO = new customer_DAO();
    public ArrayList<customer_DTO> getAllCustomers(){
        return customerDAO.getAllCustomers();
    }
    public customer_DTO getCustomerByID(String customerID){
        return customerDAO.getCustomerByID(customerID);
    }
    public boolean addCustomer(customer_DTO customer){
        return customerDAO.addCustomer(customer);
    }
    public boolean deleteCustomer(String customerID){
        return customerDAO.deleteCustomer(customerID);
    }
    public boolean updateCustomer(customer_DTO customer){
        return customerDAO.updateCustomer(customer);
    }
    public ArrayList<customer_DTO> searchCustomerByID(String customerID){
        return customerDAO.searchCustomerByID(customerID);
    }
    public ArrayList<customer_DTO> searchCustomerByName(String customerName){
        return customerDAO.searchCustomerByName(customerName);
    }
    public ArrayList<customer_DTO> searchCustomerByDateOfBirth(String customerDateOfBirth){
        return customerDAO.searchCustomerByDateOfBirth(customerDateOfBirth);
    }
    public ArrayList<customer_DTO> searchCustomerByAddress(String customerAddress){
        return customerDAO.searchCustomerByAddress(customerAddress);
    }
    public ArrayList<customer_DTO> searchCustomerByPhone(String customerPhone){
        return customerDAO.searchCustomerByPhone(customerPhone);
    }
    public ArrayList<customer_DTO> searchCustomerBySex(String customerSex){
        return customerDAO.searchCustomerBySex(customerSex);
    }
    public ArrayList<customer_DTO> searchCustomerByPoint(String customerPoint){
        return customerDAO.searchCustomerByPoint(customerPoint);
    }
    public boolean isExistCustomerID(String customerID){
        return customerDAO.isExistCustomerID(customerID);
    }
}
