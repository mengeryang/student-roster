package com.worksap.stm2017.domain;

import java.util.Date;

public class Leave {
    private int id;
    private String askId;
    private String replaceId;
    private String dptId;
    Date date;
    private String timeSlot;
    private int replaceStatus;
    private int adminStatus;
    private String msg;

    public Leave(int id, String askId, String replaceId,
                 String dptId, Date date, String timeSlot,
                 int replaceStatus, int adminStatus, String msg) {
        this.id = id;
        this.askId = askId;
        this.replaceId = replaceId;
        this.dptId = dptId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.replaceStatus = replaceStatus;
        this.adminStatus = adminStatus;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAskId() {
        return askId;
    }

    public void setAskId(String askId) {
        this.askId = askId;
    }

    public String getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(String replaceId) {
        this.replaceId = replaceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getReplaceStatus() {
        return replaceStatus;
    }

    public void setReplaceStatus(int replaceStatus) {
        this.replaceStatus = replaceStatus;
    }

    public int getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(int adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }
}
