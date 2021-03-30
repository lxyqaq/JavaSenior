package com.company.Bean;

public class Laboratory {

    private int ID;
    private String laboratoryName;
    private String freeTime;
    private String function;
    private String states;

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
