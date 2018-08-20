package com.worksap.stm2017.domain;

import java.util.Date;

public class RosterRecord {
    private String userId;
    private String departmentId;
    private Date date;
    private int sh;
    private int sm;
    private int eh;
    private int em;

    public RosterRecord(String userId, String departmentId, Date date, int sh, int sm, int eh, int em) {
        this.userId = userId;
        this.departmentId = departmentId;
        this.date = date;
        this.sh = sh;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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
