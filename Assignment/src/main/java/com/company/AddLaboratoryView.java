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
        setTitle("添加实验室");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("实验室名称：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setBounds(107, 103, 93, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("空闲时间：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(118, 156, 71, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("实验室功能：");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(107, 206, 93, 15);
        contentPane.add(lblNewLabel_2);

        labname = new JTextField();
        labname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        labname.setBounds(188, 100, 131, 21);
        contentPane.add(labname);
        labname.setColumns(10);

        function = new JTextField();
        function.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        function.setBounds(188, 203, 131, 21);
        contentPane.add(function);
        function.setColumns(10);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String labnameText = labname.getText().trim();
                String freeTimeText = freeTime.getText().trim();
                String functionText = function.getText().trim();
                if (labnameText == null || "".equals(labnameText)) {
                    JOptionPane.showMessageDialog(null, "请输入实验室名称！");
                    return;
                }
                if (freeTimeText == null || "".equals(freeTimeText)) {
                    JOptionPane.showMessageDialog(null, "请输入空闲时间！");
                    return;
                }
                if (functionText == null || "".equals(functionText)) {
                    JOptionPane.showMessageDialog(null, "请输入实验室功能！");
                    return;
                }
                Laboratory laboratory = new Laboratory(labnameText, freeTimeText, functionText);
                int i = laboratoryDao.addLaboratoryDao(laboratory);
                if (i == 1) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败");
                }
            }
        });
        btnNewButton.setBounds(188, 252, 93, 23);
        contentPane.add(btnNewButton);

        freeTime = new JTextField();
        freeTime.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        freeTime.setBounds(188, 153, 131, 21);
        contentPane.add(freeTime);
        freeTime.setColumns(10);

        JLabel label = new JLabel("添加实验室");
        label.setFont(new Font("方正舒体", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(146, 30, 151, 32);
        contentPane.add(label);
    }
}
