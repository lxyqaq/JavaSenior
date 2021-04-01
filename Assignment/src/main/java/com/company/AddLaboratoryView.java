package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.company.Bean.Laboratory;
import com.company.Dao.LaboratoryDao;
import com.company.impl.LaboratoryImpl;

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

public class AddLaboratoryView extends JFrame {

    private JPanel contentPane;
    private JTextField labname;
    private JTextField function;
    private JTextField freeTime;
    private LaboratoryDao laboratoryDao = new LaboratoryImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddLaboratoryView frame = new AddLaboratoryView();
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
    public AddLaboratoryView() {
        setTitle("Add Laboratory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laboratory: ");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel.setBounds(107, 103, 93, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Time: ");
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(107, 156, 71, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Lab Functions：");
        lblNewLabel_2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(107, 206, 98, 15);
        contentPane.add(lblNewLabel_2);

        labname = new JTextField();
        labname.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        labname.setBounds(195, 100, 131, 21);
        contentPane.add(labname);
        labname.setColumns(10);

        function = new JTextField();
        function.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        function.setBounds(195, 203, 131, 21);
        contentPane.add(function);
        function.setColumns(10);

        JButton btnNewButton = new JButton("ADD");
        btnNewButton.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String labnameText = labname.getText().trim();
                String freeTimeText = freeTime.getText().trim();
                String functionText = function.getText().trim();
                if (labnameText == null || "".equals(labnameText)) {
                    JOptionPane.showMessageDialog(null, "Please enter the laboratory!");
                    return;
                }
                if (freeTimeText == null || "".equals(freeTimeText)) {
                    JOptionPane.showMessageDialog(null, "Please enter the time!");
                    return;
                }
                if (functionText == null || "".equals(functionText)) {
                    JOptionPane.showMessageDialog(null, "Please enter the lab functions!");
                    return;
                }
                Laboratory laboratory = new Laboratory(labnameText, freeTimeText, functionText);
                int i = laboratoryDao.addLaboratoryDao(laboratory);
                if (i == 1) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Success");
                } else {
                    JOptionPane.showMessageDialog(null, "Fail");
                }
            }
        });

        btnNewButton.setBounds(195, 252, 93, 23);
        contentPane.add(btnNewButton);

        freeTime = new JTextField();
        freeTime.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        freeTime.setBounds(195, 153, 131, 21);
        contentPane.add(freeTime);
        freeTime.setColumns(10);

        JLabel label = new JLabel("Add Laboratory");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(146, 30, 151, 32);
        contentPane.add(label);
    }
}
