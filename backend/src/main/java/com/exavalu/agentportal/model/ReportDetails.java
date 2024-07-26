
package com.exavalu.agentportal.model;

import com.exavalu.agentportal.model.GWINOpenWork.OpenWork;

import java.util.ArrayList;
import java.util.List;

public class ReportDetails {

    public List<Object[]> getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(List<Object[]> reportDetail) {
        this.reportDetail = reportDetail;
    }

    private List<Object[]> reportDetail = new ArrayList<Object[]>();


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
    private String effectiveDate;
    private String classCode;
    private String stateCd;
    private String liabilityLimit;
    private String indicationStatus;
    private String submissionStatus;
    private String quoteNumber;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    public String getLiabilityLimit() {
        return liabilityLimit;
    }

    public void setLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    public String getIndicationStatus() {
        return indicationStatus;
    }

    public void setIndicationStatus(String indicationStatus) {
        this.indicationStatus = indicationStatus;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getAccountingMonth() {
        return accountingMonth;
    }

    public void setAccountingMonth(String accountingMonth) {
        this.accountingMonth = accountingMonth;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getIndicationDate() {
        return indicationDate;
    }

    public void setIndicationDate(String indicationDate) {
        this.indicationDate = indicationDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    private String accountingMonth;
    private String premium;
    private String insuredName;
    private String indicationDate;
    private String product;

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    private String submissionDate;
}
