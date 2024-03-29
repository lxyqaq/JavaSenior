package com.company.pojo;

import com.company.view.AdminMainView;
import com.company.dbutils.DB;
import com.company.Main;

import javax.swing.*;

public class Admin implements Person {

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void login() {
        adminLogin();
    }

    private int ID;
    private String account;
    private String password;

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Admin() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void adminLogin() {
        if (Main.comboBox.getSelectedItem().equals("Admin")) {
            String account = Main.txtName.getText().trim();
            String password = Main.txtPwd.getText().trim();
            Admin admin = new Admin(account, password);
            if (DB.Login(admin)) {
                JOptionPane.showMessageDialog(null, "Success!", "", JOptionPane.PLAIN_MESSAGE);
                AdminMainView adminMain = new AdminMainView();
                adminMain.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Fail!", "", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
