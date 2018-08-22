package com.worksap.stm2017.entity;

public class StuRegForm {
    private String stuId;
    private String stuName;
    private String dptId;

    public StuRegForm(String stuId, String stuName, String dptId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.dptId = dptId;
    }

    public StuRegForm() {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }
}
