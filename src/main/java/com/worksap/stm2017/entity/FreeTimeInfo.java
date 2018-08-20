package com.worksap.stm2017.entity;

public class FreeTimeInfo {
    private String stuId;
    private String weekday;
    private String intervals;

    public FreeTimeInfo(String stuId, String weekday, String intervals) {
        this.stuId = stuId;
        this.weekday = weekday;
        this.intervals = intervals;
    }

    public FreeTimeInfo() {
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

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }
}
