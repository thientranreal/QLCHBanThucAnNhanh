package GUI;

import BUS.HD_BUS;
import DTO.HD_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

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
    private JButton update_btn;
    private JButton rm_btn;
    private JButton add_btn;
    private JPanel btn_panel;
    private JLabel date_lb;
    private JTextField day_txt;
    private JTextField month_txt;
    private JTextField year_txt;
    private JTextField hour_txt;
    private JTextField minute_txt;
    private JTextField second_txt;
    private JLabel time_lb;
    private JPanel date_panel;
    private JPanel time_panel;
    private JButton generate_dtime_btn;
    private JPanel generate_btn_panel;
    private JButton get_dtime_now_btn;
    private static ArrayList<HD_DTO> hoaDonList;
    private static ArrayList<String> cusEmList;
    private static HD_BUS hdBus = new HD_BUS();
    private ArrayList<HD_DTO> hoaDonListTemp;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDon_GUI() {
        JFrame frame = new JFrame("Quản lý hóa đơn");
        frame.add(HoaDon_panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HoaDon_panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        show_info.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
        HoaDon_table.setRowHeight(20);
        HoaDon_table.setDefaultEditor(Object.class, null);
        search_panel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        search_result.setVisibleRowCount(4);

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

//        Input listener for search input
        search_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int index = search_type.getSelectedIndex();
                if (index == -1) {
                    return;
                }
//                if input is empty then load all data to Hoa Don table
                if (search_input.getText().equals("")) {
                    loadTableModel(HoaDon_table, columns, hoaDonList);
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
//        Double click event for Hoa Don table to show CT_HD frame
        HoaDon_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    int row = HoaDon_table.getSelectedRow();
                    String orderId = HoaDon_table.getValueAt(row, 0).toString();
                    new CT_HD_GUI(orderId, frame);
                }
            }
        });
//        Add button hoa don event
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
//        Get current date time button
        get_dtime_now_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderDate_txt.setText(getCurrentDateTime());
            }
        });
//        generate date time button
        generate_dtime_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = String.format("%s-%s-%s",
                        year_txt.getText().trim(),
                        month_txt.getText().trim(),
                        day_txt.getText().trim());

//                add zero in front of time
                if(hour_txt.getText().length() == 1) {
                    hour_txt.setText("0" + hour_txt.getText());
                }
                if(minute_txt.getText().length() == 1) {
                    minute_txt.setText("0" + minute_txt.getText());
                }
                if(second_txt.getText().length() == 1) {
                    second_txt.setText("0" + second_txt.getText());
                }
                String time = String.format("%s:%s:%s",
                        hour_txt.getText().trim(),
                        minute_txt.getText().trim(),
                        second_txt.getText().trim());

//                check if date is valid
                try {
                    df.setLenient(false);
                    date = df.format(df.parse(date));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Ngày không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Integer.parseInt(year_txt.getText().trim()) < 2000) {
                    JOptionPane.showMessageDialog(frame, "Năm không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//                format date time to sql date time
                try {
                    orderDate_txt.setText(dtf.format(dtf.parse(date + " " + time)));
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Giờ không hợp lệ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
//        Add hoa don to database
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderId = HDId_txt.getText().trim();
                String emId = emID_txt.getText().trim();
                String cusId = cusId_txt.getText().trim();
                String orderDate = orderDate_txt.getText().trim();

                int updatedRows = hdBus.addNewHD(orderId, emId, cusId, orderDate);
                JOptionPane.showMessageDialog(frame, String.format("Thêm %d dòng thành công.", updatedRows),
                        "Updated", JOptionPane.INFORMATION_MESSAGE);
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
//    Check orderId exist
    private boolean isOrderIdExist(String orderId) {
        for (HD_DTO item: hoaDonList) {
            if (item.getOrderID().equals(orderId)) return true;
        }
        return false;
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
//    Get current date time
    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
