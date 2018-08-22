package com.worksap.stm2017.entity;

import java.util.List;

public class FreeTimeForm {
    private String stuId;
    private String weekday;
    private String timeSlot;

    public FreeTimeForm() {
    }

    public FreeTimeForm(String stuId, String weekday, String timeSlot) {
        this.stuId = stuId;
        this.weekday = weekday;
        this.timeSlot = timeSlot;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
