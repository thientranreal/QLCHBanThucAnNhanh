package GUI;

import BUS.*;
import DTO.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class QuanLiTaiKhoan_GUI {
    private final Account_BUS accountBUS = new Account_BUS();
    private final Access_BUS accessBUS = new Access_BUS();

    private JPanel container;
    private JTextField idInput;
    private JComboBox accessCbx;
    private JPanel mainPanel;
    private JLabel qldnLabel;
    private JPanel listPanel;
    private JPanel infoPanel;
    private JLabel sttLabel;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField userInput;
    private JTextField passInput;
    private JLabel accessLabel;
    private JComboBox SearchCbx;
    private JList<String> SearchResultList;
    private JTable AccountTable;
    private JPanel btnPanel;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton delBtn;
    private JTextField searchInput;

    public QuanLiTaiKhoan_GUI(JFrame frame) {
//      --------------FRAME---------------
//        JFrame frame = new JFrame();
//        frame.setTitle("Quản lí Tài khoản");
//        frame.add(container);
//        SearchResultList.setVisibleRowCount(6);

//      --------------TABLE---------------
        String[] columnNames = {"Mã tài khoản", "Tên tài khoản", "Mật khẩu", "Quyền"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        AccountTable.setModel(tableModel);
        AccountTable.setDefaultEditor(Object.class, null);
        updateTable(tableModel, accountBUS.getAllAccounts());
        AccountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Vector<String> AccountList = tableModel.getDataVector().elementAt(
                        AccountTable.convertRowIndexToModel(AccountTable.getSelectedRow()));
                idInput.setText(AccountList.get(0));
                userInput.setText(AccountList.get(1));
                passInput.setText(AccountList.get(2));

                accessCbx.setSelectedItem(AccountList.get(3));
            }
        });

//      --------------INPUT---------------
        ArrayList<Access_DTO> accessList = accessBUS.getAllAccesses();
        idInput.setMargin(new Insets(2, 6, 2, 6));
        userInput.setMargin(new Insets(2, 6, 2, 6));
        searchInput.setMargin(new Insets(2, 6, 2, 6));
        accessCbx.setSelectedItem(accessList.get(0));
        SearchCbx.setSelectedItem("Mã Tài Khoản");

        searchInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String q = searchInput.getText().trim();
                    if (!q.isBlank()) {
                        ArrayList<Account_DTO> resultList;
                        switch (SearchCbx.getSelectedIndex()) {
                            case 0 -> {
                                resultList = accountBUS.SearchAccountsByID(q.toUpperCase());
                                updateTable(tableModel, resultList);
                            }
                            case 1 -> {
                                resultList = accountBUS.SearchAccountsByUsername(q);
                                updateTable(tableModel, resultList);
                            }
                            case 2 -> {
                                resultList = accountBUS.SearchAccountsByAuthority(q);
                                updateTable(tableModel, resultList);
                            }
                        }
                    } else {
                        updateTable(tableModel, accountBUS.getAllAccounts());
                    }
                }
            }
        });

//      --------------BUTTON---------------
        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                String AccountID = idInput.getText().toUpperCase().trim();
                String AccessID = accessBUS.getIDFromAuthority(accessCbx.getSelectedItem().toString());
                String Username = userInput.getText().trim();
                String Password = passInput.getText().trim();

                if (AccountID.isBlank() || AccessID.isBlank() || Username.isBlank() || Password.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại: Thiểu dữ liệu!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                } else if (!accountBUS.hasAccount(AccountID)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại: Mã tài khoản không tồn tại!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                } else if (accountBUS.hasAccountUsernameForUpdate(AccountID, Username)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại: Tên tài khoản đã tổn tại!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(AccountID);
                    account.setAccessID(AccessID);
                    account.setUsername(Username);
                    account.setPassword(Password);
                    if (accountBUS.updateAccount(account)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        updateTable(tableModel, accountBUS.getAllAccounts());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                searchInput.setText("");
                }
            }
        });

        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String AccountID = idInput.getText().toUpperCase().trim();
                String AccessID = accessBUS.getIDFromAuthority(accessCbx.getSelectedItem().toString());
                String Username = userInput.getText().trim();
                String Password = passInput.getText().trim();
                if (AccountID.isBlank() || AccessID.isBlank() || Username.isBlank() || Password.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại: Thiểu dữ liệu!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else if (accountBUS.hasAccount(AccountID)) {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại: Mã tài khoản đã tồn tại!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else if (accountBUS.hasAccountUsername(Username)) {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại: Tên tài khoản đã tồn tại!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Account_DTO account = new Account_DTO();
                    account.setAccountID(AccountID);
                    account.setAccessID(AccessID);
                    account.setUsername(Username);
                    account.setPassword(Password);
                    if (accountBUS.addAccount(account)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        updateTable(tableModel, accountBUS.getAllAccounts());
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                searchInput.setText("");
            }
        });

        delBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc không?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                String AccountID = idInput.getText().trim();
                if (AccountID.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Xoá thất bại: Thiếu Mã tài khoản!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (accountBUS.deleteAccount(AccountID)) {
                        JOptionPane.showMessageDialog(null, "Xoá thành công!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        updateTable(tableModel, accountBUS.getAllAccounts());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xoá thất bại!",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                searchInput.setText("");
                }
            }
        });

//      --------------CONFIG---------------
//        frame.setMinimumSize(new Dimension(1000, 600));
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    public void updateTable(DefaultTableModel tableModel, ArrayList<Account_DTO> AccountList) {
        tableModel.setRowCount(0);
        for (Account_DTO account : AccountList) {
            String AccountID = account.getAccountID();
            String Username = account.getUsername();
            String Password = account.getPassword();
            String AccessID = account.getAccessID();
            String Authority = accessBUS.getAccessFromID(AccessID).getAuthority();
            Object[] row = {AccountID, Username, Password, Authority};
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
    }

    public JPanel QLTK() {
        return container;
    }



}
