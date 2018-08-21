package com.worksap.stm2017.entity;

public class LoginInfo {
    String id;
    String password;

    public LoginInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public LoginInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
