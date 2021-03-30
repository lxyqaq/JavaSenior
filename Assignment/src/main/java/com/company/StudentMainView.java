package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.company.Bean.Laboratory;
import com.company.Bean.Reserve;
import com.company.Bean.Student;
import com.company.Dao.LaboratoryDao;
import com.company.Dao.ReserveDao;
import com.company.impl.LaboratoryImpl;
import com.company.impl.ReserveImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StudentMainView extends JFrame {

	private JPanel contentPane;
	private JTextField keyword;
	private JTextField labname;
	private JTextField stuname;
	private JTable table;
	DefaultTableModel dm;
	private LaboratoryDao laboratoryDao = new LaboratoryImpl();
	private ReserveDao reserveDao = new ReserveImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student student = new Student();
					StudentMainView frame = new StudentMainView(student);
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
	public StudentMainView(Student student) {
		setTitle("学生主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("实验室查询");
		lblNewLabel.setFont(new Font("方正舒体", Font.PLAIN, 20));
		lblNewLabel.setBounds(200, 10, 117, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("实验室名称：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(112, 63, 72, 15);
		contentPane.add(lblNewLabel_1);
		
		keyword = new JTextField();
		keyword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		keyword.setBounds(203, 60, 107, 21);
		contentPane.add(keyword);
		keyword.setColumns(10);
		
		//查询按钮绑定事件
		JButton searchbtn = new JButton("搜索");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = keyword.getText();
				fillTable(text);
			}
		});
		searchbtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchbtn.setBounds(320, 59, 72, 23);
		contentPane.add(searchbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 516, 95);
		contentPane.add(scrollPane);
		
		//初始化表格数据
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setBackground(Color.pink);
		//绑定表格行事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow(); 
				// 获取点击行的实验室名称内容 
				String value = (String) dm.getValueAt(row, 1);      
				labname.setText(value);
			}
		});
//		table=new JTable(new DefaultTableModel(new String[] {
//				"ID","实验室名称","空闲时间","功能","状态"
//		},0){
//			public boolean isCellEditable(int row, int column) {
//			    return false;
//			   }   
//		});
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","实验室名称","空闲时间","功能","状态"}){
			public boolean isCellEditable(int row, int column) {
				return false;
			}   
		});
		//显示table
		fillTable(null);
		//修改表格样式
		//设置表格大小改变自动变化模型
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		//设置表格中网格线颜色
//		table.setGridColor(Color.black);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		//设置字体颜色粉红
		r.setBackground(Color.pink);
		//设置单元格数据居中显示   
	    r.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, r); 
//	    table.setDefaultEditor(Object.class,new MyEditor());
	    
	    //支持滚动
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("实验室名称：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(52, 211, 81, 15);
		contentPane.add(lblNewLabel_2);
		
		labname = new JTextField();
		labname.setEditable(false);
		labname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		labname.setBounds(143, 208, 107, 21);
		contentPane.add(labname);
		labname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("学生姓名：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(288, 211, 65, 15);
		contentPane.add(lblNewLabel_3);
		
		stuname = new JTextField();
		stuname.setText(student.getStudentName());
		stuname.setEditable(false);
		stuname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		stuname.setBounds(365, 208, 109, 21);
		contentPane.add(stuname);
		stuname.setColumns(10);
		
		//预约实验室按钮绑定事件
		JButton appoint = new JButton("预约");
		appoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (labname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {
                	int row = table.getSelectedRow();
                	// 获取点击行的实验室状态内容 
                	String status = (String) dm.getValueAt(row, 4);      
                    if(!status.equals("空闲")) {
                        JOptionPane.showMessageDialog(null, "该实验室已被占用！");
                    } else {
	                    Reserve reserve=new Reserve(labname.getText().trim(),stuname.getText().trim());
	                    int i =reserveDao.addReserve(reserve);
	                    if (i == 1) {
	                    	fillTable(null);
	                        JOptionPane.showMessageDialog(null, "申请成功！等待审批");
	                    }
                    }
                }
			}
		});
		appoint.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		appoint.setBounds(97, 276, 93, 23);
		contentPane.add(appoint);
		
		JButton btnNewButton_3 = new JButton("返回");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main main = new Main();
				main.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_3.setBounds(347, 276, 93, 23);
		contentPane.add(btnNewButton_3);
	}
	
	
	public void fillTable(String labName){
		dm=(DefaultTableModel) table.getModel();
		dm.setRowCount(0);
		List<Laboratory> laboratories =laboratoryDao.QueryLaboratory(labName);
		for(Laboratory lab:laboratories){
			Vector<Object> v=new Vector<>();
			v.add(lab.getID());
			v.add(lab.getLaboratoryName());
			v.add(lab.getFreeTime());
			v.add(lab.getFunction());
			v.add(lab.getStates());
			dm.addRow(v);
		}
	}
	
}

