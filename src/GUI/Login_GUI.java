package GUI;

import BUS.Access_BUS;
import BUS.Account_BUS;
import DTO.Account_DTO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_GUI {
    private JPanel mainPanel;
    private JTextField UserInp;
    private JPasswordField PassInp;
    private JButton LoginBtn;
    private JLabel LoginFailedLb;
    private JCheckBox ShowHidePass;
    private JPanel space1;
    private JPanel space2;
    private JLabel LoginLb;
    private JLabel UsernameLb;
    private JLabel PasswordLb;
    private JPanel space3;

    private final Account_BUS accountBUS = new Account_BUS();
    private final Access_BUS accessBUS = new Access_BUS();

    public Login_GUI() {

//      --------FRAME--------
        JFrame frame = new JFrame();
        frame.setTitle("Đăng nhập");
        frame.add(mainPanel);
        UserInp.setMargin(new Insets(6, 6, 6, 6));
        PassInp.setMargin(new Insets(6, 6, 6, 6));

//      --------FUNTIONS--------
        ShowHidePass.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    PassInp.setEchoChar((char)0);
                    DarkMode();
                } else {
                    PassInp.setEchoChar('*');
                    LightMode();
                }
            }
        });

        LoginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String user = UserInp.getText();
                String pass = PassInp.getText();

                Account_DTO result = accountBUS.checkLogin(user, pass);
                if (result != null) {
                    String power = accessBUS.getAccessFromID(result.getAccessID()).getAuthority();
                    if(power.equals("Admin")) {
                        System.out.println("Quyen Admin");
                    } else {
                        System.out.println("None");
                    }
                } else {
                    LoginFailedLb.setVisible(true);
                }
            }
        });

//      --------CONFIG--------
        LoginBtn.setBorder(new LineBorder(Color.white, 1, false));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void DarkMode() {
        mainPanel.setBackground(Color.darkGray);
        space1.setBackground(Color.darkGray);
        space2.setBackground(Color.darkGray);
        space3.setBackground(Color.darkGray);
        LoginLb.setForeground(Color.white);
        UsernameLb.setForeground(Color.white);
        PasswordLb.setForeground(Color.white);
        ShowHidePass.setBackground(Color.darkGray);
        ShowHidePass.setForeground(Color.white);
    }

    public void LightMode() {
        mainPanel.setBackground(Color.WHITE);
        space1.setBackground(Color.WHITE);
        space2.setBackground(Color.WHITE);
        space3.setBackground(Color.WHITE);
        LoginLb.setForeground(Color.black);
        UsernameLb.setForeground(Color.black);
        PasswordLb.setForeground(Color.black);
        ShowHidePass.setBackground(Color.white);
        ShowHidePass.setForeground(Color.black);
    }

    public JPanel Login() {
        return mainPanel;
    }
}
