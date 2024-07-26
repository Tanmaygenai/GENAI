package com.exavalu.agentportal.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="claimdetails")
public class ClaimDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="policy_number")
    private String policyNumber;
    @Column(name="claim_number")
    private String claimNumber;
    @Column(name="loss_date")
    private Date lossDate;
    @Column(name="short_description")
    private String shortDescription;
    @Column(name="status_code")
    private String statusCode;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="assigned_adjustor")
    private String assignedAdjustor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Date getLossDate() {
        return lossDate;
    }

    public void setLossDate(Date lossDate) {
        this.lossDate = lossDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAssignedAdjustor() {
        return assignedAdjustor;
    }

    public void setAssignedAdjustor(String assignedAdjustor) {
        this.assignedAdjustor = assignedAdjustor;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}
