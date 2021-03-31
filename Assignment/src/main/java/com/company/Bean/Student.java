package com.company.Bean;

public class Student implements Person {

    public void accept(Visitor v) {
        v.visit(this);
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
}
