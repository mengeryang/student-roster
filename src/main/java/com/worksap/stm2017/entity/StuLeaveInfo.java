package com.worksap.stm2017.entity;

public class StuLeaveInfo {
    private int msgId;
    private String msg;
    private String status;

    public StuLeaveInfo(int msgId, String msg, String status) {
        this.msgId = msgId;
        this.msg = msg;
        this.status = status;
    }

    public StuLeaveInfo() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
