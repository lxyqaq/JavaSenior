package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.company.Bean.Iterator;
import com.company.Bean.Laboratory;
import com.company.Bean.Reserve;
import com.company.Bean.ReserveContainer;
import com.company.Dao.LaboratoryDao;
import com.company.Dao.ReserveDao;
import com.company.impl.LaboratoryImpl;
import com.company.impl.ReserveImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherMainView extends JFrame {

    private JPanel contentPane;
    private JTextField keyword;
    private JTextField labtext;
    private JTextField stutext;
    private JComboBox statusComBox;
    private JTable table;
    DefaultTableModel dm;
    private ReserveDao reserveDao = new ReserveImpl();
    private LaboratoryDao laboratoryDao = new LaboratoryImpl();
    private String ID = null, laboratoryName = null, StudentName = null, States = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeacherMainView frame = new TeacherMainView();
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
    public TeacherMainView() {
        setTitle("教师主界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 552, 383);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("实验室预约审批");
        lblNewLabel.setFont(new Font("方正舒体", Font.PLAIN, 20));
        lblNewLabel.setBounds(191, 10, 140, 29);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("实验室名称：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label.setBounds(99, 66, 72, 15);
        contentPane.add(label);

        keyword = new JTextField();
        keyword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        keyword.setColumns(10);
        keyword.setBounds(203, 63, 107, 21);
        contentPane.add(keyword);

        JButton search = new JButton("搜索");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keyword.getText();
                fillTable(text);
            }
        });
        search.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        search.setBounds(355, 62, 72, 23);
        contentPane.add(search);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 108, 526, 115);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                // 获取点击行的实验室名称内容
                ID = dm.getValueAt(row, 0) + "";
                laboratoryName = (String) dm.getValueAt(row, 1);
                StudentName = (String) dm.getValueAt(row, 2);
                States = (String) dm.getValueAt(row, 3);
                labtext.setText(laboratoryName);
                stutext.setText(StudentName);
            }
        });
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "实验室名称", "申请人", "状态", "结果"}) {
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
        //支持滚动
        scrollPane.setViewportView(table);

        JLabel label_1 = new JLabel("实验室名称：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_1.setBounds(10, 261, 72, 15);
        contentPane.add(label_1);

        labtext = new JTextField();
        labtext.setEditable(false);
        labtext.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        labtext.setColumns(10);
        labtext.setBounds(84, 258, 90, 21);
        contentPane.add(labtext);

        JLabel label_2 = new JLabel("申请人：");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_2.setBounds(191, 261, 48, 15);
        contentPane.add(label_2);

        stutext = new JTextField();
        stutext.setEditable(false);
        stutext.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        stutext.setColumns(10);
        stutext.setBounds(242, 258, 90, 21);
        contentPane.add(stutext);

        JLabel label_3 = new JLabel("审核：");
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        label_3.setBounds(362, 261, 42, 15);
        contentPane.add(label_3);

        statusComBox = new JComboBox();
        statusComBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        statusComBox.setModel(new DefaultComboBoxModel(new String[]{"同意", "拒绝"}));
        statusComBox.setBounds(399, 258, 90, 21);
        contentPane.add(statusComBox);

        JButton audit = new JButton("审批");
        audit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (ID == null) {
                    JOptionPane.showMessageDialog(null, "请选择行！");
                } else {
                    if (States.equals("待审批")) {
                        while (table.getRowCount() > 0) {
                            dm.removeRow(0);
                        }
                        if (statusComBox.getSelectedItem().toString().equals("同意")) {
                            int k = laboratoryDao.UpdateLaboratoryStates(laboratoryName);
                        }
                        int i = reserveDao.UpdateReserve(ID, statusComBox.getSelectedItem().toString());
                        if (i == 1) {
                            JOptionPane.showMessageDialog(null, "审批成功");
                            fillTable(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "这条信息已经审批不能更改！");
                    }
                }
            }
        });
        audit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        audit.setBounds(84, 311, 93, 23);
        contentPane.add(audit);

        JButton back = new JButton("返回");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        });
        back.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        back.setBounds(396, 311, 93, 23);
        contentPane.add(back);
    }


    public void fillTable(String labName) {
        dm = (DefaultTableModel) table.getModel();
        dm.setRowCount(0);
        List<Reserve> reserves = reserveDao.QueryReserve(labName);
        /*for (Reserve reserve : reserves) {
            Vector<Object> v = new Vector<>();
            v.add(reserve.getID());
            v.add(reserve.getLaboratoryName());
            v.add(reserve.getStudentName());
            v.add(reserve.getStates());
            v.add(reserve.getResult());
            dm.addRow(v);
        }*/
        ReserveContainer reserveContainer = new ReserveContainer();
        reserveContainer.list = reserves;
        Iterator iterator = reserveContainer.getIterator();
        while (iterator.hasNext()) {
            Reserve reserve = (Reserve) iterator.next();
            Vector<Object> v = new Vector<>();
            v.add(reserve.getID());
            v.add(reserve.getLaboratoryName());
            v.add(reserve.getStudentName());
            v.add(reserve.getStates());
            v.add(reserve.getResult());
            dm.addRow(v);
        }
    }
}
