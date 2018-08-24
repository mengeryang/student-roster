package com.worksap.stm2017.entity;

public class SettingInfo {
    private String setfree;
    private String workload;

    public SettingInfo(String setfree, String workload) {
        this.setfree = setfree;
        this.workload = workload;
    }

    public SettingInfo() {
    }

    public String getSetfree() {
        return setfree;
    }

    public void setSetfree(String setfree) {
        this.setfree = setfree;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }
}
