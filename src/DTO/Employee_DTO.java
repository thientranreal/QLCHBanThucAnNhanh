package DTO;
import java.sql.Date;
public class Employee_DTO {
    private String EmployeeID, AccountID, Name, Address, Phone, Sex, Role, UserName, Password;
    private Date DateOfBirth;

    public Employee_DTO() {

    }


    public Employee_DTO(String employeeID, String accountID, String name, String address, String phone, Date dateOfBirth, String sex, String role, String userName, String password) {
        EmployeeID = employeeID;
        AccountID = accountID;
        Name = name;
        DateOfBirth = dateOfBirth;
        Address = address;
        Phone = phone;
        Sex = sex;
        Role = role;
        UserName = userName;
        Password = password;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Employee_DTO{" +
                "EmployeeID='" + EmployeeID + '\'' +
                ", AccountID='" + AccountID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Role='" + Role + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                '}';
    }

    //    String employeeID,String accountID, String name,String address, String phone,Date dateOfBirth, String sex, String role, String userName, String password)
    public String getByIndex(int index) {
        switch (index) {
            case 0:
                return AccountID;
            case 1:
                return UserName;
            case 2:
                return Password;
            default:
                return "N/A";
        }
    }


    public void setByIndex(int index, String value) {
        switch (index) {
            case 0:
                setAccountID(value);
                break;
            case 1:
                setUserName(value);
                break;
            case 2:
                setPassword(value);
                break;
        }
    }



}
