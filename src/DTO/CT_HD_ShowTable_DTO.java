package DTO;

public class CT_HD_ShowTable_DTO {
    private String orderID;
    private String customerID;
    private String customerName;
    private String productID;
    private String productName;
    private int quantity;
    private float price;

    public CT_HD_ShowTable_DTO() {
    }

    public CT_HD_ShowTable_DTO(String orderID, String customerID, String customerName, String productID, String productName, int quantity, float price) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
