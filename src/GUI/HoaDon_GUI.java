package GUI;

import BUS.HD_BUS;
import DTO.HD_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
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
    private JPanel search_panel;
    private JComboBox search_type;
    private JTextField search_input;
    private JList search_result;
    private JPanel col_1;
    private JPanel col_2;
    private static ArrayList<HD_DTO> hoaDonList = new ArrayList<>();
    private static HD_BUS hdBus = new HD_BUS();
    private ArrayList<HD_DTO> hoaDonListTemp = new ArrayList<>();

    public HoaDon_GUI() {
        JFrame frame = new JFrame("Quản lý hóa đơn");
        frame.add(HoaDon_panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HoaDon_panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        show_info.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        HoaDon_table.setRowHeight(20);
        search_panel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

//        Begin

//        Load data to hoaDonList
        loadHoaDonList();
//        Show hoaDonList to JTable
        String[] columns = {"Mã hóa đơn", "Mã nhân viên", "Tên nhân viên",
                "Mã khách hàng", "Tên khách hàng", "Ngày giờ đặt"};
        loadTableModel(HoaDon_table, columns, hoaDonList);
//        Add data for combo box search
        DefaultComboBoxModel dModel = new DefaultComboBoxModel();
        for (String col : columns) {
            dModel.addElement(col);
        }
        search_type.setModel(dModel);

        search_result.setVisibleRowCount(4);
//        End

        frame.pack();
        frame.setLocationRelativeTo(null);

//        Data binding from table to text field
        HoaDon_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int index = HoaDon_table.getSelectedRow();
                HD_DTO hoaDon = hoaDonList.get(index);
                HDId_txt.setText(hoaDon.getOrderID());
                cusId_txt.setText(hoaDon.getCustomerID());
                cusName_txt.setText(hoaDon.getCustomerName());
                emID_txt.setText(hoaDon.getEmployeeID());
                emName_txt.setText(hoaDon.getEmployeeName());
                orderDate_txt.setText(hoaDon.getOrderDate());
            }
        });
//        Data binding to combo box search type to search result list
        search_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = search_type.getSelectedIndex();

//                Add data to search_result
                DefaultListModel<String> lModel = new DefaultListModel<>();
                for (HD_DTO hd : hoaDonList) {
                    lModel.addElement(hd.getByIndex(index));
                }
                search_result.setModel(lModel);
            }
        });

//        Input lister for search input
        search_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int index = search_type.getSelectedIndex();
                if (index == -1) {
                    return;
                }

//                Add matching string to search result list
                DefaultListModel<String> lModel = new DefaultListModel<>();
                String search = search_input.getText(), element;

                for (HD_DTO item : hoaDonList) {
                    element = item.getByIndex(index);
                    if (element.contains(search)) {
                        lModel.addElement(element);
                    }
                }

                search_result.setModel(lModel);
            }
        });
//        select item in list then show result to table
        search_result.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String thisValue = search_result.getSelectedValue().toString();
                int index = search_type.getSelectedIndex();
                if (index == -1) return;

                hoaDonListTemp = getAllOrdersByCondition(thisValue, index);
                loadTableModel(HoaDon_table, columns, hoaDonListTemp);
            }
        });
    }

//    Load Hoa Don to hoaDonList
    public void loadHoaDonList() {
        hoaDonList = hdBus.getAllOrders();
    }

//    Load hoaDonList to table model
    public void loadTableModel(JTable jTable, String[] columns, ArrayList<HD_DTO> list) {
        DefaultTableModel model = new DefaultTableModel(null, columns);
        byte i;
        Object[] rows = new Object[6];

        try {
            for (HD_DTO item : list) {
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
        jTable.setModel(model);
    }
    private ArrayList<HD_DTO> getAllOrdersByCondition(String searchStr, int index) {
        ArrayList<HD_DTO> result = new ArrayList<>();
        for (HD_DTO item : hoaDonList) {
            String t = item.getByIndex(index);
            if (item.getByIndex(index).contains(searchStr)) {
                result.add(item);
            }
        }

        return result;
    }
}
