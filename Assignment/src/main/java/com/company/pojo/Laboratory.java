package com.company.pojo;

import java.util.List;

public class Laboratory implements Iterator {

    private int ID;
    private String laboratoryName;
    private String freeTime;
    private String function;
    private String states;

    private List<Laboratory> itemList;
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

    public Laboratory(List<Laboratory> itemList) {
        this.itemList = itemList;
    }

    public Laboratory(String laboratoryName, String freeTime, String function) {
        this.laboratoryName = laboratoryName;
        this.freeTime = freeTime;
        this.function = function;
    }

    public Laboratory(int ID, String laboratoryName, String freeTime, String function, String states) {
        this.ID = ID;
        this.laboratoryName = laboratoryName;
        this.freeTime = freeTime;
        this.function = function;
        this.states = states;
    }

    public Laboratory() {

    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
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

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
