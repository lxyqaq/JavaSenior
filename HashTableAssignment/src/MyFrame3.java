
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

public class MyFrame3 extends JFrame implements ActionListener {
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
    private HashTable tree = new HashTable();
    private int current = 0;

    //CONSTRUCTOR
    public MyFrame3(String s) {
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
        setSize(580, 370);
        setVisible(true);
    }

    public HashTable readTable() {
        return tree;
    }

    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();
        if (target == b1) {
            int id = Integer.parseInt(t1.getText());
            String nm = t2.getText();
            int ag = Integer.parseInt(t3.getText());
            tree.insert(id, nm, ag);
        }

        if (target == b2) {
            int id = Integer.parseInt(t1.getText());
            HNode temp = tree.search(id);
            if (temp == null) {
                t2.setText("Unknown");
                t3.setText("");
            } else {
                t1.setText("" + temp.readKey());
                t2.setText("" + temp.readName());
                t3.setText("" + temp.readAge());
            }
        }
        if (target == b3) {
            this.setVisible(false);
        }
        if (target == b4) {
            new HashDisplay(this);
        }

    }

    public void InsertInitialItems() {
        tree.insert(13, "T.Lee", 64);
        tree.insert(4, "J.Jones", 22);
        tree.insert(14, "M.Nally", 24);
        tree.insert(1, "J.Smith", 22);
        tree.insert(19, "T.Long", 68);
        tree.insert(20, "M.Lar", 22);
        tree.insert(2, "T.Black", 24);
        tree.insert(15, "J.Orr", 42);
        tree.insert(16, "T.Tim", 26);
        tree.insert(18, "M.Moon", 77);
        tree.insert(17, "Q.Tri", 36);

        tree.insert(21, "M.Gee", 28);
        tree.insert(22, "T.Sol", 49);
        tree.insert(23, "M.Mars", 57);


        tree.insert(3, "M.Gray", 24);
        tree.insert(5, "T.Smith", 29);
        tree.insert(6, "M.Mack", 54);
        tree.insert(7, "M.Mack", 42);
        tree.insert(10, "T.Tone", 74);
        tree.insert(11, "M.Mart", 27);
        tree.insert(12, "J.Shine", 32);

    }
}

