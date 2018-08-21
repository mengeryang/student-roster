package com.worksap.stm2017.entity;

import java.util.List;

public class DetailInfo {
    private String dptId;
    private String dptName;
    private String stuId;
    private String stuName;
    private String date;
    private List<String> timeSlots;
    private String weekday;

    public DetailInfo(String dptId, String dptName, String stuId, String stuName, String date, List<String> timeSlots, String weekday) {
        this.dptId = dptId;
        this.dptName = dptName;
        this.stuId = stuId;
        this.stuName = stuName;
        this.date = date;
        this.timeSlots = timeSlots;
        this.weekday = weekday;
    }

    public DetailInfo() {
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
