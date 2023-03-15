package GUI;

import BUS.CT_HD_BUS;
import DTO.CT_HD_Product_DTO;
import DTO.CT_HD_ShowTable_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CT_HD_GUI {
    private JPanel CT_HD_panel;
    private JPanel header;
    private JPanel TTChung;
    private JPanel TTSP;
    private JLabel header_lb;
    private JTable CT_HD_table;
    private JPanel col_1;
    private JPanel col_2;
    private JPanel col_3;
    private JPanel col_4;
    private JTextField order_id_txt;
    private JTextField order_date_txt;
    private JTextField emp_id_txt;
    private JTextField emp_name_txt;
    private JLabel order_id_lb;
    private JLabel order_date_lb;
    private JLabel emp_id_lb;
    private JLabel emp_name_lb;
    private JTextField cus_id_txt;
    private JTextField cus_name_txt;
    private JTextField cus_address_txt;
    private JTextField cus_phone_txt;
    private JLabel cus_id_lb;
    private JLabel cus_name_lb;
    private JLabel cus_address_lb;
    private JLabel cus_phone_lb;
    private JTextField product_quantity_txt;
    private JTextField product_price_txt;
    private JLabel product_id_lb;
    private JLabel product_name_lb;
    private JLabel product_quantity_lb;
    private JLabel product_price_lb;
    private JTextField product_desc_txt;
    private JTextField discount_txt;
    private JTextField total_price_txt;
    private JLabel product_desc_lb;
    private JLabel discount_lb;
    private JLabel total_price_lb;
    private JTextField product_id_txt;
    private JTextField product_name_txt;
    private JList pro_id_ls;
    private JList pro_name_ls;
    private JScrollPane JScroll_table;
    private JScrollPane JScroll_proId;
    private JScrollPane JScroll_proName;
    private JButton rmPro_btn;
    private JButton addPro_btn;
    private JTextField stock_txt;
    private JLabel stock_lb;
    private JButton printOrder_btn;
    private JPanel Button_Pnl;
    private static ArrayList<CT_HD_ShowTable_DTO> list;
    private static ArrayList<CT_HD_Product_DTO> products;
    private static CT_HD_BUS busCTHD = new CT_HD_BUS();
    private ArrayList<CT_HD_Product_DTO> productsTemp;

    public CT_HD_GUI(String orderID, String emId, String emName, String cusId, String cusName, String orderDate, JFrame fatherFrame) {
        fatherFrame.setVisible(false);
        JFrame frame = new JFrame("Quản lý chi tiết đơn hàng");

//      Add window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                fatherFrame.setVisible(true);
            }
        });

        frame.add(CT_HD_panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CT_HD_table.setRowHeight(20);
        CT_HD_table.setDefaultEditor(Object.class, null);

        // margin for panel root
        CT_HD_panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // add title for panel
        TTChung.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        TTSP.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        order_id_txt.setText(orderID);
        order_id_txt.setEditable(false);

        // add text field for customer and employee
        order_id_txt.setText(orderID);
        String[] chiTiet = busCTHD.getCusPhoneAddress(cusId).split(":");
        order_date_txt.setText(orderDate);
        emp_id_txt.setText(emId);
        emp_name_txt.setText(emName);
        cus_id_txt.setText(cusId);
        cus_name_txt.setText(cusName);
        cus_address_txt.setText(chiTiet[0]);
        cus_phone_txt.setText(chiTiet[1]);

        // text field read only
        order_date_txt.setEditable(false);
        emp_id_txt.setEditable(false);
        emp_name_txt.setEditable(false);
        cus_id_txt.setEditable(false);
        cus_name_txt.setEditable(false);
        cus_address_txt.setEditable(false);
        cus_phone_txt.setEditable(false);
        order_id_txt.setEditable(false);
        product_desc_txt.setEditable(false);
        stock_txt.setEditable(false);
        product_price_txt.setEditable(false);

        // add columns for JTable
        String[] columns = {"Mã HD", "Mã khách hàng",
                "Tên khách hàng", "Mã sản phẩm",
                "Tên sản phẩm", "Số lượng mua", "Đơn giá"};
        DefaultTableModel model = new DefaultTableModel(null, columns);

        // get CT_HD by orderID and show to JTable
        list = busCTHD.getCT_HD_ByOrderID(orderID);
        loadCT_HD_Table(columns);

        // add text field for thanh tien
        total_price_txt.setText(String.format("%,.2f", tinhThanhTien()));

        // get data from product and show to JList MaSP, TenSP
        products = busCTHD.getProductInfo();
        productsTemp = busCTHD.getProductInfo();
        loadProductList(products);

        pro_id_ls.setVisibleRowCount(4);
        pro_name_ls.setVisibleRowCount(4);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Event listener==============================================================================

        // data binding for list id and name product 2 way
        pro_id_ls.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = pro_id_ls.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                product_id_txt.setText(pro_id_ls.getSelectedValue().toString());
                pro_name_ls.setSelectedIndex(index);
                product_desc_txt.setText(productsTemp.get(index).getCategory());
                stock_txt.setText(String.valueOf(productsTemp.get(index).getStock()));
            }
        });

        pro_name_ls.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = pro_name_ls.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                product_name_txt.setText(pro_name_ls.getSelectedValue().toString());
                pro_id_ls.setSelectedIndex(index);
                product_desc_txt.setText(productsTemp.get(index).getCategory());
                stock_txt.setText(String.valueOf(productsTemp.get(index).getStock()));
            }
        });
        // End data binding for list id and name product 2 way

        // data binding for table --> text field 1 way
        CT_HD_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row = CT_HD_table.getSelectedRow();
                // get soluong, don gia and add them to JtextField
                product_quantity_txt.setText(CT_HD_table.getModel().getValueAt(row, 5).toString());
                product_price_txt.setText(CT_HD_table.getModel().getValueAt(row, 6).toString());

                // force select product JList
                String proId = CT_HD_table.getModel().getValueAt(row, 3).toString();
                for (int i = 0; i < productsTemp.size(); i++) {
                    if (proId.equals(productsTemp.get(i).getId())) {
                        pro_id_ls.setSelectedIndex(i);
                    }
                }

            }
        });
        // End data binding for table --> text field 1 way

        // event listener for add product button
        addPro_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // string processing
                product_id_txt.setText(product_id_txt.getText().trim().toUpperCase());

                // check empty for product id txt
                if (product_id_txt.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Không được bỏ trống Mã sản phẩm",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    product_id_txt.grabFocus();
                    return;
                }
                // check if product exists in product table
                if (!isProductExist(product_id_txt.getText())) {
                    JOptionPane.showMessageDialog(frame, "Mã sản phẩm không tồn tại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    product_id_txt.grabFocus();
                    return;
                }
                int sl;
                // check sl is a number or not
                try {
                    sl = Integer.parseInt(product_quantity_txt.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Số lượng phải là số nguyên", "Error", JOptionPane.ERROR_MESSAGE);
                    product_quantity_txt.grabFocus();
                    return;
                }

                if (sl <= 0) {
                    JOptionPane.showMessageDialog(frame, "Số lượng phải lớn hơn 0", "Error", JOptionPane.ERROR_MESSAGE);
                    product_quantity_txt.grabFocus();
                    return;
                }

                // check if that product is still available or not
                int slConLai = Integer.parseInt(stock_txt.getText()) - sl;
                if (slConLai < 0) {
                    JOptionPane.showMessageDialog(frame, "Số lượng kho không đủ", "Error", JOptionPane.ERROR_MESSAGE);
                    product_quantity_txt.grabFocus();
                    return;
                }

                int updatedRow;
                // update product quantity
                if (isProductExistInOrder(product_id_txt.getText())) {
                    // find sl cu in list then add to sl moi
                    for (CT_HD_ShowTable_DTO item : list) {
                        if (item.getProductID().equals(product_id_txt.getText())) {
                            sl = sl + item.getQuantity();
                            break;
                        }
                    }

                    updatedRow = busCTHD.updateSLByProOrderId(product_id_txt.getText(), order_id_txt.getText(), sl);
                    JOptionPane.showMessageDialog(frame, String.format("Cập nhập thành công %d dòng", updatedRow),
                            "Updated", JOptionPane.INFORMATION_MESSAGE);
                } // add new product to order detail
                else {
                    updatedRow = busCTHD.addNewCT_HD(product_id_txt.getText(), order_id_txt.getText(), sl);
                    JOptionPane.showMessageDialog(frame, String.format("Thêm thành công %d dòng", updatedRow),
                            "Updated", JOptionPane.INFORMATION_MESSAGE);
                }

                // update stock in Product
                busCTHD.updateProductStock(product_id_txt.getText(), slConLai);

                // Load updated data to CT_HD_table, load updated product to products
                loadDataDisplay(orderID, columns);
            }
        });
        // End event listener for add product button

        // Add event for delete btn
        rmPro_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // string processing
                product_id_txt.setText(product_id_txt.getText().trim().toUpperCase());

                // check empty for product id txt
                if (product_id_txt.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Không được bỏ trống Mã sản phẩm",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    product_id_txt.grabFocus();
                    return;
                }
                // check if product exists in product table
                if (!isProductExistInOrder(product_id_txt.getText())) {
                    JOptionPane.showMessageDialog(frame, "Mã sản phẩm không tồn tại",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    product_id_txt.grabFocus();
                    return;
                }

                // update product stock
                int sl = Integer.parseInt(stock_txt.getText());
                for (CT_HD_ShowTable_DTO item : list) {
                    if (item.getProductID().equals(product_id_txt.getText())) {
                        sl = sl + item.getQuantity();
                        break;
                    }
                }
                // add deleted quantity back to stock
                busCTHD.updateProductStock(product_id_txt.getText(), sl);

                int updateRow = busCTHD.deleteProduct(order_id_txt.getText(), product_id_txt.getText());
                JOptionPane.showMessageDialog(frame, String.format("Xóa thành công %d dòng", updateRow),
                        "Updated", JOptionPane.INFORMATION_MESSAGE);

                loadDataDisplay(orderID, columns);
            }
        });
        // End add event for delete btn

//        Input listener for product id text
        product_id_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String proId = product_id_txt.getText();

//                Empty productsTemp for storing new search data
                productsTemp = new ArrayList<>();
                for (CT_HD_Product_DTO item : products) {
                    if (item.getId().contains(proId)) {
                        productsTemp.add(item);
                    }
                }
//                Load productsTemp to List
                loadProductList(productsTemp);
            }
        });
//        End Input listener for product id text

//        Input listener for product name text
        product_name_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String proName = product_name_txt.getText();

//                Empty productsTemp for storing new search data
                productsTemp = new ArrayList<>();
                for (CT_HD_Product_DTO item : products) {
                    if (item.getName().contains(proName)) {
                        productsTemp.add(item);
                    }
                }
//                Load productsTemp to List
                loadProductList(productsTemp);
            }
        });
//        End Input listener for product name text

//        Print order click listener
        printOrder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "Khách hàng chưa mua sản phẩm",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
//                    make directory for customer
                    String cusPath = "QLHD/" + cusId;
                    new File("QLHD").mkdir();
                    new File(cusPath).mkdir();
                    writeFile(String.format("%s/%s.txt", cusPath ,orderID), makeCTHDString());
                    JOptionPane.showMessageDialog(frame, "Xuất hóa đơn thành công",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Không xuất được hóa đơn",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
//        End Print order click listener
    }

    private void loadCT_HD_Table(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(null, columns);
        // get CT_HD by orderID and show to JTable
        Object[] row = new Object[7];
        try {
            for (CT_HD_ShowTable_DTO ct : list) {
                row[0] = ct.getOrderID();
                row[1] = ct.getCustomerID();
                row[2] = ct.getCustomerName();
                row[3] = ct.getProductID();
                row[4] = ct.getProductName();
                row[5] = ct.getQuantity();
                row[6] = String.format("%,.2f", ct.getPrice());
                model.addRow(row);
            }
        } catch (NullPointerException ex) {
            row[0] = "N/A";
            row[1] = "N/A";
            row[2] = "N/A";
            row[3] = "N/A";
            row[4] = "N/A";
            row[5] = "N/A";
            row[6] = "N/A";
            model.addRow(row);
        }
        CT_HD_table.setModel(model);
    }
    private void loadProductList(ArrayList<CT_HD_Product_DTO> products) {
        DefaultListModel<String> modelIdLs = new DefaultListModel<>();
        DefaultListModel<String> modelNameLs = new DefaultListModel<>();

        for (CT_HD_Product_DTO product : products) {
            modelIdLs.addElement(product.getId());
            modelNameLs.addElement(product.getName());
        }

        pro_id_ls.setModel(modelIdLs);
        pro_name_ls.setModel(modelNameLs);
    }
    private void loadDataDisplay(String orderID, String[] columns) {
        // Load updated data to CT_HD_table, load updated product to products
        list = busCTHD.getCT_HD_ByOrderID(orderID);
        loadCT_HD_Table(columns);

//        Update data for products and products Temp
        products = busCTHD.getProductInfo();
        productsTemp = busCTHD.getProductInfo();

        loadProductList(products);

        // force set index Productid List to 0
        pro_id_ls.setSelectedIndex(0);
        // update thanh tien
        total_price_txt.setText(String.format("%,.2f", tinhThanhTien()));
    }
    private float tinhThanhTien() {
        float sum = 0;
        for (CT_HD_ShowTable_DTO item : list) {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }
    // check if product exist in Order Detail
    private boolean isProductExistInOrder(String proId) {
        for (CT_HD_ShowTable_DTO item : list) {
            if (item.getProductID().equals(proId)) return true;
        }
        return false;
    }
    // check if product exist in Product
    private boolean isProductExist(String proId) {
        for (CT_HD_Product_DTO item : products) {
            if (item.getId().equals(proId)) return true;
        }
        return false;
    }
//    make order string
    private String makeCTHDString() {
        String title = "Hóa đơn mua hàng\n";
        String separator = "===================================\n";
        String generalInfo = String.format("Mã HD: %s\n" +
                "Ngày bán: %s\n" +
                "Thông tin nhân viên: %s-%s\n" +
                "Thông tin khách hàng: %s-%s\n", order_id_txt.getText(),
                order_date_txt.getText(),
                emp_id_txt.getText(),
                emp_name_txt.getText(),
                cus_id_txt.getText(),
                cus_name_txt.getText());
        String productHeader = String.format("%-8s%-30s%-8s%-15s\n", "Mã SP", "Tên SP", "SL", "Đơn giá");
        String productList = "";
        for (CT_HD_ShowTable_DTO item: list) {
            productList += String.format("%-8s%-30s%-8s%-15s\n", item.getProductID(),
                    item.getProductName(),
                    item.getQuantity(),
                    item.getPrice());
        }
        String thanhTien = "Thành tiền: " + total_price_txt.getText();
        return title + separator + generalInfo + separator +
                productHeader + productList + separator + thanhTien;
    }
//    Write hoa don to file
    public void writeFile(String filename, String text) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.close();
        fw.close();
    }
}
