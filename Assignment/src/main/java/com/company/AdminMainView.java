package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainView extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminMainView frame = new AdminMainView();
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
    public AdminMainView() {
        setTitle("Administrator Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 555, 385);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        setJMenuBar(menuBar);

        JMenu studentmenu = new JMenu("Student Manage");
        studentmenu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        menuBar.add(studentmenu);

        JMenuItem addstu = new JMenuItem("Add student");
        addstu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        addstu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddStudentView asv = new AddStudentView();
                asv.setVisible(true);
            }
        });
        studentmenu.add(addstu);

        JMenuItem searchstu = new JMenuItem("Search student");
        searchstu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        searchstu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QueryStudentView qsv = new QueryStudentView();
                qsv.setVisible(true);
            }
        });
        studentmenu.add(searchstu);

        JMenu teamenu = new JMenu("Teacher Manage");
        teamenu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        menuBar.add(teamenu);

        JMenuItem addtea = new JMenuItem("Add teacher");
        addtea.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        addtea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddTeacherView atv = new AddTeacherView();
                atv.setVisible(true);
            }
        });
        teamenu.add(addtea);

        JMenuItem searchtea = new JMenuItem("Search teacher");
        searchtea.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        searchtea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QueryTeacherView qtv = new QueryTeacherView();
                qtv.setVisible(true);
            }
        });
        teamenu.add(searchtea);

        JMenu labmenu = new JMenu("Laboratory Manage");
        labmenu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        menuBar.add(labmenu);

        JMenuItem addlab = new JMenuItem("Add laboratory");
        addlab.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        addlab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddLaboratoryView alv = new AddLaboratoryView();
                alv.setVisible(true);
            }
        });
        labmenu.add(addlab);

        JMenuItem searchlab = new JMenuItem("Search laboratory");
        searchlab.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        searchlab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QueryLaboratoryView qlv = new QueryLaboratoryView();
                qlv.setVisible(true);
            }
        });
        searchlab.setHorizontalAlignment(SwingConstants.TRAILING);
        labmenu.add(searchlab);

        JMenu mnNewMenu = new JMenu("About me");
        mnNewMenu.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("About me");
        mntmNewMenuItem.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutMe me = new AboutMe();
                me.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome!");
        lblNewLabel.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(192, 87, 149, 31);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Laboratory Appointment Management System");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        lblNewLabel_1.setBounds(13, 149, 540, 40);
        contentPane.add(lblNewLabel_1);
    }
}
