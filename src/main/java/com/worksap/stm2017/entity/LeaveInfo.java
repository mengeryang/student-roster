package com.worksap.stm2017.entity;

public class LeaveInfo {
    private int msgId;
    private String msg;

    public LeaveInfo(int msgId, String msg) {
        this.msgId = msgId;
        this.msg = msg;
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
}
