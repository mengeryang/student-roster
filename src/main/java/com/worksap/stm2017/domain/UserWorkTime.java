package com.worksap.stm2017.domain;

import java.util.Date;

public class UserWorkTime {
    private String userId;
    private String dptId;
    private String weekday;
    private int start_h;
    private int start_m;
    private int end_h;
    private int end_m;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getStart_h() {
        return start_h;
    }

    public void setStart_h(int start_h) {
        this.start_h = start_h;
    }

    public int getStart_m() {
        return start_m;
    }

    public void setStart_m(int start_m) {
        this.start_m = start_m;
    }

    public int getEnd_h() {
        return end_h;
    }

    public void setEnd_h(int end_h) {
        this.end_h = end_h;
    }

    public int getEnd_m() {
        return end_m;
    }

    public void setEnd_m(int end_m) {
        this.end_m = end_m;
    }

    public UserWorkTime(String userId, String dptId, String weekday, int start_h, int start_m, int end_h, int end_m) {
        this.userId = userId;
        this.dptId = dptId;
        this.weekday = weekday;
        this.start_h = start_h;
        this.start_m = start_m;
        this.end_h = end_h;
        this.end_m = end_m;
    }
}
