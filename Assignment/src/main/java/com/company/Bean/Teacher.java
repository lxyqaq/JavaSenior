package com.company.Bean;

import com.company.DB.DB;
import com.company.Main;
import com.company.TeacherMainView;

import javax.swing.*;
import java.util.List;

public class Teacher implements Person, Iterator {

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void login() {
        teacherLogin();
    }

    @Override
    public boolean hasNext() {
        if (position >= itemList.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        return itemList.get(position++);
    }

    private int ID;
    private String TeacherAccount;
    private String password;
    private String TeacherName;
    private String TeacherSex;

    private List<Teacher> itemList;
    private int position;

    public Teacher() {

    }

    public Teacher(List<Teacher> itemList) {
        this.itemList = itemList;
    }

    public Teacher(String teacherAccount, String password, String teacherName, String teacherSex) {
        TeacherAccount = teacherAccount;
        this.password = password;
        TeacherName = teacherName;
        TeacherSex = teacherSex;
    }

    public Teacher(String teacherAccount, String password) {
        TeacherAccount = teacherAccount;
        this.password = password;
    }


    public Teacher(int ID, String teacherAccount, String teacherName, String teacherSex) {
        this.ID = ID;
        TeacherAccount = teacherAccount;
        TeacherName = teacherName;
        TeacherSex = teacherSex;
    }


    public Teacher(int ID, String teacherAccount, String password, String teacherName, String teacherSex) {
        this.ID = ID;
        TeacherAccount = teacherAccount;
        this.password = password;
        TeacherName = teacherName;
        TeacherSex = teacherSex;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTeacherAccount() {
        return TeacherAccount;
    }

    public void setTeacherAccount(String teacherAccount) {
        TeacherAccount = teacherAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getTeacherSex() {
        return TeacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        TeacherSex = teacherSex;
    }

    public void teacherLogin() {
        if (Main.comboBox.getSelectedItem().equals("Teacher")) {
            String account = Main.txtName.getText().trim();
            String password = Main.txtPwd.getText().trim();
            Teacher teacher = new Teacher(account, password);
            if (DB.teacherLogin(teacher)) {
                TeacherMainView teacherMain = new TeacherMainView();
                teacherMain.setVisible(true);
                JOptionPane.showMessageDialog(null, "登录成功", "", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "账号密码错误", "", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
