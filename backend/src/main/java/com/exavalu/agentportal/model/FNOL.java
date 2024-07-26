package com.exavalu.agentportal.model;

public class FNOL {
    private String policyNumber;
    private String insuredName;
    private String insuredStreetAddress;
    private String insuredCity;
    private String insuredState;
    private String insuredZip;
    private String contactName;
    private String primaryPhone;
    private String secondaryPhone;
    private String reportedTime;
    private String lossLocationStreetAddress;
    private String lossLocationCity;
    private String lossLocationState;
    private String lossLocationZip;
    private String lossDesc;
    private String reportedBy;
    private String reportedDt;
    private String reporterPhone;
    private String reporterEmail;
    private String relationshipToInsured;
    private String claimantTypeCode;
    private String vinNumber;
    private String driverFirstName;
    private String driverMiddleName;
    private String driverLastName;
    private String lossCauseCd;
    private String subLossCauseCd;
    private String reportedTo;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredCity() {
        return insuredCity;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return insuredState;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return insuredZip;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getLossLocationStreetAddress() {
        return lossLocationStreetAddress;
    }

    public void setLossLocationStreetAddress(String lossLocationStreetAddress) {
        this.lossLocationStreetAddress = lossLocationStreetAddress;
    }

    public String getLossLocationCity() {
        return lossLocationCity;
    }

    public void setLossLocationCity(String lossLocationCity) {
        this.lossLocationCity = lossLocationCity;
    }

    public String getLossLocationState() {
        return lossLocationState;
    }

    public void setLossLocationState(String lossLocationState) {
        this.lossLocationState = lossLocationState;
    }

    public String getLossLocationZip() {
        return lossLocationZip;
    }

    public void setLossLocationZip(String lossLocationZip) {
        this.lossLocationZip = lossLocationZip;
    }

    public String getLossDesc() {
        return lossDesc;
    }

    public void setLossDesc(String lossDesc) {
        this.lossDesc = lossDesc;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getReportedDt() {
        return reportedDt;
    }

    public void setReportedDt(String reportedDt) {
        this.reportedDt = reportedDt;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }


    public String getInsuredStreetAddress() {
        return insuredStreetAddress;
    }

    public void setInsuredStreetAddress(String insuredStreetAddress) {
        this.insuredStreetAddress = insuredStreetAddress;
    }

    public String getRelationshipToInsured() {
        return relationshipToInsured;
    }

    public void setRelationshipToInsured(String relationshipToInsured) {
        this.relationshipToInsured = relationshipToInsured;
    }
    public String getClaimantTypeCode() {
        return claimantTypeCode;
    }

    public void setClaimantTypeCode(String claimantTypeCode) {
        this.claimantTypeCode = claimantTypeCode;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverMiddleName() {
        return driverMiddleName;
    }

    public void setDriverMiddleName(String driverMiddleName) {
        this.driverMiddleName = driverMiddleName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getLossCauseCd() {
        return lossCauseCd;
    }

    public void setLossCauseCd(String lossCauseCd) {
        this.lossCauseCd = lossCauseCd;
    }

    public String getSubLossCauseCd() {
        return subLossCauseCd;
    }

    public void setSubLossCauseCd(String subLossCauseCd) {
        this.subLossCauseCd = subLossCauseCd;
    }

    public String getReportedTo() {
        return reportedTo;
    }

    public void setReportedTo(String reportedTo) {
        this.reportedTo = reportedTo;
    }

    public String getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(String reportedTime) {
        this.reportedTime = reportedTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\'policyNumber\'='" + policyNumber + '\'' +
                ", \'insuredName\'='" + insuredName + '\'' +
                ", \'insuredStreetAddress\'='" + insuredStreetAddress + '\'' +
                ", \'insuredCity\'='" + insuredCity + '\'' +
                ", \'insuredState\'='" + insuredState + '\'' +
                ", \'insuerZip\'='" + insuredZip + '\'' +
                ", \'contactName\'='" + contactName + '\'' +
                ", \'primaryPhone\'='" + primaryPhone + '\'' +
                ", \'secondaryPhone\'='" + secondaryPhone + '\'' +
                ", \'lossLocationStreetAddress\'='" + lossLocationStreetAddress + '\'' +
                ", \'lossLocationCity\'='" + lossLocationCity + '\'' +
                ", \'lossLocationState\'='" + lossLocationState + '\'' +
                ", \'lossLocationZip\'='" + lossLocationZip + '\'' +
                ", \'lossDescription\'='" + lossDesc + '\'' +
                ", \'reportedBy\'='" + reportedBy + '\'' +
                ", \'reportedDate\'='" + reportedDt + '\'' +
                ", \'reporterPhone\'='" + reporterPhone + '\'' +
                ", \'reporterEmail\'='" + reporterEmail + '\'' +
                ", \'relationshipToInsured\'='" + relationshipToInsured + '\'' +
                ", \'claimantTypeCode\'='" + claimantTypeCode + '\'' +
                ", \'vinNumber\'='" + vinNumber + '\'' +
                ", \'driverFirstName\'='" + driverFirstName + '\'' +
                ", \'driverLastName\'='" + driverLastName + '\'' +
                ", \'driverMiddleName\'='" + driverMiddleName + '\'' +
                ", \'lossCauseCd\'='" + lossCauseCd + '\'' +
                ", \'subLossCauseCd\'='" + subLossCauseCd + '\'' +
                ", \'reportedTo\'='" + reportedTo + '\'' +
                ", \'reportedTime\'='" + reportedTime + '\'' +
                
                '}';
    }


}
