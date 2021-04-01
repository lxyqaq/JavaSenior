package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.company.Bean.Iterator;
import com.company.Bean.Laboratory;
import com.company.Bean.Reserve;
import com.company.Bean.ReserveContainer;
import com.company.Dao.LaboratoryDao;
import com.company.Dao.ReserveDao;
import com.company.impl.LaboratoryImpl;
import com.company.impl.ReserveImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherMainView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTextField labtext;
    private JTextField stutext;
    private JComboBox statusComBox;
    private JTable table;
    DefaultTableModel dm;
    private ReserveDao reserveDao = new ReserveImpl();
    private LaboratoryDao laboratoryDao = new LaboratoryImpl();
    private String ID = null, laboratoryName = null, StudentName = null, States = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeacherMainView frame = new TeacherMainView();
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
    public TeacherMainView() {
        setTitle("Teacher Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 552, 383);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laboratory Appointment Approval");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lblNewLabel.setBounds(150, 10, 300, 29);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("Laboratory:");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label.setBounds(99, 66, 72, 15);
        contentPane.add(label);

        keyword = new JTextField();
        keyword.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        keyword.setColumns(10);
        keyword.setBounds(203, 63, 107, 21);
        contentPane.add(keyword);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keyword.getText();
                fillTable(text);
            }
        });
        search.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        search.setBounds(355, 62, 72, 23);
        contentPane.add(search);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 108, 526, 115);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                ID = dm.getValueAt(row, 0) + "";
                laboratoryName = (String) dm.getValueAt(row, 1);
                StudentName = (String) dm.getValueAt(row, 2);
                States = (String) dm.getValueAt(row, 3);
                labtext.setText(laboratoryName);
                stutext.setText(StudentName);
            }
        });
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Laboratory", "Applicant", "State", "Result"}) {
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

        JLabel label_1 = new JLabel("Laboratory:");
        label_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_1.setBounds(10, 261, 72, 15);
        contentPane.add(label_1);

        labtext = new JTextField();
        labtext.setEditable(false);
        labtext.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        labtext.setColumns(10);
        labtext.setBounds(84, 258, 90, 21);
        contentPane.add(labtext);

        JLabel label_2 = new JLabel("Applicant:");
        label_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_2.setBounds(191, 261, 72, 15);
        contentPane.add(label_2);

        stutext = new JTextField();
        stutext.setEditable(false);
        stutext.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        stutext.setColumns(10);
        stutext.setBounds(252, 258, 90, 21);
        contentPane.add(stutext);

        JLabel label_3 = new JLabel("Result:");
        label_3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_3.setBounds(362, 261, 42, 15);
        contentPane.add(label_3);

        statusComBox = new JComboBox();
        statusComBox.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        statusComBox.setModel(new DefaultComboBoxModel(new String[]{"Agree", "Refuse"}));
        statusComBox.setBounds(399, 258, 90, 24);
        contentPane.add(statusComBox);

        JButton audit = new JButton("Confirm");
        audit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (ID == null) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {
                    if (States.equals("Wait")) {
                        while (table.getRowCount() > 0) {
                            dm.removeRow(0);
                        }
                        if (statusComBox.getSelectedItem().toString().equals("Agree")) {
                            int k = laboratoryDao.UpdateLaboratoryStates(laboratoryName);
                        }
                        int i = reserveDao.UpdateReserve(ID, statusComBox.getSelectedItem().toString());
                        if (i == 1) {
                            JOptionPane.showMessageDialog(null, "Success!");
                            fillTable(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This message has been approved and cannot be changed!");
                    }
                }
            }
        });
        audit.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        audit.setBounds(84, 311, 93, 23);
        contentPane.add(audit);

        JButton back = new JButton("Previous");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        });

        back.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        back.setBounds(396, 311, 93, 23);
        contentPane.add(back);
    }


    public void fillTable(String labName) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Reserve> reserves = reserveDao.QueryReserve(labName);
        ReserveContainer reserveContainer = new ReserveContainer();
        reserveContainer.list = reserves;
        Iterator iterator = reserveContainer.getIterator();
        while (iterator.hasNext()) {
            Reserve reserve = (Reserve) iterator.next();
            Vector<Object> v = new Vector<>();
            v.add(reserve.getID());
            v.add(reserve.getLaboratoryName());
            v.add(reserve.getStudentName());
            v.add(reserve.getStates());
            v.add(reserve.getResult());
            dm.addRow(v);
        }
    }
}
