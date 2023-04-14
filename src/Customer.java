public class Customer {
    private String customerID;
    private String customerName;
    private String customerDayOfBirth;
    private String customerAddress;
    private String customerPhone;
    private String customerSex;
    private int customerPoint;
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void setCustomerDayOfBirth(String customerDayOfBirth){
        this.customerDayOfBirth = customerDayOfBirth;
    }
    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }
    public void setCustomerPhone(String customerPhone){
        this.customerPhone = customerPhone;
    }
    public void setCustomerSex(String customerSex){
        this.customerSex = customerSex;
    }
    public void setCustomerPoint(int customerPoint){
        this.customerPoint = customerPoint;
    }
    public String getCustomerID(){
        return customerID;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCustomerDayOfBirth(){
        return customerDayOfBirth;
    }
    public String getCustomerAddress(){
        return customerAddress;
    }
    public String getCustomerPhone(){
        return customerPhone;
    }
    public String getCustomerSex(){
        return customerSex;
    }
    public int getCustomerPoint(){
        return customerPoint;
    }
}
