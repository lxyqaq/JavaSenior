package q2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class MyFrameQ2Thread implements ActionListener {

    private JFrame f1 = new JFrame();
    private JLabel l = new JLabel();
    private JLabel l2 = new JLabel();

    Container content;
    private JButton b1 = new JButton("Suspend");
    private JButton b2 = new JButton("Resume");

    private Font fnt = new Font("TimesNewRoman", Font.BOLD, 26);

    MyToggle c;

    Thread t;

    public MyFrameQ2Thread() {

        content = f1.getContentPane();
        l.setFont(fnt);
        l.setText("  " + 1);
        l2.setText("  " + 0);
        content.setLayout(new GridLayout(2, 2));
        f1.setSize(192, 150);
        f1.setResizable(false);
        content.add(l);
        content.add(l2);
        content.add(b1);
        b1.addActionListener(this);
        content.add(b2);
        b2.addActionListener(this);
        c = new MyToggle(l, l2);
        f1.setVisible(true);
        t = new Thread(c);
        t.start();


    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == b1) {
            c.mySuspend();
        }
        if (target == b2) {
            c.myResume();
        }
    }


}

//-------------------------------------------------------------

class Toggle {
    protected Font fnt = new Font("TimesNewRoman", Font.BOLD, 16);
    protected JLabel label2;

    public Toggle(JLabel lab2) {
        label2 = lab2;
        label2.setFont(fnt);
    }
}


class MyToggle extends Toggle implements Runnable {
    protected JLabel label1;

    public MyToggle(JLabel lab1, JLabel lab2) {
        super(lab2);
        label1 = lab1;
        label1.setFont(fnt);
    }

    private boolean suspendFlag = false;

    public void mySuspend() {
        suspendFlag = true;
    }

    public synchronized void myResume() {
        suspendFlag = false;
        notify();
    }

    public void run() {
        int counter = 1;
        while (counter < 100) {
            counter++;
            if (counter % 2 == 0)
                label2.setText("   " + counter);
            else
                label1.setText("   " + counter);
            try {
                Thread.sleep(1000);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}

//-------------------------------------------------------

public class Q2Skel {
    public static void main(String[] args) {
        MyFrameQ2Thread c = new MyFrameQ2Thread();
    }
}

