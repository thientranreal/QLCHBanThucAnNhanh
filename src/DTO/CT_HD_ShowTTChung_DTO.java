package DTO;

public class CT_HD_ShowTTChung_DTO{
    private String emID;
    private String emName;
    private String cusID;
    private String cusName;
    private String OrderDate;
    private String phone;
    private String address;

    public CT_HD_ShowTTChung_DTO() {
    }

    public CT_HD_ShowTTChung_DTO(String emID, String emName, String cusID, String cusName, String orderDate, String phone, String address) {
        this.emID = emID;
        this.emName = emName;
        this.cusID = cusID;
        this.cusName = cusName;
        OrderDate = orderDate;
        this.phone = phone;
        this.address = address;
    }

    public String getEmID() {
        return emID;
    }

    public void setEmID(String emID) {
        this.emID = emID;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
