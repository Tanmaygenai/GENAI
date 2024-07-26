package com.exavalu.agentportal.model;

public class FireLine {


    public String getDba() {
        return dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public PrimaryBusinessAddress getPrimaryBusinessAddress() {
        return primaryBusinessAddress;
    }

    public void setPrimaryBusinessAddress(PrimaryBusinessAddress primaryBusinessAddress) {
        this.primaryBusinessAddress = primaryBusinessAddress;
    }
 

    @Override
    public String toString() {
        return "FireLine{" +
                "InsuredName='" + insuredName + '\'' +
                ", dba='" + dba + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", primaryBusinessAddress=" + primaryBusinessAddress +
                '}';
    }
    private String dba;
private String effectiveDate;

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    private  String insuredName;
private PrimaryBusinessAddress primaryBusinessAddress;

}
