package GUI;

import BUS.Employee_BUS;
import DTO.Employee_DTO;

import java.awt.event.*;
import java.sql.Date;
import javax.swing.*;
import java.util.ArrayList;

public class Employee_GUI implements ActionListener, MouseListener, KeyListener {
    private JPanel container;
    private JTextField txtMaNV;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtDate;
    private JTextField txtSex;
    private JTable tableEmp;
    private JComboBox cbSearch;
    private JList searchList;
    private JPanel pnThongTinChung;
    private JPanel pnTimKiem;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDel;
    private JComboBox cbRole;
    private JComboBox cbDay;
    private JComboBox cbMonth;
    private JComboBox cbYear;
    private JPanel pnDate;
    private JPanel pnTable;
    private JPanel pnTitle;
    private JTextField txtSearch;
    JFrame frame;

    public String[] columns = {"Mã nhân viên", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Ngày sinh","Giới tính", "Chức vụ","Mã tài khoản", "Tên tài khoản", "Mật khẩu" };
    Employee_BUS employeeBus = new Employee_BUS();
    ArrayList<Employee_DTO> listEmployee = employeeBus.getAllEmployee();


    public Employee_GUI(){
        frame = new JFrame();


        //        Xử lý panel Thông tin chung
        pnThongTinChung.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));

        //        Xử lý panel Tìm kiếm
        pnTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
//        Xử lý combobox
        DefaultComboBoxModel searchsList = new DefaultComboBoxModel();
        searchsList.addElement("Mã nhân viên");
        searchsList.addElement("Tên nhân viên");
        searchsList.addElement("Chức vụ");
        cbSearch.setModel(searchsList);


        DefaultComboBoxModel cbModelRole = new DefaultComboBoxModel();
        cbModelRole.addElement("Quản lý");
        cbModelRole.addElement("Nhân viên");
        cbRole.setModel(cbModelRole);

        DefaultComboBoxModel cbModelDay = new DefaultComboBoxModel();
        for(int i=1; i<=31; i++){
            cbModelDay.addElement(i);
        }
        cbDay.setModel(cbModelDay);
        DefaultComboBoxModel cbModelMonth = new DefaultComboBoxModel();
        for(int i=1; i<=12; i++){
            cbModelMonth.addElement(i);
        }
        cbMonth.setModel(cbModelMonth);
        DefaultComboBoxModel cbModelYear = new DefaultComboBoxModel();
        for(int i=1970; i<=2050; i++){
            cbModelYear.addElement(i);
        }
        cbYear.setModel(cbModelYear);


//        render dữ liệu ra table
        tableEmp.setDefaultEditor(Object.class, null);
        tableEmp.setRowHeight(20);
        employeeBus.renderDataTable(tableEmp,columns,listEmployee);


        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        btnUpdate.addActionListener(this);
        tableEmp.addMouseListener(this);
        txtSearch.addKeyListener(this);
        searchList.addMouseListener(this);




        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public void reset(){
        txtMaNV.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtSex.setText("");
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String employeeID = txtMaNV.getText().trim().toUpperCase();
//            String accountID = "";
            String name = txtName.getText().trim();
            String address = txtAddress.getText().trim().toLowerCase();
            String phone = txtPhone.getText().trim();
            String dateOfBirth = "";
            String day = String.valueOf(cbDay.getSelectedItem());
            String month = String.valueOf(cbMonth.getSelectedItem());
            String year = String.valueOf(cbYear.getSelectedItem());
            String Sex = txtSex.getText().trim().toLowerCase();
            String Role = String.valueOf(cbRole.getSelectedItem());
            String userName = "";
            String Password = "";
            if (employeeID.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã nhân viên",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtMaNV.grabFocus();
                return;
            } else {
                if (!employeeID.startsWith("NV")) {
                    JOptionPane.showMessageDialog(frame, "Mã nhân viên không hợp lệ, vui lòng nhập lại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtMaNV.grabFocus();
                    return;
                } else {
                    if (employeeBus.hasEmployeeID(employeeID)) {
                        JOptionPane.showMessageDialog(frame, "Mã nhân viên đã tồn tại, vui lòng nhập lại",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtMaNV.grabFocus();
                        return;
                    } else {
                        if (employeeID.length() != 4) {
                            JOptionPane.showMessageDialog(frame, "Mã nhân viên phải đủ 4 kí tự, vui lòng nhập lại",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            txtMaNV.grabFocus();
                            return;
                        }
                    }
                }
            }

            if (name.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống tên nhân viên",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtName.grabFocus();
                return;
            }

            if (address.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống địa chỉ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtAddress.grabFocus();
                return;
            }

            if (phone.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống số điện thoại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtPhone.grabFocus();
                return;
            }

//            if(day.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống ngày sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if(month.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống tháng sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if(year.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống năm sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            if (Sex.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống giới tính",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtSex.grabFocus();
                return;
            } else {
                if (!(Sex.equals("nam")) && !(Sex.equals("nữ"))) {
                    JOptionPane.showMessageDialog(frame, "Giới tính không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtSex.grabFocus();
                    return;
                }
            }

            if (Role.equals("")) {
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống chức vụ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date date = Date.valueOf(String.format("%s-%s-%s", year, month, day));


            Employee_DTO emp = new Employee_DTO(employeeID, null, name, address, phone, date, Sex, Role, userName, Password);
            System.out.println(emp.toString());

            try {
                if (employeeBus.addEmployee(emp)) {
                    JOptionPane.showMessageDialog(frame, "Thêm nhân viên thành công",
                            "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error when trying add product!");
            }
            reset();
            ArrayList<Employee_DTO> listEmployee = employeeBus.getAllEmployee();
            employeeBus.renderDataTable(tableEmp, columns, listEmployee);
        } else {
            if (e.getSource() == btnDel) {
                String EmpID = txtMaNV.getText().trim().toUpperCase();
                if (EmpID.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã nhân viên",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtMaNV.grabFocus();
                    return;
                } else {
                    if (!EmpID.startsWith("NV")) {
                        JOptionPane.showMessageDialog(frame, "Mã nhân viên không hợp lệ, vui lòng nhập lại",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtMaNV.grabFocus();
                        return;
                    }
                    employeeBus.deleteEmp(EmpID);
                    JOptionPane.showMessageDialog(frame, "Xóa nhân viên thành công",
                            "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                    ArrayList<Employee_DTO> listEmp = employeeBus.getAllEmployee();
                    employeeBus.renderDataTable(tableEmp, columns, listEmp);
                }
            } else {
                if (e.getSource() == btnUpdate) {
                    String employeeID = txtMaNV.getText().trim().toUpperCase();
                    String name = txtName.getText().trim();
                    String address = txtAddress.getText().trim().toLowerCase();
                    String phone = txtPhone.getText().trim();
                    String day = String.valueOf(cbDay.getSelectedItem());
                    String month = String.valueOf(cbMonth.getSelectedItem());
                    String year = String.valueOf(cbYear.getSelectedItem());
                    String Sex = txtSex.getText().trim().toLowerCase();
                    String Role = String.valueOf(cbRole.getSelectedItem());

                    if (employeeID.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã nhân viên",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtMaNV.grabFocus();
                        return;
                    } else {
                        if (!employeeID.startsWith("NV")) {
                            JOptionPane.showMessageDialog(frame, "Mã nhân viên không hợp lệ, vui lòng nhập lại",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            txtMaNV.grabFocus();
                            return;
                        }
                    }

                    if (name.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống tên nhân viên",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtName.grabFocus();
                        return;
                    }

                    if (address.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống địa chỉ",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtAddress.grabFocus();
                        return;
                    }

                    if (phone.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống số điện thoại",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtPhone.grabFocus();
                        return;
                    }

//            if(day.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống ngày sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if(month.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống tháng sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if(year.equals("")){
//                JOptionPane.showMessageDialog(frame, "Không được bỏ trống năm sinh",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

                    if (Sex.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống giới tính",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtSex.grabFocus();
                        return;
                    } else {
                        if (!(Sex.equals("nam")) && !(Sex.equals("nữ"))) {
                            JOptionPane.showMessageDialog(frame, "Giới tính không hợp lệ",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            txtSex.grabFocus();
                            return;
                        }
                    }

                    if (Role.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Không được bỏ trống chức vụ",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    Date date = Date.valueOf(String.format("%s-%s-%s", year, month, day));

                    try {
                        if (employeeBus.updateEmp(employeeID, name, address, phone, date, Sex, Role)) {
                            JOptionPane.showMessageDialog(frame, "Cập nhật nhân viên thành công",
                                    "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error when trying update employee!");
                    }
                    reset();
                    ArrayList<Employee_DTO> listEmp = employeeBus.getAllEmployee();
                    employeeBus.renderDataTable(tableEmp, columns, listEmp);


                }
            }


        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == tableEmp){
//            int column = tableEmp.getSelectedColumn();
            int row = tableEmp.getSelectedRow();
            String value1 = tableEmp.getValueAt(row,0).toString();
            String value2 = tableEmp.getValueAt(row,1).toString();
            String value3 = tableEmp.getValueAt(row,2).toString();
            String value4 = tableEmp.getValueAt(row,3).toString();
            String[] value5 = tableEmp.getValueAt(row,4).toString().split("-");
            String value6 = tableEmp.getValueAt(row,5).toString();
            String value7 = tableEmp.getValueAt(row,6).toString();
//            if (jComboBox1.getItemAt(0).toString ().contains ("two"))
//            {
//                jComboBox1.setSelectedIndex(0);
//            }

            txtMaNV.setText(value1);
            txtName.setText(value2);
            txtAddress.setText(value3);
            txtPhone.setText(value4);


            for(int i=0; i<= cbDay.getItemCount(); i++){
                if( Integer.parseInt(value5[2]) < 10){
                    if (cbDay.getItemAt(i).toString().contains(value5[2].split("0")[1]))
                    {
                        cbDay.setSelectedIndex(i);
                        break;
                    }
                }else{
                    if( Integer.parseInt(value5[2]) >= 10){
                        if (cbDay.getItemAt(i).toString().contains(value5[2]))
                        {
                            cbDay.setSelectedIndex(i);
                            break;
                        }
                    }
                }

            }


            for(int i=0; i<= cbMonth.getItemCount(); i++){
                if( Integer.parseInt(value5[1]) < 10){
                    if (cbMonth.getItemAt(i).toString().contains(value5[1].split("0")[1]))
                    {
                        cbMonth.setSelectedIndex(i);
                        break;
                    }
                }else{
                    if( Integer.parseInt(value5[1]) >= 10){
                        if (cbMonth.getItemAt(i).toString().contains(value5[1]))
                        {
                            cbMonth.setSelectedIndex(i);
                            break;
                        }
                    }
                }

            }


            for(int i=0; i<= cbYear.getItemCount(); i++){
                if (cbYear.getItemAt(i).toString().contains(value5[0]))
                {
                    cbYear.setSelectedIndex(i);
                    break;
                }

            }




            txtSex.setText(value6);
//            cbMaNCC.setSelectedItem(value7);
        }




    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(e.getSource() == searchList){
            String cbSearchValue =cbSearch.getSelectedItem().toString();
            String searchListValue = searchList.getSelectedValue().toString();
            ArrayList<Employee_DTO> listEmp= new ArrayList<Employee_DTO>();
            listEmp=employeeBus.getEmpFromCondition(searchListValue,cbSearchValue);
            employeeBus.renderDataTable(tableEmp,columns,listEmp);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public static void main(String[] args) {
        new Employee_GUI();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String value = txtSearch.getText().trim().toLowerCase();
        DefaultListModel<String> lModel = new DefaultListModel<>();
        ArrayList<String> listSearch = new ArrayList<>();
        if(cbSearch.getSelectedItem().equals("Mã nhân viên")){
            listSearch = employeeBus.getAColumn("EmployeeID","Employee");
            for(int i=0; i<listSearch.size(); i++){
                if(value.equals("")){
                    employeeBus.renderDataTable(tableEmp,columns,listEmployee);
                    break;
                }
                if(listSearch.get(i).toLowerCase().contains(value)){
                    lModel.addElement(listSearch.get(i));
                }
            }
        }else if(cbSearch.getSelectedItem().equals("Tên nhân viên")){
            listSearch = employeeBus.getAColumn("Name","Employee");
            for(int i=0; i<listSearch.size(); i++){
                if(value.equals("")){
                    employeeBus.renderDataTable(tableEmp,columns,listEmployee);
                    break;
                }
                if(listSearch.get(i).toLowerCase().contains(value)){
                    lModel.addElement(listSearch.get(i));
                }
            }
        }
        searchList.setModel(lModel);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}



