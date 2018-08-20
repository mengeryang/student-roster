package com.worksap.stm2017.domain;


public class FreeTime {
    private String stuId;
    private String weekday;
    private String intervals;

    public FreeTime(String stuId, String weekday, String intervals) {
        this.stuId = stuId;
        this.weekday = weekday;
        this.intervals = intervals;
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
