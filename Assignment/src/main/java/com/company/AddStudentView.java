package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.company.Bean.Student;
import com.company.Dao.StudentDao;
import com.company.impl.StudentImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AddStudentView extends JFrame {

    private JPanel contentPane;
    private JTextField accountText;
    private JTextField nameText;
    private JPasswordField pwdText;
    private JComboBox sex;
    private StudentDao studentDao = new StudentImpl();
    private JLabel lblNewLabel_4;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddStudentView frame = new AddStudentView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AddStudentView() {
        setTitle("\u6DFB\u52A0\u5B66\u751F");
        //隐藏当前窗口，并释放窗体占有的其他资源。在窗口被关闭的时候会dispose这个窗口。
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("学生账号：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setBounds(107, 96, 71, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("学生密码：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(107, 143, 71, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("学生姓名：");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(107, 191, 71, 15);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("学生性别：");
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_3.setBounds(107, 234, 71, 15);
        contentPane.add(lblNewLabel_3);

        accountText = new JTextField();
        accountText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        accountText.setBounds(177, 93, 131, 21);
        contentPane.add(accountText);
        accountText.setColumns(10);

        nameText = new JTextField();
        nameText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        nameText.setBounds(177, 188, 131, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentAccount = accountText.getText().trim();
                String studentPassword = pwdText.getText().trim();
                String studentName = nameText.getText().trim();
                String studentSex = sex.getSelectedItem().toString();
                if (studentAccount == null || "".equals(studentAccount)) {
                    JOptionPane.showMessageDialog(null, "请输入学生账号！");
                    return;
                }
                if (studentPassword == null || "".equals(studentPassword)) {
                    JOptionPane.showMessageDialog(null, "请输入密码！");
                    return;
                }
                if (studentName == null || "".equals(studentName)) {
                    JOptionPane.showMessageDialog(null, "请输入学生姓名！");
                    return;
                }
                if (studentSex == null || "".equals(studentSex)) {
                    JOptionPane.showMessageDialog(null, "请选择性别！");
                    return;
                }
                Student student = new Student(studentAccount, studentPassword, studentName, studentSex);
                int i = studentDao.addStudent(student);
                if (i == 1) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败");
                }
            }
        });
        btnNewButton.setBounds(177, 278, 93, 23);
        contentPane.add(btnNewButton);

        pwdText = new JPasswordField();
        pwdText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        pwdText.setBounds(177, 140, 131, 21);
        contentPane.add(pwdText);

        sex = new JComboBox();
        sex.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        sex.setModel(new DefaultComboBoxModel(new String[]{"男", "女"}));
        sex.setBounds(177, 231, 131, 21);
        contentPane.add(sex);

        lblNewLabel_4 = new JLabel("添加学生");
        lblNewLabel_4.setFont(new Font("方正舒体", Font.PLAIN, 20));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(124, 31, 184, 35);
        contentPane.add(lblNewLabel_4);
    }
}
