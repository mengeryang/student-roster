package com.worksap.stm2017.entity;

import com.worksap.stm2017.domain.UserWorkTime;

import java.util.Date;

public class Record {
    private String userId;
    private String username;
    private String dptId;
    private String dptname;
    private String weekday;
    private Date date;
    private int sh;
    private int sm;
    private int eh;
    private int em;

    public Record() {
    }

    public Record(UserWorkTime u) {
        userId = u.getUserId();
        dptId = u.getDptId();
        weekday = u.getWeekday();
        sh = u.getStart_h();
        sm = u.getStart_m();
        eh = u.getEnd_h();
        em = u.getEnd_m();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    public String getDptname() {
        return dptname;
    }

    public void setDptname(String dptname) {
        this.dptname = dptname;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public int getSm() {
        return sm;
    }

    public void setSm(int sm) {
        this.sm = sm;
    }

    public int getEh() {
        return eh;
    }

    public void setEh(int eh) {
        this.eh = eh;
    }

    public int getEm() {
        return em;
    }

    public void setEm(int em) {
        this.em = em;
    }
}
