package com.worksap.stm2017.entity;

import java.util.List;

public class WorkTimeInfo {
    private  String stuId;
    private String dptId;
    private String weekday;
    private List<String> timeSlots;
    private String rawSlots;

    public WorkTimeInfo(String stuId, String dptId, String weekday, List<String> timeSlots, String rawSlots) {
        this.stuId = stuId;
        this.dptId = dptId;
        this.weekday = weekday;
        this.timeSlots = timeSlots;
        this.rawSlots = rawSlots;
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

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public String getRawSlots() {
        return rawSlots;
    }

    public void setRawSlots(String rawSlots) {
        this.rawSlots = rawSlots;
    }
}
