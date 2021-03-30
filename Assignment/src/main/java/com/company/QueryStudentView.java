package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.company.Bean.Laboratory;
import com.company.Bean.Student;
import com.company.Dao.ReserveDao;
import com.company.Dao.StudentDao;
import com.company.impl.ReserveImpl;
import com.company.impl.StudentImpl;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryStudentView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTable table;
    private JTextField account;
    private JTextField stuname;
    private JComboBox sex;
    private DefaultTableModel dm;
    private StudentDao studentDao = new StudentImpl();
    private ReserveDao reserveDao = new ReserveImpl();
    //选中行的值
    private String id, studentAccount, studentName, studentSex;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueryStudentView frame = new QueryStudentView();
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
    public QueryStudentView() {
        setTitle("查询学生信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("查询学生信息");
        label.setFont(new Font("方正舒体", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(216, 22, 165, 35);
        contentPane.add(label);

        JLabel label_1 = new JLabel("学生姓名：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setBounds(134, 70, 77, 15);
        contentPane.add(label_1);

        keyword = new JTextField();
        keyword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        keyword.setBounds(216, 67, 155, 21);
        contentPane.add(keyword);
        keyword.setColumns(10);

        JButton search = new JButton("查询");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillTable(keyword.getText());
            }
        });
        search.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        search.setBounds(392, 66, 93, 23);
        contentPane.add(search);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 98, 564, 130);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                // 获取点击行的内容
                id = dm.getValueAt(row, 0).toString();
                studentAccount = (String) dm.getValueAt(row, 1);
                studentName = (String) dm.getValueAt(row, 3);
                studentSex = (String) dm.getValueAt(row, 4);
                account.setText(studentAccount);
                stuname.setText(studentName);
                sex.setSelectedItem(studentSex);
            }
        });
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "账号", "密码", "姓名", "性别"}) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        fillTable(null);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        //设置字体颜色粉红
        r.setBackground(Color.pink);
        //设置单元格数据居中显示
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        scrollPane.setViewportView(table);

        JLabel label_2 = new JLabel("学生账号：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(20, 254, 77, 15);
        contentPane.add(label_2);

        account = new JTextField();
        account.setEditable(false);
        account.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        account.setBounds(92, 251, 85, 21);
        contentPane.add(account);
        account.setColumns(10);

        JLabel lblNewLabel = new JLabel("学生姓名：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setBounds(209, 252, 76, 15);
        contentPane.add(lblNewLabel);

        stuname = new JTextField();
        stuname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        stuname.setBounds(280, 251, 85, 21);
        contentPane.add(stuname);
        stuname.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("学生性别：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(398, 253, 82, 15);
        contentPane.add(lblNewLabel_1);

        JButton update = new JButton("修改");
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                studentAccount = account.getText();
                studentName = stuname.getText();
                studentSex = sex.getSelectedItem().toString();

                if (studentAccount == null || "".equals(studentAccount)) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {

                    Student stu = new Student(Integer.valueOf(id), studentAccount, studentName, studentSex);
                    int i = studentDao.UpdateStudent(stu);
                    if (i == 1) {
                        fillTable(null);
                        JOptionPane.showMessageDialog(null, "修改成功");
                    }
                }
            }
        });
        update.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        update.setBounds(98, 299, 93, 23);
        contentPane.add(update);

        JButton delete = new JButton("删除");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (studentAccount == null || "".equals(studentAccount)) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {
                    int i = studentDao.DeleteStudent(id);
                    int k = reserveDao.DeleteReserveByStudent(studentName);
                    fillTable(null);
                    JOptionPane.showMessageDialog(null, "删除成功");
                }
            }
        });
        delete.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        delete.setBounds(244, 299, 93, 23);
        contentPane.add(delete);

        JButton back = new JButton("返回");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        back.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        back.setBounds(392, 299, 93, 23);
        contentPane.add(back);

        sex = new JComboBox();
        sex.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        sex.setModel(new DefaultComboBoxModel(new String[]{"男", "女"}));
        sex.setBounds(467, 251, 82, 21);
        contentPane.add(sex);
    }


    public void fillTable(String stuname) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Student> students = studentDao.QueryStudent(stuname);
        for (Student student : students) {
            Vector<Object> v = new Vector<>();
            v.add(student.getID());
            v.add(student.getStudentAccount());
            v.add(student.getPassword());
            v.add(student.getStudentName());
            v.add(student.getStudentSex());
            dm.addRow(v);
        }
    }
}
