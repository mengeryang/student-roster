package com.worksap.stm2017.domain;

public class StuDptRel {
    private String stuId;
    private String dptId;

    public StuDptRel(String stuId, String dptId) {
        this.stuId = stuId;
        this.dptId = dptId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }
}
