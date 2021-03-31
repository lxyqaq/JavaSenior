package com.company.Bean;

import com.company.DB.DB;
import com.company.Dao.StudentDao;
import com.company.Main;
import com.company.StudentMainView;
import com.company.impl.StudentImpl;

import javax.swing.*;

public class Student implements Person {

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void login() {
        studentLogin();
    }

    private int ID;
    private String studentAccount;
    private String password;
    private String studentName;
    private String studentSex;

    public Student(String studentAccount, String password, String studentName, String studentSex) {
        this.studentAccount = studentAccount;
        this.password = password;
        this.studentName = studentName;
        this.studentSex = studentSex;
    }

    public Student(String studenAccount, String password) {
        this.studentAccount = studenAccount;
        this.password = password;
    }

    public Student(int ID, String studentAccount, String studentName, String studentSex) {
        this.ID = ID;
        this.studentAccount = studentAccount;
        this.studentName = studentName;
        this.studentSex = studentSex;
    }

    public Student(int ID, String studentAccount, String password, String studentName, String studentSex) {
        this.ID = ID;
        this.studentAccount = studentAccount;
        this.password = password;
        this.studentName = studentName;
        this.studentSex = studentSex;
    }

    public Student() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studenAccount) {
        this.studentAccount = studenAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public void studentLogin() {
        if (Main.comboBox.getSelectedItem().equals("Student")) {
            String account = Main.txtName.getText().trim();
            String password = Main.txtPwd.getText().trim();
            Student student = new Student(account, password);
            if (DB.studentLogin(student)) {
                StudentDao studentDao = new StudentImpl();
                student = studentDao.selectStudent(student.getStudentAccount());
                StudentMainView studentMain = new StudentMainView(student);
                studentMain.setVisible(true);
                JOptionPane.showMessageDialog(null, "登录成功", "", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "账号密码错误", "", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

}
