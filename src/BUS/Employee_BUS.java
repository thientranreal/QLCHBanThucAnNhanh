package BUS;
import DTO.Employee_DTO;
import DAO.Employee_DAO;
import DTO.product_DTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Employee_BUS {
    Employee_DAO emp = new Employee_DAO();
    public ArrayList<Employee_DTO>getAllEmployee(){
//        ArrayList<Employee_DTO> tmp = emp.getAllEmployee();
//        for(Employee_DTO item : tmp){
//            if()
//        }
        return emp.getAllEmployee();
    }


    public void renderDataTable(JTable table, String[] columns, ArrayList<Employee_DTO> listEmployee ){
        DefaultTableModel columnModel = new DefaultTableModel(null, columns);
        Object[] row = new Object[10];
        int i=0;
        try{
            for(Employee_DTO item : listEmployee ){
                i=0;
                row[i++] = item.getEmployeeID();
                row[i++] = item.getName();
                row[i++] = item.getAddress();
                row[i++] = item.getPhone();
                row[i++] = item.getDateOfBirth();
                row[i++] = item.getSex();
                row[i++] = item.getRole();
                row[i++] = item.getAccountID();
                row[i++] = item.getUserName();
                row[i++] = item.getPassword();

                columnModel.addRow(row);
            }
        }catch (NullPointerException ex){
            System.out.println("Error when trying get data to render in table");
            i = 0;
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            row[i++] = "N/A";
            columnModel.addRow(row);
        }
        table.setModel(columnModel);
    }


    public boolean hasEmployeeID(String id){
        return emp.hasEmployeeID(id);
    }



    public boolean addEmployee(Employee_DTO employee) {
        return emp.addEmployee(employee);
    }
}
