
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*; //This is new

class MyFrame3 extends JFrame implements ActionListener {
    private JLabel l0 = new JLabel("Person");
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
    private JPanel p1 = new JPanel();
    private HashTable tree = new HashTable();//This is new
    private int current = 0; //This is new

    //CONSTRUCTOR
    public MyFrame3(String s) {
        super(s);
        //tree.insert(1,"J.Smith",22);
        //tree.insert(2,"T.Black",24);
        //tree.insert(3,"M.Gray",24);
        InsertInitialItems();
        Container content = getContentPane();
        content.setLayout(new FlowLayout());
        Font f = new Font("TimesRoman", Font.BOLD, 20);
        p1.setLayout(new GridLayout(4, 2));
        l0.setFont(f);
        l1.setFont(f);
        content.add(l0);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(b1);
        p1.add(b2);
        content.add(p1);
        content.add(b4);
        content.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        //Horiz , Vert
        setSize(250, 220);
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
        tree.insert(7, "J.Lacy", 42);
        tree.insert(10, "T.Tone", 74);
        tree.insert(11, "M.Mart", 27);
        tree.insert(12, "J.Shine", 32);

    }
}

