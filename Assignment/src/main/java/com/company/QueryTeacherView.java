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

import com.company.Bean.Student;
import com.company.Bean.Teacher;
import com.company.Dao.TeacherDao;
import com.company.impl.TeacherImpl;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryTeacherView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTable table;
    private JTextField account;
    private JTextField teaname;
    private JComboBox sex;
    private DefaultTableModel dm;
    private TeacherDao teacherDao = new TeacherImpl();
    //选中行的内容
    private String id, teacherAccount, teacherName, teacherSex;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueryTeacherView frame = new QueryTeacherView();
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
    public QueryTeacherView() {
        setTitle("查询教师信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("查询教师信息");
        label.setFont(new Font("方正舒体", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(216, 22, 165, 35);
        contentPane.add(label);

        JLabel label_1 = new JLabel("教师姓名：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setBounds(134, 70, 77, 15);
        contentPane.add(label_1);

        keyword = new JTextField();
        keyword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        keyword.setBounds(216, 67, 155, 21);
        contentPane.add(keyword);
        keyword.setColumns(10);

        JButton search = new JButton("查询");
        search.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keyword.getText();
                fillTable(text);

            }
        });
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
                teacherAccount = (String) dm.getValueAt(row, 1);
                teacherName = (String) dm.getValueAt(row, 3);
                teacherSex = (String) dm.getValueAt(row, 4);
                account.setText(teacherAccount);
                teaname.setText(teacherName);
                sex.setSelectedItem(teacherSex);
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

        JLabel label_2 = new JLabel("教师账号：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(16, 253, 68, 15);
        contentPane.add(label_2);

        account = new JTextField();
        account.setEditable(false);
        account.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        account.setBounds(83, 250, 92, 21);
        contentPane.add(account);
        account.setColumns(10);

        JLabel lblNewLabel = new JLabel("教师姓名：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel.setBounds(209, 254, 74, 15);
        contentPane.add(lblNewLabel);

        teaname = new JTextField();
        teaname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        teaname.setBounds(272, 251, 93, 21);
        contentPane.add(teaname);
        teaname.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("教师性别：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(392, 254, 80, 15);
        contentPane.add(lblNewLabel_1);

        JButton update = new JButton("修改");
        update.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                teacherAccount = account.getText();
                teacherName = teaname.getText();
                teacherSex = sex.getSelectedItem().toString();
                if (teacherAccount == null || "".equals(teacherAccount)) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {

                    Teacher te = new Teacher(Integer.valueOf(id), teacherAccount, teacherName, teacherSex);
                    int i = teacherDao.UpdateTeacher(te);
                    if (i == 1) {
                        fillTable(null);
                        JOptionPane.showMessageDialog(null, "修改成功");
                    }
                }
            }
        });
        update.setBounds(98, 299, 93, 23);
        contentPane.add(update);

        JButton delete = new JButton("删除");
        delete.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (teacherAccount == null || "".equals(teacherAccount)) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {
                    int i = teacherDao.DeleteTeacher(id);
                    if (i == 1) {
                        fillTable(null);
                        JOptionPane.showMessageDialog(null, "删除成功");
                    }
                }
            }
        });
        delete.setBounds(244, 299, 93, 23);
        contentPane.add(delete);

        JButton btnNewButton_3 = new JButton("返回");
        btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_3.setBounds(392, 299, 93, 23);
        contentPane.add(btnNewButton_3);

        sex = new JComboBox();
        sex.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        sex.setModel(new DefaultComboBoxModel(new String[]{"男", "女"}));
        sex.setBounds(460, 251, 82, 21);
        contentPane.add(sex);
    }

    public void fillTable(String teaname) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Teacher> teachers = teacherDao.QueryTeacher(teaname);
        for (Teacher teacher : teachers) {
            Vector<Object> v = new Vector<>();
            v.add(teacher.getID());
            v.add(teacher.getTeacherAccount());
            v.add(teacher.getPassword());
            v.add(teacher.getTeacherName());
            v.add(teacher.getTeacherSex());
            dm.addRow(v);
        }
    }
}
