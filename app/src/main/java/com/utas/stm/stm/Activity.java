package com.utas.stm.stm;

/**
 * Created by dave on 15/05/17.
 */

public class Activity {
    private String unitCode;
    private String taskDescription;
    private int weighting;
    private String dueDate;
    private String dueTime;
    private String importance;

    public void SetUnitCode(String unit) {
        unitCode = unit;
    }
    public void SetTaskDescription(String task) {
        taskDescription = task;
    }
    public void SetWeighting(int weight) {
        weighting = weight;
    }
    public void SetDueDate(String due) {
        dueDate = due;
    }
    public void SetDueTime(String due) {
        dueTime = due;
    }

    public String GetUnitCode() {
        return unitCode;
    }
    public String GetTaskDescription() {
        return taskDescription;
    }
    public int GetWeighting() {
        return weighting;
    }
    public String GetDueDate() {
        return dueDate;
    }
    public String GetDueTime() {
        return dueTime;
    }
}
