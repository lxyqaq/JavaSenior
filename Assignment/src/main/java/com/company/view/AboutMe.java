package com.company.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class AboutMe extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AboutMe frame = new AboutMe();
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
    public AboutMe() {
        setTitle("About me");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("Author: ");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label.setBounds(87, 106, 75, 27);
        contentPane.add(label);

        JLabel label_1 = new JLabel("Email: ");
        label_1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        label_1.setBounds(87, 176, 75, 27);
        contentPane.add(label_1);

        JLabel lblQq = new JLabel("ID: ");
        lblQq.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lblQq.setBounds(87, 143, 64, 23);
        contentPane.add(lblQq);

        JLabel label_2 = new JLabel("Xiangyu Liu");
        label_2.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        label_2.setBounds(154, 112, 150, 15);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("A00279565");
        label_3.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        label_3.setBounds(154, 148, 150, 15);
        contentPane.add(label_3);

        JLabel label_5 = new JLabel("A00279565@student.ait.ie");
        label_5.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        label_5.setBounds(154, 182, 250, 15);
        contentPane.add(label_5);

        JLabel label_4 = new JLabel("Athlone Institute of Technology");
        label_4.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        label_4.setBounds(87, 56, 350, 27);
        contentPane.add(label_4);
    }

}
