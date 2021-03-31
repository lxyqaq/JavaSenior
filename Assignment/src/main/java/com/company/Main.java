package com.company;

import com.alee.laf.WebLookAndFeel;
import com.company.Bean.Student;
import com.company.Bean.Teacher;
import com.company.Bean.User;
import com.company.DB.DB;
import com.company.Dao.StudentDao;
import com.company.impl.StudentImpl;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Main extends JFrame {
    private JPanel p;
    private JLabel lblName, lblPwd, lblRole;
    private JTextField txtName;
    private JPasswordField txtPwd;
    private JButton btnOk, btnCancle;
    String[] s = {"学生", "教师", "管理员"};
    private JComboBox comboBox;
    private StudentDao studentDao = new StudentImpl();
    private JLabel label;

    public Main() {
        super("用户登录界面");
        p = new JPanel();
        p.setBackground(Color.PINK);
        p.setLayout(null);
        lblRole = new JLabel("角色：");
        lblRole.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox = new JComboBox(s);
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"学生", "教师", "管理员"}));
        lblName = new JLabel("账户：");
        lblName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblPwd = new JLabel("密码：");
        lblPwd.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        txtName = new JTextField(20);
        txtPwd = new JPasswordField(20);
        btnOk = new JButton("登录");
        btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        btnCancle = new JButton("取消");
        btnCancle.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        //注册确定按钮的事件处理
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strName = txtName.getText();
                String strPwd = new String(txtPwd.getPassword());
                System.out.println("用户名:" + strName + "\n密码:" + strPwd);

            }
        });
        //注册取消按钮的事件处理
        btnCancle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //清空
                txtName.setText("");
                txtPwd.setText("");

            }
        });
        lblRole.setBounds(90, 86, 60, 25);
        comboBox.setBounds(130, 86, 140, 25);
        lblName.setBounds(90, 121, 60, 25);
        txtName.setBounds(130, 121, 140, 25);
        lblPwd.setBounds(90, 156, 60, 25);
        txtPwd.setBounds(130, 156, 140, 25);
        btnOk.setBounds(130, 191, 60, 25);
        btnCancle.setBounds(210, 191, 60, 25);
        p.add(lblRole);
        p.add(comboBox);
        p.add(lblName);
        p.add(txtName);
        p.add(lblPwd);
        p.add(txtPwd);
        p.add(btnOk);
        p.add(btnCancle);
        getContentPane().add(p);

        label = new JLabel("实验室预约管理");
        label.setFont(new Font("方正舒体", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(114, 28, 154, 25);
        p.add(label);
        this.setSize(400, 300);
        this.setLocation(300, 300);
        //设置窗体不可改变大小
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = txtName.getText().trim();
                String password = txtPwd.getText().trim();
                String role = null;
                if (comboBox.getSelectedItem().equals("管理员")) {
                    role = "admin";
                } else if (comboBox.getSelectedItem().equals("学生")) {
                    role = "student";
                } else {
                    role = "teacher";
                }
                if (role.equals("admin")) {
                    User user = new User(account, password);
                    if (DB.Login(user)) {
                        JOptionPane.showMessageDialog(null, "登录成功", "", JOptionPane.PLAIN_MESSAGE);
                        AdminMainView adminMain = new AdminMainView();
                        adminMain.setVisible(true);
                        Main.this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "账号密码错误", "", JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (role.equals("student")) {
                    Student student = new Student(account, password);
                    if (DB.studentLogin(student)) {
                        //查询学生信息
                        student = studentDao.selectStudent(student.getStudentAccount());
                        StudentMainView studentMain = new StudentMainView(student);
                        studentMain.setVisible(true);
                        Main.this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "登录成功", "", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "账号密码错误", "", JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (role.equals("teacher")) {
                    Teacher teacher = new Teacher(account, password);
                    if (DB.teacherLogin(teacher)) {
                        TeacherMainView teacherMain = new TeacherMainView();
                        teacherMain.setVisible(true);
                        Main.this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "登录成功", "", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "账号密码错误", "", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        // write your code here
        Font font = new Font("微软雅黑", Font.PLAIN, 14);
        UIManager.put("Table.font", font);
        WebLookAndFeel.globalControlFont = new FontUIResource(font);
        WebLookAndFeel.install();
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
            Main main = new Main();
            main.setVisible(true);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
