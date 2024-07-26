
package com.exavalu.agentportal.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LossNoticeList {
	private List<Object[]> lossNoticeLists = new ArrayList<Object[]>();
	public List<Object[]> getLossNoticeList() {
		return lossNoticeLists;
	}
	public void setLossNoticeList(List<Object[]> lossNoticeList) {
		this.lossNoticeLists = lossNoticeList;
	}
    private int id;
    private String reportedDate;
    private String lossNoticeNumber;
    private String lossnoticeData;
    private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getLossNoticeNumber() {
		return lossNoticeNumber;
	}
	public void setLossNoticeNumber(String lossNoticeNumber) {
		this.lossNoticeNumber = lossNoticeNumber;
	}
	public String getLossnoticeData() {
		return lossnoticeData;
	}
	public void setLossnoticeData(String lossnoticeData) {
		this.lossnoticeData = lossnoticeData;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    
    
    

}
