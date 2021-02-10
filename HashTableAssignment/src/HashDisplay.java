
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*; //This is new

class HashDisplay extends JFrame implements ActionListener {
    private JLabel l0 = new JLabel("Display Hash Table");
    private JTextArea tArea = new JTextArea("");
    private JScrollPane scrollPane = new JScrollPane(tArea);
    private HashTable htable;
    private HNode[] head;
    private JButton b1 = new JButton("Close");
    private MyFrame3 parent;

    //CONSTRUCTOR
    public HashDisplay(JFrame p) {
        super("");
        parent = (MyFrame3) p;
        htable = parent.readTable();
        head = htable.readHNode();
        Container content = getContentPane();
        //	content.setLayout(new FlowLayout());
        content.setLayout(null);
        Font f = new Font("TimesRoman", Font.BOLD, 20);
        //p1.setLayout(new GridLayout(3,1));
        l0.setFont(f);
        b1.setFont(f);
        content.add(l0);
        content.add(scrollPane);
        content.add(b1);
        l0.setBounds(5, 5, 250, 80);
        scrollPane.setBounds(5, 80, 620, 220);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        b1.setBounds(200, 310, 100, 40);
        b1.addActionListener(this);
        this.printTable();

        setSize(650, 400);
        setVisible(true);
    }

    public void printTable() {
        //tArea.setLineWrap(true);
        for (int i = 0; i < 10; i++) {
            HNode temp = head[i];

            tArea.append("\n");
            tArea.append("[ " + i + "] -> ");
            while (temp != null) {
                printNode(temp);
                temp = temp.next;
            }
            tArea.append("NULL");
        }
    }

    public void printNode(HNode temp) {
        tArea.append("[" + temp.key + ":" + temp.name + ":" + temp.age + "] -->  ");
    }

    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();
        if (target == b1) {
            this.setVisible(false);
            parent.setVisible(true);
        }


    }
}

