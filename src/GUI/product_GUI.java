package GUI;

import DTO.product_DTO;
import BUS.product_BUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

public class product_GUI implements ActionListener, KeyListener,MouseListener{
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtLoai;
    private JTextField txtGia;
    private JTextField txtCalories;
    private JTextField txtSoLuong;
    private JTextField txtMaNCC;
    private JPanel pnThongTinChung;
    private JPanel pnData;
    private JPanel pnTable;
    private JPanel pnTimKiem;
    private JPanel pnButton;
    private JComboBox cbSearch;
    private JTextField txtSearch;
    private JList searchList;
    private JTable tableSP;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnUpdate;
    private JPanel container;
    private JComboBox cbMaNCC;
    JFrame frame;
    product_BUS productBus = new product_BUS();
    public String[] columns = {"Mã sản phẩm", "Tên sản phẩm", "Giá", "Calories", "Số lượng","Loại sản phẩm"};
    ArrayList<product_DTO> listProduct= productBus.getAllProduct();



    public product_GUI(JFrame frame) {
//        frame = new JFrame("Quản lý sản phẩm");
        container.setBorder(new EmptyBorder(5, 10, 5, 10));

//        Xử lý panel Thông tin chung
        pnThongTinChung.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));

        //        Xử lý panel Tìm kiếm
        pnTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
//        Xử lý combobox
        DefaultComboBoxModel searchsList = new DefaultComboBoxModel();
        searchsList.addElement("Mã sản phẩm");
        searchsList.addElement("Tên sản phẩm");
        searchsList.addElement("Loại sản phẩm");
        cbSearch.setModel(searchsList);

//        Xử lý combobox mã ncc
//        DefaultComboBoxModel cbSupplier = new DefaultComboBoxModel();
//        for (String item : productBus.getAllSupplierID()){
//            cbSupplier.addElement(item);
//        }
//        cbMaNCC.setModel(cbSupplier);


//        render dữ liệu ra table
        tableSP.setDefaultEditor(Object.class, null);
//        tableSP.setRowHeight(20);
        productBus.renderDataTable(tableSP,columns,listProduct);




//        Xử lý sự kiện cho các nút thêm sửa xóa
        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        btnUpdate.addActionListener(this);
        txtSearch.addKeyListener(this);
        searchList.addMouseListener(this);
        tableSP.addMouseListener(this);





//        frame.add(container);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);


    }





    public void reset(){
        txtMa.setText("");
        txtTen.setText("");
        txtLoai.setText("");
        txtGia.setText("");
        txtCalories.setText("");
        txtSoLuong.setText("");
//        cbMaNCC.setSelectedItem("NC01");
    }

    public JTable getTableSP(){
        return tableSP;
    }

    public JPanel getPnProduct(){
        return container;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd){
            String productID = txtMa.getText().trim().toUpperCase();
            String name = txtTen.getText().trim();
            String category = txtLoai.getText().trim().toLowerCase();
            String price = txtGia.getText().trim();
            String calories = txtCalories.getText().trim();
            String stock = txtSoLuong.getText().trim();
//            String supplierID = String.valueOf(cbMaNCC.getSelectedItem());
            if(productID.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtMa.grabFocus();
                return;
            }else{
                if(!productID.startsWith("SP")){
                    JOptionPane.showMessageDialog(frame, "Mã sản phẩm không hợp lệ, vui lòng nhập lại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtMa.grabFocus();
                    return;
                }else {
                    if (productBus.hasProductID(productID)) {
                        JOptionPane.showMessageDialog(frame, "Mã sản phẩm đã tồn tại, vui lòng nhập lại",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtMa.grabFocus();
                        return;
                    }else{
                        if(productID.length() != 4){
                            JOptionPane.showMessageDialog(frame, "Mã sản phẩm phải đủ 4 kí tự, vui lòng nhập lại",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            txtMa.grabFocus();
                            return;
                        }
                    }
                }
            }

            if(name.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống tên sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtTen.grabFocus();
                return;
            }

            if(category.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống loại sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtLoai.grabFocus();
                return;
            }else{
                if(!category.equals("đồ ăn") && !category.equals("đồ uống")){
                    JOptionPane.showMessageDialog(frame, "Loại sản phẩm không hợp lệ (vui lòng nhập lại đồ ăn/đồ uống)",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtLoai.grabFocus();
                    return;
                }
            }

            if (price.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống giá sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtGia.grabFocus();
                return;
            }

            if(calories.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống calories sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtCalories.grabFocus();
                return;
            }

            if (stock.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống số lượng sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtSoLuong.grabFocus();
                return;
            }
            product_DTO p = new product_DTO(productID,name,Float.parseFloat(price),Float.parseFloat(calories),Integer.parseInt(stock),category);

            try {
                if (productBus.addProduct(p)){
                    JOptionPane.showMessageDialog(frame, "Thêm sản phẩm thành công",
                            "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception ex){
                System.out.println("Error when trying add product!");
            }
            reset();
            ArrayList<product_DTO> listProduct= productBus.getAllProduct();
            productBus.renderDataTable(tableSP,columns,listProduct);
        }else if(e.getSource() == btnDel){

            String productID = txtMa.getText().trim().toUpperCase();
            if(productID.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtMa.grabFocus();
                return;
            }else{
                if(!productID.startsWith("SP")){
                    JOptionPane.showMessageDialog(frame, "Mã sản phẩm không hợp lệ, vui lòng nhập lại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtMa.grabFocus();
                    return;
                }
            }
            productBus.deleteProduct(productID);
            JOptionPane.showMessageDialog(frame, "Xóa sản phẩm thành công",
                    "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
            reset();
            ArrayList<product_DTO> listProduct= productBus.getAllProduct();
            productBus.renderDataTable(tableSP,columns,listProduct);
        }else if(e.getSource() == btnUpdate){
            String productID = txtMa.getText().trim().toUpperCase();
            String name = txtTen.getText().trim();
            String category = txtLoai.getText().trim().toLowerCase();
            String price = txtGia.getText().trim();
            String calories = txtCalories.getText().trim();
            String stock = txtSoLuong.getText().trim();
//            String supplierID = String.valueOf(cbMaNCC.getSelectedItem());

            if(productID.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống mã sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtMa.grabFocus();
                return;
            }else{
                if(!productID.startsWith("SP")){
                    JOptionPane.showMessageDialog(frame, "Mã sản phẩm không hợp lệ, vui lòng nhập lại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtMa.grabFocus();
                    return;
                }else{
                    if(productID.length() != 4){
                        JOptionPane.showMessageDialog(frame, "Mã sản phẩm phải đủ 4 kí tự, vui lòng nhập lại",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        txtMa.grabFocus();
                        return;
                    }
                }
            }

            if(name.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống tên sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtTen.grabFocus();
                return;
            }

            if(category.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống loại sản phâm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtLoai.grabFocus();
                return;
            }else{
                if(!category.equals("đồ ăn") && !category.equals("đồ uống")){
                    JOptionPane.showMessageDialog(frame, "Loại sản phẩm không hợp lệ (vui lòng nhập lại đồ ăn/đồ uống)",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    txtLoai.grabFocus();
                    return;
                }
            }

            if (price.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống giá sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtGia.grabFocus();
                return;
            }

            if(calories.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống calories sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtCalories.grabFocus();
                return;
            }

            if (stock.equals("")){
                JOptionPane.showMessageDialog(frame, "Không được bỏ trống số lượng sản phẩm",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtSoLuong.grabFocus();
                return;
            }

            try {
                if (productBus.updateProduct(productID,name,Float.parseFloat(price),Float.parseFloat(calories),Integer.parseInt(stock),category)){
                    JOptionPane.showMessageDialog(frame, "Cập nhật sản phẩm thành công",
                            "Congratulations!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception ex){
                System.out.println("Error when trying add product!");
            }
            reset();
            ArrayList<product_DTO> listProduct= productBus.getAllProduct();
            productBus.renderDataTable(tableSP,columns,listProduct);

        }

    }




    @Override
    public void keyTyped(KeyEvent e) {
//        String value = txtSearch.getText();
//        DefaultListModel<String> lModel = new DefaultListModel<>();
//        ArrayList<String> listSearch = new ArrayList<>();
//        if(cbSearch.getSelectedItem().equals("Mã sản phẩm")){
//            listSearch = productBus.getAColumn("ProductID","Product");
//            for(int i=0; i<listSearch.size(); i++){
//                if(value.equals("")){
//                    productBus.renderDataTable(tableSP,columns,listProduct);
//                    break;
//                }
//                if(listSearch.get(i).contains(value.trim().toUpperCase())){
//                    lModel.addElement(listSearch.get(i));
//                }
//            }
//        }else if(cbSearch.getSelectedItem().equals("Tên sản phẩm")){
//            listSearch = productBus.getAColumn("Name","Product");
//            for(int i=0; i<listSearch.size(); i++){
//                if(value.equals("")){
//                    productBus.renderDataTable(tableSP,columns,listProduct);
//                    break;
//                }
//                if(listSearch.get(i).contains(productBus.toUpperCaseFirstChar(value.trim().toLowerCase()))){
//                    lModel.addElement(listSearch.get(i));
//                }
//            }
//        }
//        searchList.setModel(lModel);

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {
        String value = txtSearch.getText().trim().toLowerCase();
        DefaultListModel<String> lModel = new DefaultListModel<>();
        ArrayList<String> listSearch = new ArrayList<>();
        if(cbSearch.getSelectedItem().equals("Mã sản phẩm")){
            listSearch = productBus.getAColumn("ProductID","Product");
            for(int i=0; i<listSearch.size(); i++){
                if(value.equals("")){
                    productBus.renderDataTable(tableSP,columns,listProduct);
                    break;
                }
                if(listSearch.get(i).toLowerCase().contains(value)){
                    lModel.addElement(listSearch.get(i));
                }
            }
        }else if(cbSearch.getSelectedItem().equals("Tên sản phẩm")){
            listSearch = productBus.getAColumn("Name","Product");
            for(int i=0; i<listSearch.size(); i++){
                if(value.equals("")){
                    productBus.renderDataTable(tableSP,columns,listProduct);
                    break;
                }
                if(listSearch.get(i).toLowerCase().contains(value)){
                    lModel.addElement(listSearch.get(i));
                }
            }
        }
        searchList.setModel(lModel);










//        String value = txtSearch.getText();
//
//        ArrayList<String> listSearch = new ArrayList<>();
//        if(cbSearch.getSelectedItem().equals("Mã sản phẩm")){
//            listSearch = productBus.getAColumn("ProductID","Product");
//        }else if(cbSearch.getSelectedItem().equals("Tên sản phẩm")){
//            listSearch = productBus.getAColumn("Name","Product");
//        }
//
//        DefaultListModel<String> lModel = new DefaultListModel<>();
//        for(int i=0; i<listSearch.size(); i++){
//            if(value.equals("")){
//                productBus.renderDataTable(tableSP,columns,listProduct);
//                break;
//            }else if(listSearch.get(i).contains(value.trim().toUpperCase())){
//                lModel.addElement(listSearch.get(i));
//            }
////            if(listSearch.get(i).contains(value.trim().toUpperCase())){
////                lModel.addElement(listSearch.get(i));
////            }
//        }
//        searchList.setModel(lModel);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == tableSP){
            int column = tableSP.getSelectedColumn();
            int row = tableSP.getSelectedRow();
            String value1 = tableSP.getValueAt(row,0).toString();
            String value2 = tableSP.getValueAt(row,1).toString();
            String value3 = tableSP.getValueAt(row,2).toString();
            String value4 = tableSP.getValueAt(row,3).toString();
            String value5 = tableSP.getValueAt(row,4).toString();
            String value6 = tableSP.getValueAt(row,5).toString();
//            String value7 = tableSP.getValueAt(row,6).toString();


            txtMa.setText(value1);
            txtTen.setText(value2);
            txtGia.setText(value3);
            txtCalories.setText(value4);
            txtSoLuong.setText(value5);
            txtLoai.setText(value6);
//            cbMaNCC.setSelectedItem(value7);
        }



    }

    @Override
    public void mousePressed(MouseEvent e) {
//        productBus.renderDataTable(tableSP,columns,listProduct);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(e.getSource() == searchList){
            String cbSearchValue =cbSearch.getSelectedItem().toString();
            String searchListValue = searchList.getSelectedValue().toString();
            ArrayList<product_DTO> listProduct= new ArrayList<product_DTO>();
            listProduct=productBus.getProductFromCondition(searchListValue,cbSearchValue);
            productBus.renderDataTable(tableSP,columns,listProduct);
        }




    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }








}
