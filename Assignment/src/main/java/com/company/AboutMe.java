package com.company;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

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
        setTitle("关于作者");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("作者:");
        label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        label.setBounds(87, 106, 75, 27);
        contentPane.add(label);

        JLabel label_1 = new JLabel("网站");
        label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        label_1.setBounds(87, 176, 75, 27);
        contentPane.add(label_1);

        JLabel lblQq = new JLabel("QQ：");
        lblQq.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        lblQq.setBounds(87, 143, 64, 23);
        contentPane.add(lblQq);

        JLabel label_2 = new JLabel("lxyqaq");
        label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
        label_2.setBounds(154, 115, 54, 15);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("1344127185");
        label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
        label_3.setBounds(154, 151, 99, 15);
        contentPane.add(label_3);

        JLabel lblNewLabel = new JLabel("<html><a href='http://www.xiaoniucr.com/index.html'>\u5F53\u725B\u4F5C\u7801\uFF0C\u6E90\u4EE3\u7801\u5206\u4EAB</a></html>");
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                URL link;
                try {
                    link = new URL("http://www.xiaoniucr.com/index.html");
                    Desktop.getDesktop().browse(link.toURI());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
        lblNewLabel.setBounds(152, 185, 242, 15);
        contentPane.add(lblNewLabel);

        JLabel label_4 = new JLabel("系统经过在开发，和界面美化，功能完善");
        label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
        label_4.setBounds(87, 56, 282, 27);
        contentPane.add(label_4);
    }

}
