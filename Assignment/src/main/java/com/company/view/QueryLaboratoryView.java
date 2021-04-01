package com.company.view;

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

import com.company.pojo.Iterator;
import com.company.pojo.Laboratory;
import com.company.pojo.LaboratoryContainer;
import com.company.dao.LaboratoryDao;
import com.company.dao.ReserveDao;
import com.company.impl.LaboratoryImpl;
import com.company.impl.ReserveImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class QueryLaboratoryView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTable table;
    private JTextField labname;
    private JTextField freeTime;
    private JTextField function;
    private JComboBox states;
    private DefaultTableModel dm;
    private LaboratoryDao laboratoryDao = new LaboratoryImpl();
    private ReserveDao reserveDao = new ReserveImpl();
    private String id, laboratoryNameText, freeTimeText, functionText, statesText;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueryLaboratoryView frame = new QueryLaboratoryView();
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
    public QueryLaboratoryView() {
        setTitle("Laboratory Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("Laboratory Information");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(150, 22, 300, 35);
        contentPane.add(label);

        JLabel label_1 = new JLabel("Laboratory:");
        label_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        label_1.setBounds(121, 70, 110, 15);
        contentPane.add(label_1);

        keyword = new JTextField();
        keyword.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        keyword.setBounds(216, 67, 155, 21);
        contentPane.add(keyword);
        keyword.setColumns(10);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keyword.getText();
                fillTable(text);
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
                laboratoryNameText = (String) dm.getValueAt(row, 1);
                freeTimeText = (String) dm.getValueAt(row, 2);
                functionText = (String) dm.getValueAt(row, 3);
                statesText = (String) dm.getValueAt(row, 4);
                labname.setText(laboratoryNameText);
                freeTime.setText(freeTimeText);
                function.setText(functionText);
                states.setSelectedItem(statesText);
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

        JLabel label_2 = new JLabel("Laboratory: ");
        label_2.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        label_2.setBounds(10, 254, 72, 15);
        contentPane.add(label_2);

        labname = new JTextField();
        labname.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        labname.setBounds(71, 251, 82, 21);
        contentPane.add(labname);
        labname.setColumns(10);

        JLabel lblNewLabel = new JLabel("Time: ");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        lblNewLabel.setBounds(161, 254, 73, 15);
        contentPane.add(lblNewLabel);

        freeTime = new JTextField();
        freeTime.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        freeTime.setBounds(195, 251, 82, 21);
        contentPane.add(freeTime);
        freeTime.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Function: ");
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(280, 254, 60, 15);
        contentPane.add(lblNewLabel_1);

        function = new JTextField();
        function.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        function.setBounds(335, 251, 83, 21);
        contentPane.add(function);
        function.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("State: ");
        lblNewLabel_2.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(419, 254, 62, 15);
        contentPane.add(lblNewLabel_2);

        states = new JComboBox();
        states.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        states.setModel(new DefaultComboBoxModel(new String[]{"Vacant", "Occupied"}));
        states.setBounds(450, 251, 110, 24);
        contentPane.add(states);

        JButton btnNewButton_1 = new JButton("Update");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                laboratoryNameText = labname.getText();
                freeTimeText = freeTime.getText();
                functionText = function.getText();
                statesText = states.getSelectedItem().toString();
                if (labname.getText() == null || "".equals(labname)) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {

                    Laboratory laboratory = new Laboratory(Integer.valueOf(id), laboratoryNameText, freeTimeText, functionText, statesText);
                    int i = laboratoryDao.UpdateLaboratory(laboratory);
                    if (i == 1) {
                        fillTable(null);
                        JOptionPane.showMessageDialog(null, "Success!");
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        btnNewButton_1.setBounds(98, 299, 93, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Delete");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (laboratoryNameText == null || "".equals(laboratoryNameText)) {
                    JOptionPane.showMessageDialog(null, "Please select the line!");
                } else {
                    int i = laboratoryDao.DeleteLaboratory(id);
                    int k = reserveDao.DeleteReserveByLa(laboratoryNameText);
                    fillTable(null);
                    JOptionPane.showMessageDialog(null, "Success!");
                }
            }
        });
        btnNewButton_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        btnNewButton_2.setBounds(244, 299, 93, 23);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Previous");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        btnNewButton_3.setBounds(392, 299, 93, 23);
        contentPane.add(btnNewButton_3);

    }

    public void fillTable(String labname) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Laboratory> laboratorys = laboratoryDao.QueryLaboratory(labname);
        LaboratoryContainer laboratoryContainer = new LaboratoryContainer();
        laboratoryContainer.list = laboratorys;
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
