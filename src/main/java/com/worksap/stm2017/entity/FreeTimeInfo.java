package com.worksap.stm2017.entity;

import java.util.List;

public class FreeTimeInfo {
    private String stuId;
    private String weekday;
    private List<String> timeSlots;

    public FreeTimeInfo(String stuId, String weekday, List<String> timeSlots) {
        this.stuId = stuId;
        this.weekday = weekday;
        this.timeSlots = timeSlots;
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

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
