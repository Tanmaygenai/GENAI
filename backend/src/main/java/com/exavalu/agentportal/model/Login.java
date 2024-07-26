package com.exavalu.agentportal.model;

public class Login {
    String userName;
    String password;
    String statusCd;
    String systemId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }


    @Override
    public String toString() {
        return "Login{" +
                "systemId='" + systemId + '\'' +
                ", statusCd='" + statusCd + '\'' +
                '}';
    }

}
