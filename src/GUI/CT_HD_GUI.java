package GUI;

import BUS.CT_HD_BUS;
import DAO.CT_HD_DAO;
import DTO.CT_HD_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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

    public CT_HD_GUI(String orderID) {
        JFrame frame = new JFrame("Quản lý chi tiết đơn hàng");
        frame.setVisible(true);
        frame.add(CT_HD_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // margin for panel root
        CT_HD_panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // add title for panel
        TTChung.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        TTSP.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        // add columns for JTable
        String[] columns = {"Mã HD", "Mã khách hàng",
                "Tên khách hàng", "Mã sản phẩm",
                "Tên sản phẩm", "Số lượng mua", "Đơn giá"};
        DefaultTableModel model = new DefaultTableModel(null, columns);

        // get CT_HD by orderID and show to JTable
        loadCT_HD(orderID, model);
        CT_HD_table.setModel(model);

        // get data from product and show to JList MaSP, TenSP
        ArrayList<CT_HD_DAO.Product> products = new CT_HD_BUS().getProductIdAndName();

        DefaultListModel<String> modelIdLs = new DefaultListModel<>();
        DefaultListModel<String> modelNameLs = new DefaultListModel<>();

        for (CT_HD_DAO.Product product : products) {
            modelIdLs.addElement(product.getId());
            modelNameLs.addElement(product.getName());
        }

        pro_id_ls.setModel(modelIdLs);
        pro_name_ls.setModel(modelNameLs);
        pro_id_ls.setVisibleRowCount(4);
        pro_name_ls.setVisibleRowCount(4);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void loadCT_HD(String orderID, DefaultTableModel model) {
        // get CT_HD by orderID and show to JTable
        ArrayList<CT_HD_DTO> list = new CT_HD_BUS().getCT_HD_ByOrderID(orderID);
        Object[] row = new Object[7];
        try {
            for (CT_HD_DTO ct : list) {
                row[0] = ct.getOrderID();
                row[1] = ct.getCustomerID();
                row[2] = ct.getCustomerName();
                row[3] = ct.getProductID();
                row[4] = ct.getProductName();
                row[5] = ct.getQuantity();
                row[6] = ct.getPrice();
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
    }
}
