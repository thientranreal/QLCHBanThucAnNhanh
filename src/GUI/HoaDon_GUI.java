package GUI;

import BUS.HD_BUS;
import DTO.HD_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HoaDon_GUI {
    private JPanel HoaDon_panel;
    private JLabel title_lb;
    private JPanel title_panel;
    private JPanel info_wrapper;
    private JTable HoaDon_table;
    private JPanel table_panel;
    private JPanel show_info;
    private JTextField HDId_txt;
    private JTextField emID_txt;
    private JTextField emName_txt;
    private JTextField cusId_txt;
    private JTextField cusName_txt;
    private JTextField orderDate_txt;
    private JLabel HDId_lb;
    private JLabel emID_lb;
    private JLabel emName_lb;
    private JLabel cusId_lb;
    private JLabel cusName_lb;
    private JLabel orderDate_lb;
    private static ArrayList<HD_DTO> hoaDonList = new ArrayList<>();
    private static HD_BUS hdBus = new HD_BUS();

    public HoaDon_GUI() {
        JFrame frame = new JFrame("Quản lý hóa đơn");
        frame.add(HoaDon_panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HoaDon_panel.setBorder(new EmptyBorder(5,10,5,10));
        show_info.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        HoaDon_table.setRowHeight(20);

//        Begin

//        Load data to hoaDonList
        loadHoaDonList();
//        Show hoaDonList to JTable
        String[] columns = {"Mã hóa đơn", "Mã nhân viên", "Tên nhân viên",
                "Mã khách hàng", "Tên khách hàng", "Ngày giờ đặt"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columns);
        loadTableModel(tableModel);
        HoaDon_table.setModel(tableModel);

//        End

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

//    Load Hoa Don to hoaDonList
    public void loadHoaDonList() {
        hoaDonList = hdBus.getAllOrders();
    }

//    Load hoaDonList to table model
    public void loadTableModel(DefaultTableModel model) {
        byte i;
        Object[] rows = new Object[6];

        try {
            for (HD_DTO item : hoaDonList) {
                i = 0;
                rows[i++] = item.getOrderID();
                rows[i++] = item.getEmployeeID();
                rows[i++] = item.getEmployeeName();
                rows[i++] = item.getCustomerID();
                rows[i++] = item.getCustomerName();
                rows[i++] = item.getOrderDate();
                model.addRow(rows);
            }
        } catch (NullPointerException ex) {
            i = 0;
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            rows[i++] = "N/A";
            model.addRow(rows);
        }
    }
}
