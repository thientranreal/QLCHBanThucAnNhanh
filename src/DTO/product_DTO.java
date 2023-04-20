package DTO;

public class product_DTO {
    private String productID ;
    private String productName;
    private float price;
    private float calories;
    private int stocks;
    private String category;
    private String supplierID;


    public product_DTO(){

    }

    public product_DTO(String productID, String productName, float price, float calories, int stocks, String category, String supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.calories = calories;
        this.stocks = stocks;
        this.category = category;
        this.supplierID = supplierID;

    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

}
