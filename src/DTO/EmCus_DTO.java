package DTO;

public class EmCus_DTO {
    private String customerID;
    private String customerName;
    private String employeeID;
    private String employeeName;

    public EmCus_DTO() {
    }

    public EmCus_DTO(String customerID, String customerName, String employeeID, String employeeName) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
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
}
