package com.company.Bean;

public class Teacher implements Visitable {

    public void accept(Visitor v) {
        v.visit(this);
    }

    private int ID;
    private String TeacherAccount;
    private String password;
    private String TeacherName;
    private String TeacherSex;

    public Teacher() {
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
}
