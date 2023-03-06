package DTO;

public class HD_DTO {
    private String orderID;
    private String customerID;
    private String customerName;
    private String employeeID;
    private String employeeName;
    private String orderDate;

    public HD_DTO() {
    }

    public HD_DTO(String orderID, String customerID, String customerName, String employeeID, String employeeName, String orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.orderDate = orderDate;
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
