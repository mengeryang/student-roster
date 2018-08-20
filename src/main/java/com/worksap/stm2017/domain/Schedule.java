package com.worksap.stm2017.domain;

public class Schedule {
    private String stuId;
    private String dptId;
    private String weekday;
    private String intervals;

    public Schedule(String stuId, String dptId, String weekday, String intervals) {
        this.stuId = stuId;
        this.dptId = dptId;
        this.weekday = weekday;
        this.intervals = intervals;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String interval) {
        this.intervals = interval;
    }
}
