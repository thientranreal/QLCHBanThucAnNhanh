package GUI;

import BUS.ThongKe_BUS;
import DTO.HD_DTO;
import DTO.ThongKe_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThongKe_GUI {
    private JPanel thongKe_pnl;
    private JPanel title_pnl;
    private JPanel main_pnl;
    private JLabel title_lb;
    private JPanel col_1;
    private JPanel col_2;
    private JTextField product_txt;
    private JList product_search_ls;
    private JComboBox from_day_cb;
    private JComboBox from_month_cb;
    private JComboBox to_day_cb;
    private JComboBox to_month_cb;
    private JLabel product_lb;
    private JLabel from_lb;
    private JPanel from_date_pnl;
    private JPanel to_date_pnl;
    private JLabel to_lb;
    private JTable show_table;
    private JPanel tongDT_pnl;
    private JTextField tongDT_txt;
    private JLabel tongDT_lb;
    private JPanel btn_pnl;
    private JButton thongKe_btn;
    private JTextField from_year_txt;
    private JTextField to_year_txt;
    private static ThongKe_BUS tkBus = new ThongKe_BUS();
    private static ArrayList<String> productList;
    private ArrayList<String> productListTemp;
    private ArrayList<ThongKe_DTO> thongKeList;
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKe_GUI(JFrame frame) {
//        JFrame frame = new JFrame("Thống kê sản phẩm");
//        frame.add(thongKe_pnl);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thongKe_pnl.setBorder(new EmptyBorder(5, 10, 5, 10));
//        Set height for search product list
        product_search_ls.setVisibleRowCount(4);

//        Set editable for combo box
        from_day_cb.setEditable(true);
        to_day_cb.setEditable(true);
        from_month_cb.setEditable(true);
        to_month_cb.setEditable(true);

//        Load data to productList
        productList = tkBus.getAllProduct();

//        Load data to JList
        loadProductSearchList(product_search_ls, productList);

//        Load day for combo box
        loadDateCb(from_day_cb, 31);
        loadDateCb(to_day_cb, 31);

//        Load month for combo box
        loadDateCb(from_month_cb, 12);
        loadDateCb(to_month_cb, 12);

//       Load heading for JTable
        String[] columns = {"Mã SP", "Tên sản phẩm", "Số lượng bán", "Đơn giá", "Tổng tiền"};
        DefaultTableModel tModel = new DefaultTableModel(null, columns);
        show_table.setModel(tModel);
        show_table.setRowHeight(20);



//        frame.pack();
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);

//        click event for product_search_ls
        product_search_ls.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = product_search_ls.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                product_txt.setText(product_search_ls.getSelectedValue().toString());
            }
        });
//        Input listener for product text box
        product_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                productListTemp = new ArrayList<>();
//                find matching product from productList then add to productTemp
                for (String item : productList) {
                    if (item.contains(product_txt.getText())) {
                        productListTemp.add(item);
                    }
                }
//                show search result to JList
                loadProductSearchList(product_search_ls, productListTemp);
            }
        });
//        Click listener for thong ke button
        thongKe_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromDate = String.format("%s-%s-%s",
                        from_year_txt.getText().trim(),
                        from_month_cb.getSelectedItem().toString().trim(),
                        from_day_cb.getSelectedItem().toString().trim());

                String toDate = String.format("%s-%s-%s",
                        to_year_txt.getText().trim(),
                        to_month_cb.getSelectedItem().toString().trim(),
                        to_day_cb.getSelectedItem().toString().trim());

//                Check from date and to date
                if (!isDateValid(fromDate)) {
                    JOptionPane.showMessageDialog(frame, "Ngày bắt đầu không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isDateValid(toDate)) {
                    JOptionPane.showMessageDialog(frame, "Ngày kết thúc không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int index = product_search_ls.getSelectedIndex();

//                Nếu search list không được chọn thì sẽ tính thống kê hết sản phẩm
                if (index < 0) {
                    thongKeList = tkBus.thongKe(fromDate, toDate);
                    loadShowTable(show_table, columns, thongKeList);
                }
//                  Nếu search list được chọn thì sẽ thống kê sản phẩm được chọn đó
                else {
                    thongKeList = tkBus.thongKeDieuKien(fromDate, toDate, product_search_ls.getSelectedValue().toString().split(":")[0]);
                    loadShowTable(show_table, columns, thongKeList);
                }
//                Tính tổng doanh thu
                tongDT_txt.setText(String.format("%,.2f", tinhTongDT()));
            }
        });
    }

//    getPanel
    public JPanel getThongKe_pnl() {
        return thongKe_pnl;
    }

//    Load data to list
    private void loadProductSearchList(JList jList, ArrayList<String> proList) {
        DefaultListModel model = new DefaultListModel();
        for (String item : proList) {
            model.addElement(item);
        }
        jList.setModel(model);
    }

//    Load data to date combo box
    private void loadDateCb(JComboBox comboBox, int max) {
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
        for (int i = 1; i <= max; i++) {
            cbModel.addElement(i);
        }
        comboBox.setModel(cbModel);
    }

//    Check date valid
    private boolean isDateValid(String date)
    {
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

//    Load data to show table
    private void loadShowTable(JTable table, String[] columns, ArrayList<ThongKe_DTO> thongKeList) {
        DefaultTableModel model = new DefaultTableModel(null, columns);
        byte i;
        Object[] rows = new Object[5];

        try {
            for (ThongKe_DTO item : thongKeList) {
                i = 0;
                rows[i++] = item.getProId();
                rows[i++] = item.getProName();
                rows[i++] = item.getQuantity();
                rows[i++] = String.format("%,.2f", item.getPrice());
                rows[i++] = String.format("%,.2f", item.getTotalPrice());
                model.addRow(rows);
            }
        } catch (NullPointerException ex) {
            i = 0;
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            model.addRow(rows);
        }
        table.setModel(model);
    }

//    Tính tổng doanh thu
    private float tinhTongDT() {
        float sum = 0;
        for (ThongKe_DTO item : thongKeList) {
            sum += item.getTotalPrice();
        }
        return sum;
    }
}
