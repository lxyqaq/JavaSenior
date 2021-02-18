package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 8486147879661945934L;

    public LoginFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        setTitle("Store Management System");
        setSize(300, 220);
        setContentPane(createContentPane());
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    private JPanel createContentPane() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(BorderLayout.NORTH, new JLabel("Store Management System", JLabel.CENTER));
        p.add(BorderLayout.CENTER, createCenterPane());
        p.add(BorderLayout.SOUTH, createBtnPane());
        p.setBorder(new EmptyBorder(6, 45, 6, 45));
        return p;
    }

    private JPanel createCenterPane() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(BorderLayout.NORTH, createIdPwdPane());
        p.setBorder(new EmptyBorder(6, 6, 6, 6));
        return p;
    }

    private JPanel createIdPwdPane() {
        JPanel p = new JPanel(new GridLayout(3, 1, 0, 6));
        p.add(createIdPane());
        p.add(createPwdPane());
        return p;
    }

    private JPanel createIdPane() {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        return p;
    }

    private JPanel createPwdPane() {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        p.add(BorderLayout.WEST, new JLabel("Password:"));
        pwdField = new JPasswordField(20);
        pwdField.enableInputMethods(true);
        p.add(BorderLayout.CENTER, pwdField);
        return p;
    }

    private JPanel createBtnPane() {
        JPanel p = new JPanel(new FlowLayout());
        JButton login = new JButton("Sign in");
        JButton cancel = new JButton("Sign out");
        p.add(login);
        p.add(cancel);

        getRootPane().setDefaultButton(login);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientContext.login();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFrame.this.clientContext.exit(LoginFrame.this);
            }
        });

        return p;
    }

    private ClientContext clientContext;

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    private JPasswordField pwdField;
    private JComboBox userType;

    public String getUserPwd() {
        char[] pwd = pwdField.getPassword();
        return new String(pwd);
    }

    public int getUserType() {
        return userType.getSelectedIndex();
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }

}
