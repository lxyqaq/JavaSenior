package com.company.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.company.Main;
import com.company.pojo.*;
import com.company.dao.LaboratoryDao;
import com.company.dao.ReserveDao;
import com.company.impl.LaboratoryImpl;
import com.company.impl.ReserveImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMainView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTextField labname;
    private JTextField stuname;
    private JTable table;
    DefaultTableModel dm;
    private LaboratoryDao laboratoryDao = new LaboratoryImpl();
    private ReserveDao reserveDao = new ReserveImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Student student = new Student();
                    StudentMainView frame = new StudentMainView(student);
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
    public StudentMainView(Student student) {
        setTitle("Student Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 552, 383);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laboratory Search");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lblNewLabel.setBounds(190, 10, 200, 29);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Laboratory:");
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(112, 63, 72, 15);
        contentPane.add(lblNewLabel_1);

        keyword = new JTextField();
        keyword.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        keyword.setBounds(203, 60, 107, 21);
        contentPane.add(keyword);
        keyword.setColumns(10);

        JButton searchbtn = new JButton("Search");
        searchbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keyword.getText();
                fillTable(text);
            }
        });
        searchbtn.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        searchbtn.setBounds(320, 59, 72, 23);
        contentPane.add(searchbtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 103, 516, 95);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        table.setBackground(Color.pink);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                String value = (String) dm.getValueAt(row, 1);
                labname.setText(value);
            }
        });
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Laboratory", "Time", "Function", "State"}) {
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

        JLabel lblNewLabel_2 = new JLabel("Laboratory:");
        lblNewLabel_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(52, 211, 81, 15);
        contentPane.add(lblNewLabel_2);

        labname = new JTextField();
        labname.setEditable(false);
        labname.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        labname.setBounds(143, 208, 107, 21);
        contentPane.add(labname);
        labname.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Student Name：");
        lblNewLabel_3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(288, 211, 100, 15);
        contentPane.add(lblNewLabel_3);

        stuname = new JTextField();
        stuname.setText(student.getStudentName());
        stuname.setEditable(false);
        stuname.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        stuname.setBounds(390, 208, 109, 21);
        contentPane.add(stuname);
        stuname.setColumns(10);

        JButton appoint = new JButton("Reserve");
        appoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (labname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {
                    int row = table.getSelectedRow();
                    String status = (String) dm.getValueAt(row, 4);
                    if (!status.equals("Vacant")) {
                        JOptionPane.showMessageDialog(null, "The laboratory is already occupied!");
                    } else {
                        Reserve reserve = new Reserve(labname.getText().trim(), stuname.getText().trim());
                        int i = reserveDao.addReserve(reserve);
                        if (i == 1) {
                            fillTable(null);
                            JOptionPane.showMessageDialog(null, "Successful application! Pending for approval");
                        }
                    }
                }
            }
        });
        appoint.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        appoint.setBounds(97, 276, 93, 23);
        contentPane.add(appoint);

        JButton btnNewButton_3 = new JButton("Previous");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        });
        btnNewButton_3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        btnNewButton_3.setBounds(347, 276, 93, 23);
        contentPane.add(btnNewButton_3);
    }


    public void fillTable(String labName) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Laboratory> laboratories = laboratoryDao.QueryLaboratory(labName);
        LaboratoryContainer laboratoryContainer = new LaboratoryContainer();
        laboratoryContainer.list = laboratories;
        Iterator iterator = laboratoryContainer.getIterator();
        while (iterator.hasNext()) {
            Laboratory lab = (Laboratory) iterator.next();
            Vector<Object> v = new Vector<>();
            v.add(lab.getID());
            v.add(lab.getLaboratoryName());
            v.add(lab.getFreeTime());
            v.add(lab.getFunction());
            v.add(lab.getStates());
            dm.addRow(v);
        }
    }

}

