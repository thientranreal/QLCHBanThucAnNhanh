package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import BUS.product_BUS;

public class Dashboard_GUI {


    private JButton btnAdminAccount;
    private JButton btnAdminProduct;
    private JButton btnAdminOrder;
    private JButton btnAdminState;
    private JPanel pnMenu;
    private JPanel pnShow;
    private JPanel container;
    private JLabel lbTitle;
    private JPanel pnTitle;
    private JPanel pnItem;
    private JPanel pnProduct;
    private JPanel pnOrder;
    private JPanel pnEmp;
    private JPanel pnCus;
    private JPanel pnAcc;
    private JPanel pnState;
    private JPanel pnLogout;
    private JPanel pnUser;
    private JLabel lbUser;
    private JLabel lbProduct;
    private JLabel lbOrder;
    private JLabel lbEmp;
    private JLabel lbCus;
    private JLabel lbAcc;
    private JLabel lbState;
    private JLabel lbLogOut;
    private JPanel pn;
    JFrame frame;
    Color DefaultColor;
    Color ClickedColor;

    CardLayout cardLayout;

    public Dashboard_GUI(String username, String Access,JFrame frameParent){
        frameParent.setVisible(false);

        lbUser.setText(String.format("Xin chào, %s !!!",username));
        if(!Access.equals("Admin")){
            pnEmp.setVisible(false);
            pnAcc.setVisible(false);
        }



        frame = new JFrame();
        DefaultColor = new Color(55,55,55);
        ClickedColor = new Color(247,0,5);


        product_GUI productGui = new product_GUI(frame);
        QuanLiTaiKhoan_GUI quanLiTaiKhoanGui = new QuanLiTaiKhoan_GUI(frame);
        HoaDon_GUI hoaDonGui= new HoaDon_GUI(frame, username, Access);
        Employee_GUI employeeGui = new Employee_GUI(frame);
        ThongKe_GUI thongKeGui = new ThongKe_GUI(frame);
        product_BUS productBus = new product_BUS();
        customerGUI customerGUI = new customerGUI(frame);


        JPanel listGui[] = {productGui.getPnProduct(),hoaDonGui.getHoaDon_panel(),employeeGui.getPNEmp(),customerGUI.getPNCustomer(),quanLiTaiKhoanGui.QLTK(),thongKeGui.getThongKe_pnl()};


        for(int i=0; i<listGui.length; i++){
            pnShow.add(listGui[i],"pn"+(i+1));
        }

        cardLayout = (CardLayout)(pnShow.getLayout());
        ImageIcon imageIconTitle = new ImageIcon(new ImageIcon("src/img/icon-brand.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
//        ImageIcon imageIconTitle = new ImageIcon(new ImageIcon(getClass().getResource("./img/icon-brand.png")));
//        ImageIcon imageIconTitle = new ImageIcon(new ImageIcon("./img/icon-brand.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));

        lbTitle.setIcon(imageIconTitle);
        ImageIcon imageIconOrder = new ImageIcon(new ImageIcon("src/img/icon-order.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbOrder.setIcon(imageIconOrder);
        ImageIcon imageIconProduct = new ImageIcon(new ImageIcon("src/img/icon-product.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbProduct.setIcon(imageIconProduct);
        ImageIcon imageIconEmp = new ImageIcon(new ImageIcon("src/img/icon-employee.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbEmp.setIcon(imageIconEmp);
        ImageIcon imageIconCus = new ImageIcon(new ImageIcon("src/img/icon-customer.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbCus.setIcon(imageIconCus);
        ImageIcon imageIconAcc = new ImageIcon(new ImageIcon("src/img/icon-account.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbAcc.setIcon(imageIconAcc);
        ImageIcon imageIconState = new ImageIcon(new ImageIcon("src/img/icon-state.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbState.setIcon(imageIconState);
        ImageIcon imageIconLogOut = new ImageIcon(new ImageIcon("src/img/icon-logout.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        lbLogOut.setIcon(imageIconLogOut);






        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        pnProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(ClickedColor);
                pnOrder.setBackground(DefaultColor);
                pnEmp.setBackground(DefaultColor);
                pnCus.setBackground(DefaultColor);
                pnAcc.setBackground(DefaultColor);
                pnState.setBackground(DefaultColor);

                //show pn
                cardLayout.show(pnShow,"pn1");
                productBus.renderDataTable(productGui.getTableSP(),productGui.columns,productBus.getAllProduct());

            }
        });

        pnOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(DefaultColor);
                pnOrder.setBackground(ClickedColor);
                pnEmp.setBackground(DefaultColor);
                pnCus.setBackground(DefaultColor);
                pnAcc.setBackground(DefaultColor);
                pnState.setBackground(DefaultColor);

                if (Access.equals("Admin")) {
                    hoaDonGui.renderEmResultList();
                }
                hoaDonGui.renderCusResultList();
                //show pn
                cardLayout.show(pnShow,"pn2");
            }
        });

        pnEmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(DefaultColor);
                pnOrder.setBackground(DefaultColor);
                pnEmp.setBackground(ClickedColor);
                pnCus.setBackground(DefaultColor);
                pnAcc.setBackground(DefaultColor);
                pnState.setBackground(DefaultColor);
                cardLayout.show(pnShow,"pn3");
            }
        });


        pnCus.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(DefaultColor);
                pnOrder.setBackground(DefaultColor);
                pnEmp.setBackground(DefaultColor);
                pnCus.setBackground(ClickedColor);
                pnAcc.setBackground(DefaultColor);
                pnState.setBackground(DefaultColor);
                //show pn
                cardLayout.show(pnShow,"pn4");

            }
        });



        pnAcc.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(DefaultColor);
                pnOrder.setBackground(DefaultColor);
                pnEmp.setBackground(DefaultColor);
                pnCus.setBackground(DefaultColor);
                pnAcc.setBackground(ClickedColor);
                pnState.setBackground(DefaultColor);

                //show pn
                cardLayout.show(pnShow,"pn5");
            }
        });



        pnState.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pnProduct.setBackground(DefaultColor);
                pnOrder.setBackground(DefaultColor);
                pnEmp.setBackground(DefaultColor);
                pnCus.setBackground(DefaultColor);
                pnAcc.setBackground(DefaultColor);
                pnState.setBackground(ClickedColor);


                //show pn
                cardLayout.show(pnShow,"pn6");
            }

        });


        pnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frameParent.setVisible(true);
                frame.setVisible(false);
            }
        });
    }




//    public static void main(String[] args) {
//
//        new Main();
//
//    }






}
