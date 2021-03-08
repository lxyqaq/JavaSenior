package Solutions.Q2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class MyFrameQ2Thread implements ActionListener {
    private int counter;
    private JFrame f1 = new JFrame();
    private JLabel l = new JLabel();

    Container content;
    private JButton b1 = new JButton("Suspend");
    private JButton b2 = new JButton("Resume");

    private Font fnt = new Font("TimesNewRoman", Font.BOLD, 16);

    Thread t;                               // Solution

    MyCounter c;


    public MyFrameQ2Thread() {
        counter = 0;
        content = f1.getContentPane();
        l.setFont(fnt);
        l.setText("Counter:  " + 0);
        f1.setLayout(new FlowLayout());
        f1.setSize(192, 150);
        f1.setResizable(false);
        content.add(l);
        content.add(b1);
        b1.addActionListener(this);
        content.add(b2);
        b2.addActionListener(this);

        f1.setVisible(true);
        c = new MyCounter(l);
        t = new Thread(c);                            // solution
        t.start();                                    // solution


    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == b1) {
            c.mySuspend();
        }//solution2   note c. not t.
        if (target == b2) {
            c.myResume();
        } //solution2
    }


}

//-------------------------------------------------------------

class Counter {
    protected Font fnt = new Font("TimesNewRoman", Font.BOLD, 16);
    protected JLabel label;

    public Counter(JLabel lab) {
        label = lab;
    }
}


class MyCounter extends Counter implements Runnable {

    public MyCounter(JLabel lab) {
        super(lab);
        label = lab;
    }


    private boolean suspendFlag = false;      // Solution 2

    public void mySuspend() {               // Solution 2
        suspendFlag = true;
    }

    public synchronized void myResume() {   // Solution 2
        suspendFlag = false;
        notify();
    }

    public void run() {
        int counter = 0;
        while (counter < 100) {
            counter++;
            label.setFont(fnt);
            label.setText("MyCounter:    " + counter);
            try {
                Thread.sleep(1000);

                synchronized (this) {      // Solution 2
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

//-------------------------------------------------------

//-------------------------------------------------------

public class Q2Sol2ThreadSafe {
    public static void main(String[] args) {
        MyFrameQ2Thread c = new MyFrameQ2Thread();
    }
}

