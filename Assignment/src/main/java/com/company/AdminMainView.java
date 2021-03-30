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
		setTitle("管理员主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 383);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu studentmenu = new JMenu("学生管理");
		studentmenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(studentmenu);
		
		JMenuItem addstu = new JMenuItem("添加学生");
		addstu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		addstu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStudentView asv = new AddStudentView();
				asv.setVisible(true);
			}
		});
		studentmenu.add(addstu);
		
		JMenuItem searchstu = new JMenuItem("查询学生");
		searchstu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchstu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryStudentView qsv = new QueryStudentView();
				qsv.setVisible(true);
			}
		});
		studentmenu.add(searchstu);
		
		JMenu teamenu = new JMenu("教师管理");
		teamenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(teamenu);
		
		JMenuItem addtea = new JMenuItem("添加教师");
		addtea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		addtea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacherView atv = new AddTeacherView();
				atv.setVisible(true);
			}
		});
		teamenu.add(addtea);
		
		JMenuItem searchtea = new JMenuItem("查询教师");
		searchtea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchtea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryTeacherView qtv = new QueryTeacherView();
				qtv.setVisible(true);
			}
		});
		teamenu.add(searchtea);
		
		JMenu labmenu = new JMenu("实验室管理");
		labmenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(labmenu);
		
		JMenuItem addlab = new JMenuItem("添加实验室");
		addlab.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		addlab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLaboratoryView alv = new AddLaboratoryView();
				alv.setVisible(true);
			}
		});
		labmenu.add(addlab);
		
		JMenuItem searchlab = new JMenuItem("查询实验室");
		searchlab.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchlab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QueryLaboratoryView qlv = new QueryLaboratoryView();
				qlv.setVisible(true);
			}
		});
		searchlab.setHorizontalAlignment(SwingConstants.TRAILING);
		labmenu.add(searchlab);
		
		JMenu mnNewMenu = new JMenu("关于作者");
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("关于作者");
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
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
		
		JLabel lblNewLabel = new JLabel("欢迎进入");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(192, 87, 149, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("实验室预约管理系统");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("方正舒体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(125, 149, 307, 40);
		contentPane.add(lblNewLabel_1);
	}
}
