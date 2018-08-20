package com.worksap.stm2017.entity;

public class StaffInfo {
    private String userId;
    private String username;
    private String dptId;
    private String raw_schedule;

    public StaffInfo(String userId, String username, String dptId, String raw_schedule) {
        this.userId = userId;
        this.username = username;
        this.dptId = dptId;
        this.raw_schedule = raw_schedule;
    }

    public StaffInfo() {}

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

    public String getRaw_schedule() {
        return raw_schedule;
    }

    public void setRaw_schedule(String raw_schedule) {
        this.raw_schedule = raw_schedule;
    }
}
