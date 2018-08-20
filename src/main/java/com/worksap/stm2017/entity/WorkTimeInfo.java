package com.worksap.stm2017.entity;

public class WorkTimeInfo {
    private  String stuId;
    private String dptId;
    private String weekday;
    private String interval;

    public WorkTimeInfo(String stuId, String dptId, String weekday, String interval) {
        this.stuId = stuId;
        this.dptId = dptId;
        this.weekday = weekday;
        this.interval = interval;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public WorkTimeInfo() {
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
