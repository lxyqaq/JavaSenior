package com.company.pojo;

import java.util.List;

public class Reserve implements Iterator {

    private int ID;
    private String laboratoryName;
    private String StudentName;
    private String States;
    private String result;

    private List<Reserve> itemList;
    private int position;

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

    public Reserve(List<Reserve> list) {
        this.itemList = list;
    }

    public Reserve(int ID, String laboratoryName, String studentName, String states, String result) {
        this.ID = ID;
        this.laboratoryName = laboratoryName;
        StudentName = studentName;
        States = states;
        this.result = result;
    }

    public Reserve(String laboratoryName, String studentName) {
        this.laboratoryName = laboratoryName;
        StudentName = studentName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStates() {
        return States;
    }

    public void setStates(String states) {
        States = states;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
