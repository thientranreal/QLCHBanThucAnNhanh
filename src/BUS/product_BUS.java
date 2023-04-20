package BUS;
import DTO.product_DTO;
import DAO.product_DAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class product_BUS {
    product_DAO productDao = new product_DAO();

    public ArrayList<product_DTO> getAllProduct(){
        return productDao.getAllProduct();
    }

    public boolean addProduct(product_DTO product){
        return productDao.addProduct(product);
    }

    public boolean hasProductID(String id){
        return productDao.hasProductID(id);
    }

    public ArrayList<String> getAllSupplierID(){
        return productDao.getAllSupplierID();
    }

    public void renderDataTable(JTable table, String[] columns,ArrayList<product_DTO> listProduct ){
        DefaultTableModel columnModel = new DefaultTableModel(null, columns);
        Object[] row = new Object[7];
        int i=0;
        try{
            for(product_DTO item : listProduct ){
                i=0;
                row[i++] = item.getProductID();
                row[i++] = item.getProductName();
                row[i++] = item.getPrice();
                row[i++] = item.getCalories();
                row[i++] = item.getStocks();
                row[i++] = item.getCategory();
                row[i++] = item.getSupplierID();
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
            columnModel.addRow(row);
        }
        table.setModel(columnModel);
    }

    public ArrayList<product_DTO> getProductFromCondition(String condition, String columnName){
        return productDao.getProductFromCondition(condition,columnName);
    }



    public boolean deleteProduct(String id){
        return productDao.deleteProduct(id);
    }

    public ArrayList<String> getAColumn(String columnName, String table){
        return productDao.getAColumn(columnName,table);
    }

    public boolean updateProduct(String productID, String name, float price, float calories, int stock, String category, String supplierID){
        return productDao.updateProduct(productID,name,price, calories,stock,category,supplierID);
    }

    public String toUpperCaseFirstChar(String str){
        String firstLetter = str.substring(0, 1);
        // chuỗi remainingLetters chứa phần còn lại của name
        String remainingLetters = str.substring(1, str.length());

        //sử dụng phương thức toUpperCase() để chuyển đổi firstLetter thành chữ in hoa
        firstLetter = firstLetter.toUpperCase();

        //sau khi chuyển đổi thì gộp chúng lại
        str = firstLetter + remainingLetters;
        return str;
    }











}
