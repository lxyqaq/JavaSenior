package com.company.Bean;

import com.company.Main;

public class User implements Person {

    public boolean accept(Visitor v) {
        return v.visit(this);
    }

     private int ID;
     private String account;
     private String password;

    @Override
    public boolean getType() {
        return Main.comboBox.getSelectedItem().equals("Admin");
    }

    public User(String account, String password) {

        this.account = account;
        this.password = password;
    }
    public User() {
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
}
