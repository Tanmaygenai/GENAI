package com.exavalu.agentportal.model;

public class EligibilityCheck {
    private String lossDate;
    private String product;
    private String classCode;
    private String state;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getLossDate() {
        return lossDate;
    }

    public void setLossDate(String lossDate) {
        this.lossDate = lossDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EligibilityCheck{" +
                "lossDate='" + lossDate + '\'' +
                ", product='" + product + '\'' +
                ", classCode='" + classCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }


}
