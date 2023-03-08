package DTO;

public class HD_DTO extends EmCus_DTO {
    private String orderID;
    private String orderDate;

    public HD_DTO() {
    }

    public HD_DTO(String orderID, String employeeID, String employeeName, String customerID, String customerName, String orderDate) {
        super(customerID, customerName, employeeID, employeeName);
        this.orderID = orderID;
        this.orderDate = orderDate;
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

    public String getByIndex(int index) {
        switch (index) {
            case 0:
                return orderID;
            case 1:
                return getEmployeeID();
            case 2:
                return getEmployeeName();
            case 3:
                return getCustomerID();
            case 4:
                return getCustomerName();
            case 5:
                return orderDate;
            default:
                return "N/A";
        }
    }
}
