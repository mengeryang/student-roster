package com.worksap.stm2017.entity;

public class DetailInfo {
    private String dptId;
    private String dptName;
    private String stuId;
    private String stuName;
    private String date;
    private String times;
    private String weekday;

    public DetailInfo(String dptId, String dptName, String stuId, String stuName, String date, String times, String weekday) {
        this.dptId = dptId;
        this.dptName = dptName;
        this.stuId = stuId;
        this.stuName = stuName;
        this.date = date;
        this.times = times;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
