package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.company.Bean.Teacher;
import com.company.Dao.TeacherDao;
import com.company.impl.TeacherImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AddTeacherView extends JFrame {

    private JPanel contentPane;
    private JTextField account;
    private JTextField teaname;
    private JPasswordField password;
    private JComboBox sex;
    private TeacherDao teacherDao = new TeacherImpl();
    private JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddTeacherView frame = new AddTeacherView();
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
    public AddTeacherView() {
        setTitle("Add Teacher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username: ");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel.setBounds(107, 86, 71, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password: ");
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(107, 132, 71, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Name: ");
        lblNewLabel_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(107, 182, 71, 15);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Gender: ");
        lblNewLabel_3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(107, 236, 71, 15);
        contentPane.add(lblNewLabel_3);

        account = new JTextField();
        account.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        account.setBounds(177, 83, 131, 21);
        contentPane.add(account);
        account.setColumns(10);

        teaname = new JTextField();
        teaname.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        teaname.setBounds(177, 179, 131, 21);
        contentPane.add(teaname);
        teaname.setColumns(10);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String teacherAccount = account.getText().trim();
                String teacherPassword = password.getText().trim();
                String teacherName = teaname.getText().trim();
                String teacherSex = sex.getSelectedItem().toString();
                if (teacherAccount == null || "".equals(teacherAccount)) {
                    JOptionPane.showMessageDialog(null, "Please enter the teacher ID!");
                    return;
                }
                if (teacherPassword == null || "".equals(teacherPassword)) {
                    JOptionPane.showMessageDialog(null, "Please enter the teacher password!");
                    return;
                }
                if (teacherName == null || "".equals(teacherName)) {
                    JOptionPane.showMessageDialog(null, "Please enter the teacher name!");
                    return;
                }
                if (teacherSex == null || "".equals(teacherSex)) {
                    JOptionPane.showMessageDialog(null, "Please select gender!");
                    return;
                }
                Teacher teacher = new Teacher(teacherAccount, teacherPassword, teacherName, teacherSex);
                int i = teacherDao.addTeacher(teacher);
                if (i == 1) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Success");
                } else {
                    JOptionPane.showMessageDialog(null, "Fail");
                }
            }
        });
        btnNewButton.setBounds(177, 273, 93, 23);
        contentPane.add(btnNewButton);

        password = new JPasswordField();
        password.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        password.setBounds(177, 129, 131, 21);
        contentPane.add(password);

        sex = new JComboBox();
        sex.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        sex.setModel(new DefaultComboBoxModel(new String[]{"Male", "Female"}));
        sex.setBounds(177, 229, 131, 30);
        contentPane.add(sex);

        label = new JLabel("Add Teacher");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(133, 24, 181, 37);
        contentPane.add(label);
    }
}
