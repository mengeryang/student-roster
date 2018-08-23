package com.worksap.stm2017.entity;

public class LeaveForm {
    private String askId;
    private String replaceId;
    private String dptId;
    private String rawDate;
    private String timeSlot;
    private String comment;

    public LeaveForm() {
    }

    public LeaveForm(String askId, String replaceId, String dptId, String rawDate, String timeSlot, String comment) {
        this.askId = askId;
        this.replaceId = replaceId;
        this.dptId = dptId;
        this.rawDate = rawDate;
        this.timeSlot = timeSlot;
        this.comment = comment;
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

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    public String getRawDate() {
        return rawDate;
    }

    public void setRawDate(String rawDate) {
        this.rawDate = rawDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
