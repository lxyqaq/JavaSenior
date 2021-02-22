
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class MyFrame1 extends JFrame implements ActionListener {
    private JLabel l0 = new JLabel("Search Employee App");


    private JButton b1 = new JButton("");
    private JButton b2 = new JButton("Hash Tab");
    private JButton b3 = new JButton("Exit");
    private JPanel p1 = new JPanel();


    //CONSTRUCTOR
    public MyFrame1(String s) {
        super(s);

        Container content = getContentPane();
        content.setLayout(new FlowLayout());
        Font f = new Font("TimesRoman", Font.BOLD, 20);
        p1.setLayout(new GridLayout(2, 1));
        l0.setFont(f);
        content.add(l0);

        p1.add(b2);
        p1.add(b3);
        content.add(p1);

        b2.addActionListener(this);
        b3.addActionListener(this);
        //Horiz , Vert
        setSize(250, 200);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();


        if (target == b2) {
            new MyFrame3("H Table");
        }
        if (target == b3) {
            System.exit(0);
        }


    }
}

