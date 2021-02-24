package assign;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @ClassName MainFrame
 * @Description MainFrame
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/22 01:31
 * @Version 1.0
 */
public class MainFrame extends JFrame implements ActionListener {

    private JLabel l0 = new JLabel("Employee");
    private JLabel l1 = new JLabel("ID");
    private JLabel l2 = new JLabel("Name");
    private JLabel l3 = new JLabel("Age");
    private JTextField t1 = new JTextField("", 9);
    private JTextField t2 = new JTextField("", 9);
    private JTextField t3 = new JTextField("", 9);

    private JButton b1 = new JButton("Insert");
    private JButton b2 = new JButton("Search");
    private JButton b3 = new JButton("Close");
    private JButton b4 = new JButton("Display");
    private JButton b5 = new JButton("Update");
    private JButton b6 = new JButton("Delete");
    private JPanel p1 = new JPanel();
    private HashT hashT = new HashT(10);
    private MainFrame mainFrame;

    public MainFrame(String s) {
        super(s);
        InsertInitialItems();
        Container content = getContentPane();
        Font f = new Font("TimesRoman", Font.BOLD, 20);
        p1.setLayout(new GridLayout(4, 2));
        l0.setFont(f);
        l1.setFont(f);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        b1.addActionListener(this);
        b2.addActionListener(this);

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(b4)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(b1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                                .addGap(33)
                                                .addComponent(b2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(b3)
                                                .addGap(47))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(b5, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)))
                                .addComponent(b6, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(25))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(193, Short.MAX_VALUE)
                                .addComponent(p1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(157))
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(257)
                                .addComponent(l0)
                                .addContainerGap(270, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(26)
                                .addComponent(l0)
                                .addGap(18)
                                .addComponent(p1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(b1)
                                        .addComponent(b2)
                                        .addComponent(b6)
                                        .addComponent(b5))
                                .addGap(29)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(b4)
                                        .addComponent(b3))
                                .addGap(30))
        );
        getContentPane().setLayout(groupLayout);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        setSize(580, 370);
        setVisible(true);
    }

    public HashT readTable() {
        return hashT;
    }

    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();

        if (target == b1) {
            if (t1.getText().isBlank() && t2.getText().isBlank() && t3.getText().isBlank()) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter information", "report", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = Integer.parseInt(t1.getText());
                String nm = t2.getText();
                int ag = Integer.parseInt(t3.getText());
                Employee employee = new Employee(id, ag, nm);
                hashT.add(employee);
                JOptionPane.showMessageDialog(mainFrame, "Inserted successfully", "report", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (target == b2) {
            int id = Integer.parseInt(t1.getText());
            Employee temp = hashT.find(id);
            int count = hashT.findStep(id);
            if (temp == null) {
                t2.setText("Unknown");
                t3.setText("");
            } else {
                t1.setText("" + temp.getId());
                t2.setText("" + temp.getName());
                t3.setText("" + temp.getAge());
                JOptionPane.showMessageDialog(mainFrame, "Number of steps for a search: " + count + " steps", "report", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (target == b3) {
            this.setVisible(false);
        }

        if (target == b4) {
            new HashDisplay(this);
        }

        if (target == b5) {
            if (t1.getText().isBlank() || t2.getText().isBlank() || t3.getText().isBlank()) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter information", "report", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = Integer.parseInt(t1.getText());
                String nm = t2.getText();
                int ag = Integer.parseInt(t3.getText());
                hashT.update(id, ag, nm);
                JOptionPane.showMessageDialog(mainFrame, "Update successfully", "report", JOptionPane.INFORMATION_MESSAGE);
                refershTable();
            }
        }

        if (target == b6) {
            int id = Integer.parseInt(t1.getText());
            if (hashT.del(id) == true) {
                JOptionPane.showMessageDialog(mainFrame, "Delete successfully", "report", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "The ID does not exist and cannot be deleted", "report", JOptionPane.ERROR_MESSAGE);
                refershTable();
            }
        }

    }

    public void refershTable() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
    }

    public void InsertInitialItems() {

        hashT.add(new Employee(13, 24, "T.Lee"));
        hashT.add(new Employee(4, 14, "J.Jones"));
        hashT.add(new Employee(14, 41, "M.Nally"));
        hashT.add(new Employee(1, 45, "J.Smith"));
        hashT.add(new Employee(19, 56, "T.Long"));
        hashT.add(new Employee(20, 34, "M.Lar"));
        hashT.add(new Employee(2, 12, "T.Black"));
        hashT.add(new Employee(15, 43, "J.Orr"));
        hashT.add(new Employee(16, 78, "T.Tim"));
        hashT.add(new Employee(18, 54, "M.Moon"));
        hashT.add(new Employee(17, 67, "Q.Tri"));
        hashT.add(new Employee(21, 21, "M.Gee"));
        hashT.add(new Employee(22, 78, "T.Sol"));
        hashT.add(new Employee(23, 36, "M.Mars"));
        hashT.add(new Employee(3, 89, "M.Gray"));
        hashT.add(new Employee(5, 15, "T.Smith"));
        hashT.add(new Employee(6, 62, "M.Mack"));
        hashT.add(new Employee(7, 23, "M.Blank"));
        hashT.add(new Employee(10, 58, "T.Tone"));
        hashT.add(new Employee(11, 78, "M.Mart"));
        hashT.add(new Employee(12, 65, "J.Shine"));
        hashT.add(new Employee(8, 63, "J.James"));
        hashT.add(new Employee(9, 91, "J.Faker"));
        hashT.add(new Employee(24, 12, "S.Jane"));
        hashT.add(new Employee(25, 7, "W.Kobe"));

    }

}
