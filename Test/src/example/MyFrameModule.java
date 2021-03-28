package example;

import java.awt.*;
import java.awt.event.*;
//import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

class MyFrameModule extends JFrame implements ActionListener {
    private JLabel l0 = new JLabel("  Module  Details  ");
    private JLabel lplayers = new JLabel(" List of Students ");
    private JLabel lblank = new JLabel("                ");
    private JLabel l1 = new JLabel("Name ");
    private JTextField t1 = new JTextField("", 8);
    private JLabel l2 = new JLabel("Lecturer    ");
    private JTextField t2 = new JTextField("0", 13);


    private JLabel l3 = new JLabel("Name");
    private JTextField t3 = new JTextField("", 8);
    private JLabel l4 = new JLabel("Attended ");
    private JTextField t4 = new JTextField("0", 8);
    private JLabel l5 = new JLabel("Absent ");
    private JLabel l8 = new JLabel("  ");
    private JLabel l9 = new JLabel("  ");
    private JButton b1 = new JButton("MarkAbsent");
    private JButton b2 = new JButton("MarkAttended");
    private JButton b3 = new JButton("  << ");
    private JButton b4 = new JButton(" >>  ");
    private JButton b5 = new JButton("AddStudent");
    private JTextField t5 = new JTextField("0", 8);
    private JTextField t6 = new JTextField("", 8);
    //private JButton b3=new JButton("StepGames");private JButton b4=new JButton("Exit");
    private JButton b6 = new JButton("% Attended");
    private JButton b7 = new JButton("% Attended");
    private JTextField t7 = new JTextField("", 8);

    private JButton b8 = new JButton("50% Attendance?");
    private JTextField t8 = new JTextField("", 8);

    String[] list = {"1", "2", "3", "4", "5"};
    private JComboBox cb1 = new JComboBox(list);// instead of t6
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();

    private Module m = new Module("C# Programming", "J.Smith", AddElements());
    private Student s;

    //private ArrayList arr=new ArrayList();
    private static int index = 0;

    JMenuBar mbar = new JMenuBar();

    JMenu options = new JMenu("Options");

    JMenuItem reset = new JMenuItem("Reset");
    JMenuItem exit = new JMenuItem("Exit");

    MoreFunctions mf = new MoreFunctions();

    public MyFrameModule(String s) {
        super(s);
        java.awt.Container content = getContentPane();
        content.setLayout(new FlowLayout());
        Font f = new Font("TimesRoman", Font.BOLD, 16);
        Font f2 = new Font("TimesRoman", Font.BOLD, 30);
        p1.setLayout(new GridLayout(2, 2));
        p2.setLayout(new GridLayout(9, 2));
        p3.setLayout(new GridLayout(3, 2));
        l0.setFont(f2);
        l1.setFont(f);
        l2.setFont(f);
        lplayers.setFont(f2);
        content.add(l0);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        content.add(p1);
        content.add(lplayers);

        p2.setBackground(Color.LIGHT_GRAY);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        p2.add(l3);
        p2.add(t3);
        p2.add(l4);
        p2.add(t4);
        p2.add(l5);
        p2.add(t5);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        p2.add(t6);
        p2.add(l8);
        p2.add(l9);
        p2.add(b5);
        p2.add(t6);
        p2.add(b7);
        p2.add(t7);
        p2.add(b8);
        p2.add(t8);
        content.add(p2);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);

        t1.setText("" + m.readName());
        t2.setText("" + m.readLecturer());
        this.setJMenuBar(mbar);
        mbar.add(options);                           // add Menu to MenuBar
        options.add(reset);                            //  Add MenuItem to Menu
        options.add(exit);
        reset.addActionListener(this);
        exit.addActionListener(this);


        AddElements();

        refresh();
        setSize(400, 500);
        setVisible(true);
    }


    public void refresh() {
        Iterator iter = m.iterator();
        int i = 0;
        while (iter.hasNext()) {
            s = (Student) iter.next();
            if (i == index) break;
            i++;
        }
        mf.visit(s);
        t3.setText("" + s.readName());
        t4.setText("" + s.readAttented());
        t5.setText("" + s.readAbsent());
    }

    public Student[] AddElements() {
        Student[] list = {new Student("Ronaldo"),
                new Student("Messi"),
                new Student("Rashford"),
                new Student("Bale"),
                new Student("Connolly")
        };
        return list;
    }

    public int calculatePerCentAttendance() {
        double total = (double) s.readAbsent() + s.readAttented();
        return (int) ((s.readAttented() / total) * 100);
    }

    public boolean fiftyPerCentAttendance() {
        if (s.readAbsent() > s.readAttented()) return true;
        else return false;
    }

    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();
        if (target == b1) {
            s.markAbsent();
            refresh();
        }
        if (target == b2) {
            s.markAttented();
            refresh();
        }
        if (target == b3) {
            if (index > 0) index--;
            refresh();
        }
        if (target == b4) {
            if (index < m.reaNumberOfStudents() - 1)
                index++;
            refresh();
        }
        if (target == b7) {   // % Attended
            t7.setText("" + mf.calculatePerCentAttendance() + " %");
            // refresh();
        }
        if (target == b5) {   // add
            m.addStudent(t6.getText());
            index = m.reaNumberOfStudents() - 1;
            refresh();
        }
        if (target == b8) {   // 50% attendance
            t8.setText("" + mf.fiftyPerCentAttendance() + " ");
        }
        if (target == reset) {   // reset
            t6.setText("");
            t7.setText("");
            t8.setText("");
        }
        if (target == exit) {   // last
            System.exit(0);
        }

    }

}