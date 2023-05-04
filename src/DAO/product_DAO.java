package DAO;

import DTO.product_DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class product_DAO {
    public ArrayList<product_DTO> getAllProduct() {
        ArrayList<product_DTO> arr = new ArrayList<product_DTO>();
        if (JDBC.openConnection()) {
            try {
                String sql = "SELECT * from Product where status=1";
                Statement stml = JDBC.getCon().createStatement();
                ResultSet rs = stml.executeQuery(sql);
                while (rs.next()) {
                    product_DTO product = new product_DTO();
                    product.setProductID(rs.getString("ProductID"));
                    product.setProductName(rs.getNString("Name"));
                    product.setPrice(rs.getFloat("Price"));
                    product.setStocks(rs.getInt("Stock"));
                    product.setCalories(rs.getFloat("Calories"));
                    product.setCategory(rs.getNString("Category"));
//                    product.setSupplierName(rs.getNString("TenNCC"));
                    arr.add(product);
                }
                stml.close();
            } catch (SQLException ex) {
                System.out.println("Error when get data product from SQL Server");
            } finally {
                JDBC.closeConnection();
            }
        }
        return arr;
    }

    public boolean addProduct(product_DTO product) {
        boolean flag = false;
        if (JDBC.openConnection()) {
            try {
                String sql = "INSERT INTO Product values (?,?,?,?,?,?,1)";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                stmt.setString(1, product.getProductID());
                stmt.setString(2, product.getProductName());
                stmt.setFloat(3, product.getPrice());
                stmt.setFloat(4, product.getCalories());
                stmt.setInt(5, product.getStocks());
                stmt.setString(6, product.getCategory());

                if (stmt.executeUpdate() >= 1) {
                    flag = true;
                }
                stmt.close();

            } catch (SQLException ex) {
                System.out.println("Lỗi khi thực hiện thêm sản phẩm");
            } finally {
                JDBC.closeConnection();
            }
        }
        return flag;
    }

    public boolean hasProductID(String id){
        if (JDBC.openConnection()){
            try{
                ArrayList<String> listProductID = new ArrayList<>();
                String sql = "Select ProductID from Product";
                Statement stmt = JDBC.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    listProductID.add(rs.getString("ProductID"));
                }
                for (String item : listProductID){
                    if(item.equals(id)){
                        return true;
                    }
                }

            }catch (SQLException ex){
                System.out.println("Lỗi khi thực hiện kiểm tra sản phẩm tồn tại");
            }
        }
        return  false;
    }

    public ArrayList<String> getAColumn(String columnName,String table){
        ArrayList<String> list = new ArrayList<String>();
        if (JDBC.openConnection()){
            try {
                String sql = "SELECT" + " " +columnName + " " + "FROM"+ " " + table + " WHERE Status =1";
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

    public ArrayList<String> getAllSupplierID(){
        ArrayList<String> list = new ArrayList<String>();
        if (JDBC.openConnection()){
            try {
                String sql = "SELECT SupplierID FROM Supplier";
                Statement stml = JDBC.getCon().createStatement();
                ResultSet rs = stml.executeQuery(sql);
                while (rs.next()){
                    list.add(rs.getString("SupplierID"));
                }
                stml.close();
            }catch (SQLException ex){
                System.out.println("Error when trying to get ArrayList SupplierID");
            }finally {
                JDBC.closeConnection();
            }
        }
        return list;
    }

    public ArrayList<product_DTO>getProductFromCondition(String condition,String columnName){
        String sql=null;
        ArrayList<product_DTO> arr = new ArrayList<product_DTO>();
        if(JDBC.openConnection()){
            try{
                product_DTO product = new product_DTO();
                if(columnName.equals("Mã sản phẩm")){
                      sql = "SELECT * FROM Product WHERE ProductID = " + "N'"+condition+"'" + " and Status =1";
                }else if(columnName.equals("Tên sản phẩm")){
                        sql= "SELECT * FROM Product WHERE Name = "+ "N'"+condition+"'" + " and Status =1";
                }
                Statement stmt = JDBC.getCon().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    product.setProductID(rs.getString("ProductID"));
                    product.setProductName(rs.getNString("Name"));
                    product.setPrice(rs.getFloat("Price"));
                    product.setStocks(rs.getInt("Stock"));
                    product.setCalories(rs.getFloat("Calories"));
                    product.setCategory(rs.getNString("Category"));
                    arr.add(product);
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

//    public boolean deleteProduct(String id){
//        boolean flag = false;
//        if (JDBC.openConnection()){
//            try {
//                String sql = "DELETE FROM Product WHERE ProductID = ?";
//                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
//                stmt.setString(1, id);
//                if(stmt.executeUpdate() >= 1){
//                    flag= true;
//                }
//                stmt.close();
//
//            }catch (SQLException ex){
//                System.out.println("Error when trying to get rid of product from database! ");
//            }finally {
//                JDBC.closeConnection();
//            }
//        }
//        return flag;
//    }



    public boolean deleteProduct(String id){
        boolean flag = false;
        if (JDBC.openConnection()){
            try {
                String sql = "UPDATE Product SET Status=0 WHERE ProductID = ?";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                stmt.setString(1, id);
                if(stmt.executeUpdate() >= 1){
                    flag= true;
                }
                stmt.close();

            }catch (SQLException ex){
                System.out.println("Error when trying to get rid of product from database! ");
            }finally {
                JDBC.closeConnection();
            }
        }
        return flag;
    }












    public boolean updateProduct(String productID, String name, float price, float calories, int stock, String category){
        boolean flag = false;
        if(JDBC.openConnection()){
            try{
                String sql = "UPDATE Product SET  Name= ?, Price= ?, Calories= ?, Stock= ?, Category= ? " + " WHERE ProductID = ? and Status=1";
                PreparedStatement stmt = JDBC.getCon().prepareStatement(sql);
                stmt.setString(1,name);
                stmt.setFloat(2,price);
                stmt.setFloat(3,calories);
                stmt.setInt(4,stock);
                stmt.setString(5,category);
//                stmt.setString(6,supplierID);
                stmt.setString(6,productID);
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











}
