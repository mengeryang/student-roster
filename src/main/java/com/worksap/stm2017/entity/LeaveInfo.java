package com.worksap.stm2017.entity;

public class LeaveInfo {
    private int msgId;
    private String msg;
    private String comment;

    public LeaveInfo(int msgId, String msg, String comment) {
        this.msgId = msgId;
        this.msg = msg;
        this.comment = comment;
    }

    public LeaveInfo() {
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
