package com.company;

import com.alee.laf.WebLookAndFeel;
import com.company.pojo.*;
import com.company.dao.StudentDao;
import com.company.impl.StudentImpl;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private JPanel p;
    private JLabel lblName, lblPwd, lblRole;
    private JButton btnOk, btnCancle;
    private String[] s = {"Student", "Teacher", "Admin"};
    private StudentDao studentDao = new StudentImpl();
    private JLabel label;
    public static JTextField txtName;
    public static JPasswordField txtPwd;
    public static JComboBox comboBox;

    private Person student = new Student();
    private Person teacher = new Teacher();
    private Person user = new User();

    private List<Person> personList = new ArrayList<>();

    public Main() {
        super("Login system");
        p = new JPanel();
        p.setBackground(Color.PINK);
        p.setLayout(null);
        lblRole = new JLabel("Role: ");
        lblRole.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        comboBox = new JComboBox(s);
        comboBox.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Student", "Teacher", "Admin"}));
        lblName = new JLabel("Username: ");
        lblName.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        lblPwd = new JLabel("Password: ");
        lblPwd.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        txtName = new JTextField(20);
        txtPwd = new JPasswordField(20);
        btnOk = new JButton("Login");
        btnOk.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        btnCancle = new JButton("Cancel");
        btnCancle.setFont(new Font("TimesRoman", Font.PLAIN, 12));

        lblRole.setBounds(90, 86, 60, 25);
        comboBox.setBounds(150, 86, 140, 25);
        lblName.setBounds(90, 121, 60, 25);
        txtName.setBounds(150, 121, 140, 25);
        lblPwd.setBounds(90, 156, 60, 25);
        txtPwd.setBounds(150, 156, 140, 25);
        btnOk.setBounds(150, 191, 60, 25);
        btnCancle.setBounds(230, 191, 60, 25);
        p.add(lblRole);
        p.add(comboBox);
        p.add(lblName);
        p.add(txtName);
        p.add(lblPwd);
        p.add(txtPwd);
        p.add(btnOk);
        p.add(btnCancle);
        getContentPane().add(p);

        label = new JLabel("Laboratory Appointment Management System");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(11, 28, 380, 25);
        p.add(label);
        this.setSize(400, 300);
        this.setLocation(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visitor visitor = new MyVisitor();
                login(visitor);
                Main.this.setVisible(false);
            }
        });

        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtPwd.setText("");
            }
        });

    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void login(Visitor visitor) {

        personList.add(student);
        personList.add(teacher);
        personList.add(user);

        for (Person person : personList) {
            person.accept(visitor);
        }

    }

    public static void main(String[] args) {
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        UIManager.put("Table.font", font);
        WebLookAndFeel.globalControlFont = new FontUIResource(font);
        WebLookAndFeel.install();
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
            Main main = new Main();
            main.setVisible(true);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
