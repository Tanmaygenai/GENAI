package com.exavalu.agentportal.model.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="openwork")
public class OpenWork{
    @Id
    private String applicationNo;
    private int customerNo;
    @Temporal(value= TemporalType.DATE)
    private Date effectiveDate;
    private String state;
    private String insuredName;
    private String transactionType;
    private String productType;
    private int daysInStatus;

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getDaysInStatus() {
        return daysInStatus;
    }

    public void setDaysInStatus(int daysInStatus) {
        this.daysInStatus = daysInStatus;
    }
}
