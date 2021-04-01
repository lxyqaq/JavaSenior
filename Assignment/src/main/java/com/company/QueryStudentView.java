package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.company.Bean.Iterator;
import com.company.Bean.Laboratory;
import com.company.Bean.Student;
import com.company.Bean.StudentContainer;
import com.company.Dao.ReserveDao;
import com.company.Dao.StudentDao;
import com.company.impl.ReserveImpl;
import com.company.impl.StudentImpl;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryStudentView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTable table;
    private JTextField account;
    private JTextField stuname;
    private JComboBox sex;
    private DefaultTableModel dm;
    private StudentDao studentDao = new StudentImpl();
    private ReserveDao reserveDao = new ReserveImpl();
    private String id, studentAccount, studentName, studentSex;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueryStudentView frame = new QueryStudentView();
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
    public QueryStudentView() {
        setTitle("Student Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("Student Information");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(216, 22, 165, 35);
        contentPane.add(label);

        JLabel label_1 = new JLabel("Name: ");
        label_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_1.setBounds(134, 70, 77, 15);
        contentPane.add(label_1);

        keyword = new JTextField();
        keyword.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        keyword.setBounds(216, 67, 155, 21);
        contentPane.add(keyword);
        keyword.setColumns(10);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillTable(keyword.getText());
            }
        });
        search.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        search.setBounds(392, 66, 93, 23);
        contentPane.add(search);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 98, 564, 130);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                id = dm.getValueAt(row, 0).toString();
                studentAccount = (String) dm.getValueAt(row, 1);
                studentName = (String) dm.getValueAt(row, 3);
                studentSex = (String) dm.getValueAt(row, 4);
                account.setText(studentAccount);
                stuname.setText(studentName);
                sex.setSelectedItem(studentSex);
            }
        });
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Username", "Password", "Name", "Gender"}) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        fillTable(null);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setBackground(Color.pink);
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        scrollPane.setViewportView(table);

        JLabel label_2 = new JLabel("Username: ");
        label_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_2.setBounds(20, 254, 77, 15);
        contentPane.add(label_2);

        account = new JTextField();
        account.setEditable(false);
        account.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        account.setBounds(92, 251, 85, 21);
        contentPane.add(account);
        account.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name: ");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel.setBounds(209, 254, 76, 15);
        contentPane.add(lblNewLabel);

        stuname = new JTextField();
        stuname.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        stuname.setBounds(275, 251, 85, 21);
        contentPane.add(stuname);
        stuname.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Gender: ");
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(398, 253, 82, 15);
        contentPane.add(lblNewLabel_1);

        sex = new JComboBox();
        sex.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        sex.setModel(new DefaultComboBoxModel(new String[]{"Male", "Female"}));
        sex.setBounds(467, 251, 92, 24);
        contentPane.add(sex);

        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                studentAccount = account.getText();
                studentName = stuname.getText();
                studentSex = sex.getSelectedItem().toString();
                if (studentAccount == null || "".equals(studentAccount)) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {
                    Student stu = new Student(Integer.valueOf(id), studentAccount, studentName, studentSex);
                    int i = studentDao.UpdateStudent(stu);
                    if (i == 1) {
                        fillTable(null);
                        JOptionPane.showMessageDialog(null, "Success");
                    }
                }
            }
        });
        update.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        update.setBounds(98, 299, 93, 23);
        contentPane.add(update);

        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (studentAccount == null || "".equals(studentAccount)) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {
                    int i = studentDao.DeleteStudent(id);
                    int k = reserveDao.DeleteReserveByStudent(studentName);
                    fillTable(null);
                    JOptionPane.showMessageDialog(null, "Success");
                }
            }
        });
        delete.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        delete.setBounds(244, 299, 93, 23);
        contentPane.add(delete);

        JButton back = new JButton("Previous");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        back.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        back.setBounds(392, 299, 93, 23);
        contentPane.add(back);

    }

    public void fillTable(String stuname) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Student> students = studentDao.QueryStudent(stuname);
        StudentContainer studentContainer = new StudentContainer();
        studentContainer.list = students;
        Iterator iterator = studentContainer.getIterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            Vector<Object> v = new Vector<>();
            v.add(student.getID());
            v.add(student.getStudentAccount());
            v.add(student.getPassword());
            v.add(student.getStudentName());
            v.add(student.getStudentSex());
            dm.addRow(v);
        }
    }

}
