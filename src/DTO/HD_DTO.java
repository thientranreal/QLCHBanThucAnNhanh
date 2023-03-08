package DTO;

public class HD_DTO {
    private String orderID;
    private String orderDate;
    private String customerID;
    private String customerName;
    private String employeeID;
    private String employeeName;

    public HD_DTO() {
    }

    public HD_DTO(String orderID, String employeeID, String employeeName, String customerID, String customerName, String orderDate) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getByIndex(int index) {
        switch (index) {
            case 0:
                return orderID;
            case 1:
                return employeeID;
            case 2:
                return employeeName;
            case 3:
                return customerID;
            case 4:
                return customerName;
            case 5:
                return orderDate;
            default:
                return "N/A";
        }
    }
}
