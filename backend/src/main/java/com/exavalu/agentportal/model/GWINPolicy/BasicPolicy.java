
package com.exavalu.agentportal.model.GWINPolicy;

public class BasicPolicy {
    private String expirationDt;


    private String renewalTermCd;

    private String subTypeCd;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    private String policyNumber;
    private String effectiveDt;

    public String getEffectiveDt() {
        return effectiveDt;
    }

    public void setEffectiveDt(String effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public String getExpirationDt() {
        return expirationDt;
    }

    public void setExpirationDt(String expirationDt) {
        this.expirationDt = expirationDt;
    }

    public String getRenewalTermCd() {
        return renewalTermCd;
    }

    public void setRenewalTermCd(String renewalTermCd) {
        this.renewalTermCd = renewalTermCd;
    }

    public String getSubTypeCd() {
        return subTypeCd;
    }

    public void setSubTypeCd(String subTypeCd) {
        this.subTypeCd = subTypeCd;
    }

}
