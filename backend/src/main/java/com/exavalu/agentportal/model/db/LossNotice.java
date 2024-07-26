package com.exavalu.agentportal.model.db;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert

@Table(name="lossnotice")
public class LossNotice implements Serializable {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name="reported_date")
    private String reportedDt;
    @Column(name="loss_notice_number")
    private String lossNoticeNumber;
    @Column(name="loss_notice_data_json")
    private String lossNoticeDataJson;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="user_name")
    private String userName;

    public LossNotice() {
/*
 * 
 */
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportedDt() {
        return reportedDt;
    }

    public void setReportedDt(String reportedDt) {
        this.reportedDt = reportedDt;
    }

    public String getLossNoticeNumber() {
        return lossNoticeNumber;
    }

    public void setLossNoticeNumber(String lossNoticeNumber) {
        this.lossNoticeNumber = lossNoticeNumber;
    }

    public String getLossNoticeDataJson() {
        return lossNoticeDataJson;
    }

    public void setLossNoticeDataJson(String lossNoticeDataJson) {
        this.lossNoticeDataJson = lossNoticeDataJson;
    }

}
