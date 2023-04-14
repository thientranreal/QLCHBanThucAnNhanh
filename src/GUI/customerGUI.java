package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import BUS.customer_BUS;
import DAO.JDBC;
import DAO.customer_DAO;
import DTO.customer_DTO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class customerGUI {
    private final customer_BUS customerBUS = new customer_BUS();
    private JPanel customerLeftPanel;
    private JTextField customerIDTextField;
    private JTextField customerNameTextField;
    private JTextField customerDayOfBirthTextField;
    private JTextField customerSexTextField;
    private JTextField customerPhoneTextField;
    private JTextField customerPointTextField;
    private JButton customerAddButton;
    private JButton customerDeleteButton;
    private JButton customerEditButton;
    private JComboBox customerSearchComboBox;
    private JPanel customerPanel;
    private JPanel customerRightPanel;
    private JTable customerTable;
    private JList customerList;
    private JLabel customerIDLabel;
    private JLabel customerNameLabel;
    private JLabel customerDayOfBirthLabel;
    private JLabel customerSexLabel;
    private JLabel customerAddressLabel;
    private JLabel customerPhoneLabel;
    private JLabel customerPointLabel;
    private JTextField customerAddressTextField;
    private JLabel customerTitle;
    private JTextField customerSearchTextField;
    private JScrollPane tableScrollPane;
    public customerGUI(String title){
        initCustomerGUI(title);
    }
    private void initCustomerGUI(String title){
        JFrame frame = new JFrame(title);
        frame.add(customerPanel);
        customerPanel.setBorder(new EmptyBorder(5,10,5,10));
        customerRightPanel.setBorder(new EmptyBorder(5,10,5,10));
        customerLeftPanel.setBorder(new EmptyBorder(5,10,5,10));
        customerRightPanel.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        customerLeftPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        String[] searchCriterion = {"Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Giới " +
                "tính", "Điểm "};
        DefaultComboBoxModel cbM = new DefaultComboBoxModel();
        for (String i : searchCriterion)
            cbM.addElement(i);
        customerSearchComboBox.setModel(cbM);
        // TABLE
//        tableScrollPane = new JScrollPane();
//        tableScrollPane.add(customerPanel);
        String[] colums = {"Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại", "Điểm"};
        DefaultTableModel customerTableModel = new DefaultTableModel(null,colums);
        customerTable.setModel(customerTableModel);
        customerTable.setDefaultEditor(Object.class,null);
        updateTable(customerTableModel, customerBUS.getAllCustomers());
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int row = customerTable.getSelectedRow();
                customerIDTextField.setText(customerTable.getModel().getValueAt(row,0).toString());
                customerNameTextField.setText(customerTable.getModel().getValueAt(row,1).toString());
                customerDayOfBirthTextField.setText(customerTable.getModel().getValueAt(row,2).toString());
                customerSexTextField.setText(customerTable.getModel().getValueAt(row,3).toString());
                customerAddressTextField.setText(customerTable.getModel().getValueAt(row,4).toString());
                customerPhoneTextField.setText(customerTable.getModel().getValueAt(row,5).toString());
                customerPointTextField.setText(customerTable.getModel().getValueAt(row,6).toString());
            }
        });
        // SEARCH INPUT //
        customerSearchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e ){
                super.keyReleased(e);
                String result = customerSearchTextField.getText().trim();
                if (!result.isBlank()){
                    ArrayList<customer_DTO> resultList;
                    DefaultListModel defaultListModel = new DefaultListModel();
                    switch (customerSearchComboBox.getSelectedIndex()){
                        case 0: // SEARCH BY ID
                            resultList = customerBUS.searchCustomerByID(result.toUpperCase());
                            for (customer_DTO i : resultList)
                                if (i.getCustomerID().contains(result))
                                    defaultListModel.addElement(i.getCustomerID());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 1: // SEARCH BY NAME
                            resultList = customerBUS.searchCustomerByName(result);
                            for (customer_DTO i : resultList)
                                if (i.getCustomerName().contains(result))
                                    defaultListModel.addElement(i.getCustomerName());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 2: // SEARCH BY DATE OF BIRTH
                            resultList = customerBUS.searchCustomerByDateOfBirth(result);
                            for (customer_DTO i : resultList)
                                if (i.getCustomerDayOfBirth().contains(result))
                                    defaultListModel.addElement(i.getCustomerDayOfBirth());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 3: // SEARCH BY ADDRESS
                            resultList = customerBUS.searchCustomerByAddress(result);
                            for (customer_DTO i : resultList)
                                if (i.getCustomerAddress().contains(result))
                                    defaultListModel.addElement(i.getCustomerAddress());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 4: // SEARCH BY PHONE
                            resultList = customerBUS.searchCustomerByPhone(result);
                            for (customer_DTO i : resultList)
                                if (i.getCustomerPhone().contains(result))
                                    defaultListModel.addElement(i.getCustomerPhone());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 5: // SEARCH BY SEX
                            resultList = customerBUS.searchCustomerBySex(result);
                            for (customer_DTO i : resultList)
                                if (i.getCustomerSex().contains(result))
                                    defaultListModel.addElement(i.getCustomerSex());
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 6: // SEARCH BY POINT
                            resultList = customerBUS.searchCustomerByPoint(result);
                            for (customer_DTO i : resultList) {
                                String customerTemp = Integer.toString(i.getCustomerPoint());
                                if (customerTemp.contains(result))
                                    defaultListModel.addElement(customerTemp);
                            }
                            customerList.setModel(defaultListModel);
                            updateTable(customerTableModel,resultList);
                            break;
                    }
                }
            }
        });
        // EVENT FOR CLICKED ON THE SELECTED ITEM IN JLIST //
        customerList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String result = customerList.getSelectedValue().toString();
                if (!result.isBlank()){
                    ArrayList<customer_DTO> resultList;
                    switch (customerSearchComboBox.getSelectedIndex()){
                        case 0:
                            resultList = customerBUS.searchCustomerByID(result.toUpperCase());
                            updateTable(customerTableModel,resultList);
                            break;
                        case 1:
                            resultList = customerBUS.searchCustomerByName(result);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 2:
                            resultList = customerBUS.searchCustomerByDateOfBirth(result);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 3:
                            resultList = customerBUS.searchCustomerByAddress(result);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 4:
                            resultList = customerBUS.searchCustomerByPhone(result);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 5:
                            resultList = customerBUS.searchCustomerBySex(result);
                            updateTable(customerTableModel,resultList);
                            break;
                        case 6:
                            resultList = customerBUS.searchCustomerByPoint(result);
                            updateTable(customerTableModel,resultList);
                            break;
                    }
                }
            }
        });
        // EVENT FOR BUTTON //
        customerAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int result = JOptionPane.showConfirmDialog(null,"Bạn muốn thêm?","THÔNG BÁO",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    String id = customerIDTextField.getText().toUpperCase().trim();
                    String name = customerNameTextField.getText().trim();
                    String dayOfBirth = customerDayOfBirthTextField.getText().trim();
                    String address = customerAddressTextField.getText().trim();
                    String phone = customerPhoneTextField.getText().trim();
                    String sex = customerSexTextField.getText().trim();
                    String point = customerPointTextField.getText().trim();
                    if (id.isBlank() || name.isBlank() || dayOfBirth.isBlank() || address.isBlank() || phone.isBlank() ||
                            sex.isBlank() || point.isBlank())
                        JOptionPane.showMessageDialog(null,"Thêm thất bại! Vui lòng nhập đầy đủ" +
                                "thông tin","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                    else if (customerBUS.isExistCustomerID(id))
                        JOptionPane.showMessageDialog(null,"Mã khách hàng bạn nhập đã tồn tại!, Vui" +
                                "lòng nhập mã khách hàng khác!","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                    else {
                        customer_DTO customer =  new customer_DTO();
                        customer.setCustomerID(id);
                        customer.setCustomerName(name);
                        customer.setCustomerDayOfBirth(dayOfBirth);
                        customer.setCustomerAddress(address);
                        customer.setCustomerPhone(phone);
                        customer.setCustomerSex(sex);
                        customer.setCustomerPoint(Integer.parseInt(point));
                        if (customerBUS.addCustomer(customer)) {
                            JOptionPane.showMessageDialog(null, "Thêm thành công!",
                                    "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                            updateTable(customerTableModel,customerBUS.getAllCustomers());
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Thêm thất bại!",
                                    "THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        customerDeleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String id = customerIDTextField.getText().toUpperCase().trim();
                if (id.isBlank())
                    JOptionPane.showMessageDialog(null,"Xóa thất bại! Bạn chưa nhập mã khách" +
                            "hàng","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                else {
                    if (customerBUS.deleteCustomer(id)) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công!",
                                "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                        updateTable(customerTableModel,customerBUS.getAllCustomers());
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Xóa thất bại!","THÔNG BÁO",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        customerEditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String id = customerIDTextField.getText().toUpperCase().trim();
                String name = customerNameTextField.getText().trim();
                String dayOfBirth = customerDayOfBirthTextField.getText().trim();
                String address  = customerAddressTextField.getText().trim();
                String phone = customerPhoneTextField.getText().trim();
                String sex = customerSexTextField.getText().trim();
                String point = customerPointTextField.getText().trim();
                if (id.isBlank() || name.isBlank() || dayOfBirth.isBlank() || address.isBlank() || phone.isBlank() ||
                sex.isBlank() || point.isBlank())
                    JOptionPane.showMessageDialog(null,"Cập nhật thất bại! Thiếu thông tin vui " +
                            "lòng nhập đầy đủ thông tin!","THÔNG BÁO",JOptionPane.INFORMATION_MESSAGE);
                else if (!customerBUS.isExistCustomerID(id))
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại! Mã khách hàng " +
                            "không tồn tại!", "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                else {
                        customer_DTO customer = new customer_DTO();
                        customer.setCustomerID(id);
                        customer.setCustomerName(name);
                        customer.setCustomerDayOfBirth(dayOfBirth);
                        customer.setCustomerAddress(address);
                        customer.setCustomerPhone(phone);
                        customer.setCustomerSex(sex);
                        customer.setCustomerPoint(Integer.parseInt(point));
                        if (customerBUS.updateCustomer(customer)) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thành công!",
                                    "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                            updateTable(customerTableModel, customerBUS.getAllCustomers());
                        } else
                            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!",
                                    "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                    }
            }
        });
        // FRAME SETTING //
//        frame.add(customerSearchComboBox);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void updateTable(DefaultTableModel tableModel, ArrayList<customer_DTO> customers){
        tableModel.setRowCount(0);
        for (customer_DTO customer : customers){
            String ID = customer.getCustomerID();
            String Name = customer.getCustomerName();
            String DayOfBirth = customer.getCustomerDayOfBirth();
            String Sex = customer.getCustomerSex();
            String Address = customer.getCustomerAddress();
            String Phone = customer.getCustomerPhone();
            int Point = customer.getCustomerPoint();
            Object[] row = {ID, Name, DayOfBirth, Sex, Address, Phone, Point};
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
    }
    public static void main(String[] args) {
        customerGUI gui = new customerGUI("quản lý khách hàng");
    }
}
